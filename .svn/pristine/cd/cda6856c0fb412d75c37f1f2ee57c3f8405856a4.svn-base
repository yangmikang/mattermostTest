package kr.go.distep.calendar.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.go.distep.calendar.vo.CalendarFileVO;
import kr.go.distep.calendar.vo.CalendarVO;

@Mapper("calendarMapper")
public interface CalendarMapper {
	int insertCalendar(CalendarVO calendar);

	int updateCalendar(CalendarVO calendar);

	int deleteCalendar(int id);

	CalendarVO selectCalendar(int id);

	List<CalendarVO> selectCalendarList();

	// 파일 관련
	int insertCalendarFile(CalendarFileVO file);

	int deleteCalendarFilesByCalendarId(int calendarId);

	List<CalendarFileVO> selectFilesByCalendarId(int calendarId);

	int getTotal(Map<String, Object> getTotal);

	List<CalendarVO> list(Map<String, Object> map);

	int updateTitle(CalendarVO vo);

	int updateDates(CalendarVO vo);

	CalendarVO detail(CalendarVO calendar);

	int removeCalendar(CalendarVO calendar);

	List<CalendarVO> mainlist(Map<String, Object> map);

	String getBoarUpdateAuth(HashMap<String, String> authParam);

	String getBoarFileAuth(HashMap<String, String> authParam);
}
