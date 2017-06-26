package com.sglj.fbf.service.userservice;

import java.util.List;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.entity.user.User;

/** 
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午5:07:59 
 * @version 1.0 
 */
public interface UserService {
	/**
	 * 后台获取用户列表
	 * 根据queryRule查询
	 * @param queryRule
	 * @return
	 */
	/**
	 * 根据用户昵称，邮箱，电话号码，用户等级模糊查询用户
	 * @param mag
	 * @param index
	 * @param size
	 * @return
	 */
	public Page queryUserList(String mag,Integer index, Integer size);
	
	/**
	 * 注册（增加新用户）
	 * @param user
	 * @return 提示语
	 */
	public String saveUser(User user);
	/**
	 * 登录
	 * @param mobile 手机号或邮箱号
	 * @param pwd 密码
	 * @return
	 */
	public List<User> loginInfo(String mobile, String pwd);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public String updateUser(User user);
	
	/**
	 * 根据用户id查询用户
	 * @param id
	 */
	public User getUser(String id);
}
