package com.study.app.domains.aiChat;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import lombok.RequiredArgsConstructor;

//@Slf4j
//@Component
@RequiredArgsConstructor
public class ManualDataMigrationRunner implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(ManualDataMigrationRunner.class);
	
	@Autowired
	private ResourcePatternResolver resolver;
	@Autowired
	private RagDAO ragDao;

	@Override
	public void run(String... args) throws Exception{
		executeMigration();
	}

	private void executeMigration() {
		try {
			log.info("=== 기존 마크다운 수동 문서 적재(Migration) 시작 ===");

			// migration 폴더 내 txt 파일 가져오기
			Resource[] resources = resolver.getResources("classpath:migration/*.txt");

			if(resources.length == 0) {
				log.warn("마이그레이션 폴더 내에 처리할 txt 파일이 없음.");
				return;
			}

			for(Resource resource : resources) {
				String fileName = resource.getFilename();

				log.info("파일 분석 중: {}", fileName);

				// 파일 본문 읽기
				String content = StreamUtils.copyToString(resource.getInputStream(), 
						StandardCharsets.UTF_8);

				RagDocumentsDTO ragDoc = new RagDocumentsDTO();

				ragDoc.setSource_type("DOCUMENTS");
				ragDoc.setSource_seq(0L);
				ragDoc.setFile_name(fileName);
				ragDoc.setFile_ext("txt");
				ragDoc.setRaw_text(content);
				ragDoc.setExtract_status("DONE");

				ragDao.insertRagDocuments(ragDoc);

				Long ragDocSeq = ragDoc.getRag_doc_seq();

				// md 형식 txt 파일 소주제 하나 = 청크 하나 기준으로 분리
				Pattern pattern =
						Pattern.compile("(?=##\\s+\\d+\\.\\d+)");
				String[] chunks =
						pattern.split(content);

				long chunkIndex = 0;

				for(String chunk : chunks) {
					chunk = chunk.trim();
					if(chunk.isEmpty()) continue;


					RagChunksDTO chunkDto =
							new RagChunksDTO();

					chunkDto.setRag_doc_seq(ragDocSeq);
					chunkDto.setChunk_index(chunkIndex);
					chunkDto.setChunk_text(chunk);
					chunkDto.setEmbed_status("PENDING");

					ragDao.insertRagChunks(chunkDto);
					chunkIndex++;
				}

				log.info("DB 저장 완료 [청크 개수: {}] : {}",fileName);
			}

			log.info("=== 수동 마이그레이션 성공 ===");

		}catch(Exception e) {
			log.error("마이그레이션 중 오류 발생: ", e);
		}
	}
}