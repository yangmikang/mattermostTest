package kr.go.distep.cmmn.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.go.distep.cmmn.util.Util;

@Controller
public class FileController {

	@Resource(name = "crProperties")
	Properties fileUploadProperties;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    
	@RequestMapping(value = "/fileDownLoad.do")
	public void fileDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Map<String, Object> params = Util.getParameterMap(request);

        if(Util.nullToBlank(params.get("fileNamed")).equals("")) {
            LOGGER.error("parameter fileNamed is null");
        } else {
            String fileDir  = fileUploadProperties.getProperty("file.upload.path");            
            String fileName = (String)params.get("fileNamed");
            
            if("".equals(fileName)) {
                LOGGER.error( "fileName is empty");
            } else {

                InputStream in = null;
                ServletOutputStream out = null;
                File file = null;

                try {
                    String fileFullPath = fileDir + "/" +  fileName;
                    //LOGGER.info("download file full path : " + fileFullPath);
                    file = new File(String.format(fileFullPath));
                    if(!file.exists()) {
                        Util.alertGotoBack(response, "선택하신 파일은 존재하지 않습니다.");
                    }else{
                    	in    = new FileInputStream(file);
                    	out = response.getOutputStream();

                        response.setContentType("application/download; charset=utf-8");
                        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
                        response.setHeader("Content-Transfer-Encoding", "binary");
                        response.setHeader("Content-Length", String.valueOf(file.length()));

                        byte b[] = new byte[1024];
                        int leng = in.read(b);
                        
                        while( leng > 0 ) {
                            out.write(b,0,leng);
                            leng = in.read(b);
                        }
                    }                    

                }catch(SecurityException | IOException e) {
                    LOGGER.error("Exception Occured");
                } finally {
                	if(in != null) in.close();
                	if(out != null) out.close();
                }
            }
        }
        
	}
}
