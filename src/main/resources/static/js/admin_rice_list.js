/**
 * 
 */
$(function() {

	// 選択可
	$('button[name="display"]').on(
			'click',
			function() {

				setCsrfTokenToAjaxHeader();

				var riceName = $(this).parent().prev().prev().prev().children()
						.children('.riceName').text();

				if (window.confirm(riceName + 'を選択可能にしますか？')) {

					var deleted = false;
					var riceId = parseInt($(this).siblings('.riceId').val());

					$(this).parent().prev().children().text('選択可').css(
							'font-weight', 'bold').css('color', 'blue');

					changeDeleted(deleted, riceId);

					$(this).prop('disabled', true);
					$(this).parent().next()
							.children('button[name="noDisplay"]').prop(
									'disabled', false);

					return true;
				} else {
					return false;
				}

			});

	// 選択不可
	$('button[name="noDisplay"]').on(
			'click',
			function() {

				setCsrfTokenToAjaxHeader();

				var riceName = $(this).parent().prev().prev().prev().prev()
						.children().children('.riceName').text();

				if (window.confirm(riceName + 'を選択不可能にしますか？')) {

					var deleted = true;
					var riceId = parseInt($(this).siblings('.riceId').val());

					$(this).parent().prev().prev().children().text('選択不可').css(
							'font-weight', 'bold').css('color', 'red');

					changeDeleted(deleted, riceId);

					$(this).prop('disabled', true);
					$(this).parent().prev().children('button[name="display"]')
							.prop('disabled', false);

					return true;
				} else {
					return false;
				}

			});

});

function changeDeleted(deleted, riceId) {

	$.ajax({
		type : "POST",
		url : "/ajax/updateRiceDeleteFlag",
		data : {
			"deleted" : deleted,
			"riceId" : riceId
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