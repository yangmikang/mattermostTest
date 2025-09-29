package kr.go.distep.calendar.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.go.distep.cmmn.file.vo.FileGroupVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CalendarVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String eventTitle;
	private String eventDescription;

    private Date startDateObj;
    private Date endDateObj;
	private String startDatetime;
	private String endDatetime;
    private String clientTimeZone;

    private String eventTz;

	private String organizer;
	private String location;
	private String contact;
	private String uploadThumbnail;
	private String externalLink;
	private String eventStatus;
	private long fileGroupNo;
	private MultipartFile[] uploadFiles;
	
	private FileGroupVO fileGroupVO;
	
	private List<CalendarFileVO> fileList;
	private String deletedFileIds; 
	
	private MultipartFile thumbnailFile;

	
	private String userId;
	private String username;
}
