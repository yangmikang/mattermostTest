package kr.go.distep.cmmn.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import com.initech.eam.nls.CookieManager;
//import com.initech.eam.smartenforcer.SECode;

import egovframework.rte.psl.dataaccess.util.CamelUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.go.distep.cmmn.web.HttpRequestWrapper;

public class Util {
	/**
     * null값을 빈문자열로 반환
     * 
     * @param str 
     * @return
     */
    public static String nullToBlank(Object str) {

        if (str == null) {
            return "";
        } else {
            return str.toString();
        }
    }
	/**
     * 웹페이지에 문자열 Display
     * 
     * @param res -
     *            HttpServletResponse
     * @param str -
     *            웹페이지에 뿌려줄 문자열
     */
    public static void pageWrite(HttpServletResponse res, String str) {

        try {
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println(str);
            out.flush();
        } catch(IOException e) {
            Logger.getLogger("Util").error("Exception Occured");
        }
    }
	
    /**
     * request parameter 값을 Map 객체로 반환
     * @param request
     * @return map -  request parameter 값 정보
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) { 

        Map<String, Object> parameterMap = new HashMap<String, Object>();
        HttpRequestWrapper req = new HttpRequestWrapper(request);
        Enumeration<?> enums = req.getParamNames();
        while(enums.hasMoreElements()) { 
            String paramName = (String)enums.nextElement();
            String[] parameters = req.getParameterValues(paramName);

            if(parameters.length > 1) { 
                parameterMap.put(paramName, parameters);
            }else{ 
                parameterMap.put(paramName,parameters[0]);
            }                     
        } 

        return parameterMap;
    }
    
    /**
     * 경고 메세지 후 메인 페이지로 이동
     * 
     * @param res -
     *            HttpServletResponse
     * @param msg -
     *            경고메세지
     * @return 스크립트 문자열
     */
    public static void alertMgntMsg(HttpServletResponse res, String msg) {

        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert(\"");
        sb.append(msg);
        sb.append("\");");
        sb.append("location.href='/ai/login.do';");
        sb.append("</script>");
        String retStr = sb.toString();

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
		try {
			out = res.getWriter();
			out.println(retStr);
			out.flush();
		}catch (IOException e) {
			Logger.getLogger("Util").error("Exception Occured");
		}
    }
    
    /**
     * 경고 메세지 후 페이지 닫음
     * 
     * @param res -
     *            HttpServletResponse
     * @param msg -
     *            경고메세지
     * @return 스크립트 문자열
     */
    public static void alertMsg(HttpServletResponse res, String msg) {

        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert(\"");
        sb.append(msg);
        sb.append("\");");
        sb.append("</script>");
        String retStr = sb.toString();

        pageWrite(res, retStr);
    }
   
    /**
     * 경고메세지 후 이전페이지로 이동
     * 
     * @param res -
     *            HttpServletResponse
     * @param msg -
     *            경고메세지
     * @return
     */
    public static void alertGotoBack(HttpServletResponse res, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert(\"");
        sb.append(msg);
        sb.append("\");");
        sb.append("history.go(-1);");
        sb.append("</script>");

        pageWrite(res, sb.toString());
    }
    
    /**
     * 업무관리 파일 경로 반환
     * 
     * @param request
     * @return
     */
    public static String getFileDir(HttpServletRequest request) {

        String retStr = "";
        String token = "/";
        retStr = Util.getWebRoot(request);
        if (retStr.indexOf("\\") != -1)
            token = "\\";

        retStr = retStr.substring(0, retStr.lastIndexOf(token,retStr.length() - 2)) + token + "upload";
        if(retStr!=null || !"".equals(retStr)){
        	retStr = retStr.replaceAll("<", "").replaceAll(">", "");
        }
        
        File dir = new File(retStr);
        
        if(!dir.exists()) {
        	dir.setExecutable(false, true);
            dir.setReadable(true);
            dir.setWritable(false, true);
            dir.mkdirs();
        }

        return retStr+token;
    }
    
    /**
     * 웹 루트 폴더의 절대경로를 구함
     * 
     * @param request
     * @return
     */
    public static String getWebRoot(HttpServletRequest request) {
        String retStr = "";
        retStr = request.getSession().getServletContext().getRealPath("/");
        return retStr;
    }
    
    /**
     * 세자리마다 ","를 찍어 String 리턴 예) 100000 => 100,000
     * 
     * @param value
     * @return
     */
    public static String insertComma(long value) {

        NumberFormat formatter = NumberFormat.getInstance();
        String money = formatter.format(value);

        return money;
    }

    public static String d3nation(String natn) {
		if ("Afghanistan".equals(natn)) {
			return "AFG";
		} else if ("Angola".equals(natn)) {
			return "AGO";
		} else if ("Albania".equals(natn)) {
			return "ALB";
		} else if ("United Arab Emirates".equals(natn)) {
			return "ARE";
		} else if ("Argentina".equals(natn)) {
			return "ARG";
		} else if ("Armenia".equals(natn)) {
			return "ARM";
		} else if ("Antarctica".equals(natn)) {
			return "ATA";
		} else if ("French Southern and Antarctic Lands".equals(natn)) {
			return "ATF";
		} else if ("Australia".equals(natn)) {
			return "AUS";
		} else if ("Austria".equals(natn)) {
			return "AUT";
		} else if ("Azerbaijan".equals(natn)) {
			return "AZE";
		} else if ("Burundi".equals(natn)) {
			return "BDI";
		} else if ("Belgium".equals(natn)) {
			return "BEL";
		} else if ("Benin".equals(natn)) {
			return "BEN";
		} else if ("Burkina Faso".equals(natn)) {
			return "BFA";
		} else if ("Bangladesh".equals(natn)) {
			return "BGD";
		} else if ("Bulgaria".equals(natn)) {
			return "BGR";
		} else if ("The Bahamas".equals(natn)) {
			return "BHS";
		} else if ("Bosnia and Herzegovina".equals(natn)) {
			return "BIH";
		} else if ("Belarus".equals(natn)) {
			return "BLR";
		} else if ("Belize".equals(natn)) {
			return "BLZ";
		} else if ("Bolivia".equals(natn)) {
			return "BOL";
		} else if ("Brazil".equals(natn)) {
			return "BRA";
		} else if ("Brunei".equals(natn)) {
			return "BRN";
		} else if ("Bhutan".equals(natn)) {
			return "BTN";
		} else if ("Botswana".equals(natn)) {
			return "BWA";
		} else if ("Central African Republic".equals(natn)) {
			return "CAF";
		} else if ("Canada".equals(natn)) {
			return "CAN";
		} else if ("Switzerland".equals(natn)) {
			return "CHE";
		} else if ("Chile".equals(natn)) {
			return "CHL";
		} else if ("China".equals(natn)) {
			return "CHN";
		} else if ("Ivory Coast".equals(natn)) {
			return "CIV";
		} else if ("Cameroon".equals(natn)) {
			return "CMR";
		} else if ("Democratic Republic of the Congo".equals(natn)) {
			return "COD";
		} else if ("Republic of the Congo".equals(natn)) {
			return "COG";
		} else if ("Colombia".equals(natn)) {
			return "COL";
		} else if ("Costa Rica".equals(natn)) {
			return "CRI";
		} else if ("Cuba".equals(natn)) {
			return "CUB";
		} else if ("Northern Cyprus".equals(natn)) {
			return "-99";
		} else if ("Cyprus".equals(natn)) {
			return "CYP";
		} else if ("Czech Republic".equals(natn)) {
			return "CZE";
		} else if ("Germany".equals(natn)) {
			return "DEU";
		} else if ("Djibouti".equals(natn)) {
			return "DJI";
		} else if ("Denmark".equals(natn)) {
			return "DNK";
		} else if ("Dominican Republic".equals(natn)) {
			return "DOM";
		} else if ("Algeria".equals(natn)) {
			return "DZA";
		} else if ("Ecuador".equals(natn)) {
			return "ECU";
		} else if ("Egypt".equals(natn)) {
			return "EGY";
		} else if ("Eritrea".equals(natn)) {
			return "ERI";
		} else if ("Spain".equals(natn)) {
			return "ESP";
		} else if ("Estonia".equals(natn)) {
			return "EST";
		} else if ("Ethiopia".equals(natn)) {
			return "ETH";
		} else if ("Finland".equals(natn)) {
			return "FIN";
		} else if ("Fiji".equals(natn)) {
			return "FJI";
		} else if ("Falkland Islands".equals(natn)) {
			return "FLK";
		} else if ("France".equals(natn)) {
			return "FRA";
		} else if ("Gabon".equals(natn)) {
			return "GAB";
		} else if ("United Kingdom".equals(natn)) {
			return "GBR";
		} else if ("Georgia".equals(natn)) {
			return "GEO";
		} else if ("Ghana".equals(natn)) {
			return "GHA";
		} else if ("Guinea".equals(natn)) {
			return "GIN";
		} else if ("Gambia".equals(natn)) {
			return "GMB";
		} else if ("Guinea Bissau".equals(natn)) {
			return "GNB";
		} else if ("Equatorial Guinea".equals(natn)) {
			return "GNQ";
		} else if ("Greece".equals(natn)) {
			return "GRC";
		} else if ("Greenland".equals(natn)) {
			return "GRL";
		} else if ("Guatemala".equals(natn)) {
			return "GTM";
		} else if ("Guyana".equals(natn)) {
			return "GUY";
		} else if ("Honduras".equals(natn)) {
			return "HND";
		} else if ("Croatia".equals(natn)) {
			return "HRV";
		} else if ("Haiti".equals(natn)) {
			return "HTI";
		} else if ("Hungary".equals(natn)) {
			return "HUN";
		} else if ("Indonesia".equals(natn)) {
			return "IDN";
		} else if ("India".equals(natn)) {
			return "IND";
		} else if ("Ireland".equals(natn)) {
			return "IRL";
		} else if ("Iran".equals(natn)) {
			return "IRN";
		} else if ("Iraq".equals(natn)) {
			return "IRQ";
		} else if ("Iceland".equals(natn)) {
			return "ISL";
		} else if ("Israel".equals(natn)) {
			return "ISR";
		} else if ("Italy".equals(natn)) {
			return "ITA";
		} else if ("Jamaica".equals(natn)) {
			return "JAM";
		} else if ("Jordan".equals(natn)) {
			return "JOR";
		} else if ("Japan".equals(natn)) {
			return "JPN";
		} else if ("Kazakhstan".equals(natn)) {
			return "KAZ";
		} else if ("Kenya".equals(natn)) {
			return "KEN";
		} else if ("Kyrgyzstan".equals(natn)) {
			return "KGZ";
		} else if ("Cambodia".equals(natn)) {
			return "KHM";
		} else if ("South Korea".equals(natn) || "Korea, Republic".equals(natn)) {
			return "KOR";
		} else if ("Kosovo".equals(natn)) {
			return "-99";
		} else if ("Kuwait".equals(natn)) {
			return "KWT";
		} else if ("Laos".equals(natn)) {
			return "LAO";
		} else if ("Lebanon".equals(natn)) {
			return "LBN";
		} else if ("Liberia".equals(natn)) {
			return "LBR";
		} else if ("Libya".equals(natn)) {
			return "LBY";
		} else if ("Sri Lanka".equals(natn)) {
			return "LKA";
		} else if ("Lesotho".equals(natn)) {
			return "LSO";
		} else if ("Lithuania".equals(natn)) {
			return "LTU";
		} else if ("Luxembourg".equals(natn)) {
			return "LUX";
		} else if ("Latvia".equals(natn)) {
			return "LVA";
		} else if ("Morocco".equals(natn)) {
			return "MAR";
		} else if ("Moldova".equals(natn)) {
			return "MDA";
		} else if ("Madagascar".equals(natn)) {
			return "MDG";
		} else if ("Mexico".equals(natn)) {
			return "MEX";
		} else if ("Macedonia".equals(natn)) {
			return "MKD";
		} else if ("Mali".equals(natn)) {
			return "MLI";
		} else if ("Myanmar".equals(natn)) {
			return "MMR";
		} else if ("Montenegro".equals(natn)) {
			return "MNE";
		} else if ("Mongolia".equals(natn)) {
			return "MNG";
		} else if ("Mozambique".equals(natn)) {
			return "MOZ";
		} else if ("Mauritania".equals(natn)) {
			return "MRT";
		} else if ("Malawi".equals(natn)) {
			return "MWI";
		} else if ("Malaysia".equals(natn)) {
			return "MYS";
		} else if ("Namibia".equals(natn)) {
			return "NAM";
		} else if ("New Caledonia".equals(natn)) {
			return "NCL";
		} else if ("Niger".equals(natn)) {
			return "NER";
		} else if ("Nigeria".equals(natn)) {
			return "NGA";
		} else if ("Nicaragua".equals(natn)) {
			return "NIC";
		} else if ("Netherlands".equals(natn)) {
			return "NLD";
		} else if ("Norway".equals(natn)) {
			return "NOR";
		} else if ("Nepal".equals(natn)) {
			return "NPL";
		} else if ("New Zealand".equals(natn)) {
			return "NZL";
		} else if ("Oman".equals(natn)) {
			return "OMN";
		} else if ("Pakistan".equals(natn)) {
			return "PAK";
		} else if ("Panama".equals(natn)) {
			return "PAN";
		} else if ("Peru".equals(natn)) {
			return "PER";
		} else if ("Philippines".equals(natn)) {
			return "PHL";
		} else if ("Papua New Guinea".equals(natn)) {
			return "PNG";
		} else if ("Poland".equals(natn)) {
			return "POL";
		} else if ("Puerto Rico".equals(natn)) {
			return "PRI";
		} else if ("North Korea".equals(natn)) {
			return "PRK";
		} else if ("Portugal".equals(natn)) {
			return "PRT";
		} else if ("Paraguay".equals(natn)) {
			return "PRY";
		} else if ("Qatar".equals(natn)) {
			return "QAT";
		} else if ("Romania".equals(natn)) {
			return "ROU";
		} else if ("Russia".equals(natn)) {
			return "RUS";
		} else if ("Rwanda".equals(natn)) {
			return "RWA";
		} else if ("Western Sahara".equals(natn)) {
			return "-99";
		} else if ("Saudi Arabia".equals(natn)) {
			return "SAU";
		} else if ("Sudan".equals(natn)) {
			return "SDN";
		} else if ("South Sudan".equals(natn)) {
			return "SDS";
		} else if ("Senegal".equals(natn)) {
			return "SEN";
		} else if ("Solomon Islands".equals(natn)) {
			return "SLB";
		} else if ("Sierra Leone".equals(natn)) {
			return "SLE";
		} else if ("El Salvador".equals(natn)) {
			return "SLV";
		} else if ("Somaliland".equals(natn)) {
			return "-99";
		} else if ("Somalia".equals(natn)) {
			return "SOM";
		} else if ("Republic of Serbia".equals(natn)) {
			return "SRB";
		} else if ("Suriname".equals(natn)) {
			return "SUR";
		} else if ("Slovakia".equals(natn)) {
			return "SVK";
		} else if ("Slovenia".equals(natn)) {
			return "SVN";
		} else if ("Sweden".equals(natn)) {
			return "SWE";
		} else if ("Swaziland".equals(natn)) {
			return "SWZ";
		} else if ("Syria".equals(natn)) {
			return "SYR";
		} else if ("Chad".equals(natn)) {
			return "TCD";
		} else if ("Togo".equals(natn)) {
			return "TGO";
		} else if ("Thailand".equals(natn)) {
			return "THA";
		} else if ("Tajikistan".equals(natn)) {
			return "TJK";
		} else if ("Turkmenistan".equals(natn)) {
			return "TKM";
		} else if ("East Timor".equals(natn)) {
			return "TLS";
		} else if ("Trinidad and Tobago".equals(natn)) {
			return "TTO";
		} else if ("Tunisia".equals(natn)) {
			return "TUN";
		} else if ("Turkey".equals(natn)) {
			return "TUR";
		} else if ("Taiwan".equals(natn)) {
			return "TWN";
		} else if ("United Republic of Tanzania".equals(natn)) {
			return "TZA";
		} else if ("Uganda".equals(natn)) {
			return "UGA";
		} else if ("Ukraine".equals(natn)) {
			return "UKR";
		} else if ("Uruguay".equals(natn)) {
			return "URY";
		} else if ("United States of America".equals(natn)) {
			return "USA";
		} else if ("Uzbekistan".equals(natn)) {
			return "UZB";
		} else if ("Venezuela".equals(natn)) {
			return "VEN";
		} else if ("Vietnam".equals(natn)) {
			return "VNM";
		} else if ("Vanuatu".equals(natn)) {
			return "VUT";
		} else if ("West Bank".equals(natn)) {
			return "PSE";
		} else if ("Yemen".equals(natn)) {
			return "YEM";
		} else if ("South Africa".equals(natn)) {
			return "ZAF";
		} else if ("Zambia".equals(natn)) {
			return "ZMB";
		} else if ("Zimbabwe".equals(natn)) {
			return "ZWE"; 
		} else {
			return "";
		}
    }
    
    
	
	/**
	 * FAST 검색 결과 ERROR 체크
	 * @param resultDocument			검색 결과 JDOM Document
	 * @return							false : 에러 없음, true : 에러
	 * @throws JDOMException
	 */
	public static boolean getFastErrorCheck(Document resultDocument) {

		boolean error = true;

		Element rootElement = resultDocument.getRootElement();

		@SuppressWarnings("unchecked")
		Iterator<Element> childrenIter = rootElement.getChildren().iterator();

		if (childrenIter.hasNext()) {
			Element childrenElement = childrenIter.next();


			if ("ERROR".equals(childrenElement.getName())) {
				error = true;
			} else {
				error = false;
			}
		}

		return error;
	}
	
	/**
	 * 개발 서버 체크
	 * @return
	 */
	public static boolean isDevEnv() {
		return isChkWasEnv("TEST_WAS_193"); 
	}
	
	/**
	 * 로컬 서버 체크
	 * @return
	 */
	public static boolean isLocalEnv() {
		return !isOperEnv() && !isDevEnv();
	}
	
	/**
	 * 운영 서버 체크
	 * @return
	 */
	public static boolean isOperEnv() {
		return isChkWasEnv("ns17was093") || isChkWasEnv("ns17was103"); 
	}
	
	/**
	 * 개발 / 운영 서버 체크
	 * @return
	 */
	private static boolean isChkWasEnv(String hostName) {
		Log log = LogFactory.getLog(Util.class);
		try {
			String wasHostName = InetAddress.getLocalHost().getHostName();
			
			if(hostName.equals(wasHostName)){
				return true;
			}else {
				return false;
			}
		} catch (UnknownHostException e) {
			log.error("운영서버/개발서버 체크시에 예기치 못한 에러발생", e);
			return false;
		}
	}
	
	public static String getNtisUrl(){
		String url = "https://www.ntis.go.kr";
		
//		if(!isOperEnv()){
//			url = "https://www18.ntis.go.kr";	
//		}
		return url;
	}
	
    /**
     * 
     * SSO ID 호출
     * 
     * @param request
     * @return
     * @version Update History
     * <pre>
     * ---------------------------------------------------------------------------------------
     * - 정정일자 :
     * - 정 정 자 :
     * - 정정내용 :
     * ---------------------------------------------------------------------------------------
     * </pre>
     */
    public static String getSsoId(HttpServletRequest request) {
  	  String sso_id = null;
  	  try{
//    	  CookieManager.setEncStatus(true);
//    	  SECode.setCookiePadding("_V42");
//    	  sso_id = CookieManager.getCookieValue(SECode.USER_ID, request);
  	  }catch(Exception e) {
  		  sso_id="";
  	  }
  	  
  	  return sso_id;
  	 }
    
    /**
	 * 
	 * 회원정보 조회(사용자ID, 항목명 )
	 * 
	 * @param userid
	 * @param column
	 * @return
	 * @throws UnsupportedEncodingException
	 * @version Update History
	 * <pre>
	 * ---------------------------------------------------------------------------------------
	 * - 정정일자 :
	 * - 정 정 자 :
	 * - 정정내용 :
	 * ---------------------------------------------------------------------------------------
	 * </pre>
	 */
	public static String getUserTHInfo(String userid, String column) throws IOException, Exception {
    	String returnVal = "";
		String authHost = "https://sso1.ntis.go.kr";
		String authURL = "/3rdParty/sso/userInfoApi.jsp";
		String reqStr = "user_id=" + userid + "&user_col=" + column;	
		String returnString = "";    //querySSO(authHost, authURL, reqStr);
		try {
			System.setProperty("https.protocols", "TLSv1.2");
			System.setProperty("jsse.enableSNIExtension", "false");
			
			URL url = new URL(authHost + authURL + "?" + reqStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			con.setConnectTimeout(1000);
			con.setReadTimeout(1000);
			con.setRequestMethod("GET");
			con.setDoOutput(false); 

			StringBuilder sb = new StringBuilder();
			String line;
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} 
			
			returnString = sb.toString();
			if(returnString != null && !"".equals(returnString)) {
//				returnVal = CookieManager.decryptWithSEED(URLDecoder.decode(returnString, "UTF-8"));
			}else {
				returnVal = "";
			}
			
			System.out.println("returnVal===" + returnVal);
		}catch(IOException iee) {			
			throw iee;
		}catch(Exception ee) {			
			throw ee;
		}
		return returnVal;
	}
	


/**
	 * 
	 * 회원권한 조회(사용자ID, 서비스코드 )
	 * 
	 * @param userid
	 * @param column
	 * @return
	 * @throws UnsupportedEncodingException
	 * @version Update History
	 * <pre>
	 * ---------------------------------------------------------------------------------------
	 * - 정정일자 :
	 * - 정 정 자 :
	 * - 정정내용 :
	 * ---------------------------------------------------------------------------------------
	 * </pre>
	 */
	public static String getUserRoleInfo(String userid, String role) throws IOException, Exception {
    	String returnVal = "";
		String authHost = "https://sso1.ntis.go.kr";
		String authURL = "/3rdParty/sso/userInfoApi_role.jsp";
		String reqStr = "user_id=" + userid + "&user_role_cd=" + role;	
		String returnString = "";    //querySSO(authHost, authURL, reqStr);
		try {
			System.setProperty("https.protocols", "TLSv1.2");
			System.setProperty("jsse.enableSNIExtension", "false");
			
			URL url = new URL(authHost + authURL + "?" + reqStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			con.setConnectTimeout(1000);
			con.setReadTimeout(1000);
			con.setRequestMethod("GET");
			con.setDoOutput(false); 

			StringBuilder sb = new StringBuilder();
			String line;
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} 
			returnString = sb.toString();
			if(returnString != null && !"".equals(returnString)) {
//				returnVal = CookieManager.decryptWithSEED(URLDecoder.decode(returnString, "UTF-8"));
			}else {
				returnVal = "";
			}
			
			System.out.println("returnVal===" + returnVal);
		}catch(IOException iee) {
			Logger.getLogger("Util").error("IO Exception Occured");
			throw iee;
		}catch(Exception ee) {
			Logger.getLogger("Util").error("Exception Occured");
			throw ee;
		}
		return returnVal;
	}
	
	public static HashMap<?, ?> bnoStatus(String bno) throws UnsupportedEncodingException {

		String apiURL = "http://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=D4CZSEqQpu9CjVh5sp2yz83D%2FhKN7QRNl%2BO4S%2BYbY5fyvfMPAVc1MBhaq0VKb%2F%2B4fZiZHYvu0sXrpmKKXEkH4A%3D%3D";

		String jsonData = "{  \"b_no\": [    \""+bno+"\"  ]}";
		HashMap<String, String> returnData = new HashMap();
		
		String responseBody = post(apiURL, jsonData);

		System.out.println(responseBody);

		try {

			JSONParser jsonParser = new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

			JSONArray message = (JSONArray) jsonObject.get("data");
			
			JSONObject result = (JSONObject) message.get(0); 
			
			returnData.put("bno", result.get("b_no").toString()); //사업자등록번호
			returnData.put("b_stt", result.get("b_stt").toString()); // 납세자상태(명칭): 01: 계속사업자, 02: 휴업자, 03: 폐업자
			returnData.put("clbnCd", result.get("b_stt_cd").toString()); // 납세자상태(코드): 01: 계속사업자, 02: 휴업자, 03: 폐업자
			returnData.put("b_stt_cd", result.get("b_stt_cd").toString()); // 휴폐업상태코드
			returnData.put("clbnDt", result.get("end_dt").toString()); // 폐업일
			returnData.put("end_dt", result.get("end_dt").toString()); // 폐업일
			returnData.put("tax_type", result.get("tax_type").toString()); // 과세유형메세지(명칭): 01:부가가치세 일반과세자, 02:부가가치세 간이과세자, 03:부가가치세 과세특례자, 04:부가가치세 면세사업자, 05:수익사업을 영위하지 않는 비영리법인이거나 고유번호가 부여된 단체,국가기관 등, 
			returnData.put("tax_type_cd", result.get("tax_type_cd").toString()); //과세유형메세지(코드):06:고유번호가 부여된 단체, 07:부가가치세 간이과세자(세금계산서 발급사업자), * 등록되지 않았거나 삭제된 경우: "국세청에 등록되지 않은 사업자등록번호입니다"
			returnData.put("utcc_yn", result.get("utcc_yn").toString()); //단위과세전환폐업여부(Y,N)
			returnData.put("tax_type_change_dt", result.get("tax_type_change_dt").toString()); //최근과세유형전환일자 
			returnData.put("invoice_apply_dt", result.get("invoice_apply_dt").toString()); //세금계산서적용일자 

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return returnData;
	}
	
	
	public static HashMap<?, ?> bnoValidate(String bno) throws UnsupportedEncodingException {
		
		String apiURL = "https://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey=D4CZSEqQpu9CjVh5sp2yz83D%2FhKN7QRNl%2BO4S%2BYbY5fyvfMPAVc1MBhaq0VKb%2F%2B4fZiZHYvu0sXrpmKKXEkH4A%3D%3D";
		
		String jsonData = "{ \"businesses\": [ {  \"b_no\":    \""+bno+"\"  }]}";
		HashMap<String, String> returnData = new HashMap();
		
		String responseBody = post(apiURL, jsonData);
		
		System.out.println(responseBody);
		
		try {
			
			JSONParser jsonParser = new JSONParser();
			
			JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
			
			JSONArray message = (JSONArray) jsonObject.get("data");
			
			JSONObject result = (JSONObject) message.get(0); 
			
			JSONArray status = (JSONArray) message.get(1); 
			
			JSONObject statusResult = (JSONObject) status.get(0); 
			
			returnData.put("bno", result.get("b_no").toString());	// 사업자등록번호
			returnData.put("start_dt", result.get("start_dt").toString()); // 개업일자 
			returnData.put("p_nm", result.get("p_nm").toString()); // 대표자성명1
			returnData.put("p_nm2", result.get("p_nm2").toString()); // 대표자성명2 - 대표자성명1 이 한글이 아닌 경우, 이에 대한 한글명
			returnData.put("b_nm", result.get("b_nm").toString()); // 상호 
			returnData.put("corp_no", result.get("corp_no").toString()); // 법인등록번호 
			returnData.put("b_sector", result.get("b_sector").toString()); // 주업태명 
			returnData.put("b_type", result.get("b_type").toString()); // 주종목명 
			
			returnData.put("bno", statusResult.get("b_no").toString()); //사업자등록번호
			returnData.put("b_stt", statusResult.get("b_stt").toString()); // 납세자상태(명칭): 01: 계속사업자, 02: 휴업자, 03: 폐업자
			returnData.put("clbnCd", statusResult.get("b_stt_cd").toString()); // 납세자상태(코드): 01: 계속사업자, 02: 휴업자, 03: 폐업자
			returnData.put("b_stt_cd", statusResult.get("b_stt_cd").toString()); // 휴폐업상태코드
			returnData.put("clbnDt", statusResult.get("end_dt").toString()); // 폐업일
			returnData.put("end_dt", statusResult.get("end_dt").toString()); // 폐업일
			returnData.put("tax_type", statusResult.get("tax_type").toString()); // 과세유형메세지(명칭): 01:부가가치세 일반과세자, 02:부가가치세 간이과세자, 03:부가가치세 과세특례자, 04:부가가치세 면세사업자, 05:수익사업을 영위하지 않는 비영리법인이거나 고유번호가 부여된 단체,국가기관 등, 
			returnData.put("tax_type_cd", statusResult.get("tax_type_cd").toString()); //과세유형메세지(코드):06:고유번호가 부여된 단체, 07:부가가치세 간이과세자(세금계산서 발급사업자), * 등록되지 않았거나 삭제된 경우: "국세청에 등록되지 않은 사업자등록번호입니다"
			returnData.put("utcc_yn", statusResult.get("utcc_yn").toString()); //단위과세전환폐업여부(Y,N)
			returnData.put("tax_type_change_dt", statusResult.get("tax_type_change_dt").toString()); //최근과세유형전환일자 
			returnData.put("invoice_apply_dt", statusResult.get("invoice_apply_dt").toString()); //세금계산서적용일자 
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return returnData;
	}
	
	private static String post(String apiUrl, String text) throws UnsupportedEncodingException {
		HttpURLConnection con = connect(apiUrl);
		String postParams = URLEncoder.encode(text, "UTF-8"); 
		try {
			con.setRequestMethod("POST");
			con.setRequestProperty("accept", "application/json");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			
			 try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(text.getBytes("UTF-8"));
                wr.flush();
            }
			// api 번역 
// 			
			//응답
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
				return readBody(con.getInputStream());
			} else { // 에러 응답
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			//================ TLS 1.2버전 사용하도록 추가 ===============
            System.setProperty("https.protocols", "TLSv1.2");
			System.setProperty("jsse.enableSNIExtension", "false");
			//=======================================================
//			sslTrustAllCerts();// ssl 회피
			URL url = new URL(apiUrl);
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.4.101", 3048));
			return (HttpURLConnection) url.openConnection(proxy);
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {

		try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(body, "UTF-8"))) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	/*
	 * titleList 제목열
	 * contentList 내용열 (제목항과 일치필요)
	 * title : 제목
	 * topString : 0,0 텍스트
	 */
	public static void excelDown (String cd, String nm, List<EgovMap> contentList, String title, String topString, HttpServletResponse response) throws IOException {

		try {
			
			/* WorkBook 생성 */
			SXSSFWorkbook xssfWorkBook = new SXSSFWorkbook();
			xssfWorkBook.setCompressTempFiles(true);
			/* Sheet 생성 */
			Sheet xssfSheet;
			xssfSheet = xssfWorkBook.createSheet(title);
			
			/* 검색 List 총 수 Row 생성 */
			Row xssfRow = xssfSheet.createRow(0);
			xssfRow.setHeight((short)400);
			
			/* Cell Style 정의 */
			CellStyle xssfCellStyleTop = xssfWorkBook.createCellStyle();
			
			Font xssfFontTotal = xssfWorkBook.createFont();
			xssfFontTotal.setFontHeightInPoints((short) 10);
			xssfCellStyleTop.setFont(xssfFontTotal);
			
			//Color white = new Color(Color.white);
			
			xssfCellStyleTop.setFillForegroundColor(HSSFColor.WHITE.index);
			xssfCellStyleTop.setFillPattern(xssfCellStyleTop.SOLID_FOREGROUND);
			
			/* Cell 출력 */
			Cell xssfCell = xssfRow.createCell(0);
			
			
			//xssfCell.setCellValue(nullToBlank(topString));
			xssfCell.setCellStyle(xssfCellStyleTop);
			
			/* Head Style 정의 */
			CellStyle xssfCellStyleHead = xssfWorkBook.createCellStyle();
			
			Font xssfFontHead = xssfWorkBook.createFont();
			xssfFontHead.setFontHeightInPoints((short) 11);
			xssfFontHead.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			xssfCellStyleHead.setFont(xssfFontHead);
			
			//Color lightGray = new XSSFColor(Color.LIGHT_GRAY);
			xssfCellStyleHead.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			xssfCellStyleHead.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			xssfCellStyleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			xssfCellStyleHead.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			xssfCellStyleHead.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleHead.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleHead.setBorderRight(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleHead.setBorderTop(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleHead.setWrapText(true);
			
			/* Head 설정 - 기관명 */
			xssfRow = xssfSheet.createRow(1);
			xssfRow.setHeight((short)400);
			
			List<Cell> list = new ArrayList<Cell>();
			String nm_list[]= null;
			String cd_list[]= null;
			if(!"".equals(nm)) {
				nm_list = nm.split(",");
			}
			if(!"".equals(cd)) {
				cd_list = CamelUtil.convert2CamelCase((String) cd).split(",");
			}
			
			
			int num = nm_list.length;
			 
			for (int i = 0; i < num; i++) {
				list.add(xssfRow.createCell(i));
			}
			
			for(int j=0; j<list.size(); j++) {
//				if(j==0) {
//					list.get(j).setCellValue(nullToBlank(topString));
//				}else {
					//EgovMap orgList = pjtItem_list[j]);
					String titleNm = nm_list[j];
					list.get(j).setCellValue(titleNm);
//				}
			}
			
			/* Column Width 설정 기본 - 항목*/

			for (int i = 0; i < list.size(); i++) {
				xssfSheet.setColumnWidth(i, 3000);
			}
			
			/* Column Width 설정 기본 내용  */
			for (int i = 1; i < list.size(); i++) {
				xssfSheet.setColumnWidth(i, 8000);
			}
			
			/* Cell Style 적용 */
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCellStyle(xssfCellStyleHead);
			}

			/* cell 스타일 적용 */
			CellStyle xssfCellStyleCenter = xssfWorkBook.createCellStyle();
			xssfCellStyleCenter.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			
			CellStyle xssfCellStyleLeft = xssfWorkBook.createCellStyle();
			xssfCellStyleLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			
			CellStyle xssfCellStyleRight = xssfWorkBook.createCellStyle();
			xssfCellStyleRight.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			
			
			/* Content Style 정의 */
			CellStyle xssfCellStyleContent = xssfWorkBook.createCellStyle();
			
			Font xssfFontContent= xssfWorkBook.createFont();
			xssfFontContent.setFontHeightInPoints((short) 9);
			xssfCellStyleContent.setFont(xssfFontContent);
			
			xssfCellStyleContent.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleContent.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleContent.setBorderRight(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleContent.setBorderTop(XSSFCellStyle.BORDER_THIN);
			xssfCellStyleContent.setWrapText(true);
			
			xssfRow = xssfSheet.createRow(2);
			xssfRow.setHeight((short)400);
			
			List<Cell> cellList = new ArrayList<Cell>();
			
			for (int i = 0; i < num; i++) {
				cellList.add(xssfRow.createCell(i));
			}
			
			for (int i = 0; i < cellList.size(); i++) {
				cellList.get(i).setCellStyle(xssfCellStyleContent);
			}
			
			
			/* Data List 출력 */
			int rownum = 3;
			
			String itemNm = "";	// 항목명
			String value = "";	// 항목값
			
			Iterator<String> k = contentList.get(0).keySet().iterator();
			HashMap<Object, Object> mapKey = new HashMap<>();
			int rnum = 0;
			while(k.hasNext()){
				String key = k.next();
				mapKey.put(rnum, key);
				rnum++;
				
			}
			
			for (int i = 0; i < contentList.size(); i++) {
				rnum= 0;
				xssfRow = xssfSheet.createRow(rownum);
				xssfRow.setHeight((short)600);
				
				//1번로우 부터 시작.
				for (int j = 0; j < cd_list.length; j++) {
				//컨텐츠 표시영역
				//cellList.size()
					String tempVal = "";
					for ( Object key : mapKey.values() ) {
						
						//선택한 항목과 조회한 목록 비교해서 출력
					    if(cd_list[j].equals(key)){
							if(String.valueOf(contentList.get(i).get(key)) == null  ) {
								tempVal = "";
							}else {
								tempVal = nullToBlank(contentList.get(i).get(key));
							}
						}
					cellList.get(j).setCellValue(tempVal);
					cellList.get(j).setCellStyle(xssfCellStyleContent);
				}
				}
					
				/* 행 초기화 */
				for (int s = 0; s < cellList.size(); s++) {
					cellList.set(s, xssfRow.createCell(s));
				}
				
				rownum++;
				
			}
			
			String today = DateTime.getDateString("-");
			String formName = new String("DataService".getBytes("UTF-8"),"ISO-8859-1");  //파일명
			
			/* 파일 다운로드 형식으로 출력 */
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + formName + "_"+ topString+"_"+ today + ".xlsx;");
			
			xssfWorkBook.write(response.getOutputStream());
			//서버에 저장
			
		} catch (IOException e) {
			throw e;
		}
		
	}
	
	/*파라미터 빈값 체크*/
	public static String paramCheck(String param, String str) {
		if(param == null || "".equals(param)) {
			return str;
		}else {
			return param;
		}
	}	
	
	/* 파일확장자 추출 */
	public static String getFileExtension(String str) {
		return (str.lastIndexOf(".") > 0) ? str.substring(str.lastIndexOf(".")) : str;
	}
	
	/* html 태그 제거: 예외로 둘 태그는 ,로 구분 ex) p,br */
	public static String removeHtmlTag(String str, String tag) {
		String result = str;
		String arr[] = tag.split(",");
		
		if(!"".equals(tag) && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				result = result.replaceAll("<"+arr[i]+">", "&lt;"+arr[i]+"&gt;");
				result = result.replaceAll("</"+arr[i]+">", "&lt;/"+arr[i]+"&gt;");
			}
		}
		
		if(str.length() > 0) {
			result = result.replaceAll(System.getProperty("line.separator"), " ");
			result = result.replaceAll("<(/)?([a-zA-Z]*)(\\s+[a-zA-Z]*=[^>]*)?(\\s+)*(/)?>", "");
			result = result.replaceAll("(?m)(?s)<!--(.*)-->", "");
			if(!"".equals(tag) && arr.length > 0) {
				result = result.replaceAll("&lt;", "<");
				result = result.replaceAll("&gt;", ">");
			}
			
			result = result.trim();
		}
		return result;
	}
	
	//제스퍼 라이브러리 경로 설정
	public static String getDefaultPath() {
		String defaultPath = "/data1/home/as/websrc_as/";
        if(Util.isLocalEnv()) {
        	defaultPath = "C:\\NTIS20/workspace/ntis-as/src/main/webapp/";
        }else if(Util.isDevEnv()){
        	defaultPath = "/data1/home/as/websrc_as/"; 
        }
        return defaultPath;
	}
	
	public static String getProperNmWithSuffix(String properNm) {
		if(Util.isLocalEnv()) {
			return properNm + ".local";
		}else if(Util.isDevEnv()) {
			return properNm + ".dev";
		}else {
			return properNm;
		}
	}
	
	/* Clob 타입 String 변환 */
	public static List convertClob(List paramList, String col) throws IOException, SQLException{
		List returnList = paramList;
		
		for (int i = 0; i < returnList.size(); i++) {
			EgovMap map = (EgovMap) returnList.get(i);
			Object targetData = map.get(col);
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(((Clob)targetData).getCharacterStream());
			String dummy = "";
			while((dummy = reader.readLine()) != null){
				buffer.append(dummy);
			}
			reader.close();
			map.put(col, buffer.toString());
		}
		
		return returnList;

	}
	
	/* Clob 타입 String 변환 */
	public static List convertClob(List paramList, String[] col) throws IOException, SQLException{
		List returnList = paramList;
		
			for (int i = 0; i < returnList.size(); i++) {
				for (int j = 0; j < col.length; j++) {
				try {
					EgovMap map = (EgovMap) returnList.get(i);
					Object targetData = map.get(col[j]);
					StringBuffer buffer = new StringBuffer();
					BufferedReader reader = new BufferedReader(((Clob)targetData).getCharacterStream());
					String dummy = "";
					while((dummy = reader.readLine()) != null){
						buffer.append(dummy);
					}
					reader.close();
					map.put(col[j], buffer.toString());
				} catch (NullPointerException e) {
					continue; 
				}
			}
		}
		
		return returnList;

	}
	
	
	/*서버에 따라 프로퍼티 값 load
	 * param : 프로퍼티명, ex) attachFile.real => attachFile
	 * */
	public static String  getDivProValue(String proName) {
		String propFileName = "config.properties";
		 Properties prop = new Properties();
		   InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(propFileName);
		    try {
		        if (inputStream != null) {
		            prop.load(inputStream);
		            
		            String devision = prop.getProperty("server.devision");
		            String id = proName+"."+devision;
		            
		            return prop.getProperty(id);
				    
		        } else {
		            throw new FileNotFoundException("프로퍼티 파일 '" + propFileName + "'을 resource에서 찾을 수 없습니다.");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null;
		    }
	}

	/*프로퍼티 값 load
	 * param : 프로퍼티명, ex) attachFile.real => attachFile.real
	 * */
	public static String  getProValue(String proName) {
		String propFileName = "config.properties";
		Properties prop = new Properties();
		InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
				
				String propertiesID = prop.getProperty(proName);
				
				return prop.getProperty(propertiesID);
				
			} else {
				throw new FileNotFoundException("프로퍼티 파일 '" + propFileName + "'을 resource에서 찾을 수 없습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}