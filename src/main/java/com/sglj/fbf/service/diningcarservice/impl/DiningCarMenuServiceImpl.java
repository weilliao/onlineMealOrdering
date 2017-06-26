package com.sglj.fbf.service.diningcarservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.diningcardao.DiningCarMenuDao;
import com.sglj.fbf.entity.diningcar.DiningCarMenu;
import com.sglj.fbf.entity.user.User;
import com.sglj.fbf.service.diningcarservice.DiningCarMenuService;

/** 
 * @author  wei 
 * @date 创建时间：2017年2月19日 下午9:24:57 
 * @version 1.0 
 */
@Service
public class DiningCarMenuServiceImpl implements DiningCarMenuService {
	@Resource
	DiningCarMenuDao diningCarMenuDao;

	@Override
	public List<DiningCarMenu> queryDiningCarMeunList(User user) {
		QueryRule queryRule = new QueryRule();
		queryRule.addEqual("user", user);
		queryRule.addDescOrder("lastModified");
		return diningCarMenuDao.find(queryRule);
	}

	@Override
	public void delDiningCarMenu(String diningCarMenuId) {
		diningCarMenuDao.deleteById(diningCarMenuId);
	}

	@Override
	public void updateDiningCarMenu(DiningCarMenu diningCarMenu) {
		diningCarMenuDao.update(diningCarMenu);
	}

	@Override
	public void addDiningCarMenu(DiningCarMenu diningCarMenu) {
		QueryRule queryRule = new QueryRule();
		queryRule.addEqual("user", diningCarMenu.getUser());
		queryRule.addEqual("menuId", diningCarMenu.getMenuId());
		List<DiningCarMenu> list = diningCarMenuDao.find(queryRule);
		if (list.isEmpty()||list==null) {
			diningCarMenuDao.save(diningCarMenu);
		}else{
			DiningCarMenu diningCarMenu1 = list.get(0);
			diningCarMenu1.setMenuNum(diningCarMenu1.getMenuNum()+diningCarMenu.getMenuNum());
			diningCarMenuDao.update(diningCarMenu1);
		}
		
	}

	@Override
	public DiningCarMenu getDiningCarMenu(String id) {
		// TODO Auto-generated method stub
		return diningCarMenuDao.get(id);
	}
}
