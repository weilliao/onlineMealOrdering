package com.sglj.fbf.controller.ordercontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.entity.diningcar.DiningCarMenu;
import com.sglj.fbf.entity.menu.Menu;
import com.sglj.fbf.entity.order.Order;
import com.sglj.fbf.entity.order.OrderMenu;
import com.sglj.fbf.service.diningcarservice.DiningCarMenuService;
import com.sglj.fbf.service.menuservice.MenuService;
import com.sglj.fbf.service.orderservice.OrderMenuService;
import com.sglj.fbf.service.orderservice.OrderService;
import com.sglj.fbf.service.userservice.ShippingAddressService;
import com.sglj.fbf.service.userservice.UserService;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月22日 下午7:32:54 
 * @version 1.0 
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;
	@Resource
	private OrderMenuService orderMenuService;
	@Resource
	private ShippingAddressService shippService;
	@Resource
	private UserService userService;
	@Resource
	private DiningCarMenuService diningCarMenuService;
	@Resource
	private MenuService menuService;
	
	@RequestMapping(value = "/saveOrder", produces = "application/json")
	private ResponseEntity saveOrder(Order order,String menuIds) {
		System.out.println(order.toString());
		System.out.println(menuIds);
		ResponseEntity rst = new ResponseEntity();
		try {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateString = formatter.format(currentTime);
			order.setStatus("等待处理");
			order.setOrderId(dateString);
			orderService.saveOrder(order);
			
			System.out.println(menuIds);
			String[] a = menuIds.split(",");
			//单独定一份
			if (a[a.length-1].equals("ones")) {
				OrderMenu orderMenu = new OrderMenu();
				Menu meun = menuService.getMenuById(a[0]);
				orderMenu.setOrderId(dateString);
				orderMenu.setMenuId(a[0]);
				orderMenu.setMenuNum(1);
				orderMenu.setPrice(meun.getPrice());
				orderMenu.setMenuPic(meun.getMenuPic());
				orderMenu.setMenuName(meun.getName());
				orderMenuService.saveOrderMenu(orderMenu);
			}else{
				for(int i=0;i<a.length-1;i++){
					System.out.println(a[i]);
					DiningCarMenu diningCarMenu = diningCarMenuService.getDiningCarMenu(a[i]);
					OrderMenu orderMenu = new OrderMenu();
					orderMenu.setOrderId(dateString);
					orderMenu.setMenuId(diningCarMenu.getMenuId());
					orderMenu.setMenuNum(diningCarMenu.getMenuNum());
					orderMenu.setPrice(diningCarMenu.getPrice());
					orderMenu.setMenuPic(diningCarMenu.getMenuPic());
					orderMenu.setMenuName(diningCarMenu.getName());
					orderMenuService.saveOrderMenu(orderMenu);
				}
			}
			
			/*//生成订单成功后删除餐车里的菜品
			for(int i=0;i<a.length;i++){
				diningCarMenuService.delDiningCarMenu(a[i]);
			}*/
			rst.setData(order);
			rst.setMsg("添加成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("添加失败");
			return rst;
		}
		return rst;
	}
	
	@RequestMapping(value = "/saveOrderMenu", produces = "application/json")
	private ResponseEntity saveOrderMenu(OrderMenu orderMenu) {
		
		ResponseEntity rst = new ResponseEntity();
		try {
			orderMenuService.saveOrderMenu(orderMenu);
			rst.setMsg("添加成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("添加失败");
			return rst;
		}
		return rst;
	}
	
	@RequestMapping(value = "/updateOrder", produces = "application/json")
	private ResponseEntity updateOrder(String id,String msg) {
		
		ResponseEntity rst = new ResponseEntity();
		try {
			Order order=orderService.getOrder(id);
			if (msg.equals("送餐")||msg=="送餐") {
				order.setStatus("正在送餐");
				System.out.println("正在送餐");
			}else if(msg.equals("确认送达")||msg=="确认送达") {
				System.out.println("确认送达");
				order.setStatus("待评价");
			}else if(msg.equals("评价")||msg=="评价"){
				System.out.println("评价");
				order.setStatus("已完成");
			}
			orderService.updateOrder(order);
			rst.setMsg("成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("失败");
			return rst;
		}
		return rst;
	}
	
	
	@RequestMapping(value = "/queryOrderMenuList",method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity queryOrderMenuList(String userId, String msg) {
		System.out.println(msg+"   "+userId);
		ResponseEntity rst = new ResponseEntity();
		try {
			List<Order> orderList = orderService.queryOrderList(userId,msg);
			for(int i=0;i<orderList.size();i++){
				List<OrderMenu> meunList = orderMenuService.queryOrderMenuList(orderList.get(i).getOrderId());
				orderList.get(i).setOrderMenuList(meunList);
			}
			rst.setData(orderList);
			rst.setMsg("成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("失败");
			return rst;
		}
		return rst;
	}
	/**
	 * 根据订单号获取订单下的餐品
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/getOrderMenuList",method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getOrderMenuList(String orderId) {
		ResponseEntity rst = new ResponseEntity();
		try {
			List<OrderMenu> orderMenuList = orderMenuService.queryOrderMenuList(orderId);
			rst.setData(orderMenuList);
			rst.setMsg("成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("失败");
			return rst;
		}
		return rst;
	}
}
