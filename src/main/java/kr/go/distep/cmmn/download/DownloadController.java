package kr.go.distep.cmmn.download;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.go.distep.cmmn.util.StringUtil;
import kr.go.distep.cmmn.util.Util;
import kr.go.distep.main.service.MainService;


@Controller
public class DownloadController {
	/** logger */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /*config.properties */
	@Resource(name = "configProperties")  
	Properties configProperties;
	
	@Resource(name="mainService")
	private MainService mainService; 
    
    
	@RequestMapping(value="/download.do")
	public void download(ModelMap model , HttpServletRequest request, HttpServletResponse response) throws Exception,FileNotFoundException,IOException {
		
		String fileSaveName = StringUtil.validatePath(request.getParameter("fileSaveName"));
		String fileRealName = StringUtil.toTEXT(request.getParameter("fileRealName"));
		
		logger.info("getParameter fileSaveName : " + fileSaveName);
		logger.info("getParameter fileRealName : " + fileRealName);
		
		try {
			logger.info("doDownLoad Start  2: ");
			if(fileSaveName != null && !"".equals(fileSaveName)){
				StringUtil.cleanXSS(fileSaveName);
				
				String filePath = Util.getDivProValue("attachFile");
				filePath = filePath.concat(fileSaveName);
				
				fileRealName = StringUtil.toAllTrim(StringUtil.getScriptExtensionToText(fileRealName));
				
				if (logger.isDebugEnabled()) {
					logger.info("파일명 : " + fileRealName);
				}
				
				if (logger.isDebugEnabled()) {
					logger.info("파일경로 : " + filePath);
				}
				
				File file = new File(filePath);
					
				if (!file.exists() || !file.canRead()) {
					StringUtil.htmlPrintHistoryBack(response, "파일이 존재하지 않습니다.");
					return;
				}
				
	            ServletOutputStream outStream = null;
	            FileInputStream inputStream = null;
	
	            outStream = response.getOutputStream();
	            inputStream = new FileInputStream(file);
	            fileRealName = URLEncoder.encode(fileRealName, "UTF-8").replaceAll("\\+", "%20");
	            try {
		            response.setContentType("application/octet-stream");
					response.setContentLength((int) file.length());
					response.setHeader("Content-Type", "application/octet-stream;");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileRealName + "\"");
					response.setHeader("Content-Length", String.valueOf(file.length()));
					response.setHeader("Content-Transfer-Encoding", "binary;");
		
		            byte[] outByte = new byte[4096];
		            while(inputStream.read(outByte, 0, 4096) != -1){
		            	outStream.write(outByte, 0, 4096);
		            }
	            } catch (IOException e) {
	            	throw new IOException();
	            } finally {
		            inputStream.close();
		            outStream.flush();
		            outStream.close();
	            }
			}

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}catch (IOException e) {
			throw new IOException();
		}    	
		
	}

}
