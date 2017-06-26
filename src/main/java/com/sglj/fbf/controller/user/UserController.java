package com.sglj.fbf.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.common.collect.Maps;
import com.sglj.fbf.base.BaseController;
import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.AppCacheConstant;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.entity.token.LoginUser;
import com.sglj.fbf.entity.token.TokenPool;
import com.sglj.fbf.entity.user.User;
import com.sglj.fbf.service.userservice.UserService;
import com.sglj.fbf.utils.DelFile;
import com.sglj.fbf.utils.FileUtil;

/**
 * @author wei
 * @date 创建时间：2016年12月28日 下午8:21:39
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;

	/**
	 * 后台获取用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getUserList(String msg,int index,int pagesize) {
		ResponseEntity rst = new ResponseEntity();
		try {
			if (StringUtils.isBlank(msg)) {
				msg=null;
			}
			System.out.println("MSG:"+msg+"INDEX:"+index+"PAGESIZE:"+pagesize);
			Page page = userService.queryUserList(msg,index,pagesize);
			System.out.println("总条数"+page.getTotalCount()+"总页数"+page.getTotalPage());
			rst.setData(page);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}

	/**
	 * 前台注册
	 */
	@RequestMapping(value = "/saveUser", produces = "application/json")
	private ResponseEntity saveUser(User user) {
		/*
		 * User user2=new User(); user2.setUsername("wei");
		 * user2.setMobile("157795757899"); user2.setPassword("wwi");
		 */
		System.out.println(user.getUsername() + "  " + user.getMobile() + "  " + user.getPassword());
		ResponseEntity rst = new ResponseEntity();
		try {
			String msg = userService.saveUser(user);
			rst.setMsg(msg);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}

	// 前台登录
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity loginInfo(User user) {
		ResponseEntity response = new ResponseEntity();
		String mobile = user.getMobile();
		String pwd = user.getPassword();
		System.out.println(mobile + " " + pwd);
		if (StringUtils.isBlank(mobile)) {
			response.setStatus(BusiConstant.STATUS_ERROR);
			response.setMsg("请输入手机号或邮箱");
			return response;
		}
		if (StringUtils.isBlank(pwd)) {
			response.setStatus(BusiConstant.STATUS_ERROR);
			response.setMsg("请输入密码");
			return response;
		}
		// 根据登陆用户用户账号查询
		List<User> userList = userService.loginInfo(mobile, pwd);
		if (userList.isEmpty() & userList.size() == 0) {
			response.setStatus(BusiConstant.STATUS_ERROR);
			response.setMsg("手机号或邮箱号与密码不匹配");
		} else {
			User dbUser = userList.get(0);
			if (dbUser != null) {
				boolean flag = dbUser.getIsDeleted();
				// 判断是否已经被删除
				if (flag) {
					response.setStatus(BusiConstant.STATUS_ERROR);
					response.setMsg("用户账号已被注销，无法登陆！");
				} else {
					response.setStatus(BusiConstant.STATUS_SUCCESS);
					// 登录成功，将相关用户信息加入TokenPool，便于其他请求校验
					LoginUser logindbUser = new LoginUser();
					logindbUser.setUserName(mobile);
					logindbUser.setUserId(dbUser.getId());
					// 登录token
					String token = TokenPool.createToken(logindbUser);
					Map<String, Object> rst = Maps.newConcurrentMap();
					rst.put("token", token);
					rst.put("resource", logindbUser.getResource());
					rst.put("user", dbUser);
					response.setData(rst);
				}
			}
		}
		return response;
	}
	
	
	/**
	 * 获取用户信息
	 */
	@RequestMapping(value = "/getUser", produces = "application/json")
	private ResponseEntity getUser(String id,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			User user = userService.getUser(id);
			rst.setData(user);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	/**
	 * 前台用户完善个人信息
	 */
	@RequestMapping(value = "/updateUser", produces = "application/json")
	private int updateUser(User user,@RequestParam(value = "P1", required = false) CommonsMultipartFile P1,
			HttpServletRequest request, HttpServletResponse response) {
		String imgName=null;
		if (P1==null||P1.getOriginalFilename().length()==0) {
			System.out.println("不上传头像");
			try {
				User user2 = userService.getUser(user.getId());
				user2.setUsername(user.getUsername());
				user2.setPassword(user.getPassword());
				user2.setName(user.getName());
				user2.setGender(user.getGender());
				user2.setAddress(user.getAddress());
				user2.setPositionOtherInfo(user.getPositionOtherInfo());
				userService.updateUser(user2);
				
			} catch (Exception e) {
				return 1;
			}
		}else{
			System.out.println("上传头像");
			String USER_IMG = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(BusiConstant.USER_URL,
					BusiConstant.USER_IMG);
			try {
				User user3 = userService.getUser(user.getId());
				String old=user3.getHeaderPic();
				System.out.println(user3.getHeaderPic());
				user3.setUsername(user.getUsername());
				user3.setPassword(user.getPassword());
				user3.setName(user.getName());
				user3.setGender(user.getGender());
				user3.setAddress(user.getAddress());
				user3.setPositionOtherInfo(user.getPositionOtherInfo());
				
				System.out.println(USER_IMG);
				imgName=FileUtil.uploadFile(P1,"",USER_IMG, request);
				user3.setHeaderPic(imgName);
				userService.updateUser(user3);
				if (!old.equals("default.jpg")) {
					DelFile.delFile(USER_IMG+old);
				}
				
			} catch (Exception e) {
				DelFile.delFile(USER_IMG+imgName);
				return 1;
			}
		}
		return 0;
	}
	
}
