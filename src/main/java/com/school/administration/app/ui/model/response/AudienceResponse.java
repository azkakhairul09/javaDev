package com.school.administration.app.ui.model.response;

public class AudienceResponse {
	private String audienceName;
	private AudienceAddressResponse address;
	
	public String getAudienceName() {
		return audienceName;
	}
	public void setAudienceName(String audienceName) {
		this.audienceName = audienceName;
	}
	public AudienceAddressResponse getAddress() {
		return address;
	}
	public void setAddress(AudienceAddressResponse address) {
		this.address = address;
	}
}
