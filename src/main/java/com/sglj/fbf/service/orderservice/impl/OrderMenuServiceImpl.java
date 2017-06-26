package com.sglj.fbf.service.orderservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.order.OrderMenuDao;
import com.sglj.fbf.entity.order.Order;
import com.sglj.fbf.entity.order.OrderMenu;
import com.sglj.fbf.service.orderservice.OrderMenuService;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月22日 下午6:59:14 
 * @version 1.0 
 */
@Service
public class OrderMenuServiceImpl implements OrderMenuService {
	@Resource
	private OrderMenuDao orderMenuDao;

	@Override
	public void saveOrderMenu(OrderMenu orderMenu) {
		orderMenuDao.save(orderMenu);
		
	}

	@Override
	public List<OrderMenu> queryOrderMenuList(String orderId) {
		QueryRule queryRule = new QueryRule();
		queryRule.addEqual("orderId", orderId);
		queryRule.addDescOrder("lastModified");
		return orderMenuDao.find(queryRule);
	}

	@Override
	public void delOrderMenu(Order order) {
		QueryRule queryRule = new QueryRule();
		queryRule.addEqual("order", order);
		orderMenuDao.delete(orderMenuDao.find(queryRule));
	}
}
