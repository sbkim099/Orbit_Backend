package com.study.app.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JWTUtil {
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	private Algorithm alg;
	private JWTVerifier jwt;
	
	public JWTUtil(@Value("${jwt.secret}") String secret) {
		this.alg = Algorithm.HMAC256(secret);
		this.jwt = JWT.require(alg).build();
	}
	
	public String createToken(String id) {
		return JWT.create()
				.withSubject(id)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + expiration))
				.sign(alg);
	} 
	
	public DecodedJWT validation(String token) { // 토큰 유효성
		return jwt.verify(token);
	}
	
	public String getSubject(String token) { // Subject 꺼내 쓰는 함수
		return validation(token).getSubject();
	}
}
