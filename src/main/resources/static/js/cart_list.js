/**
 * 
 */
$(function () {
	
			$('#deleteAll').on('click', function () {
				if (window.confirm('ショッピングカートの中身を全件削除してよろしいですか？')) {
					return true;
				} else {
					return false;
				}
			});
			
			
			$('button[name="delete"]').on('click', function(){
				$(this).prop('disabled', true);
				$('form[name="deleteForm"]').submit();
			});
					
			
			$('.quantity').on('change', function(){
				
				setCsrfTokenToAjaxHeader();
				
				var quantity = $(this).val();
				var itemId = $(this).siblings('.itemId').val();
				var size = $(this).siblings('.price').text().trim();
				
				var price = parseInt($(this).siblings('.itemPrice').text().replace(',', '').replace('円', ''));
		
				var toppingPriceList = $(this).parent().next().next().children().children().children().children('td.text-left').children('.toppingPrice').text().split('円');				
				toppingPriceList.pop();
				
				var toppingPrice = 0;
				for(var topPrice of toppingPriceList){
					toppingPrice = toppingPrice + parseInt(topPrice);
				}
				
				var subTotal = (price + toppingPrice) * quantity;
				$(this).parent().next().next().next().children().children('.subTotal').text(subTotal.toLocaleString());
				
				update(quantity, itemId, size, subTotal);
			});
		});
		
		function update(quantity, itemId, size, subTotal){
			
			$.ajax({
				type: "POST",
				url: "/ajax/totalPrice",
				data: {
					"newQuantity": quantity,
					"itemId": itemId,
					"size": size
				},
				dataType: "json"
			}).done(function(updateMap){
				
				var newTotalPrice = updateMap["totalPrice"];
				var newTax = updateMap["tax"];
			
				$('.tax').text('消費税：' + newTax.toLocaleString() + '円');
				$('.totalPrice').text('ご注文金額合計：' + newTotalPrice.toLocaleString() + '円');
				
			}).fail(function(XMLHttpRequest,textStatus,errorThrown){
		    	alert("リクエストに失敗"+textStatus+":\n"+errorThrown)
		    })
			
		}
		
		function setCsrfTokenToAjaxHeader() {
	        var token = $("meta[name='_csrf']").attr("content");
	        var header = $("meta[name='_csrf_header']").attr("content");
	        $(document).ajaxSend(function(e, xhr, options) {
	            xhr.setRequestHeader(header, token);
	        });
	    }