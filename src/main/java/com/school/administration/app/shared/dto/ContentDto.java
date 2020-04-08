package com.school.administration.app.shared.dto;

public class ContentDto {
	
	private String errorCode;
	private String status;
	private AudienceDto content;
	
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
	public AudienceDto getContent() {
		return content;
	}
	public void setContent(AudienceDto content) {
		this.content = content;
	}
}
