$(function() {
	// 初期値設定
	$('#total-price-number').text($('[name=size]:checked').nextAll('.money').text());

	$('#item').change(function() {
		var sizeElem = $('[name=size]:checked');
		// 本体価格
		var itemPricestr = sizeElem.nextAll('.money').text();
		var itemPrice = Number(itemPricestr.replace(/,/, ''));
		// 本体数量
		var itemQuantity = $('#quantity').val();

		// トッピング価格
		var toppingPrice = sizeElem.val() === 'M' ? 200 : 300;
		// トッピング数量
		var toppingQuantity = $('.topping:checked')
			.map(function() {
				return 1;
			})
			.size();

		// 合計金額 = (本体価格 + トッピング価格 * トッピング数量) * 本体数量
		var total = (itemPrice + toppingPrice * toppingQuantity) * itemQuantity;

		// 合計金額の更新
		$('#total-price-number').text(total.toLocaleString());
	});
});
