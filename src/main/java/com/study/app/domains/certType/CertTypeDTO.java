package com.study.app.domains.certType;

import java.time.LocalDateTime;

public class CertTypeDTO {
	private Long cert_type_seq;
	private String cert_type_name;
	private String manage_dept_code;
	private String hidden_yn;
	private LocalDateTime created_at;
	
	public CertTypeDTO() {}
	public CertTypeDTO(Long cert_type_seq, String cert_type_name, String manage_dept_code, String hidden_yn,
			LocalDateTime created_at) {
		super();
		this.cert_type_seq = cert_type_seq;
		this.cert_type_name = cert_type_name;
		this.manage_dept_code = manage_dept_code;
		this.hidden_yn = hidden_yn;
		this.created_at = created_at;
	}
	public Long getCert_type_seq() {
		return cert_type_seq;
	}
	public void setCert_type_seq(Long cert_type_seq) {
		this.cert_type_seq = cert_type_seq;
	}
	public String getCert_type_name() {
		return cert_type_name;
	}
	public void setCert_type_name(String cert_type_name) {
		this.cert_type_name = cert_type_name;
	}
	public String getManage_dept_code() {
		return manage_dept_code;
	}
	public void setManage_dept_code(String manage_dept_code) {
		this.manage_dept_code = manage_dept_code;
	}
	public String getHidden_yn() {
		return hidden_yn;
	}
	public void setHidden_yn(String hidden_yn) {
		this.hidden_yn = hidden_yn;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
}
