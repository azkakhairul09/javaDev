package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "audience")
@DynamicUpdate
public class AudienceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364349540268029286L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="audience")
	@TableGenerator(name="audience", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="audienceID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = true)
	private String audienceId;
	
	@Column(nullable = false, length = 100, unique = true)
	private String audienceName;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String birthPlace;
	
	@Column(nullable = false)
	private String birthDate;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(nullable = true)
	private String createdDate;
	
	@Column(nullable = true)
	private String modifiedBy;
	
	@Column(nullable = true)
	private String modifiedDate;
	
	@Column(nullable = true)
	private Boolean isActive;
	
	@Column(nullable = true)
	private String encryptPassword;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "address_id")
	private AudienceAddress address;
	
	@OneToMany(mappedBy = "audienceId", cascade = CascadeType.ALL)
	private Set<InvoiceEntity> invoice;
	
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

	public AudienceAddress getAddress() {
		return address;
	}

	public void setAddress(AudienceAddress address) {
		this.address = address;
	}

	public Set<InvoiceEntity> getInvoice() {
		return invoice;
	}

	public void setInvoice(Set<InvoiceEntity> invoice) {
		this.invoice = invoice;
	}
}
