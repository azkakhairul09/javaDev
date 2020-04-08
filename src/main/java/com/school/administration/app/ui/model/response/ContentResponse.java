package com.school.administration.app.ui.model.response;

public class ContentResponse {

	private String errorCode;
	private String status;
	private AudienceResponse content;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AudienceResponse getContent() {
		return content;
	}
	public void setContent(AudienceResponse content) {
		this.content = content;
	}
}
