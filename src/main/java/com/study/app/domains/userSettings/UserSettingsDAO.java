package com.study.app.domains.userSettings;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserSettingsDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public String selectQuickActionOrder(String loginId) {
	    return mybatis.selectOne("UserSettings.selectQuickActionOrder", loginId);
	}

	public void insertQuickActionOrder(String loginId, String quickActionOrder) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);
	    params.put("quickActionOrder", quickActionOrder);
	    mybatis.insert("UserSettings.insertQuickActionOrder", params);
	}

	public void updateQuickActionOrder(String loginId, String quickActionOrder) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);
	    params.put("quickActionOrder", quickActionOrder);
	    mybatis.update("UserSettings.updateQuickActionOrder", params);
	}
}
