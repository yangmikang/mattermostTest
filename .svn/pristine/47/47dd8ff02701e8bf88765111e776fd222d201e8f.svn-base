
/**
 * 문자열 앞에 "0" 을 더해주는 함수
 * @param str			문자열
 * @param len			길이
 * @returns				"0" 을 길이 만큼 더한 문자열
 */
Lpad = function(str, len) {
	str = str + "";

	while(str.length < len) {
		str = "0"+str;
	};

	return str;
};

/**
 * 문자열 HTML 제거
 * @returns				공백 제거 문자열
 */
String.prototype.removeHTML = function() {
    return this.replace(new RegExp('<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>' , 'gi'), '');
};

/**
 * 문자열 공백 제거
 * @returns				공백 제거 문자열
 */
String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, '');
};

/**
 * 입력 단어 시작 문자 대문자로 변환
 * @returns				단어 첫 글자 대문자 변환 문자열
 */
String.prototype.capitalize = function() {
	return this.replace(/\b([a-z])/g, function($1){
		return $1.toUpperCase();
	});
};

/**
 * 이미지 Position Swap
 * background-position 을 left 에서 position 만큼 이동
 * @param id			아이디
 * @param position		위치
 */
function swapImgPosition(id, position) {

	$('#'+id).css('background-position', 'left ' + position + 'px');
};

/**
 * 이미지 Position 초기화
 * background-position 을 0 으로 초기화
 * @param id			아이디
 */
function initImgPosition(id) {

	$('#'+id).css('background-position', 'left 0px');
};

/**
 * 아이디 정규표현식 체크
 * @param str			아이디
 * @returns				ture : 성공, false : 오류
 */
function checkIdExp(str) {

	//a-z와 0-9이외의 문자가 있는지 확인
	chk1 = /^[a-zA-Z\d]{6,20}$/i;
	//적어도 한개의 a-z 확인
	chk2 = /[a-z]/i;

    return chk1.test( str ) && chk2.test( str );
};

/**
 * 이메일 형식 체크
 * @param str			이메일
 * @returns				ture : 성공, false : 오류
 */
function checkEmailExp( str ) {
	var r1 = new RegExp('(@.*@)|(\\.\\.)|(@\\.)|(^\\.)');
	var r2 = new RegExp('^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$');
	return (!r1.test(str) && r2.test(str));
};


/**
 * 공통 팝업
 * @param url				경로 URL
 * @param popupName			팝업명
 * @param width				너비
 * @param height			높이
 * @return					팝업창 객체
 */
function popupWindowSize(url, popupName, width, height) {
//	var x = (screen.width - width) / 2;
//	var y = (screen.height - height) / 2;
	var x = window.screenLeft || window.screenX;
	var y = window.screenTop || window.screenY;

//	var left = x+((document.body.offsetWidth-width)/2);
//	var top = y+((document.body.offsetHeight-height)/2);

	var idWin = window.open(url, popupName, 'left=' + x + 'px ,top=' + y + 'px ,width='+ width + ',height=' + height + ', toolbar=no,menubar=no,status=no,scrollbars=no,resizable=no');

	if (idWin != null) {
		idWin.focus();
	}

	return idWin;
};

/**
 * 공통 팝업 사이즈 변경 가능
 * @param url				경로 URL
 * @param popupName			팝업명
 * @param width				너비
 * @param height			높이
 * @return					팝업창 객체
 */
function popupWindowReSize(url, popupName, width, height) {
//	var x = (screen.width - width) / 2;
//	var y = (screen.height - height) / 2;
	var x = window.screenLeft || window.screenX;
	var y = window.screenTop || window.screenY;

//	var left = x+((document.body.offsetWidth-width)/2);
//	var top = y+((document.body.offsetHeight-height)/2);

	var idWin = window.open(url, popupName, 'left=' + x + 'px, top=' + y + 'px, width='+ width + ',height=' + height + ', toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');

	if (idWin != null) {
		idWin.focus();
	}

	return idWin;
};

/**
 * 공통 팝업 사이즈 변경 가능, 스크롤 자동
 * @param url				경로 URL
 * @param popupName			팝업명
 * @param width				너비
 * @param height			높이
 * @return					팝업창 객체
 */
function popupWindowScrollAutoReSize(url, popupName, width, height) {
//	var x = (screen.width - width) / 2;
//	var y = (screen.height - height) / 2;
	var x = window.screenLeft || window.screenX;
	var y = window.screenTop || window.screenY;

//	var left = x+((document.body.offsetWidth-width)/2);
//	var top = y+((document.body.offsetHeight-height)/2);

	var idWin = window.open(url, popupName, 'left=' + x + 'px, top=' + y + 'px, width='+ width + 'px, height=' + height + 'px, toolbar=no,menubar=no,status=no,scrollbars=auto,resizable=yes');

	if (idWin != null) {
		idWin.focus();
	}

	return idWin;
};

/**
 * 공통 팝업 사이즈 스크롤 가능
 * @param url				경로 URL
 * @param popupName			팝업명
 * @param width				너비
 * @param height			높이
 * @return					팝업창 객체
 */
function popupWindowScrollSize(url, popupName, width, height) {
//	var x = (screen.width - width) / 2;
//	var y = (screen.height - height) / 2;
	var x = window.screenLeft || window.screenX;
	var y = window.screenTop || window.screenY;

//	var left = x+((document.body.offsetWidth-width)/2);
//	var top = y+((document.body.offsetHeight-height)/2);

	var idWin = window.open(url, popupName, 'left=' + x + 'px, top=' + y + 'px, width='+ width + ',height=' + height + ', toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes, location=no');

	if (idWin != null) {
		idWin.focus();
	}

	return idWin;
};

/**
 * 공통 팝업 사이즈 스크롤 가능(부모창의 오른쪽에 팝업 hhkim)
 * @param url				경로 URL
 * @param popupName			팝업명
 * @param width				너비
 * @param height			높이
 * @return					팝업창 객체
 */
function popupWindowScrollSizeR(url, popupName, width, height) {

	var winHeight = document.body.clientHeight;	// 현재창의 높이
	var winWidth = document.body.clientWidth;	// 현재창의 너비
	var winX = window.screenLeft || window.screenX;	// 현재창의 x좌표
	var winY = window.screenTop || window.screenY;	// 현재창의 y좌표

	var left = winX + (winWidth - width);
	var top = winY + (winHeight - height)/2;

	var idWin = window.open(url, popupName, 'left=' + left + 'px, top=' + top + 'px, width='+ width + ',height=' + height + ', toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes, location=no');

	if (idWin != null) {
		idWin.focus();
	}

	return idWin;
};

/**
 * 공통 팝업 사이즈 스크롤 가능
 * @param url				경로 URL
 * @param popupName			팝업명
 * @param width				너비
 * @param height			높이
 * @param left				화면 위치 X
 * @param top				화면 위치 Y
 * @return					팝업창 객체
 */
function popupWindowScrollSizePosition(url, popupName, width, height, left, top) {

	var idWin = window.open(url, popupName, 'left=' + left + 'px, top=' + top + 'px, width='+ width + ',height=' + height + ', toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');

	if (idWin != null) {
		idWin.focus();
	}

	return idWin;
};


/**
 * 현재 요청한 URI 페이지
 * @returns
 */
function getCurrentpage() {
	var temp = document.location.href;

	if (temp.lastIndexOf('?') > -1) {
		temp = temp.substring(0, temp.lastIndexOf('?'));
	};

	return temp;
};

/**
 * 현재 요청한 URI 파라메터
 * @returns
 */
function getCurrentparameter() {
	var temp = document.location.href;
	var current = getCurrentpage();

	if (temp.indexOf('?') > -1) {
		temp = temp.substring(temp.lastIndexOf(current)+current.length, temp.length);
	} else {
		temp = '';
	};

	return temp;
};

/**
 * URL Encode
 * @param clearString		변환 문자열
 * @returns {String}		Encode 문자열
 */
function URLEncode (clearString) {

	var output = '';
	var x = 0;

	clearString = clearString.toString();

	var regex = /(^[a-zA-Z0-9_.]*)/;

	while (x < clearString.length) {
		var match = regex.exec(clearString.substr(x));

		if (match != null && match.length > 1 && match[1] != '') {
			output += match[1];
			x += match[1].length;
		} else {
			if (clearString[x] == ' ') {
				output += '+';
			} else {
				var charCode = clearString.charCodeAt(x);
				var hexVal = charCode.toString(16);
				output += '%' + ( hexVal.length < 2 ? '0' : '' ) + hexVal.toUpperCase();
			}

			x++;
		};
	};

	return output;
};

/**
 * 파일 확장자 가져오기
 * @param fileName			파일 명
 * @returns {String}		파일 확장자
 */
function getFileExtension(fileName) {

	var fileExtension = '';

	fileExtension = fileName.substring(fileName.lastIndexOf('.')+1);

	return fileExtension;
};

/**
 * 첨부파일 확장자 및 파일 사이즈(20M) 체크
 */
function checkFileExtentionAndSize(file) {

	var fileName = file.name;

	var fileExtension = getFileExtension(fileName).toLowerCase();

	var totSize = 0;
	$('input[name*=fileSize]').each(function(i){
		totSize += parseInt($(this).val());
	});

	totSize += file.size;

	if (fileExtension == 'jsp' || fileExtension == 'cgi' || fileExtension == 'php' || fileExtension == 'htm' ||
			fileExtension == 'html' || fileExtension == 'js') {
		$('#scriptMsg').html('해당 파일 확장자는 첨부 할 수 없습니다.');
		$('#dialogMessage').dialog('option', 'title', '첨부파일 오류');
		$('#dialogMessage').dialog('open');

		return false;
	} else if (totSize >= 20971520) {
		$('#scriptMsg').html('20M 이상 첨부 할 수 없습니다.');
		$('#dialogMessage').dialog('option', 'title', '첨부파일 오류');
		$('#dialogMessage').dialog('open');

		return false;
	} else {
		return true;
	};
};

/**
 * 숫자 Format
 * @param baseNumber				입력 문자열
 * @param unitDivisors				변환 단위(1073741824, 1048576, 1024, 1)
 * @param unitLabels				변환 단위(GB, MB, KB, bytes)
 * @param singleFractional			단일 단위 여부(true : 소수점 없음, false : 소수점 있음)
 * @returns
 */
function formatUnits (baseNumber, unitDivisors, unitLabels, singleFractional) {
	var i, unit, unitDivisor, unitLabel;

	if (baseNumber === 0) {
		return '0 ' + unitLabels[unitLabels.length - 1];
	};

	if (singleFractional) {
		unit = baseNumber;
		unitLabel = unitLabels.length >= unitDivisors.length ? unitLabels[unitDivisors.length - 1] : '';

		for (i = 0; i < unitDivisors.length; i++) {
			if (baseNumber >= unitDivisors[i]) {
				unit = (baseNumber / unitDivisors[i]).toFixed(0);
				unitLabel = unitLabels.length >= i ? ' ' + unitLabels[i] : '';
				break;
			};
		};

		return unit + unitLabel;
	} else {
		var formattedStrings = [];
		var remainder = baseNumber;

		for (i = 0; i < unitDivisors.length; i++) {
			unitDivisor = unitDivisors[i];
			unitLabel = unitLabels.length > i ? ' ' + unitLabels[i] : '';

			unit = remainder / unitDivisor;
			if (i < unitDivisors.length -1) {
				unit = Math.floor(unit);
			} else {
				unit = unit.toFixed(2);
			};

			if (unit > 0) {
				remainder = remainder % unitDivisor;

				formattedStrings.push(unit + unitLabel);
			};
		};

		return formattedStrings.join(' ');
	};
};

/**
 * File Size Format
 * @param baseNumber
 * @returns
 */
function formatBytes(baseNumber) {
	var sizeUnits = [1073741824, 1048576, 1024, 1], sizeUnitLabels = ['GB', 'MB', 'KB', 'bytes'];
	return formatUnits(baseNumber, sizeUnits, sizeUnitLabels, true);

};


/**
 * Document 에 저장된 Cookie 가져오기
 * @param name				Cookie 저장 명
 * @returns
 */
function getCookie( name ){
	var nameOfCookie = name + '=';
	var x = 0;

	while ( x <= document.cookie.length ) {
		var y = (x+nameOfCookie.length);

		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
			if ( (endOfCookie=document.cookie.indexOf( ';', y )) == -1 ) {
				endOfCookie = document.cookie.length;
			};

			return unescape(document.cookie.substring( y, endOfCookie ));
		};

		x = document.cookie.indexOf(' ', x) + 1;

		if ( x == 0 ) {
			break;
		};
	}

	return '';
};

/**
 * Document 에 Cookie 설정
 * @param name				Cookie 저장 명
 * @param value				Cookie 저장 값
 * @param expiredays		만료 일자(ex: 1 - 하루)
 */
function setCookie( name, value, expiredays ) {

	var todayDate = new Date();

	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + '=' + escape( value ) + '; path=/; expires=' + todayDate.toGMTString() + ';';
};

/**
 * Document 에 Cookie 삭제
 * - Cookie 의 저장 명 만료 일자를 하루 전으로 설정
 * @param cookieName		Cookie 저장 명
 */
function deleteCookie( cookieName ) {

	var expireDate = new Date();

	expireDate.setDate( expireDate.getDate() - 1 );
	document.cookie = cookieName + '=; path=/; expires=' + expireDate.toGMTString() + ';';
};

/**
 * HTML 태그 제거
 * @param input				입력 문자열
 * @returns
 */
function removeTags(input) {
	return input.replace(/<[^>]+>/g, '');
};

/**
 * 입력된 숫자를 자리수 구분 "," 으로 변환
 * @param str				입력 문자열
 * @returns					","로 자릿 수 구분된 문자열
 */
function numberFormat(str){

	var input = String(str);
	var reg = /(\-?\d+)(\d{3})($|\.\d+)/;

	if(reg.test(input)){
		return input.replace(reg, function(str, p1,p2,p3){
				return numberFormat(p1) + ',' + p2 + '' + p3;
			}
		);
	} else {
		return input;
	};
};

/**
 * 북마크 생성
 * @param title
 * @param url
 */
function setBookmark(title, url) {

	if (window.sidebar) {
		// firefox
		window.sidebar.addPanel(title, url, "");
	} else if(window.opera && window.print) {
		// opera
		var elem = document.createElement('a');
		elem.setAttribute('href',url);
		elem.setAttribute('title',title);
		elem.setAttribute('rel','sidebar');
		elem.click();
	} else if(window.chrome){
		//google chrome
		alert("Ctrl+D를 누르시면 즐겨찾기에 추가하실 수 있습니다.");
	} else if(document.all) {
		// ie (ie11에서 동작안함.)
		window.external.AddFavorite(url, title);
	} else if(document.getElementById) {
		// ie11을 위해 추가
		window.external.AddFavorite(url, title);
	}
};


/**
 * 화면 확대 축소 시작 IE 전용
 */
var nowZoom = 100; // 현재비율
var maxZoom = 200; // 최대비율(500으로하면 5배 커진다)
var minZoom = 80; // 최소비율
/**
 * 화면 확대
 */
function zoomIn() {

	if (nowZoom < maxZoom) {
		nowZoom += 10; //25%씩 커진다.
	} else {
		return;
	}

    document.body.style.zoom = nowZoom + "%";
}

/**
 * 화면 축소
 */
function zoomOut() {

	if (nowZoom > minZoom) {
		nowZoom -= 10; //25%씩 작아진다.
	} else {
		return;
	}

	document.body.style.zoom = nowZoom + "%";
}

function popup_openWindowMsg(url, width, height, popupName){

	var urlOpt = "status=yes, resizable=yes, scrollbars=no, toolbar=no, width=" + width + ", height=" + height + ", left=" + (screen.width-width)/ 2 + ", top="+(screen.height-height)/2;
	window.open(url, popupName, urlOpt);
}

/**
 * 문자열 byte 계산하여 리턴
 */
function byteCheck(val){
	var temp_estr = escape(val);
	var s_index   = 0;
	var e_index   = 0;
	var temp_str  = "";
	var cnt       = 0;

	while ((e_index = temp_estr.indexOf("%u", s_index)) >= 0){
		temp_str += temp_estr.substring(s_index, e_index);
		s_index = e_index + 6;
		cnt ++;
	}

	temp_str += temp_estr.substring(s_index);
	temp_str = unescape(temp_str);
	return ((cnt * 2) + temp_str.length) + "";
}


// 검색조건 - 날짜 데이터 초기화
function cal_clear(name){
	$.datepicker._clearDate('#'+name+'');
}

//검색조건 - 날짜 입력 체크(모두 입력 또는 모두 미입력 : true)
function searchDt_check(fromDt,toDt){
	var searchFromDt = document.getElementById(""+fromDt+"").value;
	var searchToDt = document.getElementById(""+toDt+"").value;
	var checkResult = true;
	if(!((searchFromDt == '' && searchToDt == '') || (searchFromDt != '' && searchToDt != ''))){
		checkResult = false;
	} else {
		checkResult = true;
	}

	return checkResult;
}

// 특수문자제거
function removeSpecialChar(s) {

	var limit_char = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\(\=]/gi;	//20150616
//	var limit_char = /[~!\#$@%^&*\=+|:;?"<.>']/;		//이전

	var tmp_str = "";
	if(limit_char.test(s)) {
		for(var i=0; i<s.length; i++) {
			var data = s.charAt(i);
			tmp_str+=data.replace(limit_char,"");
		}
		return tmp_str;
	} else {
		return s;
	}
}

// 한글 포함여부(true:한글포함)
function hasHangul(str) {
	var strParam = str;
	var i;
	for(i=0; i<strParam.length; i++) {
		if(strParam.charCodeAt(i) > 255){
			return true;
		}
	}
	return false;
}

/* 숫자만 입력 */
function InputOnlyNumber(str){
	return str.replace(/[^0-9]/gi, "");
}

/* 사업자번호 자동 하이픈 입력 */
function autoHypenBusinessNo(str){
	str = str.replace(/[^0-9]/gi, '');
	var tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 5){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else{
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 2);
		tmp += '-';
		tmp += str.substr(5, 5);
		return tmp;
	}
	return str;
}


/*
 * 대상 문자가 undefined, null , "null" 일 경우 "" 반환
 */
function nullToStr(str){
	if(str==null || str==undefined || str=="null"){
		str = "";
	}
	return str;
}

/*
 * 대상 문자가 undefined, null , "null" 일 경우 userStr 반환
 */
function nullToStr(str, userStr){
	if(userStr==null) userStr="";
	if(str==null || str==undefined || str=="null"){
		str = userStr;
	}
	return str;
}

/* 키워드 대역어 변환 : '한글:영문,한글:영문' to '한글(영문), 한글(영문)' function
ex) kywd_split_print('.kywd_split'); */
function kywd_split_print(obj_str) {
	$(''+obj_str).each( function(i){
//		console.log("i:",i);
		var str = $(this).text().trim();
		if(str == ''){
			str = $(this).val().trim();
		}
//		console.log("str:",str);
    		var kywd_split_str = "";
    		var kywdArray = str.split(',');
    		for(var i=0; i<kywdArray.length; i++){
    			var kywdDivArray = kywdArray[i].trim().split(':');
    			for(var k=0; k<kywdDivArray.length; k++){
    				if(k==0){	//한글
        				if(i>0){
        					kywd_split_str += ", ";
        				}
    					kywd_split_str += kywdDivArray[k].trim();
    				}else if(k>0){	//영문
    					kywd_split_str += "(" + kywdDivArray[k].trim() + ")";
    				}
    			}
    		}
    		if($(this).text().trim() == ''){
    			$(this).val(kywd_split_str);
    		}else{
    			$(this).text(kywd_split_str);
    		}
   	});
}

/* 키워드 대역어 변환 : '한글:영문,한글:영문' to '한글, 한글' function
ex) kywd_split_print('.kywd_split'); */
function kywd_split_print_user(obj_str) {
	$(''+obj_str).each( function(i){
//		console.log("i:",i);
		var str = $(this).text().trim();
		if(str == ''){
			str = $(this).val().trim();
		}
//		console.log("str:",str);
		var kywd_split_str = "";
		var kywdArray = str.split(',');
		for(var i=0; i<kywdArray.length; i++){
			var kywdDivArray = kywdArray[i].trim().split(':');
			for(var k=0; k<kywdDivArray.length; k++){
				if(k==0){	//한글
					if(i>0){
						kywd_split_str += ", ";
					}
					kywd_split_str += kywdDivArray[k].trim();
				}else if(k>0){	//영문
//					kywd_split_str += "(" + kywdDivArray[k].trim() + ")";
				}
			}
		}
		if($(this).text().trim() == ''){
			$(this).val(kywd_split_str);
		}else{
			$(this).text(kywd_split_str);
		}
	});
}



//입력시 특수문제 제한
//object 는 html 상에 ID로 정의 한 것임
//validityValue : 입력 막기 문자
//fildName : 항목 명
//true : 입력불가항목이 있음,   false : 정상
function isValidity(object, validityValue, fildName){

	validityValue = "<';>";

	var str,msg;
	var len = 0, len_validity=0;
	var temp="";
	var count = 0;

	msg = document.getElementById(object).value;
	str = new String(msg);
	validityValue = new String(validityValue);
	len = str.length;
	len_validity = validityValue.length;

	for(var j = 0 ; j < len_validity ; j++)
	{
		for (var k=0 ; k < len ; k++)
		{
			if (validityValue.charAt(j) == str.charAt(k))
			{
				temp = temp + (temp==''?'':"  ") + str.charAt(k);
			}
		}
	}

	if (temp != "")
	{
		alert(fildName +"에 입력된 " + temp + " 값은 입력 할 수 없습니다.\n\n다른문자로 변경시켜 주세요.");
		//document.getElementById(object).value = "";
		document.getElementById(object).focus();
		return true;   // 존재하면 true 즉 에러
	}

	return false;
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/gi, "");
}

function yyyy(){
	var date = new Date();
	var year = date.getFullYear()+"";
	return year;
}


//20160920 추가
function strNull(str){
	if(str==null || str==undefined || str=="null"){
		str = "";
	}
	return str;
}

//20160928 추가
/*조사 자동 선택 - 짝을 이루는 조사중 아무거나 넣으면 됨  ex) josa("홍길동","를") -> 홍길동은;*/
function Josa(txt, josa)
{
	var code = txt.charCodeAt(txt.length-1) - 44032;
	var cho = 19, jung = 21, jong=28;
	var i1, i2, code1, code2;

	// 원본 문구가 없을때는 빈 문자열 반환
	if (txt.length == 0) return '';

	// 한글이 아닐때
	if (code < 0 || code > 11171) return txt;

	if (code % 28 == 0) return txt + Josa.get(josa, false);
	else return txt + Josa.get(josa, true);
}
Josa.get = function (josa, jong) {
	// jong : true면 받침있음, false면 받침없음

	if (josa == '을' || josa == '를') return (jong?'을':'를');
	if (josa == '이' || josa == '가') return (jong?'이':'가');
	if (josa == '은' || josa == '는') return (jong?'은':'는');
	if (josa == '와' || josa == '과') return (jong?'와':'과');

	// 알 수 없는 조사
	return '**';
}



var toast=function(msg){
	if(strNull(msg)!=""){
		$("<div ><h3 style='margin-left:0px;' >"+msg+"</h3></div>")
		.css({
			position: "fixed",			top: "50%",			left: "50%",			"margin-left": "-21px",			"margin-top": "-21px",
			"line-height": "1.7em",
			"font-size":'1.4em',
			display: "block",
			opacity: 0.7,
			color:'#FFFFFF',
// 			backgroundColor:'#2C3621',
//			backgroundColor:'rgba(0, 15, 25, 1)',
//			backgroundColor:'#E0844F',
			backgroundColor:'black',
			padding: "3px",
			"text-align": "center",
			width: "300px",
// 			width: "370px",
// 			height: "80px",
			left: ($(window).width() - 284)/2,
			top: $(window).height()/2 })
			.appendTo($('body')).delay( 1000 )
			.fadeOut( 3000, function(){
				$(this).remove();
			});
	}
}
// toast("토스트 테스트중");
// toast("토스트 테스트중 <br>키워드설정이 완료 되었습니다.");


function kywdSeparateChr(){
	/* 정보분석 키워드 분리 (해당 태그가 있어야 됨)
	    <input type="hidden" id="kywd" value="<c:out value="${info.kywd}" />" >
	    한글: <span id="kor_kywd" ></span>
	    <br>
	    영문: <span id="eng_kywd" ></span>
	*/

	var kywdSeparateChr = ":;:";
	if($("#kywd").val().split(kywdSeparateChr).length > 0){
        $("#kor_kywd").text( $("#kywd").val().split(kywdSeparateChr)[0]);
        $("#eng_kywd").text( $("#kywd").val().split(kywdSeparateChr)[1]);
    }else{
  	  	$("#kor_kywd").text( $("#kywd").val());
    }
}

function replaceAll(str, target, replacement) {
    return str.split(target).join(replacement);
}
