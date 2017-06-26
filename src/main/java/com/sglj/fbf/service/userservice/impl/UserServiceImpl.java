package com.sglj.fbf.service.userservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.user.UserDao;
import com.sglj.fbf.entity.user.User;
import com.sglj.fbf.exception.ServiceException;
import com.sglj.fbf.service.userservice.UserService;

/** 
 * 前台用户Service层
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午5:09:58 
 * @version 1.0 
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userdao;
	@Override
	public Page queryUserList(String msg,Integer index, Integer size) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addDescOrder("firstInsert");
		queryRule.addEqual("isDeleted", Boolean.FALSE);
		if(StringUtils.isNoneBlank(msg)){
			queryRule.addSql("(USERNAME like '%" +  msg + "%' or GRADE like '%" +  msg + "%' or MOBILE like '%" + msg + "%')");
		}
		return userdao.find(queryRule, index, size);
	}
	@Override
	public String saveUser(User user) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("mobile",user.getMobile());
		List<User> list = userdao.find(queryRule);
		if(list.isEmpty()&list.size()==0){
			QueryRule queryRule1=QueryRule.getInstance();
			queryRule1.addEqual("username",user.getUsername());
			List<User> list2 = userdao.find(queryRule1);
			if(list2.isEmpty()&list2.size()==0){
				userdao.save(user);
				return "注册成功";
			}else {
				throw new ServiceException("该昵称已存在");
			}
			
		}else{
			throw new ServiceException("该手机号或邮箱已经注册过");
		}
	}
	@Override
	public List<User> loginInfo(String mobile, String pwd) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("mobile", mobile);
		queryRule.addEqual("password", pwd);
		
		return userdao.find(queryRule);
	}
	@Override
	public String updateUser(User user) {
		
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("username",user.getUsername());
		List<User> list = userdao.find(queryRule);
		if(list.isEmpty()&list.size()==0){
			System.out.println("11111");
			userdao.update(user);
			return "更新成功";
		}else {
			System.out.println("22222");
			throw new ServiceException("该昵称已存在");
		}
	}
	@Override
	public User getUser(String id) {
		return userdao.get(id);
	}

}
