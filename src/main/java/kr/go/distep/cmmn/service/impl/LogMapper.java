package kr.go.distep.cmmn.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.go.distep.cmmn.vo.LogVO;

@Mapper("logMapper")
public interface LogMapper {

    void insertRequestLog(LogVO log);
}