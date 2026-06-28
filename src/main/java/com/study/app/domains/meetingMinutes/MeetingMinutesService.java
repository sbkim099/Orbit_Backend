package com.study.app.domains.meetingMinutes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.storage.v2.GetObjectRequest;
import com.study.app.domains.aiChat.AiChatService;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class MeetingMinutesService {
	@Autowired
	private MeetingMinutesDAO minutesDAO;
	
	@Autowired
	private MinutesAttendeesDAO minutesAttendeesDAO;
	
	@Autowired
	private AiChatService aiChatServ;
	
	@Value("${clova.speech.secret-key}")
	private String speechSecretKey;

	@Value("${clova.speech.invoke-url}")
	private String speechInvokeUrl;
	
	@Value("${naver.access-key}")
	private String naverAccessKey;

	@Value("${naver.secret-key}")
	private String naverSecretKey;

	@Value("${naver.object-storage.bucket}")
	private String naverBucket;

	@Value("${gemini.api-key}")
	private String geminiApiKey;
	
	// Clova Speech STT
	private String transcribe(MultipartFile audioFile) throws Exception {
	    String apiUrl = speechInvokeUrl + "/recognizer/upload";
	    String boundary = "----Boundary" + System.currentTimeMillis();

	    String paramsJson = "{\"language\":\"ko-KR\",\"completion\":\"async\",\"fullText\":true," +
	    	    "\"diarization\":{\"enable\":false}," +
	    	    "\"resultToObs\":true}";

	    byte[] audioBytes = audioFile.getBytes();

	    String metaPart = "--" + boundary + "\r\n"
	        + "Content-Disposition: form-data; name=\"params\"\r\n"
	        + "Content-Type: application/json\r\n\r\n"
	        + paramsJson + "\r\n";

	    String audioHeader = "--" + boundary + "\r\n"
	        + "Content-Disposition: form-data; name=\"media\"; filename=\"audio\"\r\n"
	        + "Content-Type: " + audioFile.getContentType() + "\r\n\r\n";

	    String endBoundary = "\r\n--" + boundary + "--\r\n";

	    byte[] body = concat(
	        metaPart.getBytes(), audioHeader.getBytes(),
	        audioBytes, endBoundary.getBytes()
	    );

	    HttpRequest request = HttpRequest.newBuilder()
	        .uri(URI.create(apiUrl))
	        .header("X-CLOVASPEECH-API-KEY", speechSecretKey)
	        .header("Content-Type", "multipart/form-data; boundary=" + boundary)
	        .POST(HttpRequest.BodyPublishers.ofByteArray(body))
	        .build();

	    HttpResponse<String> response = HttpClient.newHttpClient()
	        .send(request, HttpResponse.BodyHandlers.ofString());
	    
	    System.out.println("Clova Speech 응답: " + response.body());
	    
	 // Jackson으로 파싱
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode root = mapper.readTree(response.body());
	    String token = root.get("token").asText();
	    
	    return pollResult(token); 
	}
	
	private S3Client getNaverS3Client() {
	    return S3Client.builder()
	        .endpointOverride(URI.create("https://kr.object.ncloudstorage.com"))
	        .credentialsProvider(StaticCredentialsProvider.create(
	            AwsBasicCredentials.create(naverAccessKey, naverSecretKey)))
	        .region(Region.of("kr-standard"))
	        .build();
	}

	private String readResultFromObs(String token) throws Exception {
	    String objectKey = token + ".json";
	    
	    S3Client s3 = getNaverS3Client();
	    GetObjectRequest getRequest = GetObjectRequest.builder()
	        .bucket(naverBucket)
	        .key(objectKey)
	        .build();
	    
	    byte[] bytes = s3.getObjectAsBytes(getRequest).asByteArray();
	    String content = new String(bytes);
	    System.out.println("OBS 결과: " + content);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode root = mapper.readTree(content);
	    return root.get("text").asText();
	}
	
	private String pollResult(String token) throws Exception {
	    String statusUrl = speechInvokeUrl + "/recognizer/" + token;

	    for (int i = 0; i < 30; i++) {  // 최대 30번 (약 5분)
	        Thread.sleep(10000);  // 10초 대기

	        HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(statusUrl))
	            .header("X-CLOVASPEECH-API-KEY", speechSecretKey)
	            .GET()
	            .build();

	        HttpResponse<String> response = HttpClient.newHttpClient()
	            .send(request, HttpResponse.BodyHandlers.ofString());

	        System.out.println("폴링 응답: " + response.body());

	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(response.body());
	        String status = root.get("status").asText();

	        if ("COMPLETED".equals(status)) {
	            return readResultFromObs(token);
	        } else if ("ERROR".equals(status)) {
	            throw new Exception("STT 처리 실패: " + root.get("message").asText());
	        }
	        // RUNNING이면 계속 대기
	    }
	    throw new Exception("STT 처리 시간 초과");
	}
	
	private byte[] concat(byte[]... arrays) {
	    int total = 0;
	    for (byte[] a : arrays) total += a.length;
	    byte[] result = new byte[total];
	    int pos = 0;
	    for (byte[] a : arrays) {
	        System.arraycopy(a, 0, result, pos, a.length);
	        pos += a.length;
	    }
	    return result;
	}
	
	// STT + AI 요약
	public Map<String, String> sttAndSummary(MultipartFile audioFile) throws Exception {
	    String transcript = transcribe(audioFile);
//	    String[] summary = extractSummary(transcript);
	    
	    Map<String, String> result = new HashMap<>();
	    result.put("transcript", transcript);
//	    result.put("decisions", summary[0]);
//	    result.put("todos", summary[1]);
	    return result;
	}
	
	@Transactional
	public int insertMinutes(MeetingMinutesDTO dto) {
	    int result = minutesDAO.insertMinutes(dto);  // insert 후 dto.minuteSeq에 자동으로 생성된 seq 담김
	    
	    if (dto.getAttendees() != null) {
	        for (MinutesAttendeesDTO attendee : dto.getAttendees()) {
	        	 attendee.setMinute_seq(dto.getMinute_seq()); 
	        	 minutesAttendeesDAO.insertMinutesAttendees(attendee);            
	        }
	    }
	    aiChatServ.createMeetingChunk(dto);
	    return result;		
	}
	
	public List<MeetingMinutesDTO> getMinutesList() {
		return minutesDAO.getMinutesList();
	}
	
	public MeetingMinutesDTO getMinutesDetail(Long minute_seq) {
		return minutesDAO.getMinutesDetail(minute_seq);
	}
	
	@Transactional
	public void deleteMinutesAll(Long minute_seq) {
		aiChatServ.deleteMeetingRag(minute_seq);
		
		// 1. 자식 테이블(참석자) 데이터 먼저 삭제
		minutesAttendeesDAO.deleteMinutesAttendees(minute_seq);
	    
	    // 2. 부모 테이블(회의록) 데이터 최종 삭제
	    minutesDAO.deleteMinutes(minute_seq);
	}
	
	//전부 성공하면 commit, 하나라도 실패하면 전부 rollback
	@Transactional 
	public void updateMinutesAll(MeetingMinutesDTO dto) {
	    // 1. 회의록 본문 내용 수정 (제목, 내용 등 수정)
	    minutesDAO.updateMinutes(dto); 
	    aiChatServ.deleteMeetingRag(dto.getMinute_seq());
	    aiChatServ.createMeetingChunk(dto);
	    // 2. 기존 참석자 싹 지우기
	    minutesAttendeesDAO.deleteMinutesAttendees(dto.getMinute_seq()); 
	    
	    // 3. 처음 등록할 때 쓰던 insert 쿼리를 그대로 재사용해서 새로 넣기
	    if (dto.getAttendees() != null) {
	        for (MinutesAttendeesDTO emp : dto.getAttendees()) {
	        	// 꺼내온 emp에 현재 수정 중인 회의록 번호(minute_seq)를 심어줌
	            emp.setMinute_seq(dto.getMinute_seq());
	            
	            //  DTO 객체인 emp를 그대로 매개변수로
	            minutesAttendeesDAO.insertMinutesAttendees(emp);
	        }
	    }
	}
}
