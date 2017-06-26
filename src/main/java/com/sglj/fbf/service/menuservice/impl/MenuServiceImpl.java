package com.sglj.fbf.service.menuservice.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.menudao.MenuDao;
import com.sglj.fbf.entity.menu.Menu;
import com.sglj.fbf.service.menuservice.MenuService;

/** 
 * @author  wei 
 * @date 创建时间：2017年1月11日 下午2:06:58 
 * @version 1.0 
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	
	
	public Menu getMenuById(String id) {
		return menuDao.get(id);
	}

	@Override
	public Page queryMenuList(String msg, int pageIndex, int pageSize) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addDescOrder("lastModified");
		if(StringUtils.isNoneBlank(msg)){
			queryRule.addSql("(NAME like '%" +  msg + "%' or CATEGORY like '%" +  msg + "%' or REMARK like '%" + msg + "%')");
		}
		return menuDao.find(queryRule, pageIndex, pageSize);
	}
	@Override
	public Page queryQtMenuList(String msg, int pageIndex, int pageSize) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addDescOrder("lastModified");
		queryRule.addEqual("isDeleted", Boolean.TRUE);
		if(StringUtils.isNoneBlank(msg)){
			queryRule.addSql("(NAME like '%" +  msg + "%' or CATEGORY like '%" +  msg + "%' or REMARK like '%" + msg + "%')");
		}
		return menuDao.find(queryRule, pageIndex, pageSize);
	}

	@Override
	public void addMenu(Menu menu) {
		menuDao.save(menu);
		
	}

	@Override
	public void updateMunu(Menu menu) {
		// TODO Auto-generated method stub
		menuDao.update(menu);
	}

	@Override
	public void removeMunu(String menuId) {
		menuDao.deleteById(menuId);
	}

	@Override
	public void removeMunu(String menuId, boolean isXiaJia) {
		Menu menu = menuDao.get(menuId);
		//设置为下架
		menu.setIsDeleted(isXiaJia);
		menuDao.update(menu);
		
	}

	@Override
	public Page getMenuList(String category,int pageIndex, int pageSize) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addDescOrder("lastModified");
		queryRule.addEqual("CATEGORY", category);
		return menuDao.find(queryRule, pageIndex, pageSize);
	}

	@Override
	public Page getQtMenuList(String category, int pageIndex, int pageSize) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addDescOrder("lastModified");
		//判断是不是没有下架
		queryRule.addEqual("isDeleted", Boolean.TRUE);
		queryRule.addEqual("CATEGORY", category);
		return menuDao.find(queryRule, pageIndex, pageSize);
	}

}
