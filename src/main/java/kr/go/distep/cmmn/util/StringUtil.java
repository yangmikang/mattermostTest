package kr.go.distep.cmmn.util;

/**
 * NAME : StringUtil.java 
 *
 * DESC : 문자열 관련 UTIL
 *
 * @author : SIT
 *
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

//import com.initech.eam.nls.CookieManager;
//import com.initech.eam.smartenforcer.SECode;

/**
 * 문자열 관련 기능 모음
 * 
 */
public class StringUtil {
	
	private static Logger logger = LogManager.getLogger(StringUtil.class);
	
	/**
	 * 태그 변환
	 * @param str	변환할 String
	 * @return
	 */
	public static String toTEXT(String str) {
		if (str == null ) {
			return null;
		}

		String returnStr = str;

		returnStr = returnStr.replaceAll("&gt;", ">");
		returnStr = returnStr.replaceAll("/rndgate/eg/common", "http://www.ntis.go.kr/rndgate/eg/common");
		returnStr = returnStr.replaceAll("/file/save/th", "http://www.ntis.go.kr/file/save/th/");
		returnStr = returnStr.replaceAll("&lt;", "<");
		returnStr = returnStr.replaceAll("&quot;", "\"");
		returnStr = returnStr.replaceAll("&nbsp;", " ");
		returnStr = returnStr.replaceAll("&amp;", "&");
		returnStr = returnStr.replaceAll("&apos;", "'");
		returnStr = returnStr.replaceAll("&rarr;", "→");
		returnStr = returnStr.replaceAll("&uarr;", "↑");
		
		// data check
		if (tagRemoveBrP(returnStr) == "") {
			returnStr = "";
		}
		
		return returnStr;

	}
	
	/**
	 * 데이터 없는 태크 치환해서 체크
	 * @param str	체크할String
	 * @return
	 */
	public static String tagRemoveBrP (String str) {

		String returnStr = str;
		
		returnStr = returnStr.toLowerCase().replaceAll("<br>", "").replaceAll("</br>", "").replaceAll("<p>", "").replaceAll("</p>", "");
		returnStr = returnStr.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "");
		
		char[] returnStrToChr = returnStr.toCharArray();

		returnStr = "";
		
		for (char chr : returnStrToChr) {
			
			if ((int)chr == 10 || (int)chr == 13) { // enter
				continue;
			}
			returnStr += chr; 
		}
		
		return returnStr;
	}
	
	
	/**
	 * \r\n 을 &lt;br&gt;로 변환한다
	 * 
	 * @param comment		문자열
	 * @return				변환된 문자열
	 */
	public static String convertHtmlBr(String comment) {
		
		String newString = "";
		
		if (StringUtil.isNotNull(comment)) {
			Pattern crlf = Pattern.compile("(\r\n|\r|\n|\n\r)");
			Matcher m = crlf.matcher(comment);
			 
			newString = m.replaceAll("<br/>");
		}
		
		return newString;
	}
	
	/**
	 * &amp;lt;&amp;gt; 을 '<', '>' 로 변환한다
	 * 
	 * @param comment		문자열
	 * @return				변환된 문자열
	 */
	public static String convertHtmlLtGt(String comment) {
		
		String newString = "";
		
		if (StringUtil.isNotNull(comment)) {
			Pattern ltgt = Pattern.compile("(&lt;)");
			Matcher m = ltgt.matcher(comment);
			 
			newString = m.replaceAll("<");
			
			ltgt = Pattern.compile("(&gt;)");
			m = ltgt.matcher(newString);
			
			newString = m.replaceAll(">");
			
		}
		
		return newString;
	}
	
	/**
	 * &nbsp; 를 " " 으로 변환한다
	 * 
	 * @param comment		문자열
	 * @return				변환된 문자열
	 */
	public static String convertHtmlNbsp(String comment) {
		
		String newString = "";
		
		if (StringUtil.isNotNull(comment)) {
			Pattern nbsp = Pattern.compile("(&nbsp;)");
			Matcher m = nbsp.matcher(comment);
			
			newString = m.replaceAll(" ");
		}
		
		return newString;
	}
	
	/**
	 * 파일의 확장자를 추출한다. <BR>
	 * 
	 * 예)<BR>
	 * "." 포함 (true) 일 경우
	 * [입력] " <B>abcder.txt </B>" ----> [출력] " <B>.txt </B>"
	 * <BR>
	 * "." 미포함 (false) 일경우
	 * [입력] " <B>abcder.txt </B>" ----> [출력] " <B>txt </B>"
	 * @param fileName		파일 명
	 * @param dot			"." 포함 여부(true : 포함, false : 미포함)
	 * @return				파일 확장자
	 */
	public static String getFileExtension(String fileName, boolean dot) {
		
		String extention = "";

		if (dot) {
			extention = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".")): fileName;
		} else {
			extention = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".") + 1): fileName;
		}
		
		return extention;
	}
	
	/**
	 * 파일 확장자 제외 파일명 가져오기
	 * 예)<BR>
	 * [입력] " <B>abcder.txt </B>" ----> [출력] " <B>abcder</B>"
	 * @param fileName		파일 명
	 * @return				확장자 제외 파일명
	 */
	public static String getFileNameWithoutExtension(String fileName) {
		String fileNameWithoutExtension = "";
		
		fileNameWithoutExtension = fileName.lastIndexOf(".") > 0 ? fileName.substring(0, fileName.lastIndexOf(".")): fileName;
		
		return fileNameWithoutExtension;
	}

	/**
	 * 파일의 크기를 MB, KB 로 반환 한다.
	 * 
	 * @param str			Double 형 파일 크기
	 * @return 				파일 사이즈 변환 문자열
	 */
	public static String getFileSize(double fileSize) {
		String size = "";
		if (1024 < fileSize && fileSize < 1024 * 1024)
			size = Math.round(fileSize / 1024) + " KB";
		else if (1024 * 1024 <= fileSize)
			size = Math.round(fileSize / (1024 * 1024)) + " MB";
		else
			size = fileSize + " Bytes";

		return size;
	}
	
	/**
	 * 입력 문자열이 주어진 정규식 pattern 과 일치 여부 검사<BR>
	 * 
	 * <pre>
	 * StringUtil.isRegexPatternMatch("aaaaab", "a*b") = true;
	 * StringUtil.isRegexPatternMatch("cabbbb", "a*b") = false;
	 * </pre>
	 * 
	 * @param str			문자열
	 * @param pattern		regular expression pattern
	 * @return 정규식 pattern 일치 여부, <code>true</code>
	 */
	public static boolean isRegexPatternMatch(String str, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 문자열의 NULL 값 검사를 한다. <BR>
	 * 문자열이 null 또는 white space인 경우에는 "참"을 반환한다.
	 * 
	 * @param str			문자열
	 * @return boolean		true : 데이터 없음(null or ""-공백), false : 데이터 있음
	 */
	public static boolean isNull(String str) {
		return str == null || "null".equals(str) || "".equals(str);
	}

	/**
	 * 문자열의 NULL 값 검사를 한다. <BR>
	 * 문자열이 null 또는 white space인 경우에는 "거짓"을 반환한다.
	 * 
	 * @param str			문자열
	 * @return boolean		true : 데이터 존재, false : 데이터 없음(null or ""-공백)
	 */
	public static boolean isNotNull(String str) {
		return !(str == null || "".equals(str) || "".equals(str.trim()));
	}

	/**
	 * 문자열이 null인 경우에는 whiteSpace를 반환한다.
	 * 
	 * @param string		문자열
	 * @return
	 */
	public static String nullToEmptyString(String string) {
		return isNull(string) ? "" : string;
	}
	
	public static String nullToEmptyString(String string, String def) {
		return isNull(string) ? def : string;
	}
	
	/**
	 * 왼쪽 문자열 공백 제거
	 * 
	 * @param str			문자열
	 * @return				왼쪽 공백 제거 문자열
	 */
	public static String toLTrim(String strS) {
		String str = strS;
		if (str == null) return "";
		
		while(str.charAt(0) == ' ') {
			str = str.substring(1);
		}
		
		return str;
	}
	
	/**
	 * 오른쪽 문자열 공백 제거
	 * 
	 * @param str			문자열
	 * @return				오른쪽 공백 제거 문자열
	 */
	public static String toRTrim(String strS) {
		String str = strS;
		if (str == null) return "";
		
		int len = str.length();
		
		while(str.charAt(len - 1) == ' ') {
			str = str.substring(0,  len - 1);
			len--;
		}
		
		return str;
	}
	
	/**
	 * 입력받은 문자열을 지정된 길이만큼 입력한 문자로 오른쪽을 채워주는 함수
	 * 
	 * @param inputString			입력 문자열
	 * @param length				길이
	 * @param pad					채워질 문자
	 * @return
	 */
	public static String rpad(String inputString, int length, char pad) {
		
		String result = "";
		
		int loopCount = length - inputString.getBytes().length;

		for (int i = 0; i < loopCount; i++) {
			StringBuffer resultData = new StringBuffer(result);
			resultData.append(pad);
			result = resultData.toString(); 
			//result = result.concat(result + pad);
		}

		return inputString + result;
	}

	/**
	 * 입력받은 문자열을 지정된 길이만큼 입력한 문자로 왼쪽을 채워주는 함수
	 * 
	 * @param inputString			입력 문자열
	 * @param length				길이
	 * @param pad					채워질 문자
	 * @return
	 */
	public static String lpad(String inputString, int length, char pad) {
		
		String result = "";
		
		int loopCount = length - inputString.getBytes().length;

		for (int i = 0; i < loopCount; i++){
			StringBuffer resultData = new StringBuffer(result);
			resultData.append(pad);
			result = resultData.toString(); 
			//result = result.concat(result + pad);
		}

		return result + inputString;
	}

	/**
	 * 요청한 페이지의 URL을 반환한다.
	 * 
	 * @param req			HttpServletRequest
	 * @return
	 */
	public static String getURL(HttpServletRequest req) {
		return req.getRequestURL().toString();
	}
	
	/**
	 * 요청한 페이지의 URI를 반환한다.
	 * @param req
	 * @return
	 */
	public static String getURI(HttpServletRequest req) {
		
		String returnURI = null;
		
		if (StringUtil.isNull(req.getQueryString())) {
			returnURI = req.getRequestURI();
		} else {
			returnURI = req.getRequestURI() + "?" + req.getQueryString();
		}
		
		return returnURI;
	}
	
	/**
	 * 문자열의 길이가 max보다 클경우 max 크기만큼만 잘라서 반환한다.
	 * 
	 * @param s				입력 문자열
	 * @param max			길이
	 * @return
	 */
	public static String cutString(String str, int length) {
		if (length == 0)
			return str;

		if (str == null || str.length() == 0)
			return "";

		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;

		if (length >= len)
			return str;

		StringBuffer sb = new StringBuffer();
		sb.append(str);
		for (int i = 0; i < length - len; i++) {
			sb.append(' ');
		}

		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0)
				counter++;
		}
		String firstStr = null;
		firstStr = new String(bytes, 0, length - (counter % 2));

		return firstStr;
	}
	
	/**
	 * 문자열의 길이가 max보다 클경우 max 크기만큼만 잘라서 반환한다.
	 * 
	 * @param s				입력 문자열
	 * @param max			길이
	 * @param tail			마지막 문자열 "..."
	 * @return
	 */
	public static String cutString(String str, int length, String tail) {
		if (length == 0)
			return str;

		if (str == null || str.length() == 0)
			return "";

		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;

		if (length >= len)
			return str;

		StringBuffer sb = new StringBuffer();
		sb.append(str);
		for (int i = 0; i < length - len; i++) {
			sb.append(' ');
		}

		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0)
				counter++;
		}
		String firstStr = null;
		firstStr = new String(bytes, 0, length - (counter % 2));

		return firstStr + tail;
	}

	/**
	 * 문자열의 길이가 max보다 클경우 max 크기만큼만 잘라서 반환한다.
	 * 
	 * @param s				입력 문자열
	 * @param max			길이
	 * @param type			"+" 길이를 나눈 값에 더한다, "-" 길이를 나눈 값에서 뺀다
	 * @param tail			마지막 문자열 "..."
	 * @return
	 */
	public static String cutString(String str, int length, char type, String tail) {
		if (length == 0)
			return str;

		if (str == null || str.length() == 0)
			return "";

		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;

		if (length >= len)
			return str;

		StringBuffer sb = new StringBuffer();
		sb.append(str);
		for (int i = 0; i < length - len; i++) {
			sb.append(' ');
		}

		for (int i = length - 1; i >= 0; i--) {
			if (((int) bytes[i] & 0x80) != 0)
				counter++;
		}
		String firstStr = null;
		if (type == '+') {
			firstStr = new String(bytes, 0, length + (counter % 2));
		} else if (type == '-') {
			firstStr = new String(bytes, 0, length - (counter % 2));
		} else {
			firstStr = new String(bytes, 0, length - (counter % 2));
		}

		return firstStr + tail;
	}

	/**
	 * 문자열을 변환한다.
	 * 
	 * @param str			문자열
	 * @param pattern		변경 대상 문자열
	 * @param replace		변경 문자열
	 * @return
	 */
	public static String replace(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		e = str.indexOf(pattern, s);
		
		while (e >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
			e = str.indexOf(pattern, s);
		}

		result.append(str.substring(s));
		return result.toString();
	}
	
	/**
	 * 한글 포함 여부 체크
	 * @param src			문자열
	 * @return				TRUE : 한글, FALSE : 한글 포함 하지 않음
	 */
	public static boolean hasKorean(String suspect) {
		for (int i = 0; i < suspect.length(); i++) {

			char c = suspect.charAt(i);

			// korean : one character (consonant or vowel)
			if (0xAC00 <= c && c <= 0xD7A3 || 0x3131 <= c && c <= 0x318E) {
				return true;
			} /*else if ((0x61 <= c && c <= 0x7A) || (0x41 <= c && c <= 0x5A)) {
				// english
			} else if (0x30 <= c && c <= 0x39) {
				// integer/decimal
			} else {
				// unknown
			}*/
		}

		return false;
	}

	/**
	 * 이메일 형식 검사 정규 표현식
	 * @param email			EMAIL 문자열
	 * @return				표현식 성공 여부(true : 성공, false: 실패)
	 */
	public static boolean isValidEmail(String email) {
		Pattern p = Pattern.compile("^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * HTML 태그 제거 정규 표현식
	 * @param htmlStr		HTML 문자열
	 * @return				HTML 태그 제거 문자열
	 */
	public static String stripHTML(String htmlStr) {
		
		Pattern scripts = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL); 

		Pattern style = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL); 
		
		// HTML/XML tags 
		Pattern tags = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>"); 

		Pattern ntags = Pattern.compile("<\\w+\\s+[^<]*\\s*>"); 
		
		// entity references 
		Pattern entityRefs = Pattern.compile("&[^;]+;");
		
		// repeated whitespace 
		Pattern whitespace = Pattern.compile("\\s\\s+");
		
		Matcher matches = scripts.matcher(htmlStr);
		
		htmlStr = matches.replaceAll("");
		
		matches = style.matcher(htmlStr);
		
		htmlStr = matches.replaceAll("");
		
		matches = tags.matcher(htmlStr);
		
		htmlStr = matches.replaceAll("");
		
		matches = ntags.matcher(htmlStr);
		
		htmlStr = matches.replaceAll("");
		
		matches = entityRefs.matcher(htmlStr);
		
		htmlStr = matches.replaceAll("");
		
		matches = whitespace.matcher(htmlStr);
		
		htmlStr = matches.replaceAll("");
		
		return htmlStr;
    }
	
	/**
	 * 이미지 태그 삭제 정규 표현식
	 * @param htmlStr		HTML 문자열
	 * @return				이미지 태크
	 */
	public static String stripIMG(String htmlStr) {
		
		Pattern pattern = Pattern.compile("<img [^<>]*>"); 
		Matcher matches = pattern.matcher(htmlStr.toLowerCase());
		
		return matches.replaceAll("");

	}
	
	/**
	 * 이미지 태그 반환 정규 표현식
	 * @param htmlStr		HTML 문자열
	 * @return				이미지 태크
	 */
	public static String extractImgSrc(String htmlStr) {
		
		Pattern pattern = Pattern.compile("<img [^<>]*>"); 
		Matcher matches = pattern.matcher(htmlStr.toLowerCase());
		
		String imgTag = "";
		
		while (matches.find()) {
			imgTag = htmlStr.substring(matches.start(), matches.end());
		}
		
		return imgTag;

	}
	
	/**
	 * HTML 링크 생성
	 * @param sText			문자열
	 * @return				HTML 링크 생성 태크
	 */
	public static String linkedText(String sText) {
		Pattern p = Pattern.compile("(http|https|ftp)://[^\\s^\\.]+(\\.[^\\s^\\.]+)*");
		Matcher m = p.matcher(sText);
		
		StringBuffer sb = new StringBuffer();
		
		while (m.find()) {
			m.appendReplacement(sb, "<a href='" + m.group()+"' title='" + m.group() + "' target='_blank'>" + m.group() + "</a>");
		}
		
		m.appendTail(sb);
		
		return sb.toString();
	} 
	
	/**
	 * 금지어 필터링하기
	 * @param sText			문자열
	 * @return				"*" 변경 문자열
	 */
    public static String filterText(String sText) {
    	Pattern p = Pattern.compile("fuck|shit|개새끼", Pattern.CASE_INSENSITIVE);
    	Matcher m = p.matcher(sText);

    	StringBuffer sb = new StringBuffer();
    	while (m.find()) {
    		m.appendReplacement(sb, maskWord(m.group()));
    	}
    	m.appendTail(sb);

    	return sb.toString();
    }
    
    /**
     * 입력 문자 "*" MASK 처리
     * @param word			문자열
     * @return				MASK 처리된 문자열
     */
    public static String maskWord(String word) {
    	StringBuffer buff = new StringBuffer();
    	char[] ch = word.toCharArray();
    	
    	for (int i = 0; i < ch.length; i++) {
    		if (i < 1) {
    			buff.append(ch[i]);
    		} else {
    			buff.append("*");
    		}
    	}
        
    	return buff.toString();
    }
    
    /**
     * 입력 문자 "*" MASK 처리
     * @param word			문자열
     * @param count			변경할 문자열 수
     * @return				MASK 처리된 문자열
     */
    public static String maskWord(String word, int countI) {
    	StringBuffer buff = new StringBuffer();
    	int count = countI;
    	char[] ch = word.toCharArray();
    	
    	if (count >= ch.length) {
    		count = ch.length;
    	}
    	
    	buff.append(word.subSequence(0, ch.length - count));
    	
    	/*
    	 * MASK 처리 할 문자열 구하기
    	 */
    	String cutWord = word.substring(ch.length - count, ch.length);
    	char[] maskCh = cutWord.toCharArray();
    	
    	for (int i = 0; i < maskCh.length; i++) {
    		buff.append("*");
    	}
        
    	return buff.toString();
    }

	/**
	 * 문자열 해당코드로 변환
	 * 
	 * @param str			문자열
	 * @param encode		기존 ENCODE 문자열
	 * @param charsetName	변경 ENCODE 문자열
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeText(String str, String encode,
			String charsetName) {
		String result = null;

		try {
			result = isNull(str) ? null : new String(str.getBytes(encode), charsetName);
		} catch (UnsupportedEncodingException e) {
			logger.error("encodeText error");
		}

		return result;
	}

	/**
	 * 문자열을 UTF-8에서 8859_1 로 디코딩
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeUTF8(String str) {
		return encodeText(str, "UTF-8", "8859_1");
	}

	/**
	 * 문자열을 UTF-8 에서 8859_1 로 인코딩
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeUTF8(String str) {
		return encodeText(str, "8859_1", "UTF-8");
	}

	/**
	 * 문자열을 EUC-KR에서 8859_1 로 디코딩
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeEUCKR(String str) {
		return encodeText(str, "EUC-KR", "8859_1");
	}

	/**
	 * 문자열을 EUC-KR 에서 8859_1 로 인코딩 한다
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeEUCKR(String str) {
		return encodeText(str, "8859_1", "EUC-KR");
	}

	/**
	 * 현재의 시간을 주어진 format 형식으로 변환 메소드
	 * 
	 * @param format		format 문자열
	 * @return
	 */
	public static String getFormatDate(String format) {
		if (format == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		return sdf.format(new Date());
	}

	/**
	 * Javascript 출력 후 script 실행 ModelAndView 객체 리턴
	 * @param msg			메시지
	 * @param script		실행 스크립트
	 * @return
	 */
	public static ModelAndView scriptMessageView(final String msg, final String script) {
		
		View view = new AbstractView() {
			@Override
			protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, 
					HttpServletRequest request, HttpServletResponse response) throws IOException {
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				
				ServletOutputStream outs = response.getOutputStream();
				outs.println("<script type=\"text/javascript\">");
				outs.println("alert('" + new String(msg.getBytes(), "ISO_8859_1") + "');");
				outs.println(new String(script.getBytes(), "ISO_8859_1"));
				outs.println("</script>");
				outs.flush();
			}
		};
		
		return new ModelAndView(view);
	}
	
	/**
	 * 자바스크립트 alert 메시지 출력 후 HISTORY BACK 이동
	 * @param res			HttpServletResponse
	 * @param message		메시지
	 * @param href			이동 URL
	 * @throws IOException
	 */
	public static void htmlPrintMessage(HttpServletResponse res, String message) {
		
		try {
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<!doctype html>");
			out.println("<html lang=\"ko\">");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\">");
			out.println("<title>알림</title>");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('" + message + "');");
			out.println("window.history.back();");
			out.println("</script>");
			out.println("</head>");
			out.println("<body></body>");
			out.println("</html>");
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 자바스크립트 alert 메시지 출력 후 지정 URL 로 이동
	 * @param res			HttpServletResponse
	 * @param message		메시지
	 * @param href			이동 URL
	 * @throws IOException
	 */
	public static void htmlPrintHref(HttpServletResponse res, String message,
			String href) {
		
		try {
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<!doctype html>");
			out.println("<html lang=\"ko\">");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\">");
			out.println("<title>알림</title>");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('" + message + "');");
			out.println("window.location.href='" + href + "';");
			out.println("</script>");
			out.println("</head>");
			out.println("<body></body>");
			out.println("</html>");
			out.flush();
			out.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 자바스크립트 지정 URL 로 이동
	 * @param res			HttpServletResponse
	 * @param href			이동 URL
	 * @throws IOException
	 */
	public static void htmlPrintHref(HttpServletResponse res, String href) {
		
		try {
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<!doctype html>");
			out.println("<html lang=\"ko\">");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\">");
			out.println("<title>알림</title>");
			out.println("<script type=\"text/javascript\">");
			out.println("window.location.href='" + href + "';");
			out.println("</script>");
			out.println("</head>");
			out.println("<body></body>");
			out.println("</html>");
			out.flush();
			out.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 자바스크립트 부모 창 URL 변경 및 자신의 창 닫기
	 * @param res			HttpServletResponse
	 * @param href			이동 URL
	 * @throws IOException
	 */
	public static void htmlPrintSelfCloseOpenerHref(HttpServletResponse res,
			String href) {
		
		try {
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<!doctype html>");
			out.println("<html lang=\"ko\">");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\">");
			out.println("<title>알림</title>");
			out.println("<script type=\"text/javascript\">");
			out.println("window.opener.location.href='" + href + "';");
			out.println("window.self.close();");
			out.println("</script>");
			out.println("</head>");
			out.println("<body></body>");
			out.println("</html>");
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 요청 HOST URL 가져오기
	 * @param req			HttpServletRequest
	 * @param http			HTTP 포함 여부
	 * @return				HOST URL
	 */
	public static String getRequstHostURL(HttpServletRequest req, boolean http) {
		String reqUrl = req.getRequestURL().toString();
		
		reqUrl = reqUrl.substring(reqUrl.indexOf("//")+2);
		String hostUrlS = "";
		String hostUrl = reqUrl.substring(0, reqUrl.indexOf("/"));
		
		if (":80".equals(hostUrl.substring(hostUrl.length()-3))) {
			hostUrl = hostUrl.substring(0, hostUrl.length()-3);
		}
		
		if (http) {
			
			/*
			 * HTTPS SSL 접속 일 경우
			 */
			if (hostUrl.indexOf(":443") > -1) {
				hostUrlS = "https://" + hostUrl + req.getContextPath();
			} else {
				hostUrlS = "http://" + hostUrl + req.getContextPath();
			}
			hostUrl = hostUrlS;
		}
		
		return hostUrl;
	}
	
	/**
	 * 요청 HOST URL 가져오기
	 * @param reqUrl		요청 URL
	 * @param http			HTTP 포함 여부
	 * @return				HOST URL
	 */
	public static String getRequstHostURL(String reqUrlS, boolean http) {
		String reqUrl = reqUrlS; 
		reqUrl = reqUrl.substring(reqUrl.indexOf("//")+2);
		String hostUrlS = "";
		String hostUrl = reqUrl.substring(0, reqUrl.indexOf("/"));
		
		if (http) {
			hostUrlS = "http://" + hostUrl;
			hostUrl = hostUrlS;
		}
		
		return hostUrl;
	}

	/**
	 * 메시지를 RED Color 로 만든다.
	 * 
	 * @param message		문자열	
	 * @return
	 */
	public static String convertFontRed(String message) {
		return "<font style='color:#ff00'>" + message + "</font>";
	}

	/**
	 * 게시판 첨부파일 미리보기
	 * @param fileRoom		파일 저장 위치
	 * @param fileName		파일 명
	 * @param tbWidth		너비
	 * @return
	 */
	public static String getUploadFileView(String fileRoom, String fileName,
			int tbWidth) {
		StringBuffer htmlFileView = new StringBuffer("");

		if (isNull(fileName)) {
			return htmlFileView.toString();
		}

		String fileEndName = getFileExtension(fileName, false).toUpperCase();

		if ("JPG".equals(fileEndName) || "GIF".equals(fileEndName)
				|| "BMP".equals(fileEndName) || "PNG".equals(fileEndName)) {
			htmlFileView.append("<center><img src='");
			htmlFileView.append(fileRoom);
			htmlFileView.append(fileName);
			htmlFileView.append("' border=0 onLoad='upload_img_resize(this,");
			htmlFileView.append(tbWidth);
			htmlFileView.append(");' onfocus='blur();' onclick=this.src.popupView() style=\"cursor: pointer\"></center><br><br>");

		} else if ("SWF".equals(fileEndName)) {

			String id = "F_" + fileName.substring(0, fileName.lastIndexOf("."));

			htmlFileView.append("</center><script language='javascript'>");
			if (tbWidth <= 100) {
				htmlFileView.append("Flash('");
				htmlFileView.append(id);
				htmlFileView.append("', '");
				htmlFileView.append(fileRoom);
				htmlFileView.append(fileName);
				htmlFileView.append("', '350', '300', '#FFFFFF','transparent');");
			} else {
				htmlFileView.append("Flash('");
				htmlFileView.append(id);
				htmlFileView.append("', '");
				htmlFileView.append(fileRoom);
				htmlFileView.append(fileName);
				htmlFileView.append("', '");
				htmlFileView.append(tbWidth - 10);
				htmlFileView.append("', '");
				htmlFileView.append(tbWidth * 0.75);
				htmlFileView.append("', '#FFFFFF','transparent');");				
			}

			htmlFileView.append("</script></center><br><br>");

		} else if ("WAV".equals(fileEndName) || "MP3".equals(fileEndName)) {

			String id = "S_" + fileName.substring(0, fileName.lastIndexOf("."));

			htmlFileView.append("<center><script language='javascript'>");
			htmlFileView.append("WMPlayer('");
			htmlFileView.append(id);
			htmlFileView.append("', '");
			htmlFileView.append(fileRoom);
			htmlFileView.append(fileName);
			htmlFileView.append("', '350', '64');");
			htmlFileView.append("</script></center><br><br>");
		} else if ("ASF".equals(fileEndName) || "ASX".equals(fileEndName)
				|| "AVI".equals(fileEndName) || "WMA".equals(fileEndName)
				|| "WMV".equals(fileEndName) || "MPG".equals(fileEndName)
				|| "MPG".equals(fileEndName) || "MPEG".equals(fileEndName)) {

			String id = "M_" + fileName.substring(0, fileName.lastIndexOf("."));

			htmlFileView.append("<center><script language='javascript'>");
			htmlFileView.append("WMPlayer('");
			htmlFileView.append(id);
			htmlFileView.append("', '");
			htmlFileView.append(fileRoom);
			htmlFileView.append(fileName);
			htmlFileView.append("', '350', '300');");
			htmlFileView.append("</script></center><br><br>");			

		}

		return htmlFileView.toString();
	}
	
	/**
	 * 첨부파일 파일 확장자 ICON 명 가져오기
	 * @param fileName		파일 명
	 * @return				파일 ICON 명
	 */
	public static String getFileIconName(String fileName) {
		
		String fileIconName = "";
		
		String fileExt = StringUtil.getFileExtension(fileName, false).toLowerCase();
		
		if ("gif".equals(fileExt)) {
			fileIconName = "p_gif_s.gif";
		} else if ("html".equals(fileExt) || "htm".equals(fileExt)) {
			fileIconName = "p_html_s.gif";
		} else if ("hwp".equals(fileExt)) {
			fileIconName = "p_hwp_s.gif";
		} else if ("jpg".equals(fileExt) || "jpeg".equals(fileExt)) {
			fileIconName = "p_jpg_s.gif";
		} else if ("avi".equals(fileExt) || "asf".equals(fileExt) || "wmp".equals(fileExt) || "wmv".equals(fileExt) ||
				"mpeg".equals(fileExt) || "mpg".equals(fileExt) || "tp".equals(fileExt) || "ts".equals(fileExt) ||
				"mkv".equals(fileExt) || "mov".equals(fileExt) || "mp4".equals(fileExt) || "k3g".equals(fileExt) ||
				"skm".equals(fileExt) || "flv".equals(fileExt)) {
			fileIconName = "p_movie_s.gif";
		} else if ("mp3".equals(fileExt)) {
			fileIconName = "p_mp3_s.gif";
		} else if ("pdf".equals(fileExt)) {
			fileIconName = "p_pdf_s.gif";
		} else if ("png".equals(fileExt)) {
			fileIconName = "p_png_s.gif";
		} else if ("ppt".equals(fileExt) || "pptx".equals(fileExt)) {
			fileIconName = "p_ppt_s.gif";
		} else if ("swf".equals(fileExt)) {
			fileIconName = "p_swf_s.gif";
		} else if ("txt".equals(fileExt)) {
			fileIconName = "p_txt_s.gif";
		} else if ("doc".equals(fileExt) || "docx".equals(fileExt)) {
			fileIconName = "p_word_s.gif";
		} else if ("xls".equals(fileExt) || "xlsx".equals(fileExt)) {
			fileIconName = "p_xls_s.gif";
		} else if ("zip".equals(fileExt) || "rar".equals(fileExt) || "egg".equals(fileExt)) {
			fileIconName = "p_zip_s.gif";
		} else {
			fileIconName = "p_etc_s.gif";
		}
		
		return fileIconName;
	}

	/**
	 * 숫자형식을 3자리 ","형식으로 변환
	 * @param number		일반 Double 형 숫자 데이터
	 * @return				소수 자리 없는 화폐 구분자로 구분된 문자형 숫자
	 */
	public static String toMoney(int number) {

		DecimalFormat df = new DecimalFormat("#,###");
		StringBuffer result = new StringBuffer();

		df.format(number, result, new FieldPosition(1));

		return result.toString();
	}

	/**
	 * 숫자형식을 3자리","형식으로 변환 , 소수 2자리까지
	 * @param number			일반 Double 형 숫자 데이터
	 * @return					소수 2 자리 까지 표시된 화폐 구분자로 구분된 문자형 숫자
	 */
	public static String toMoney(double number) {

		DecimalFormat df = new DecimalFormat("#,###.##");
		StringBuffer result = new StringBuffer();

		df.format(number, result, new FieldPosition(1));

		return result.toString();
	}

	/**
	 * 숫자를 3자리","형식으로 지정한    소수점 자리수 만큼 변환
	 * @param number			일반 Double 형 숫자 데이터
	 * @param pointLength		소수점 자리수(0 : 없음, 1 : 1자리, 2 : 2자리)
	 * @return
	 */
	public static String toMoney(double number, int pointLength) {

		DecimalFormat df = null;
		StringBuffer result = new StringBuffer();

		StringBuffer format = new StringBuffer();
		
		format.append("#,###");
		
		for (int i = 0 ; i < pointLength ; i++) {
			
			if (i == 0) {
				format.append(".");
			}
			
			format.append("#");
		}
		
		df = new DecimalFormat(format.toString());
		df.format(number, result, new FieldPosition(1));

		return result.toString();
	}

	/**
	 * ","이 포함된 문자열에서 ","을 삭제
	 * @param number			화폐 표시 문자열
	 * @return					","를 삭제한 문자열
	 */
	public static String toNumber(String number) {

		return number.replace(",", "");
	}
	
	/**
	 * 절대 경로가 아닌 상대 경로 파일 경로 문자열 체크
	 * 
	 * @param path				경로
	 * @return					변환 경로
	 */
	public static String validatePath(String pathS) {
		String path = pathS;
		path = replace(path, "..", "");
		path = replace(path, "//", "");
		path = replace(path, "\\\\", "");
		path = replace(path, ".\\", "");
		path = replace(path, "./", "");
		path = replace(path, "../", "");
		path = replace(path, "..\\", "");
		path = replace(path, "./.\\.", "");

		return path;
	}
	
	/**
	 * 문자열에 대한 JSP, JAVASCRIPT 관련 문자 HTML ASCII 코드로 처리
	 * @param content			입력 문자열
	 * @return					변환 문자열
	 */
	public static String validate(String contentS) {
		String content = contentS; 
		
		// <SC로 시작하는 태그는 무조건 제거
		content = StringUtil.replace(content, "<SC", "&#60;SC");
		content = StringUtil.replace(content, "<Sc", "&#60;Sc");
		content = StringUtil.replace(content, "<sC", "&#60;sC");
		content = StringUtil.replace(content, "<sc", "&#60;sc");

		content = StringUtil.replace(content, "</SC", "&#60;/SC");
		content = StringUtil.replace(content, "</Sc", "&#60;/Sc");
		content = StringUtil.replace(content, "</sC", "&#60;/sC");
		content = StringUtil.replace(content, "</sc", "&#60;/sc");

		// <IF로 시작되는 태그는 무조건 제거
		content = StringUtil.replace(content, "<IF", "&#60;IF");
		content = StringUtil.replace(content, "<If", "&#60;If");
		content = StringUtil.replace(content, "<iF", "&#60;iF");
		content = StringUtil.replace(content, "<if", "&#60;if");

		content = StringUtil.replace(content, "</IF", "&#60;/IF");
		content = StringUtil.replace(content, "</If", "&#60;/If");
		content = StringUtil.replace(content, "</iF", "&#60;/iF");
		content = StringUtil.replace(content, "</if", "&#60;/if");
		
		// <% %> JSP Script 무조건 제거
		content = StringUtil.replace(content, "<%", "&#60;%");
		content = StringUtil.replace(content, "%>", "%&#62;");
		
		return content;
	}
	
	/**
	 * 문자열에 대한 JSP, JAVASCRIPT, ", ' 문자 관련 문자 HTML ASCII 코드로 처리
	 * @param content			입력 문자열
	 * @return					변환 문자열
	 */
	public static String validate2(String contentS) {
		String content = contentS; 
		
		// <SC로 시작하는 태그는 무조건 제거
		content = StringUtil.replace(content, "<SC", "&#60;SC");
		content = StringUtil.replace(content, "<Sc", "&#60;Sc");
		content = StringUtil.replace(content, "<sC", "&#60;sC");
		content = StringUtil.replace(content, "<sc", "&#60;sc");

		content = StringUtil.replace(content, "</SC", "&#60;/SC");
		content = StringUtil.replace(content, "</Sc", "&#60;/Sc");
		content = StringUtil.replace(content, "</sC", "&#60;/sC");
		content = StringUtil.replace(content, "</sc", "&#60;/sc");

		// <IF로 시작되는 태그는 무조건 제거
		content = StringUtil.replace(content, "<IF", "&#60;IF");
		content = StringUtil.replace(content, "<If", "&#60;If");
		content = StringUtil.replace(content, "<iF", "&#60;iF");
		content = StringUtil.replace(content, "<if", "&#60;if");

		content = StringUtil.replace(content, "</IF", "&#60;/IF");
		content = StringUtil.replace(content, "</If", "&#60;/If");
		content = StringUtil.replace(content, "</iF", "&#60;/iF");
		content = StringUtil.replace(content, "</if", "&#60;/if");
		
		// <% %> JSP Script 무조건 제거
		content = StringUtil.replace(content, "<%", "&#60;%");
		content = StringUtil.replace(content, "%>", "%&#62;");
		
		// " 무조건 제거
		content = StringUtil.replace(content, "\"", "&#34;");
		content = StringUtil.replace(content, "'", "&#39;");
		
		// HTML 태그 변환
		content = StringEscapeUtils.escapeHtml4(content);

		return content;
	}
	
	/**
	 * 문자열에 대한 <, >, ", ' 문자 관련 문자 HTML ASCII 코드로 처리
	 * @param contentS			입력 문자열
	 * @return					변환 문자열
	 */
	public static String validate3(String contentS) {
		String content = contentS; 
		
		content = StringUtil.replace(content, "<", "&#60;");
		content = StringUtil.replace(content, ">", "&#62;");
		
		// " 무조건 제거
		content = StringUtil.replace(content, "\"", "&#34;");
		content = StringUtil.replace(content, "'", "&#39;");
		
		return content;
	}
	
	/**
	 * 문자열에 대한 검색 제한 문자 관련 문자 삭제 처리
	 * @param searchWord		입력 문자열
	 * @return					변환 문자열
	 */
	public static String validateFastSearchWord(String searchWord) {
		
		String newString = "";
		
		if (StringUtil.isNotNull(searchWord)) {
			
			Pattern p = Pattern.compile("[^\uAC00-\uD7A3xfe.@&?*\\-+0-9a-zA-Z\\s]");
			Matcher m = p.matcher(searchWord);
			
			newString = m.replaceAll("");
		}
		
		return newString;
	}
	
	/**
	 * 파일 확장자가 서버 실행 스크립트 인지를 체크 한다.<br>
	 * 체크 확장자 : <b>jsp, cgi, php, htm, html, js</b>
	 * <br>
	 * @param fileName		파일 명
	 * @return				true : 서버 실행 스크립트, false : 일반 파일
	 */
	public static boolean getScriptExtensionCheck(String fileName) {
		
		boolean check = false;
		
		if ("jsp".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"cgi".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"php".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"htm".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"html".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"js".equals(getFileExtension(fileName, false).toLowerCase())) {
				
			check = true;
		}
		
		return check;
	}
	
	/**
	 * 서버 실행 스크립트 확장자를 Text 확장자로 변경한다.<br>
	 * 변경 대상 확장자 : <b>jsp, cgi, php, htm, html, js</b>
	 * <br>
	 * 예)<br> 
	 * [입력] " <B>abcder.jsp </B>" ----> [출력] " <B>abcder.txt </B>"
	 * @param fileName		파일 명
	 * @return				변경 파일 명
	 */
	public static String getScriptExtensionToText(String fileName) {
		
		String newFileName = fileName;
		
		if ("jsp".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"cgi".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"php".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"htm".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"html".equals(getFileExtension(fileName, false).toLowerCase()) ||
			"js".equals(getFileExtension(fileName, false).toLowerCase())) {
			
			newFileName = getFileNameWithoutExtension(fileName) + ".txt";
		} 
		
		return newFileName;
	}
	
	/**
	 * "yyyy-MM-dd" 형식의 문자열을 Date Type 으로 변경
	 * 
	 * @param textDate			변환 문자열
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringToDate(String textDate) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		
		Date date = new Date();
		
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("getStringToDate error");
		}
		
		return date;
	}
	
	/**
	 * "EEE, d MMM yyyy HH:mm:ss Z" 형식의 문자열을 Date Type 으로 변경
	 * @param textDate			변환 문자열
	 * @return
	 */
	public static Date getStringToDateTimeUS(String textDate) {
		
		SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
		
		Date date = new Date();
		
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("getStringToDateTimeUS error");
		}
		
		return date;
	}
	
	/**
	 * 패턴 형식의 문자열을 Date Type 으로 변경
	 * @param textDate			변환 문자열
	 * @param pattern			패턴
	 * @return
	 */
	public static Date getStringToDateTime(String textDate, String pattern) {
		
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.KOREA);
		
		Date date = new Date();
		
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("getStringToDateTime error");
		}
		
		return date;
	}
	
	/**
	 * "yyyy-mm-dd HH:mm:ss" 형식의 문자열을 Date Type 으로 변경
	 * @param textDate			변환 문자열
	 * @return
	 */
	public static Date getStringToDateTime(String textDate) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		
		Date date = new Date();
		
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("getStringToDateTime error");
		}
		
		return date;
	}
	
	/**
     *  검색결과 문자열에서 자리수를 자르는 함수
     * 
     * @param 문자열 cStr   
     * @param 자를 자리수 cutLen
     * @return 
     */
	public static String cutHtml(String cStr, int cutLen){
		String fTag = "<span class=\"search_word\">";
		String eTag = "</span>";
		String fChr = "▒";
		String eChr = "‡";
		
		if(cStr == null) return "";

		if(cStr.length() < cutLen) cutLen = cStr.length();
		
		cStr = cStr.replaceAll(fTag,fChr).replaceAll(eTag,eChr);

		String str = cStr.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","").replaceAll("\r|\n|&nbsp;","");

		if(str.length() < cutLen) cutLen = str.length();
		
		String rStr = str.substring(0,cutLen);


		if(rStr.replaceAll(fChr,"").length()!=rStr.replaceAll(eChr,"").length()){
			int newCutLen = str.indexOf(eChr,cutLen)+1;
			
			rStr = str.substring(0,newCutLen);
		}
		rStr = rStr.replaceAll(fChr,fTag).replaceAll(eChr,eTag);
		
		return rStr;

	}

	/**
	 * Schema Document Root Element 생성
	 * 
	 * @param rootDocumentName	Root Document 명
	 * @return Document
	 */
	public static Document createNewDocument(String rootDocumentName) {

		Element resultElement = new Element(rootDocumentName);

		Document document = new Document(resultElement);

		return document;
	}
	

	/**
	 * XML 문서 화면에 출력
	 * 
	 * @param writeDocument		출력할 XML Document
	 * @param response			HttpServletResponse
	 * @throws IOException
	 */
	public static void responseWriteXML(Document writeDocument,
			HttpServletResponse response) throws IOException {

		// XMLOutputter 생성
		XMLOutputter xmlOutputter = new XMLOutputter();

		Format format = xmlOutputter.getFormat();
		// UTF-8 Encoding
		format.setEncoding("UTF-8");
		// 들여쓰기
		format.setIndent("   ");
		// 줄바꿈
		format.setLineSeparator("\r\n");
		// XML문서파일을 읽을 때 carriage return 으로 자동줄바꿈이 일어나는 것을 해제
		format.setTextMode(Format.TextMode.TRIM);
		xmlOutputter.setFormat(format);

		// 출력
		// xmlOutputter.output(newResultDocument, System.out);
		xmlOutputter.output(writeDocument, response.getWriter()); // 웹브라우저에 출력
	}

	/**
	 * 사용자 로그인 여부 체크
	 * 
	 * @param request		HttpServeltRequest
	 * @return 로그인 여부(true : 로그인, false : 비로그인)
	 */
	public static boolean getLoginCheck(HttpServletRequest request) {

		boolean check = false;

		if (StringUtil.isNotNull(getSsoId(request))) {
			check = true;
		}

		return check;
	}

	/**
	 * 사용자 로그인 SSO ID 가져오기
	 * 
	 * @param request		HttpServeltRequest
	 * @return 				로그인 ID
	 */
	public static String getSsoId(HttpServletRequest request) {
		String ssoId = "";
		
		try {
//			CookieManager.setEncStatus(true);
//			String ssoId = CookieManager.getCookieValue(SECode.USER_ID, request);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return ssoId;
	}
	
	public static String getUserName(String ssoId) {
        BufferedReader br = null;
        
        String userNm = null;

        try {

            URL url = new URL("http://jiseo.ntis.go.kr/oa/userName.do?ssoId="+ssoId);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            String json = sb.toString();
            
            try {
            	userNm = json.split("\"ssoNm\":\"")[1].split("\"")[0];
            } catch (Exception ex) {
            	userNm = "";
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {

            if (br != null) {
            	try {
            		br.close();
            	} catch (Exception ee) {
            		ee.printStackTrace();
            	}
            }
        }		
		return userNm;
	}
	
	// 배열 중복제거
		public static String[] getRtnUniStrArr(String[] strArr) {
			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < strArr.length; i++) {
				if (!list.contains(strArr[i])) list.add(strArr[i]);
			}
			String uniArr[] = new String[list.size()];
			list.toArray(uniArr);

			return uniArr;
		}
		
		//크로스사이트 스크립트 방지
		public static String cleanXSS(String value) {
			value = value.replaceAll("<", "& lt;");
			value = value.replaceAll(">", "& gt;");
			value = value.replaceAll("\\(", "& #40;");
			value = value.replaceAll("\\)", "& #41;");
			value = value.replaceAll("'", "& #39;");
			value = value.replaceAll("&", "&amp;");
			value = value.replaceAll("eval\\((.*)\\)", "");
			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
			value = value.replaceAll("script", "");
			return value;
		}
		
		/**
		 * 문자열 모든 공백 제거
		 * 
		 * @param str			문자열
		 * @return				오른쪽 공백 제거 문자열
		 */
		public static String toAllTrim(String strS) {
			String str = strS;
			str = str.replaceAll("\\p{Z}", "");
			
			return str;
		}
		
		/**
		 * 자바스크립트 alert 메시지 출력 후 History Back() 실행
		 * @param res			HttpServletResponse
		 * @param message		메시지
		 * @throws IOException
		 */
		public static void htmlPrintHistoryBack(HttpServletResponse res,
				String message) throws IOException {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"ko\" xml:lang=\"ko\">");
			out.println("<head>");
			out.println("<title>알림</title>");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('" + message + "');");
			out.println("window.history.back();");
			out.println("</script>");
			out.println("</head>");
			out.println("<body></body>");
			out.println("</html>");
		}
}
