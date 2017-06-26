package com.sglj.fbf.service.loginservice;

import java.util.List;

import com.sglj.fbf.entity.sysuser.SysUser;

/**
 * 登陆业务逻辑层
 */
public interface LoginService {
	/**
	 * 根据用户账号密码查找
	 * @param userName
	 * @param PWD
	 * @return
	 */
	public List<SysUser> loginInfo(String userName ,String PWD);

	

}
