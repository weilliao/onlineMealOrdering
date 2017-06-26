package com.sglj.fbf.entity.order;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 订单类
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午3:31:34 
 * @version 1.0 
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "OEDER")
public class Order extends BaseEntity {
	
	private static final long serialVersionUID = -7195483910729903599L;
	/**
	 * 订单编号
	 */
    @Column(name = "ORDER_ID")
	private String orderId;
	/**
	 * 订单对应的用户
	 */
    @Column(name = "USER_ID")
	private String userId;
	
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
	
	/** 收货方式 */
	@Column(name = "RECEIVING_METHOD", length = 60)
	private String receivingMethod;
	
	/** 送达时间 */
	@Column(name = "Time", length = 60)
	private String time;
	
	/** 合计 */
	@Column(name = "ORDERTOTAL")
	private int orderTotal;
	
	/** 备注 */
	@Column(name = "REMARK", length = 60)
	private String remark;
	
	/**
	 * 订单下的菜品
	 */
	@Transient
	private List<OrderMenu> orderMenuList;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getReceivingMethod() {
		return receivingMethod;
	}

	public void setReceivingMethod(String receivingMethod) {
		this.receivingMethod = receivingMethod;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderMenu> getOrderMenuList() {
		return orderMenuList;
	}

	public void setOrderMenuList(List<OrderMenu> orderMenuList) {
		this.orderMenuList = orderMenuList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
