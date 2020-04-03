package com.school.administration.app.shared.dto;

import java.io.Serializable;
import java.util.Set;

public class AudienceDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4352677781981281181L;
	private long id;
	private String audienceId;
	private String audienceName;
	private String gender;
	private String birthPlace;
	private String birthDate;
	private String phoneNumber;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private Boolean isActive;
	private String password;
	private String encryptPassword;
	private Set<InvoiceDto> invoice;
	private AudienceAddressDto address;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAudienceId() {
		return audienceId;
	}
	public void setAudienceId(String audienceId) {
		this.audienceId = audienceId;
	}
	public String getAudienceName() {
		return audienceName;
	}
	public void setAudienceName(String audienceName) {
		this.audienceName = audienceName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getEncryptPassword() {
		return encryptPassword;
	}
	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}
	public AudienceAddressDto getAddress() {
		return address;
	}
	public void setAddress(AudienceAddressDto address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<InvoiceDto> getInvoice() {
		return invoice;
	}
	public void setInvoice(Set<InvoiceDto> invoice) {
		this.invoice = invoice;
	}
}
