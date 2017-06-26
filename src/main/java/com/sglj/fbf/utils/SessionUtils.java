package com.sglj.fbf.utils;

import com.sglj.fbf.entity.token.LoginUser;

/**
 * session manage
 * 
 * @author guanhongwei
 * 
 */
public class SessionUtils {
	private static ThreadLocal<LoginUser> user = new ThreadLocal<LoginUser>();

	public static LoginUser getUser() {
		return (LoginUser) user.get();
	}

	public static void setUser(LoginUser parm) {
		user.set(parm);
	}

}
