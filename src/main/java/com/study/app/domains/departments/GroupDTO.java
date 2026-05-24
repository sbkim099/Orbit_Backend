package com.study.app.domains.departments;

import java.util.List;

// 조직도 DTO
public class GroupDTO {
	
	private String id;
	private String deptName;
	private String leaderName;
	private String position;
	private List<GroupDTO> children;
}
