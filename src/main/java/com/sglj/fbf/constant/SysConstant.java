package com.sglj.fbf.constant;

/**
 * 系统级常量定义
 * @author guanhongwei
 *
 */
public interface SysConstant {
	
	/**
	 * 编码类型 
	 * @author guanhongwei
	 *
	 */
	public interface ENCODING {
		static String UTF8 = "UTF-8";
		
		static String GBK = "GBK";
	}
	
	/**
	 * xml声明
	 */
	public interface XML_HEAD {
		static String UTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		static String GBK =  "<?xml version=\"1.0\" encoding=\"GBK\"?>";
	}
	
	
	/**
	 * http request method - POST
	 */
	public interface HTTP_METHOD {
		static String GET = "GET";
		
		static String POST = "POST";
	}
	
	/**
	 * 
	 * 登录状态
	 * @author guanhongwei
	 *
	 */
	public interface LOGIN_STATUS {
		/**
		 * 失效
		 */
		static String Invalid = "0";
		
		/**
		 * 有效
		 */
		static String Valid = "1";
	}
	
	/**
	 * 系统级自身消息代码
	 * @author guanhongwei
	 *
	 */
	public interface SYS_CODE {
		/**
		 * 系统消息代码统一前缀
		 */
		static String Sys = "SYS";
		
		/**
		 * token 错误
		 */
		static String TokenError = Sys + "00000";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
