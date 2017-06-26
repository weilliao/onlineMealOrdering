package com.sglj.fbf.entity.user;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.sglj.fbf.base.BaseEntity;

/**
 * 基本位置信息
 *
 */
@MappedSuperclass
public class Position extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 省份 */
	@Column(name = "PROVINCE", length = 20)
	protected String province;

	/** 城市 */
	@Column(name = "CITY", length = 20)
	protected String city;

	/** 区县 */
	@Column(name = "DISTRICT", length = 20)
	protected String district;

	/** 地址 */
	@Column(name = "ADDRESS", length = 300)
	protected String address;

	/** 邮编 */
	@Column(name = "POSTCODE", length = 10)
	protected String postcode;
	
	/**其他信息*/
	@Column(name = "POSITION_OTHER_INFO", length = 300)
	protected String positionOtherInfo;

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the positionOtherInfo
	 */
	public String getPositionOtherInfo() {
		return positionOtherInfo;
	}

	/**
	 * @param positionOtherInfo the positionOtherInfo to set
	 */
	public void setPositionOtherInfo(String positionOtherInfo) {
		this.positionOtherInfo = positionOtherInfo;
	}
	
	
}
