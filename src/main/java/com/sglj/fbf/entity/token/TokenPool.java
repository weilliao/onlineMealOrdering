package com.sglj.fbf.entity.token;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.sglj.fbf.constant.SysConstant;

/**
 * token manage
 * @author guanhongwei
 *
 */
public class TokenPool {
	
	public static Map<String,TokenModel> tokenPool=new ConcurrentHashMap<String,TokenModel>();
	
	public static void putToken(String token,TokenModel tokenModel){
		tokenPool.put(token, tokenModel);
	}
	
	/**创建一个token*/
	public static String createToken(LoginUser loginUser) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        TokenModel model = new TokenModel(loginUser, new Date(), token, TokenModel.TOKEN_OK);
        //存储到内存，并设置当前时间
        tokenPool.put(token, model);
        return token;
    }
	
	/**根据key获取token对象*/
	public static TokenModel getToken(String key) {
    	TokenModel model=null;
        if (StringUtils.isBlank(key)) {
        	model=new TokenModel();
        	model.setStatus(SysConstant.LOGIN_STATUS.Invalid);
        	//该key不存在
        	model.setMsg("bucunzai");
        }else{
        	/**从我的内存中 通过key去拿我的token对象*/
            model=tokenPool.get(key);
            if(model==null) {
            	model=new TokenModel();
            	model.setStatus(SysConstant.LOGIN_STATUS.Invalid);
            	//该key是个无效key
            	model.setMsg("wuxiao");
            }else {
            	/**校验token是否有效*/
                boolean flag=TokenPool.checkToken(model);
                if(!flag){
                	model.setStatus(SysConstant.LOGIN_STATUS.Invalid);
                	//登录超时，请重新登录
                	model.setMsg("chaoshi");
                }else{
                	  model.setStatus(SysConstant.LOGIN_STATUS.Valid);
                      model.setMsg("success");
                }
            }
        }
        return model;
    }
	
    /**检验token是否失效*/
    public static boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        Date time=model.getTime();
        long lastTime=time.getTime();
        long currentTime=new Date().getTime();
        long result=currentTime-lastTime;
        if(result>0){
        	long s=result/1000/60;
        	/**如果超过30分钟，那么token失效*/
        	System.out.println("时间："+s);
        	if(s>30){
        		TokenPool.deleteToken(model.getToken());
        		return false;
        	}
        }else if(result<0){
        	return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，设置最新的系统时间
        model.setTime(new Date());
        return true;
    }
    
    /**移除token*/
    public static void deleteToken(String token) {
    	tokenPool.remove(token);
    }
}
