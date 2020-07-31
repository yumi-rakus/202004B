$(function() {
	// 0. security認証
	var token = $("meta[name='_csrf']").attr('content');
	var header = $("meta[name='_csrf_header']").attr('content');
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	// 1. お気に入りに登録されているかを判定
	determineIsRegistered($('.heart'));

	// 2. お気に入りへの追加と削除
	$('.heart').on('click', function() {
		$(this).css('background-position', '');
		favorite($(this));
	});

	/**
	 * お気に入りに登録されているかの判定を行う
	 * determineIsRegistered
	 */
	function determineIsRegistered(elem) {
		$.ajax({
			type: 'POST',
			url: '/ajax/isRegistered',
			data: {
				itemId: $('#itemId').val()
			},
			dataType: 'text'
		})
			.done(function(status) {
				if (status == '200') {
					elem.addClass('liked').attr('rel', 'unlike'); //applying animation class
				}
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert('リクエストに失敗' + textStatus + ':\n' + errorThrown);
			});
	}

	/**
	 * お気に入りへの追加or削除の判定を行う
	 * @param {*} elem 
	 */
	function favorite(elem) {
		var rel = elem.attr('rel');
		if (rel === 'like') {
			addToFavorite(elem);
		} else if (rel === 'unlike') {
			deleteFromFavorite(elem);
		}
	}

	/**
	 * お気に入りへの追加を行う
	 * addToFavorite
	 * @param {*} elem 
	 */
	function addToFavorite(elem) {
		console.log('add');
		$.ajax({
			type: 'POST',
			url: '/ajax/toFavorite',
			data: {
				itemId: $('#itemId').val()
			},
			dataType: 'text'
		})
			.done(function(status) {
				// ハートのアニメーション
				if (status == '200') {
					elem.addClass('heartAnimation').attr('rel', 'unlike'); //applying animation class
				}
				//// モーダル操作
				// メッセージ内容の操作
				if (status == '200') {
					msg = 'お気に入りに追加しました！';
				} else if (status == '201') {
					msg = '不正なリクエストです。';
				} else if (status == '401') {
					msg = 'ログインしてください。';
				} else if (status == '400') {
					msg = '不正なリクエストです。';
				}
				modal();
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert('リクエストに失敗' + textStatus + ':\n' + errorThrown);
			});
	}

	/**
	* お気に入りからの削除を行う
	* deleteFromFavorite
	* @param {*} elem 
	*/
	function deleteFromFavorite(elem) {
		console.log('delete');
		$.ajax({
			type: 'POST',
			url: '/ajax/deleteFromFavorite',
			data: {
				itemId: $('#itemId').val()
			},
			dataType: 'text'
		})
			.done(function(status) {
				// ハートのアニメーション
				if (status == '200') {
					elem.removeClass('heartAnimation liked').attr('rel', 'like');
				}

				//// モーダル操作
				// メッセージ内容の操作
				if (status == '200') {
					msg = 'お気に入りから削除しました！';
				} else if (status == '201') {
					msg = '201';
				} else if (status == '401') {
					msg = 'ログインしてください。';
				} else if (status == '400') {
					msg = '不正なリクエストです。';
				}
				$('.modalInner').text(msg);
				modal();
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert('リクエストに失敗' + textStatus + ':\n' + errorThrown);
			});
	}

	/**
	 * modal
	 */
	function modal() {
		$('.modalInner').text(msg);
		//.modalについたhrefと同じidを持つ要素を探す
		var modalId = '#toFavorite';
		var modalThis = $('body').find(modalId);
		//bodyの最下にwrapを作る
		$('body').append('<div id="modalWrap" />');
		var wrap = $('#modalWrap');
		wrap.fadeIn('200');
		modalThis.fadeIn('200');
		//モーダルの高さを取ってくる
		function mdlHeight() {
			var wh = $(window).innerHeight();
			var attH = modalThis.find('.modalInner').innerHeight();
			modalThis.css({ height: attH });
		}
		mdlHeight();
		$(window).on('resize', function() {
			mdlHeight();
		});
		function clickAction() {
			modalThis.fadeOut('100');
			wrap.fadeOut('100', function() {
				wrap.remove();
			});
		}
		//wrapクリックされたら
		wrap.on('click', function() {
			clickAction();
			return false;
		});
		//1.5秒後に消える
		setTimeout(clickAction, 1500);
		return false;
	}
});
