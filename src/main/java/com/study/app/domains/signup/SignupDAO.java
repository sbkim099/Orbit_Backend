package com.study.app.domains.signup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int isExistId(String id) {
		return mybatis.selectOne("Signup.isExistId", id);
	}
	
	public int isExistEmail(String email) {
		return mybatis.selectOne("Signup.isExistEmail", email);
	}
	
	public void signupRequest(SignupDTO dto) {
		mybatis.insert("Signup.signupRequest", dto);
	}
	
	public int countAllRequest() {
		return mybatis.selectOne("Signup.countAllRequest");
	}
	
	public List<SignupDTO> getAllRequest(Map<String, Object> param){
        return mybatis.selectList("Signup.getAllRequest", param);
    }

    public int getCount(String status, String searchTerm){
    	Map<String, String> params = new HashMap<>();
    	params.put("status", status);
    	params.put("searchTerm", searchTerm);
        return mybatis.selectOne("Signup.getCount", params);
    }

    public Map<String, Integer> getTabCount(){
        return mybatis.selectOne("Signup.getTabCount");
    }
    
    public SignupDTO getUserInfo(Long signup_seq) {
		return mybatis.selectOne("Signup.getUserInfo", signup_seq);
	}
    
    public int updateStatusToApproved(Long signup_seq) {
    	return mybatis.update("Signup.updateStatusToApproved", signup_seq);
    }
    
    public int rejectSignup(Long signup_seq) {
    	return mybatis.update("Signup.rejectSignup", signup_seq);
    }
}
