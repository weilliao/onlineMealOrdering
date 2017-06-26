package com.sglj.fbf.service.userservice;

import java.util.List;

import com.sglj.fbf.entity.user.ShippingAddress;
import com.sglj.fbf.entity.user.User;

/** 
 * 收货地址service层
 * @author  wei 
 * @date 创建时间：2017年2月14日 下午3:05:09 
 * @version 1.0 
 */
public interface ShippingAddressService {
	
	/**
	 * 查询收货地址
	 * @param id
	 */
	public ShippingAddress getAddress(String id);
	
	/**
	 * 根据用户id查询用户的收货地址
	 * @param userId
	 * @return
	 */
	public List<ShippingAddress> queryShippingAddress(User user);
	/**
	 * 保存收货地址
	 * @param shippingAddress
	 */
	public void saveAddress(ShippingAddress shippingAddress);
	
	/**
	 * 删除收货地址
	 * @param id
	 */
	public void delAddress(String id);
	
	/**
	 * 修改收货地址
	 * @param shippingAddress
	 */
	public void updateAddress(ShippingAddress shippingAddress);

}
