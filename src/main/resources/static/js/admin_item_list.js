/**
 * 
 */
$(function() {

	// 表示
	$('button[name="display"]').on(
			'click',
			function() {

				setCsrfTokenToAjaxHeader();

				var itemName = $(this).parent().prev().prev().prev().prev()
						.children().children('.itemName').text();

				if (window.confirm(itemName + 'を一覧に表示させますか？')) {

					var deleted = false;
					var itemId = parseInt($(this).siblings('.itemId').val());

					$(this).parent().prev().children().text('表示').css(
							'font-weight', 'bold').css('color', 'blue');

					changeDeleted(deleted, itemId);

					$(this).prop('disabled', true);
					$(this).parent().next()
							.children('button[name="noDisplay"]').prop(
									'disabled', false);

					return true;
				} else {
					return false;
				}

			});

	// 非表示
	$('button[name="noDisplay"]').on(
			'click',
			function() {

				setCsrfTokenToAjaxHeader();

				var itemName = $(this).parent().prev().prev().prev().prev()
						.prev().children().children('.itemName').text();

				if (window.confirm(itemName + 'を一覧から消去しますか？')) {

					var deleted = true;
					var itemId = parseInt($(this).siblings('.itemId').val());

					$(this).parent().prev().prev().children().text('非表示').css(
							'font-weight', 'bold').css('color', 'red');

					changeDeleted(deleted, itemId);

					$(this).prop('disabled', true);
					$(this).parent().prev().children('button[name="display"]')
							.prop('disabled', false);

					return true;
				} else {
					return false;
				}

			});

});

function changeDeleted(deleted, itemId) {

	$.ajax({
		type : "POST",
		url : "/ajax/updateDeleteFlag",
		data : {
			"deleted" : deleted,
			"itemId" : itemId
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