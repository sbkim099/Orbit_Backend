package com.study.app.domains.file;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileServ;
	
	// 프로필 사진 출력용 *소용량 전용으로 큰 파일, 게시판 다운로드 사용 xx
	@GetMapping("/profile/view")
	public ResponseEntity<byte[]> profileImage(@RequestParam("sysname") String sysname) {
		byte[] imgByte = fileServ.profileImage(sysname);
		
		if(imgByte == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_PNG)
				.body(imgByte);
		// -> 다운로드가 아닌 출력만 하는 이미지를 알려주기 위해 contentType을 고정하여 발송.
	}
	
	// 첨부파일 다운로드
    @GetMapping("/download/{sysname}")
    public ResponseEntity<Resource> download(@PathVariable String sysname) throws Exception {
        byte[] fileBytes = fileServ.getFileBytes(sysname);
        if (fileBytes == null) {
            return ResponseEntity.notFound().build();
        }
        
        String contentType = "application/octet-stream"; // 기본 다운로드 타입
        sysname = new String(sysname.getBytes("utf8"), "ISO-8859-1");
        String oriname = sysname.split("_", 2)[1];
        
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fileBytes));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + oriname + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

    // 문서 미리보기
    @GetMapping("/preview/{sysname}")
    public ResponseEntity<Resource> preview(@PathVariable String sysname) throws Exception {
        byte[] fileBytes = fileServ.getFileBytes(sysname);
        if (fileBytes == null) {
            return ResponseEntity.notFound().build();
        }
        
        String contentType = fileServ.getMimeType(sysname);
        if (contentType == null) {
            contentType = "application/octet-stream"; 
        }
        
        sysname = new String(sysname.getBytes("utf8"), "ISO-8859-1");
        String oriname = sysname.split("_", 2)[1];
        
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fileBytes));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + oriname + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }
}
