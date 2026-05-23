package com.study.app.domains.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.app.domains.users.UsersDAO;

@Service
public class AuthService {
	
	@Autowired
	private UsersDAO usersDao;
	
	public int login(AuthDTO dto) {
		return usersDao.login(dto);
	}
}
