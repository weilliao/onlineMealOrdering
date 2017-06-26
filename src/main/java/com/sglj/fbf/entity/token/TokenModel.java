package com.sglj.fbf.entity.token;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * tokenModel对象
 * @author guanhongwei
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class TokenModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6133259701505024985L;
	
	/**
	 * token的状态
	 */
	public static final String TOKEN_ERROR = "0";
	
	/**
	 * token的状态
	 */
	public static final String TOKEN_OK = "1";
	
    /**用户的登录名*/
    private String userName;
    /**生成一个唯一的token值*/
    private String token;
    /**时间*/
    private Date time;
    /**token的状态0错误，1有效*/
    private String status;
    /**返回到页面的错误信息*/
    private String msg;
    /**登录用户信息**/
    private LoginUser loginUser;
    
    public TokenModel(){
    	
    }
    
    public TokenModel(String userName, Date time){
    	this.userName = userName;
    	this.time = time;
    }
    
    public TokenModel(String userName, Date time, String token, String status){
    	this.userName = userName;
    	this.time = time;
    	this.token = token;
    	this.status = status;
    }
    
    public TokenModel(LoginUser loginUser, Date time, String token, String status){
    	this.loginUser = loginUser;
    	this.time = time;
    	this.token = token;
    	this.status = status;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getTokenError() {
		return TOKEN_ERROR;
	}

	public static String getTokenOk() {
		return TOKEN_OK;
	}
    
    
    
}
