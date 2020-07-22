/**
 * 
 */
$(function() {

	$('button[name="shipped"]').on(
			'click',
			function() {

				setCsrfTokenToAjaxHeader();

				// 発送済
				var status = 3;
				var orderId = parseInt($(this).siblings('.orderId').val());

				$(this).parent().prev().prev().children('.status').text('発送済')
						.css('color', 'blue').css('font-weight', 'bold');

				changeStatus(status, orderId);
				
				$(this).prop('disabled', true);
				$(this).siblings('button[name="deliveryCompleted"]').prop('disabled', false);
			});

	$('button[name="deliveryCompleted"]').on(
			'click',
			function() {

				setCsrfTokenToAjaxHeader();

				// 配送完了
				var status = 4;
				var orderId = parseInt($(this).siblings('.orderId').val());

				$(this).parent().prev().prev().children('.status').text('配送完了')
						.css('color', 'green').css('font-weight', 'bold');

				changeStatus(status, orderId);
				
				$(this).prop('disabled', true);
				$(this).siblings('button[name="shipped"]').prop('disabled', false);
			});
});

function changeStatus(status, orderId) {

	$.ajax({
		type : "POST",
		url : "/ajax/updateStatus",
		data : {
			"status" : status,
			"orderId" : orderId
		},
		dataType : "json"
	}).done(function(resultMap) {

		var result = resultMap["result"];
		if (result == 400) {
			alert("通信に失敗しました");
		}

	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		alert("リクエストに失敗" + textStatus + ":\n" + errorThrown)
	});

}

function setCsrfTokenToAjaxHeader() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
}