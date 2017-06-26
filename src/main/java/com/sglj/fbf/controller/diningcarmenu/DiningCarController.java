package com.sglj.fbf.controller.diningcarmenu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.entity.diningcar.DiningCarMenu;
import com.sglj.fbf.entity.menu.Menu;
import com.sglj.fbf.entity.user.User;
import com.sglj.fbf.service.diningcarservice.DiningCarMenuService;
import com.sglj.fbf.service.menuservice.MenuService;
import com.sglj.fbf.service.userservice.UserService;

/** 
 * 餐车控制层
 * @author  wei 
 * @date 创建时间：2017年2月20日 上午10:22:15 
 * @version 1.0 
 */
@RestController
@RequestMapping("/diningCar")
public class DiningCarController {
	@Resource
	private DiningCarMenuService diningCarMenuService;
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	/**
	 * 将菜品添加进餐车
	 * @return
	 */
	@RequestMapping(value = "/saveDiningCarMeun", produces = "application/json")
	private ResponseEntity addDiningCarMeun(String userId,String meunId,int num) {
		ResponseEntity rst = new ResponseEntity();
		try {
			DiningCarMenu diningCarMenu = new DiningCarMenu();
			diningCarMenu.setUser(userService.getUser(userId));
			Menu menu=menuService.getMenuById(meunId);
			diningCarMenu.setMenuId(meunId);
			diningCarMenu.setMenuPic(menu.getMenuPic());
			diningCarMenu.setName(menu.getName());
			diningCarMenu.setPrice(menu.getPrice());
			diningCarMenu.setMenuNum(num);
			System.out.println(diningCarMenu.toString());
			diningCarMenuService.addDiningCarMenu(diningCarMenu);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	/**
	 * 查询用户的餐车列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryDiningCarMeunList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity queryDiningCarMeunList(String userId) {
		ResponseEntity rst = new ResponseEntity();
		try {
			User user = userService.getUser(userId);
			List<DiningCarMenu> list = diningCarMenuService.queryDiningCarMeunList(user);
			rst.setData(list);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	
	/**
	 * 删除餐车中的菜品
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delDiningCarMeun", produces = "application/json")
	private ResponseEntity delDiningCarMeun(String id) {
		ResponseEntity rst = new ResponseEntity();
		try {
			diningCarMenuService.delDiningCarMenu(id);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	/**
	 * 更新餐车中的菜品
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateDiningCarMeun", produces = "application/json")
	private ResponseEntity updateDiningCarMeun(String id,int num) {
		ResponseEntity rst = new ResponseEntity();
		try {
			DiningCarMenu diningCarMenu = diningCarMenuService.getDiningCarMenu(id);
			diningCarMenu.setMenuNum(num);
			diningCarMenuService.updateDiningCarMenu(diningCarMenu);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	
	/**
	 * 查询餐车中的菜品
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getDiningCarMeun", produces = "application/json")
	private ResponseEntity getDiningCarMeun(String id) {
		ResponseEntity rst = new ResponseEntity();
		try {
			DiningCarMenu diningCarMenu = diningCarMenuService.getDiningCarMenu(id);
			rst.setData(diningCarMenu);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	
	
	
}
