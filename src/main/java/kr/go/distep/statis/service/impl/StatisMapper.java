package kr.go.distep.statis.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.go.distep.statis.vo.StatisVo;

@Mapper
public interface StatisMapper {

	List<StatisVo> getMonthlyTotalRequests();

	List<StatisVo> getDailyTotalRequests();

	List<StatisVo> getDailyRequestsByRole();

	List<StatisVo> getMonthlyRequestsByRole();

	List<StatisVo> getStatis(Map<String, Object> map);

}
