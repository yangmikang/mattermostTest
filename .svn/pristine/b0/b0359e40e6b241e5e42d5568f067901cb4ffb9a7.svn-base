package kr.go.distep.cmmn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.distep.cmmn.service.LogService;
import kr.go.distep.cmmn.vo.LogVO;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void insertRequestLog(LogVO logVO) {
        logMapper.insertRequestLog(logVO);
    }
}