package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity(name="address")
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1227177082633578253L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="audienceAddress")
	@TableGenerator(name="audienceAddress", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="addressID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = true)
	private String addressId;
	
	@Column(nullable = false)
	private String fullAddress;
	
	@Column(nullable = false)
	private String province;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String subDistrict;
	
	@Column(nullable = false, length = 5)
	private String postalCode;
	
	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	private AudienceEntity audienceDetail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public AudienceEntity getAudienceDetail() {
		return audienceDetail;
	}

	public void setAudienceDetail(AudienceEntity audienceDetail) {
		this.audienceDetail = audienceDetail;
	}
}
