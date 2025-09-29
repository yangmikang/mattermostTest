package kr.go.distep.main.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface MainService {

	public String selectUserNm(String ssoId) throws SQLException,IOException;
	
	/** 게시판 등록 **/
	public void insertKabBoard(HashMap<String, Object> map) throws SQLException,IOException;
	
	/** 게시판 수정 **/
	public void updateKabBoard(HashMap<String, Object> map) throws SQLException,IOException;
	
	/** 게시판 삭제 **/
	public void deleteKabBoard(HashMap<String, Object> map) throws SQLException,IOException;
	
	/** 게시글 목록 **/
	public List<EgovMap> selectBoardList(Map param) throws SQLException,IOException;
	
	/** 게시글 Count **/
	public int selectBoardListCount(Map param) throws SQLException,IOException;
	
	/** 게시글 조회수 수정 **/
	public void updateBbsHits(String bbsId) throws SQLException,IOException;
	
	/** 게시글 상세 **/
	public EgovMap selectBoardDetail(Map param) throws SQLException,IOException;
	
	/** 덧글 목록 **/
	public List<EgovMap> selectCommentList(Map param) throws SQLException,IOException;
	
	/** 덧글 등록 **/
	public void insertComment(Map param) throws SQLException,IOException;
	
	/** 덧글 삭제 **/
	public void updateCommentDeleteAt(String commentId) throws SQLException,IOException;
	
	/** 덧글 수정 **/
	public void updateComment(Map param) throws SQLException,IOException;
	
	/** 좋아요 등록 **/
	public void insertKabLike(Map param) throws SQLException,IOException;
	
	/** 좋아요 삭제 **/
	public void deleteKabLikeOne(Map param) throws SQLException,IOException;
	
	/** 게시판 삭제에 따른 좋아요 삭제 **/
	public void deleteKabLikeAll(HashMap<String, Object> map) throws SQLException,IOException;
	
	/** 좋아요 체크 **/
	public int selectChkKabLike(Map param) throws SQLException,IOException;
	
	/** 첨부파일 등록 **/
	public void insertKabAttachFile(HashMap<String, Object> map, MultipartHttpServletRequest multipartRequest) throws SQLException,IOException;
	
	/** 첨부파일 목록 **/
	public List<EgovMap> selectKabAttachFileList(String bbsId) throws SQLException,IOException;
	
	/** 첨부파일  삭제 **/
	public void deleteKabAttachFile(HashMap<String, Object> map) throws SQLException,IOException;

	public boolean sendMailWithAttachments(String fromEmail, String toEmail, String subject, String content,
			String userName, MultipartFile[] attachments);

	public List<HashMap<String, Object>> getMenuList();
}
