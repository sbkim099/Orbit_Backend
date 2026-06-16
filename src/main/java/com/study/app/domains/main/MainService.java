package com.study.app.domains.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.app.domains.board.BoardDAO;
import com.study.app.domains.board.BoardPostsDTO;
@Service
public class MainService {
	@Autowired
	private BoardDAO boardDAO;
	
	//게시판
	public List<BoardPostsDTO> getNoticeList() {
	    return boardDAO.getNoticeList();
	}

}
