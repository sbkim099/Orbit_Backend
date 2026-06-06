package com.study.app.domains.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
    public int insertPost(BoardPostsDTO dto) {
    	return mybatis.insert("Board.insertPost",dto);
    }

    public int insertPostFile(BoardFileDTO dto) {
    	return mybatis.insert("Board.insertPostFile",dto);
    }
    
    public List<BoardPostsDTO> getBoardList(Map<String, Object> params){
    	return mybatis.selectList("Board.getBoardList",params);
    }
    
    public int getBoardCount(Map<String, Object> params) {
    	return mybatis.selectOne("Board.getBoardCount",params);
    }
    
    public BoardPostsDTO getPostDetail(Long post_seq) {
    	return mybatis.selectOne("Board.getPostDetail", post_seq);
    }
}
