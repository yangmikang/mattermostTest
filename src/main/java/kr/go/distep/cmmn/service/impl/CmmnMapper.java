package kr.go.distep.cmmn.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("cmmnMapper")
public interface CmmnMapper {
	
	public String selectUserNm(String ssoId);
	
	/** 게시판 일련번호 조회 **/
	public int selectSequenceBltnbrdSn();
	
	/** 첨부파일 일련번호 조회 **/
	public int selectSequenceAttachFileSn();
	
	/** 분석데이터 기타 알림 메세지 **/
	public void insertEtcUserAppldataMsg(String msg);
	

}