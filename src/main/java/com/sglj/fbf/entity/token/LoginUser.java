package com.sglj.fbf.entity.token;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 已登录用户信息
 * @author guanhongwei
 *
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class LoginUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5678850668157195266L;
	
	private String id;
    private String userId;
    private String userName;
    private String realName;
    private Date loginTime;
    private String loginIp;
    //用户拥有资源权限集合
    private List<Map<String, String>> resource = Lists.newArrayList();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public List<Map<String, String>> getResource() {
		return resource;
	}
	public void setResource(List<Map<String, String>> resource) {
		this.resource = resource;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
