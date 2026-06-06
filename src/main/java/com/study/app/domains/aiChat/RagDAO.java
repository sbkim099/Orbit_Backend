package com.study.app.domains.aiChat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RagDAO {
	
	@Autowired
	private SqlSessionTemplate batis;
	
	public void insertRagDocuments(RagDocumentsDTO dto) {
		batis.insert("Rag.insertRagDocuments", dto);
	}
	
	public void insertRagChunks(RagChunksDTO dto) {
		batis.insert("Rag.insertRagChunks", dto);
	}
	
	public List<RagChunksDTO> findAllChunks() {
	    return batis.selectList("Rag.findAllChunks");
	}
	
	public List<RagChunksDTO> findChunksByRagDocSeq(Long rag_doc_seq) {
	    return batis.selectList("Rag.findChunksByRagDocSeq", rag_doc_seq);
	}
	
	public void updateChunkEmbed(Long chunk_seq, String qdrant_point_id) {
		Map<String, Object> params = new HashMap<>();
		params.put("chunk_seq", chunk_seq);
		params.put("qdrant_point_id", qdrant_point_id);
		batis.update("Rag.updateChunkEmbed", params);
	}
	
}
