package com.sglj.fbf.service.menuservice;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.entity.menu.Menu;

/** 
 * @author  wei 
 * @date 创建时间：2017年1月11日 下午2:05:14 
 * @version 1.0 
 */
public interface MenuService {
	/**
	 * 根据id查询菜品信息
	 * @param id
	 * @return
	 */
	public Menu getMenuById(String id);
	

	/**
	 * 后台根据查询信息查询菜品集合：包括已下架的菜品（模糊查询、分页）
	 * @param msg
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page queryMenuList(String msg,int pageIndex, int pageSize);
	
	/**
	 * 前台台根据查询信息查询菜品集合：不包括已下架的菜品（模糊查询、分页）
	 * @param msg
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page queryQtMenuList(String msg,int pageIndex, int pageSize);
	/**
	 * 后台根据类别查询菜品信息
	 * @param category
	 * @return
	 */
	public Page getMenuList(String category,int pageIndex, int pageSize);
	
	/**
	 * 前台根据类别查询菜品信息
	 * @param category
	 * @return
	 */
	public Page getQtMenuList(String category,int pageIndex, int pageSize);
	
	/**
	 * 增加菜品
	 * @param menu
	 */
	public void addMenu(Menu menu);
	/**
	 * 更新菜品信息
	 * @param menu
	 */
	public void updateMunu(Menu menu);
	
	/**
	 * 删除菜品信息
	 * @param menu
	 */
	public void removeMunu(String menuId);
	
	/**
	 * 菜品下架或上架
	 * @param menu
	 */
	public void removeMunu(String menuId,boolean isXiaJia);

}
