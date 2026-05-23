package com.study.app.domains.notifications;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noti")
public class NotificationsController {

	@Autowired
	private NotificationsService noServ;
	@Autowired
	private SimpMessagingTemplate stomp;

	@GetMapping("/notify")
	public String notify(@PathVariable String userId) {
		stomp.convertAndSendToUser(
				"홍길동",
				"/sub/notify",
				Map.of("message", "테스트알림")
			);
		System.out.println("연결 성공");
		return "전송 완료";
	}
}
