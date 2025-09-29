package kr.go.distep.cmmn.service;

import java.io.IOException;
import java.sql.SQLException;

public interface CmmnService {
	
	public String selectUserNm(String ssoId) throws SQLException,IOException;
	
	/** 게시판 일련번호 조회 **/
	int selectSequenceBltnbrdSn() throws IOException, Exception;
	
	/** 첨부파일 일련번호 조회 **/
	int selectSequenceAttachFileSn() throws IOException, Exception;
	
}
