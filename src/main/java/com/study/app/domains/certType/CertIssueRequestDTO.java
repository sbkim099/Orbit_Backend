package com.study.app.domains.certType;

public class CertIssueRequestDTO {
	
	private Long cert_request_seq;
	private Long cert_type_seq;
	private String users_id;
	private String request_reason;
	private String status;
	private String requested_at;
	private String hanble_id;
	private String approved_at;
	private String rejected_at;
	private String reject_reason;
	private String canceled_at;
	private String print_available_at;
	private String print_expires_at;
	private Long applied_print_days;
	private Long applied_max_print;
	private Long printed_count;
	private String created_at;
	private String updated_at;
	
	public CertIssueRequestDTO() {}
	
	public CertIssueRequestDTO(Long cert_request_seq, Long cert_type_seq, String users_id, String request_reason,
			String status, String requested_at, String hanble_id, String approved_at, String rejected_at,
			String reject_reason, String canceled_at, String print_available_at, String print_expires_at,
			Long applied_print_days, Long applied_max_print, Long printed_count, String created_at, String updated_at) {
		super();
		this.cert_request_seq = cert_request_seq;
		this.cert_type_seq = cert_type_seq;
		this.users_id = users_id;
		this.request_reason = request_reason;
		this.status = status;
		this.requested_at = requested_at;
		this.hanble_id = hanble_id;
		this.approved_at = approved_at;
		this.rejected_at = rejected_at;
		this.reject_reason = reject_reason;
		this.canceled_at = canceled_at;
		this.print_available_at = print_available_at;
		this.print_expires_at = print_expires_at;
		this.applied_print_days = applied_print_days;
		this.applied_max_print = applied_max_print;
		this.printed_count = printed_count;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Long getCert_request_seq() {
		return cert_request_seq;
	}
	public void setCert_request_seq(Long cert_request_seq) {
		this.cert_request_seq = cert_request_seq;
	}
	public Long getCert_type_seq() {
		return cert_type_seq;
	}
	public void setCert_type_seq(Long cert_type_seq) {
		this.cert_type_seq = cert_type_seq;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public String getRequest_reason() {
		return request_reason;
	}
	public void setRequest_reason(String request_reason) {
		this.request_reason = request_reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequested_at() {
		return requested_at;
	}
	public void setRequested_at(String requested_at) {
		this.requested_at = requested_at;
	}
	public String getHanble_id() {
		return hanble_id;
	}
	public void setHanble_id(String hanble_id) {
		this.hanble_id = hanble_id;
	}
	public String getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
	public String getRejected_at() {
		return rejected_at;
	}
	public void setRejected_at(String rejected_at) {
		this.rejected_at = rejected_at;
	}
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	public String getCanceled_at() {
		return canceled_at;
	}
	public void setCanceled_at(String canceled_at) {
		this.canceled_at = canceled_at;
	}
	public String getPrint_available_at() {
		return print_available_at;
	}
	public void setPrint_available_at(String print_available_at) {
		this.print_available_at = print_available_at;
	}
	public String getPrint_expires_at() {
		return print_expires_at;
	}
	public void setPrint_expires_at(String print_expires_at) {
		this.print_expires_at = print_expires_at;
	}
	public Long getApplied_print_days() {
		return applied_print_days;
	}
	public void setApplied_print_days(Long applied_print_days) {
		this.applied_print_days = applied_print_days;
	}
	public Long getApplied_max_print() {
		return applied_max_print;
	}
	public void setApplied_max_print(Long applied_max_print) {
		this.applied_max_print = applied_max_print;
	}
	public Long getPrinted_count() {
		return printed_count;
	}
	public void setPrinted_count(Long printed_count) {
		this.printed_count = printed_count;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
}
