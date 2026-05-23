package com.study.app.domains.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService {
	
	@Autowired
	private SimpMessagingTemplate stomp;
	
	@Autowired
	private NotificationsDAO noDao;
	
	public void sendNoti(String id, String message, String type) {
		
		stomp.convertAndSendToUser(id, message, type);
	}
}
