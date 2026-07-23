package com.study.app.domains.userSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSettingsService {
	@Autowired
    private UserSettingsDAO dao;
	
	 // 개인화 설정 - 빠른실행 순서 조회
    public String getQuickActionOrder(String loginId) {
        return dao.selectQuickActionOrder(loginId);
    }

    // 개인화 설정 - 빠른실행 순서 저장 (있으면 update, 없으면 insert)
    @Transactional
    public void saveQuickActionOrder(String loginId, String quickActionOrder) {
        if (quickActionOrder != null && quickActionOrder.length() > 200) {
            throw new IllegalArgumentException("빠른실행 설정 값이 너무 깁니다.");
        }

        String existing = dao.selectQuickActionOrder(loginId);
        if (existing == null) {
            dao.insertQuickActionOrder(loginId, quickActionOrder);
        } else {
            dao.updateQuickActionOrder(loginId, quickActionOrder);
        }
    }
	
}
