package com.study.app.domains.aiChat;

public class EmbedDocumentDTO {
	
	private Long fileSeq;
	private Long documentSeq;
	private String signedUrl;
	
	public EmbedDocumentDTO() {}
	
	public EmbedDocumentDTO(Long fileSeq, Long documentSeq, String signedUrl) {
		super();
		this.fileSeq = fileSeq;
		this.documentSeq = documentSeq;
		this.signedUrl = signedUrl;
	}
	
	public Long getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(Long fileSeq) {
		this.fileSeq = fileSeq;
	}
	public Long getDocumentSeq() {
		return documentSeq;
	}
	public void setDocumentSeq(Long documentSeq) {
		this.documentSeq = documentSeq;
	}
	public String getSignedUrl() {
		return signedUrl;
	}
	public void setSignedUrl(String signedUrl) {
		this.signedUrl = signedUrl;
	}
}
