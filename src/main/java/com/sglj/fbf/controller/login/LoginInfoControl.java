package com.sglj.fbf.controller.login;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.sglj.fbf.base.BaseController;
import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.entity.sysuser.SysUser;
import com.sglj.fbf.entity.token.LoginUser;
import com.sglj.fbf.entity.token.TokenPool;
import com.sglj.fbf.service.loginservice.LoginService;

@RestController
@RequestMapping("/logininfo")
public class LoginInfoControl extends BaseController{
	
	@Resource(name="service.loginService")
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping(value = "/logininfo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity loginInfo(SysUser user){
		ResponseEntity response = new ResponseEntity();
		String userName=user.getUsername();
		String pwd=user.getPassword();
		System.out.println(userName+" "+pwd);
		if(StringUtils.isBlank(userName)){
			response.setStatus(BusiConstant.STATUS_ERROR);
			response.setMsg("请输入用户名");
			return response;
		}
		if(StringUtils.isBlank(pwd)){
			response.setStatus(BusiConstant.STATUS_ERROR);
			response.setMsg("请输入密码");
			return response;
		}
		//根据登陆用户用户账号查询
		List<SysUser> sysUserList= loginService.loginInfo(userName, pwd);
		if(sysUserList.isEmpty()&sysUserList.size()==0){
			response.setStatus(BusiConstant.STATUS_ERROR);
			response.setMsg("登陆账号或密码错误！");
		}else{
			SysUser dbUser=  sysUserList.get(0);
			if(dbUser!=null){
				boolean flag= dbUser.getIsDeleted();
				//判断是否已经被删除
				if(flag){
					response.setStatus(BusiConstant.STATUS_ERROR);
					response.setMsg("用户账号已被注销，无法登陆！");
				}else{
							response.setStatus(BusiConstant.STATUS_SUCCESS);
							//登录成功，将相关用户信息加入TokenPool，便于其他请求校验
							LoginUser logindbUser=new LoginUser();
							logindbUser.setUserName(userName);
							logindbUser.setUserId(dbUser.getId());
							//登录token
							String token = TokenPool.createToken(logindbUser);
							Map<String, Object> rst = Maps.newConcurrentMap();
							rst.put("token", token);
							rst.put("resource", logindbUser.getResource());
							rst.put("username", userName);
							response.setData(rst);
				}
			}
		}
		return response;
		
	}
	
	

}
