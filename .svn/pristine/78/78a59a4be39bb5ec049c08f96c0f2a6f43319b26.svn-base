
package kr.go.distep.cmmn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
* 날짜 관련 함수 모음 Util
* 
* DateTime Class 사용예
* 
* <table>
* <tr>
* <td>올해는 :DateTime.getYear()</td>
* </tr>
* <tr>
* <td>이번달은 :DateTime.getMonth()</td>
* </tr>
* <tr>
* <td>오늘은 :DateTime.getDay()</td>
* </tr>
* <tr>
* <td>요일은 :DateTime.getDayOfWeek()</td>
* </tr>
* <tr>
* <td>현재시간 :DateTime.getHour()</td>
* </tr>
* <tr>
* <td>현재분 :DateTime.getMinute()</td>
* </tr>
* <tr>
* <td>현재 초 :DateTime.getSecond()</td>
* </tr>
* <tr>
* <td>현재 날짜 :DateTime.getDateString()</td>
* </tr>
* <tr>
* <td>현재 날짜 :DateTime.getDateString(".")</td>
* </tr>
* <tr>
* <td>현재 날짜 :DateTime.getDateString("/")</td>
* </tr>
* <tr>
* <td>시간스트링 :DateTime.getTimeString()</td>
* </tr>
* <tr>
* <td>TimeStamp :DateTime.getTimeStampString()</td>
* </tr>
* <tr>
* <td>DateTimeMin :DateTime.getDateTimeMin()</td>
* </tr>
* <tr>
* <td>DateTimeString :DateTime.getDateTimeString()</td>
* </tr>
* <tr>
* <td>윤년판단 :DateTime.checkEmbolism(2002)</td>
* </tr>
* <tr>
* <td>일수 구하기 :DateTime.getMonthDate("2002","2")</td>
* </tr>
* </table>
*/
public class DateTime {

	public static String dateSep = "-";

	public static String timeSep = ":";

	private static String[] day = { "일", "월", "화", "수", "목", "금", "토" };

	public DateTime() {
	}

	/**
	 * 
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd
	 * HH:mm:ss");
	 * 
	 * @param java.lang.String
	 *            pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your
	 *         pattern.
	 */
	public static int getNumberByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return Integer.parseInt(dateString);
	}
	
	/**
	 * 
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd
	 * HH:mm:ss");
	 * 
	 * @param java.lang.String
	 *            pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your
	 *         pattern.
	 */
	public static String getNumberByPatternString(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return dateString;
	}

	/**
	 * 현재날짜의 년도를 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static int getYear() {
		return getNumberByPattern("yyyy");
	}
	
	/**
	 * 현재날짜의 년도를 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getYearString() {
		return getNumberByPatternString("yyyy");
	}

	/**
	 * 현재날짜의 월을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static int getMonth() {
		return getNumberByPattern("MM");
	}
	
	/**
	 * 현재날짜의 월을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getMonthString() {
		return getNumberByPatternString("MM");
	}

	/**
	 * 현재날짜의 일자를 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static int getDay() {
		return getNumberByPattern("dd");
	}
	
	/**
	 * 현재날짜의 일자를 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getDayString() {
		return getNumberByPatternString("dd");
	}

	/**
	 * 주중 요일을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getDayOfWeek() {
		Calendar c = Calendar.getInstance();
		return day[c.get(java.util.Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 현재 시각의 시간를 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static int getHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 현재 시각의 분을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static int getMinute() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 현재시각의 초을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static int getSecond() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.SECOND);
	}

	/**
	 * 현재 YYYY-MM-DD 형태의 스트링을 구하는 Method
	 * 
	 * @param pattern
	 *            String
	 * @exception
	 * @author
	 */
	public static String getDateString() {
		String year = Integer.toString(getYear());
		String month = Integer.toString(getMonth());
		String day = Integer.toString(getDay());
		if (month.length() == 1){
//			month = month.concat("0" + month);
			month = new String("0" + month);
		}
		if (day.length() == 1){
//			day = day.concat("0" + day);
			day = new String("0" + day);
		}

		return year + "-" + month + "-" + day;
	}

	public static String getYearMonthString() {
		String year = Integer.toString(getYear());
		String month = Integer.toString(getMonth());

		year = year.substring(2);
		if (month.length() == 1){
//			month = month.concat("0" + month);
			month = new String("0" + month);
		}

		return year + month;
	}

	/**
	 * 현재 날짜를 Pattern에 의해 구하는 Method Pattern 값에 따른 결과 반영
	 * 
	 * @param pattern
	 *            String
	 * @exception
	 * @author
	 */
	public static String getDateString(String pattern) {
		String year = Integer.toString(getYear());
		String month = Integer.toString(getMonth());
		String day = Integer.toString(getDay());

		if (month.length() == 1){
//			month = month.concat("0" + month);
			month = new String("0" + month);
		}
		if (day.length() == 1){
//			day = day.concat("0" + day);
			day = new String("0" + day);
		}

		return year + pattern + month + pattern + day;
	}

	/**
	 * 오전,오후 HH:MI:SS 형태의 스트링을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getTimeString() {
		String hour = Integer.toString(getHour());
		String min = Integer.toString(getMinute());
		String sec = Integer.toString(getSecond());
		
		String apm = null;
		int hourInt = Integer.parseInt(hour);

		if ((hourInt - 12) < 0) {
			apm = "오전 ";
		} else {
			apm = "오후 ";
			hourInt -= 12;
			hour = String.valueOf(hourInt);
		}

		if (hour.length() == 1) {
//			hour = hour.concat("0" + hour);
			hour = new String("0" + hour);
		}
		if (min.length() == 1) {
//			min = min.concat("0" + min);
			min = new String("0" + min);
		}
		if (sec.length() == 1) {
//			sec = sec.concat("0" + sec);
			sec = new String("0" + sec);
		}

		return apm + hour + timeSep + min + timeSep + sec;

	}

	/**
	 * YYYY-MM-DD (오전오후 )HH:MI:SS 형태의 스트링을 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getTimeStampString() {
		return getDateString() + " " + getTimeString();
	}

	/**
	 * YYYYMMDDHHMI 형태의 스트링를 구하는 Method
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getDateTimeMin() {
		String month = Integer.toString(getMonth());
		String day = Integer.toString(getDay());
		String hour = Integer.toString(getHour());
		String min = Integer.toString(getMinute());

		if (month.length() == 1) {
//			month = month.concat("0" + month);
			month = new String("0" + month);
		}
		if (day.length() == 1) {
//			day = day.concat("0" + day);
			day = new String("0" + day);
		}
		if (hour.length() == 1) {
//			hour = hour.concat("0" + hour);
			hour = new String("0" + hour);
		}
		if (min.length() == 1) {
//			min = min.concat("0" + min);
			min = new String("0" + min);
		}
		return getYear() + month + day + hour + min;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 현재 형태로 날짜 반환
	 * 
	 * @param
	 * @exception
	 * @author
	 */
	public static String getDateTimeString() {
		String month = Integer.toString(getMonth());
		String day = Integer.toString(getDay());
		String hour = Integer.toString(getHour());
		String min = Integer.toString(getMinute());
		String sec = Integer.toString(getSecond());

		if (month.length() == 1) {
//			month = month.concat("0" + month);
			month = new String("0" + month);
		}
		if (day.length() == 1) {
//			day = day.concat("0" + day);
			day = new String("0" + day);
		}
		if (hour.length() == 1) {
//			hour = hour.concat("0" + hour);
			hour = new String("0" + hour);
		}
		if (min.length() == 1) {
//			min = min.concat("0" + min);
			min = new String("0" + min);
		}
		if (sec.length() == 1) {
//			sec = sec.concat("0" + sec);
			sec = new String("0" + sec);
		}
		return getYear() + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
	}

	/***************************************************************************
	 * 주어진 YYYY-MM-DD 형식을 주어진 pattern 으로 바꾸는 Method exam : 2000/02/12 => 2000-02-12
	 * 예) getParseDateString("2010/10/12", "-")
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getParseDateString(String dateTime, String pattern) {
		if (dateTime != null) {
			String year = dateTime.substring(0, 4);
			String month = dateTime.substring(5, 7);
			String day = dateTime.substring(8, 10);

			return year + pattern + month + pattern + day;
		} else {
			return "";
		}
	}
	
	/**
	 * 주어진 YYYY-MM-DD 형식을 영문 표기법 DD-MM-YYYY형식의 주어진 pattern 으로 바꾸는 Method exam : 2000/02/12 => 12-02-2000
	 * 예) getParseDateString("2010/10/12", "-")
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getParseDateStringEnglish(String dateTime, String pattern) {
		if (StringUtil.isNotNull(dateTime)) {
			String year = dateTime.substring(0, 4);
			String month = dateTime.substring(5, 7);
			String day = dateTime.substring(8, 10);

			return day + pattern + month + pattern + year;
		} else {
			return "";
		}
	}

	/**
	 * 주어진 YYYY-MM-DD 형식을 주어진 pattern 으로 바꾸는 Method exam : Date => 2000-02-12
	 * 예) getParseDateString(Date, "yyyy-MM-dd")
	 * 
	 * @param date			Date
	 * @param pattern		Pattern
	 * @return
	 */
	public static String getParseDateString(Date date, String pattern) {
		if (date == null || pattern == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat(pattern,Locale.KOREA);
		return sdf.format(date);
	}

	/**
	 * YYYYMMDD 형식을 주어진 pattern 으로 바꾸는 Method
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getParseDateString2(String dateTime, String pattern) {
		if (dateTime != null) {
			String year = dateTime.substring(0, 4);
			String month = dateTime.substring(4, 6);
			String day = dateTime.substring(6, 8);

			return year + pattern + month + pattern + day;
		} else {
			return "";
		}
	}

	/**
	 * YYYY-MM-DD HH:MM:SS 형식을 주어진 pattern 으로 바꾸는 Method
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getParseDateTimeString(String dateTime, String pattern) {
		if (dateTime != null) {
			String year = dateTime.substring(0, 4);
			String month = dateTime.substring(5, 7);
			String day = dateTime.substring(8, 10);
			String hour = dateTime.substring(11, 13);
			String minute = dateTime.substring(14, 16);
			String second = dateTime.substring(17, 19);
			int hourInt = Integer.parseInt(hour);
			String apm = null;

			if (month.length() == 1) {
//				month = month.concat("0" + month);
				month = new String("0" + month);
			}

			if (day.length() == 1) {
//				day = day.concat("0" + day);
				day = new String("0" + day);
			}

			if ((hourInt - 12) < 0) {
				apm = "오전 ";
			} else {
				apm = "오후 ";
				hourInt -= 12;
				hour = String.valueOf(hourInt);
			}

			if (hour.length() == 1) {
//				hour = hour.concat("0" + hour);
				hour = new String("0" + hour);
			}

			if (minute.length() == 1) {
//				minute = minute.concat("0" + minute);
				minute = new String("0" + minute);
			}

			if (second.length() == 1) {
//				second = second.concat("0" + second);
				second = new String("0" + second);
			}

			return year + pattern + month + pattern + day + " " + apm + hour
					+ ":" + minute + ":" + second;
		} else {
			return "";
		}
	}

	/**
	 * 주어진 년도가 윤년인지를 구하는 Method
	 * 
	 * @param year
	 * @exception
	 * @author
	 */
	public static boolean checkEmbolism(int year) {
		int remain = 0;
		int remain1 = 0;
		int remain2 = 0;

		remain = year % 4;
		remain1 = year % 100;
		remain2 = year % 400;

		// the ramain is 0 when year is divided by 4;
		if (remain == 0) {
			// the ramain is 0 when year is divided by 100;
			if (remain1 == 0) {
				// the remain is 0 when year is divided by 400;
				if (remain2 == 0)
					return true;
				else
					return false;
			} else
				return true;
		}
		return false;
	}

	/**
	 * 주어진 년,월의 일수를 구하는 Method
	 * 
	 * @param year
	 * @param month
	 */
	public static int getMonthDate(String year, String month) {
		int[] dateMonth = new int[12];

		dateMonth[0] = 31;
		dateMonth[1] = 28;
		dateMonth[2] = 31;
		dateMonth[3] = 30;
		dateMonth[4] = 31;
		dateMonth[5] = 30;
		dateMonth[6] = 31;
		dateMonth[7] = 31;
		dateMonth[8] = 30;
		dateMonth[9] = 31;
		dateMonth[10] = 30;
		dateMonth[11] = 31;

		if (checkEmbolism(Integer.parseInt(year))) {
			dateMonth[1] = 29;
		}

		int day = dateMonth[Integer.parseInt(month) - 1];

		return day;
	}
	
	/**
	 * 두 날짜의 차이를 long 형으로 표현
	 * @param begin
	 * @param end
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static long diffOfHours(String begin, String end, String pattern) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern,Locale.KOREA);
		
		Date beginDate = formatter.parse(begin);
		Date endDate = formatter.parse(end);
		
		return (endDate.getTime() - beginDate.getTime()) / (60 * 60 * 1000);
	}
	
	/**
	 * 현재 날짜에 지정된 필드에 날짜 더하기 
	 * 사용법 : getAddDate(Calendar.YEAR, 2)
	 * @param field		Calendar Field
	 * @param amount	증가 수
	 * @return			증가된 날짜 Date
	 */
	public static Date getAddDate(int field,int amount) {
		
		Calendar c = Calendar.getInstance();
		c.add(field, amount);
		
		return c.getTime();
	}
	
	/**
	 * 현재 날짜에 지정된 필드에 날짜 더하기 
	 * 사용법 : getAddDateString(Calendar.YEAR, 2)
	 * @param field		Calendar Field
	 * @param amount	증가 수
	 * @return			증가된 날짜 문자열
	 */
	public static String getAddDateString(int field,int amount) {
		
		Calendar c = Calendar.getInstance();
		c.add(field, amount);
		
		return getParseDateString(c.getTime(), "yyyy-MM-dd");
	}
}
