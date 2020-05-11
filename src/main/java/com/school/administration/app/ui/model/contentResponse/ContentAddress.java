package com.school.administration.app.ui.model.contentResponse;

import com.school.administration.app.ui.model.response.AddressResponse;

public class ContentAddress {
	private String errorCode;
	private String errorDesc;
	private AddressResponse content;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public AddressResponse getContent() {
		return content;
	}
	public void setContent(AddressResponse content) {
		this.content = content;
	}
}
