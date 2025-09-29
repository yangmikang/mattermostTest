package kr.go.distep.cmmn.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.distep.cmmn.service.CmmnService;

@Service("cmmnService")
public class CmmnServiceImpl implements CmmnService{
	
	
	@Resource(name="cmmnMapper")
	private CmmnMapper cmmnMapper; 
	
	@Resource(name = "crProperties")
	Properties fileUploadProperties;

	@Override
	public String selectUserNm(String ssoId) throws SQLException, IOException {
		return cmmnMapper.selectUserNm(ssoId);
	}

	/** 게시판 일련번호 조회 **/
	public int selectSequenceBltnbrdSn() {
		return cmmnMapper.selectSequenceBltnbrdSn();
	}

	/** 첨부파일 일련번호 조회 **/
	public int selectSequenceAttachFileSn() {
		return cmmnMapper.selectSequenceAttachFileSn();
	}

}
