package com.study.app.domains.admin;

public class AiQuestionCountDTO {
	
	private Long allCount;
	private Long pendingCount;
	private Long answeredCount;
	
	public AiQuestionCountDTO() {}
	
	public AiQuestionCountDTO(Long allCount, Long pendingCount, Long answeredCount) {
		super();
		this.allCount = allCount;
		this.pendingCount = pendingCount;
		this.answeredCount = answeredCount;
	}
	
	public Long getAllCount() {
		return allCount;
	}
	public void setAllCount(Long allCount) {
		this.allCount = allCount;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getAnsweredCount() {
		return answeredCount;
	}
	public void setAnsweredCount(Long answeredCount) {
		this.answeredCount = answeredCount;
	}
}
