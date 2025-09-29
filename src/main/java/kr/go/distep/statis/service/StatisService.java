package kr.go.distep.statis.service;

import java.util.List;
import java.util.Map;

import kr.go.distep.statis.vo.StatisVo;

public interface StatisService {

	/*
	 * List<StatisVo> getMonthlyTotalRequests();
	 * 
	 * List<StatisVo> getDailyTotalRequests();
	 * 
	 * List<StatisVo> getDailyRequestsByRole();
	 * 
	 * List<StatisVo> getMonthlyRequestsByRole();
	 */

	List<StatisVo> getStatis(Map<String, Object> map);

}
