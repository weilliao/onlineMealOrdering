package com.sglj.fbf.service.userservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.user.ShippingAddressDao;
import com.sglj.fbf.entity.user.ShippingAddress;
import com.sglj.fbf.entity.user.User;
import com.sglj.fbf.service.userservice.ShippingAddressService;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月14日 下午3:46:22 
 * @version 1.0 
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Resource
	private ShippingAddressDao shippingAddressDao;
	
	@Override
	public List<ShippingAddress> queryShippingAddress(User user) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("user", user);
		return shippingAddressDao.find(queryRule);
	}

	@Override
	public void saveAddress(ShippingAddress shippingAddress) {
		shippingAddressDao.save(shippingAddress);
	}

	@Override
	public void delAddress(String id) {
		shippingAddressDao.deleteById(id);
	}

	@Override
	public void updateAddress(ShippingAddress shippingAddress) {
		shippingAddressDao.update(shippingAddress);
	}

	@Override
	public ShippingAddress getAddress(String id) {
		// TODO Auto-generated method stub
		return shippingAddressDao.get(id);
	}

}
