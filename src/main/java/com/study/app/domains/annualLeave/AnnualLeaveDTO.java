package com.study.app.domains.annualLeave;

public class AnnualLeaveDTO {
	private Long leave_seq;
	private String users_id;
	private Long year;
	private Double total_days;
	private Double used_days;
	private Double remaining_days;
	private Double admin_days;
	
	public AnnualLeaveDTO() {}
	public AnnualLeaveDTO(Long leave_seq, String users_id, Long year, Double total_days, Double used_days,
			Double remaining_days, Double admin_days) {
		super();
		this.leave_seq = leave_seq;
		this.users_id = users_id;
		this.year = year;
		this.total_days = total_days;
		this.used_days = used_days;
		this.remaining_days = remaining_days;
		this.admin_days = admin_days;
	}
	public Long getLeave_seq() {
		return leave_seq;
	}
	public void setLeave_seq(Long leave_seq) {
		this.leave_seq = leave_seq;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Double getTotal_days() {
		return total_days;
	}
	public void setTotal_days(Double total_days) {
		this.total_days = total_days;
	}
	public Double getUsed_days() {
		return used_days;
	}
	public void setUsed_days(Double used_days) {
		this.used_days = used_days;
	}
	public Double getRemaining_days() {
		return remaining_days;
	}
	public void setRemaining_days(Double remaining_days) {
		this.remaining_days = remaining_days;
	}
	public Double getAdmin_days() {
		return admin_days;
	}
	public void setAdmin_days(Double admin_days) {
		this.admin_days = admin_days;
	}
	
}
