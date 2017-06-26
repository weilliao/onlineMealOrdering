package com.sglj.fbf.service.orderservice;

import java.util.List;

import com.sglj.fbf.entity.order.Order;
import com.sglj.fbf.entity.order.OrderMenu;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月22日 下午6:58:26 
 * @version 1.0 
 */
public interface OrderMenuService {
	/**
	 * 保存订单中的菜品
	 */
	public void saveOrderMenu(OrderMenu orderMenu);
	
	

	/**
	 * 获取用户的订单下的菜品
	 * @param order
	 * @return
	 */
	public List<OrderMenu> queryOrderMenuList(String orderId);	
	
	/**
	 * 删除订单下的菜品
	 * @param order
	 */
	public void delOrderMenu(Order order);
	
		
	
}
