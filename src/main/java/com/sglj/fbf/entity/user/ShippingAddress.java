package com.sglj.fbf.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 用户收货地址实体类
 * @author  wei 
 * @date 创建时间：2017年2月14日 下午2:30:08 
 * @version 1.0 
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SHIPPING_ADDRESS")
public class ShippingAddress extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 地址对应所属的用户
	 */
	@JoinColumn(name = "USER_ID")
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private User user;
	
	/** 省份 */
	@Column(name = "PROVINCE", length = 20)
	protected String province;

	/** 城市 */
	@Column(name = "CITY", length = 20)
	protected String city;


	/** 地址 */
	@Column(name = "ADDRESS", length = 300)
	protected String address;

	/** 邮编 */
	@Column(name = "POSTCODE", length = 10)
	protected String postcode;
	
	/** 收货人姓名 */
	@Column(name = "NAME", length = 10)
	protected String name;
	
	/** 手机号 */
	@Column(name = "PHONE", length = 11)
	protected String phone;
	
	/** 地址是否为默认地址,默认不是 */
	@Column(name = "ADDRESS_IS_DEFULT", length = 11)
	protected boolean addressIsDefult=false;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAddressIsDefult() {
		return addressIsDefult;
	}

	public void setAddressIsDefult(boolean addressIsDefult) {
		this.addressIsDefult = addressIsDefult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
