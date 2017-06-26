package com.sglj.fbf.service.orderservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.order.OrderDao;
import com.sglj.fbf.entity.order.Order;
import com.sglj.fbf.service.orderservice.OrderService;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月22日 下午7:00:04 
 * @version 1.0 
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderDao orderDao;

	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	@Override
	public List<Order> queryOrderList(String userId,String msg) {
		QueryRule queryRule = new QueryRule();
		queryRule.addDescOrder("lastModified");
		if(StringUtils.isNotBlank(msg)){
			queryRule.addEqual("status", msg);
		}
		if(StringUtils.isNotBlank(userId)){
			queryRule.addEqual("userId", userId);
		}
		return orderDao.find(queryRule);
	}

	@Override
	public void delOrder(String id) {
		orderDao.deleteById(id);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.update(order);
	}

	@Override
	public Order getOrder(String id) {
		// TODO Auto-generated method stub
		return orderDao.get(id);
	}
}
