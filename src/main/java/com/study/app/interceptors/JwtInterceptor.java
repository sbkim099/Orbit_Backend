package com.study.app.interceptors;

import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import com.study.app.util.JWTUtil;

@Component
public class JwtInterceptor implements ChannelInterceptor{
	
	@Autowired
	private JWTUtil jwt;
	
	@Override
	public @Nullable Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor acc = 
				MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		
		// 웹소켓과 연결됐을 때
		if(acc.getCommand().equals(StompCommand.CONNECT)) {
			String authHeader = acc.getFirstNativeHeader("Authorization");
			String token = authHeader.substring(7);
			String id = jwt.getSubject(token);
			
			Map<String, Object> sa = acc.getSessionAttributes();
			sa.put("loginId", id);
		}
		return message;
	}
}