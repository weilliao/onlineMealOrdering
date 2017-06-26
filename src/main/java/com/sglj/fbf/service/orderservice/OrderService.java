package com.sglj.fbf.service.orderservice;

import java.util.List;

import com.sglj.fbf.entity.order.Order;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月22日 下午6:58:06 
 * @version 1.0 
 */
public interface OrderService {
	/**
	 * 保存订单
	 */
	public void saveOrder(Order order);

	/**
	 * 获取用户的订单(msg不为空根据订单状态获取，为空获取所有订单)
	 * @param userId
	 * @return
	 */
	public List<Order> queryOrderList(String userId,String msg);
	
	/**
	 * 删除订单
	 * @param id
	 */
	public void delOrder(String id);
	
	/**
	 * 更新订单
	 * @param id
	 */
	public void updateOrder(Order order);
	
	/**
	 * 根据id获取订单
	 * @param id
	 * @return
	 */
	public Order getOrder(String id);
	
}
