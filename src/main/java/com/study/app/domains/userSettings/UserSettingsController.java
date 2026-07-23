package com.study.app.domains.userSettings;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userSettings")
public class UserSettingsController {

	@Autowired
    private UserSettingsService userSettingsServ;

    // 빠른실행 설정 조회
    @GetMapping("/quickActionOrder")
    public ResponseEntity<Map<String, String>> getQuickActionOrder(@RequestAttribute String loginId) {
        String order = userSettingsServ.getQuickActionOrder(loginId);
        Map<String, String> result = new HashMap<>();
        result.put("quick_action_order", order);
        return ResponseEntity.ok(result);
    }

    // 빠른실행 설정 저장
    @PostMapping("/quickActionOrder")
    public ResponseEntity<Void> saveQuickActionOrder(
            @RequestAttribute String loginId,
            @RequestBody Map<String, String> body) {
    	userSettingsServ.saveQuickActionOrder(loginId, body.get("quick_action_order"));
        return ResponseEntity.ok().build();
    }
}
