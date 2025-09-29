package kr.go.distep.main.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {

	@Value("${upload.path}")
    private String baseDir;
	
	@ResponseBody
	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,
	                     HttpServletRequest request,
	                     HttpServletResponse response) {
		try {
            // URL 인코딩된 파일 이름 디코딩
			String decodedFileName = URLDecoder.decode(fileName, "UTF-8");

			if (decodedFileName.startsWith("/upload")) {
			    decodedFileName = decodedFileName.substring("/upload".length());
			}

			// 중복 슬래시 없이 경로 결합
			Path fullPath = Paths.get(baseDir, decodedFileName.replace("/", File.separator));

			log.info("Download fullPath: {}", fullPath.toAbsolutePath());


            // 실제 서버 파일 경로 추출
            // baseDir 에서 경로 구분자 윈도우/리눅스 상관없이 처리
            String base = baseDir;
            if (!base.endsWith(File.separator)) {
                base += File.separator;
            }

            log.info("Download fullPath: {}", fullPath);

            File file = fullPath.toFile();
            
            if (!file.exists()) {
                log.warn("파일이 존재하지 않음: {}", fullPath);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            String resourceName = file.getName();

            // 응답 헤더 설정
            response.setContentType("application/octet-stream");
            response.setContentLengthLong(file.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" +
                    new String(resourceName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

            // 파일 전송
            Files.copy(file.toPath(), response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            log.error("파일 다운로드 오류", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
	// 다중 다운로드 (ZIP 파일)
	@RequestMapping(value = "/downloadMulti.do", method = RequestMethod.GET)
	public void downloadMultiple(@RequestParam(value = "fileNames") List<String> fileNames,
	                             HttpServletResponse response) {

	    String zipFileName = "downloaded_files.zip";
	    File tempZipFile = null;

	    try {
	        tempZipFile = createZipFile(fileNames, zipFileName);

	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" +
	                new String(zipFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
	        response.setContentLengthLong(tempZipFile.length());

	        try (FileInputStream fis = new FileInputStream(tempZipFile);
	             OutputStream os = response.getOutputStream()) {

	            byte[] buffer = new byte[4096];
	            int length;
	            while ((length = fis.read(buffer)) != -1) {
	                os.write(buffer, 0, length);
	            }

	            os.flush();
	        }

	    } catch (Exception e) {
	        log.error("다운로드 중 오류 발생", e);
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    } finally {
	        if (tempZipFile != null && tempZipFile.exists()) {
	            tempZipFile.delete();
	        }
	    }
	}

	// ZIP 파일 생성
	private File createZipFile(List<String> fileNames, String zipFileName) throws IOException {
	    String tempPath = baseDir + File.separator + "temp" + File.separator + UUID.randomUUID();
	    FileUtils.forceMkdir(new File(tempPath));

	    File tempZipFile = new File(tempPath, zipFileName);
	    Map<String, Integer> fileNameCountMap = new HashMap<>();

	    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempZipFile))) {
	        for (String filePath : fileNames) {
	            String decodedPath = URLDecoder.decode(filePath, "UTF-8");

	            if (decodedPath.startsWith("/upload")) {
	                decodedPath = decodedPath.substring("/upload".length());
	            }

	            String fullPath = baseDir + (baseDir.endsWith(File.separator) ? "" : File.separator)
	                    + decodedPath.replace("/", File.separator);

	            File file = new File(fullPath);
	            if (!file.isFile()) {
	                log.warn("존재하지 않는 파일: {}", file.getAbsolutePath());
	                continue;
	            }

	            String originalName = file.getName();
	            String zipEntryName = originalName;

	            if (fileNameCountMap.containsKey(originalName)) {
	                int count = fileNameCountMap.get(originalName) + 1;
	                fileNameCountMap.put(originalName, count);

	                int dotIndex = originalName.lastIndexOf(".");
	                String name = (dotIndex != -1) ? originalName.substring(0, dotIndex) : originalName;
	                String ext = (dotIndex != -1) ? originalName.substring(dotIndex) : "";
	                zipEntryName = name + "(" + count + ")" + ext;
	            } else {
	                fileNameCountMap.put(originalName, 1);
	            }

	            try (FileInputStream fis = new FileInputStream(file)) {
	                zos.putNextEntry(new ZipEntry(zipEntryName));

	                byte[] buffer = new byte[4096];
	                int length;
	                while ((length = fis.read(buffer)) != -1) {
	                    zos.write(buffer, 0, length);
	                }
	                zos.closeEntry();
	            } catch (IOException e) {
	                log.error("파일 복사 중 오류 발생: {}", file.getAbsolutePath(), e);
	            }
	        }
	    }

	    return tempZipFile;
	}

}
