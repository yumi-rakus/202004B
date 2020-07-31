/**
 * 
 */
$(function() {
	$('#cartInButton').on('click', function() {
		setCsrfTokenToAjaxHeader();
		$(this).prop('disabled', true);
		$('form').submit();
	});

	/*
	 * $('#on').on('click', function(){ $('.topping').attr('checked',
	 * true).prop('checked', true).change(); });
	 */

	$('#off').on('click', function() {
		setCsrfTokenToAjaxHeader();
		$('.topping').removeAttr('checked').prop('checked', false).change();
	});

	$('#rice').on('change', function() {
		setCsrfTokenToAjaxHeader();

		var riceId = $(this).val();

		changeRiceInfo(riceId);

	})

});

function changeRiceInfo(riceId) {

	$.ajax({
		type : "POST",
		url : "/ajax/getRiceInfo",
		data : {
			"riceId" : riceId
		},
		dataType : "json"
	}).done(
			function(riceMap) {

				var result = riceMap["result"];

				if (result == "200") {
					var description = riceMap["description"];
					var imagePath = riceMap["imagePath"];

					$('.riceDescription').text(description);
					$('.ricePhoto').children('img').attr('src',
							'/img_rice/' + imagePath);

				} else if (result == "400") {
					alert("通信に失敗しました。");
				}

			}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
		alert("リクエストに失敗" + textStatus + ":\n" + errorThrown)
	})

}

function setCsrfTokenToAjaxHeader() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
}