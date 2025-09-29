/**
 * 통합 홈페이지 공통 Header Script
 */

// 사용자로부터 화면 이동 이벤트가 없을경우
// 자동로그아웃까지의 대기시간 : 분단위
//var iMinute = 1;
//var iSecond = iMinute * 60 ;	//타이머 초기화

//$(function(){
$(document).ready(function(){
	// 자동 로그아웃 팝업 로그인 연장
	/*$('#autoPopupContinueLogin').click(function(e){
		e.preventDefault();
			기존 처리방식 20161028 이전(상세페이지는 파라메타를 가져오지 못했었음)
		var url = getCurrentpage() + getCurrentparameter();
		window.location.href = url;
	 	
		 ajax 빈페이지 호출로 대체 20161028 
		var url = "/enterspt/orgProfile/selectUserSsoCheckAjax.do";
		var params = "";
		$.ajax({
			type:"POST",
			url:url,
			data:params,
			success:function(data){
			}
		});
		iSecond = iMinute * 60 ;	//타이머 초기화
		$('#autoLogoutLayer').popup({
		}).popup('hide');
		$('body').css("overflow","auto");
	});*/
//	console.log(0);
//	console.log($('#loginYn').val());
//	alert(2);
	// 로그인 사용자 자동 로그아웃 Layer Popup
	if ($('#loginYn').val() == 'Y') {
		// 사용자로부터 화면 이동 이벤트가 없을경우
		// 자동로그아웃까지의 대기시간 : 분단위
//		var iMinute = 30;
//		var iMinute = 1.2;

		// 자동로그아웃 처리 몇초전에 경고를 보여줄지 설정하는 부분 : 초단위
//		var noticeSecond = 60;

//		var iSecond = iMinute * 60 ;
//		iSecond = iMinute * 60 ;
//		var timerchecker = null;

		/*initTimer = function() {

			var rMinute = parseInt(iSecond / 60);
			var rSecond = iSecond % 60;

			if(iSecond > 0) {
				// 지정한 시간동안 화면 이동 이벤트가 발생되지 않았을 경우
				if(iSecond < noticeSecond) {
					$('#autoLogoutLayer').popup({
						type: 'overlay',
						opacity: 0.7,
						scrolllock: true,
						blur: false
					}).popup('show');
				}

				// 자동로그아웃 경고레이어에 경고문+남은 시간 보여주는 부분
				$('#timer').text(Lpad(rMinute, 2) + ':' + Lpad(rSecond, 2) + ' 초');
				iSecond--;
				timerchecker = setTimeout("initTimer()", 1000); // 1초 간격으로 체크
			} else {
				clearTimeout(timerchecker);
				document.location.href = $('#autoPopupLayerLogout').attr('href'); // 로그아웃 처리 페이지
			}
		};*/
		
		// 사용자로부터 화면 이동 이벤트가 없을경우 
		// 자동로그아웃까지의 대기시간 : 분단위 
		var iMinute = 30;
		// 자동로그아웃 처리 몇초전에 경고를 보여줄지 설정하는 부분 : 초단위 
		var noticeSecond = 60; 

		var iSecond = iMinute * 60 ; 
		var timerchecker = null; 
//		var openWindowYn;
//		iSecond = 61;

		var chkDate = new Date();
		chkDate.setSeconds(chkDate.getSeconds()+iSecond)
		var cookie_value = "sessionPopup="+chkDate.getTime();
		document.cookie = cookie_value;
//		console.log(1);
		initTimer = function() { 
//			console.log(2);

			var val = document.cookie.match('(^|;) ?sessionPopup=([^;]*)(;|$)');
			val = val? val[2] : null;
			var popupYN = document.cookie.match('(^|;) ?popupYN=([^;]*)(;|$)');
			popupYN = popupYN? popupYN[2] : null;

			var currDate = new Date();
//			console.log(3+"|"+currDate.getTime()+"|"+val+"|"+popupYN);
			if(val !=null && currDate.getTime()>val && (popupYN=='N' || popupYN==null)){
				var expDate = new Date();
				expDate.setSeconds(expDate.getSeconds()+90);
				cookie_value = "popupYN=Y;expires=" + expDate.toUTCString();
				document.cookie = cookie_value;
				sessionPopupWindow = window.open('/enterspt/co/logout.do','sessionPopupWindow','width=500,height=460,top=200,left=650');
//				http://www2.ntis.go.kr:8080
			}
			
			timerchecker = setTimeout("initTimer()", 5000); // 1초 간격으로 체크 
		};

		onload = initTimer;
	};



/*	// 팝업 화면
	$(document).on('click', 'a[data-popup=popup]', function(e){
		e.preventDefault();
		var popupUrl = $(this).attr('href');
		var popupWidth = $(this).attr('data-popup-width');
		var popupHeight = $(this).attr('data-popup-height');
		var popupNm = $(this).attr('data-popup-nm');

		onPopupWindowScrollSize(popupUrl, popupNm, popupWidth, popupHeight);
	});*/



});
