package com.study.app.domains.approval;

import java.util.List;

public class CancelVacationDTO extends DraftDocumentsDTO {
	private Long cancel_seq;
	private Long doc_seq;
	private Long vac_seq;
	private String cancel_reason;
	
	private List<ApprovalLinesDTO> approvers;
    private List<ApprovalCcDTO> referrers;
	
	public CancelVacationDTO() {}
	public CancelVacationDTO(Long cancel_seq, Long doc_seq, Long vac_seq, String cancel_reason,
			List<ApprovalLinesDTO> approvers, List<ApprovalCcDTO> referrers) {
		super();
		this.cancel_seq = cancel_seq;
		this.doc_seq = doc_seq;
		this.vac_seq = vac_seq;
		this.cancel_reason = cancel_reason;
		this.approvers = approvers;
		this.referrers = referrers;
	}
	public Long getCancel_seq() {
		return cancel_seq;
	}
	public void setCancel_seq(Long cancel_seq) {
		this.cancel_seq = cancel_seq;
	}
	public Long getDoc_seq() {
		return doc_seq;
	}
	public void setDoc_seq(Long doc_seq) {
		this.doc_seq = doc_seq;
	}
	public Long getVac_seq() {
		return vac_seq;
	}
	public void setVac_seq(Long vac_seq) {
		this.vac_seq = vac_seq;
	}
	public String getCancel_reason() {
		return cancel_reason;
	}
	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}
	public List<ApprovalLinesDTO> getApprovers() {
		return approvers;
	}
	public void setApprovers(List<ApprovalLinesDTO> approvers) {
		this.approvers = approvers;
	}
	public List<ApprovalCcDTO> getReferrers() {
		return referrers;
	}
	public void setReferrers(List<ApprovalCcDTO> referrers) {
		this.referrers = referrers;
	}
}
