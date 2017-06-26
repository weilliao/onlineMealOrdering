package com.sglj.fbf.service.diningcarservice;

import java.util.List;

import com.sglj.fbf.entity.diningcar.DiningCarMenu;
import com.sglj.fbf.entity.user.User;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月19日 下午9:23:50 
 * @version 1.0 
 */
public interface DiningCarMenuService{
	/**
	 * 添加菜品进购物车
	 * @param diningCarMenu
	 */
	public void addDiningCarMenu(DiningCarMenu diningCarMenu);
	/**
	 * 查询用户餐车中的菜品
	 * @param user
	 * @return
	 */
	public List<DiningCarMenu> queryDiningCarMeunList(User user);
	/**
	 * 删除餐车中的菜品（根据菜品id删除）
	 * @param diningCarMenuId
	 */
	public void delDiningCarMenu(String diningCarMenuId);
	/**
	 * 更新餐车中的菜品（根据菜品id更新）
	 * @param diningCarMenuId
	 */
	public void updateDiningCarMenu(DiningCarMenu diningCarMenu);
	
	/**
	 * 查询菜品（根据菜品id更新）
	 * @param diningCarMenuId
	 */
	public DiningCarMenu getDiningCarMenu(String id);

}
