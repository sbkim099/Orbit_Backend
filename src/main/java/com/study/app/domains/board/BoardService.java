package com.study.app.domains.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.study.app.domains.file.FileService;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;
	
    @Autowired
    private FileService fileService; 
	   
	@Transactional //여러 DB 작업을 하나로 묶어주는 어노테이션
	public void writePost(BoardPostsDTO post, List<MultipartFile> files) throws Exception {

	    // 1. 게시글 저장
	    boardDAO.insertPost(post);

	    // 2. 파일 저장 (게시글 저장 실패 시 여기까지 안 옴)
	    if (files != null && !files.isEmpty()) {
	        for (MultipartFile file : files) {
	            if (file.isEmpty()) continue;

	            Map<String, String> fileInfo = fileService.upload(file);

	            BoardFileDTO fileDTO = new BoardFileDTO();
	            fileDTO.setPost_seq(post.getPost_seq()); // selectKey로 세팅된 post_seq 사용
	            fileDTO.setFile_oriname(fileInfo.get("oriname"));
	            fileDTO.setFile_sysname(fileInfo.get("sysname"));
	            fileDTO.setFile_path(fileInfo.get("file_path"));
	            fileDTO.setFile_size(file.getSize());

	            boardDAO.insertPostFile(fileDTO);
	        }
	    }
	}
	
	  // 에디터 이미지 업로드
    public String uploadEditorImage(MultipartFile image) throws Exception {
        Map<String, String> fileInfo = fileService.upload(image);
        String sysname = fileInfo.get("sysname");
//        return fileInfo.get("file_path"); // GCS URL만 반환
        return "http://localhost/board/editorImage/" + sysname;
    }
    
    public List<BoardPostsDTO> getBoardList(Map<String, Object> params){
    	return boardDAO.getBoardList(params);
    }
    
    public int getBoardCount(Map<String, Object> params) {
        return boardDAO.getBoardCount(params);
    }
    
    public BoardPostsDTO getPostDetail(Long post_seq) {
    	return boardDAO.getPostDetail(post_seq);
    }
}
