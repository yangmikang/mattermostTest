package kr.go.distep.statis.vo;

import java.util.Date;

import lombok.Data;

@Data
public class StatisVo {
	    private String role;         
	    private String logDate;      
	    private String logMonth;      
	    private int requestCount;    
	    private int rnum;
	    private Date startDate;
	    private Date endDate;
	    private String statType;
	    private int cnt;
	    private String date_group;
	    private String group_key;
	    

}
