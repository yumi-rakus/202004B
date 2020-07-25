$(function() {
	// 0. security認証
	var token = $("meta[name='_csrf']").attr('content');
	var header = $("meta[name='_csrf_header']").attr('content');
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	
	// 1. お気に入りに登録されているかを判定
	determineIsRegistered();
	
	// 2. お気に入りへの追加
		$('.heart').on('click', function() {
			$(this).css('background-position', '');
			var D = $(this).attr('rel');
			if (D === 'like') {
				$(this).addClass('heartAnimation'); //applying animation class
			} else {
				// 削除機能追加時に下を使う
				// $(this).removeClass('heartAnimation').attr('rel', 'like');
				// $(this).css('background-position', 'left');
			}
			addToFavorite();
		});

	function determineIsRegistered() {
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
					var msg = 'お気に入り追加済み';
					$('.heart').addClass('heartAnimation'); //applying animation class
				}
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert('リクエストに失敗' + textStatus + ':\n' + errorThrown);
			});
	}
	function addToFavorite() {
		$.ajax({
			type: 'POST',
			url: '/ajax/toFavorite',
			data: {
				itemId: $('#itemId').val()
			},
			dataType: 'text'
		})
			.done(function(status) {
				// メッセージ内容の操作
				var msg;
				if (status == '200') {
					msg = 'お気に入りに追加しました！';
				} else if (status == '201') {
					msg = 'お気に入りに追加済みです。';
				} else if (status == '401') {
					msg = 'ログインしてください。';
				} else if (status == '400') {
					msg = '不正なリクエストです。';
				}
				$('.modalInner').text(msg);

				// モーダル操作
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
			})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				alert('リクエストに失敗' + textStatus + ':\n' + errorThrown);
			});
		determineIsRegistered();
	}
});
