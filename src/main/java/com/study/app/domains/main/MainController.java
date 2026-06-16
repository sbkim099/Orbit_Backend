package com.study.app.domains.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.app.domains.board.BoardPostsDTO;
@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private MainService mainServ;
	
	@GetMapping("/boardList")
	public ResponseEntity<List<BoardPostsDTO>> getNoticeList() {
		List<BoardPostsDTO> boardList = mainServ.getNoticeList();
	    return ResponseEntity.ok(boardList);
	}

}
