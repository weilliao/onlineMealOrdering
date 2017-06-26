package com.sglj.fbf.service.loginservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.logindao.SysUserDao;
import com.sglj.fbf.entity.sysuser.SysUser;
import com.sglj.fbf.service.loginservice.LoginService;

@Service("service.loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name="logindao.sysUserDao")
	private SysUserDao sysUserDao;
	

	
 	@Override
	public List<SysUser> loginInfo(String userName, String pwd) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("username", userName);
		queryRule.addEqual("password", pwd);
		
		return sysUserDao.find(queryRule);
	}
 	
 

}
