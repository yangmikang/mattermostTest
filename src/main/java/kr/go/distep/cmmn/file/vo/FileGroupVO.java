package kr.go.distep.cmmn.file.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FileGroupVO {
	private long fileGroupNo;
	private Date fileRegDate;
	
	//파일그룹 : 파일상세 = 1 : N
	private List<FileDetailVO> fileDetailVOList;
}
