package com.study.app.domains.projects;

public class ProjectCountDTO {

	private Long allCount;
	private Long inProgressCount;
	private Long doneCount;
	
	public ProjectCountDTO() {}
	
	public ProjectCountDTO(Long allCount, Long inProgressCount, Long doneCount) {
		super();
		this.allCount = allCount;
		this.inProgressCount = inProgressCount;
		this.doneCount = doneCount;
	}
	
	public Long getAllCount() {
		return allCount;
	}
	public void setAllCount(Long allCount) {
		this.allCount = allCount;
	}
	public Long getInProgressCount() {
		return inProgressCount;
	}
	public void setInProgressCount(Long inProgressCount) {
		this.inProgressCount = inProgressCount;
	}
	public Long getDoneCount() {
		return doneCount;
	}
	public void setDoneCount(Long doneCount) {
		this.doneCount = doneCount;
	}
}
