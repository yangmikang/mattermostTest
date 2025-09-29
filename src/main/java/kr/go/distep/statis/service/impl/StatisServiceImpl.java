package kr.go.distep.statis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.distep.statis.service.StatisService;
import kr.go.distep.statis.vo.StatisVo;

@Service
public class StatisServiceImpl implements StatisService {

	@Autowired
	StatisMapper statisMapper;
	
	/*
	 * @Override public List<StatisVo> getMonthlyTotalRequests() { return
	 * this.statisMapper.getMonthlyTotalRequests(); }
	 * 
	 * @Override public List<StatisVo> getDailyTotalRequests() { return
	 * this.statisMapper.getDailyTotalRequests(); }
	 * 
	 * @Override public List<StatisVo> getDailyRequestsByRole() { return
	 * this.statisMapper.getDailyRequestsByRole(); }
	 * 
	 * @Override public List<StatisVo> getMonthlyRequestsByRole() { return
	 * this.statisMapper.getMonthlyRequestsByRole(); }
	 */

	@Override
	public List<StatisVo> getStatis(Map<String, Object> map) {
		return this.statisMapper.getStatis(map);
	}

}
