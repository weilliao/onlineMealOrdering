package com.sglj.fbf.interceptor;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.sglj.fbf.annotation.Authorization;
import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.SysConstant;
import com.sglj.fbf.entity.token.TokenModel;
import com.sglj.fbf.entity.token.TokenPool;
import com.sglj.fbf.utils.SessionUtils;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";


    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权信息的无用前缀，默认为空
    private String httpHeaderPrefix = "";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    public void setHttpHeaderName(String httpHeaderName) {
        this.httpHeaderName = httpHeaderName;
    }

    public void setHttpHeaderPrefix(String httpHeaderPrefix) {
        this.httpHeaderPrefix = httpHeaderPrefix;
    }

    public void setUnauthorizedErrorMessage(String unauthorizedErrorMessage) {
        this.unauthorizedErrorMessage = unauthorizedErrorMessage;
    }
    
    //在controller之前拦截请求
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
    	
    	//设置返回头,强制浏览器刷新
    	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    	response.setHeader("Expires", "0");
    	response.setHeader("Pragma","no-cache");
    	response.setHeader("Content-Type", "application/json;charset=UTF-8");
    	
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String t = (String) request.getParameter(httpHeaderName);
        String token = StringUtils.isNotBlank(t) ? t : request.getHeader(httpHeaderName);
        TokenModel model=TokenPool.getToken(token);
        String stutas=model.getStatus();
        String msg=model.getMsg();
        if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0) {
            token = token.substring(httpHeaderPrefix.length());
            //验证token
            /*TokenModel model=TokenPool.getToken(token);
            String stutas=model.getStatus();
            String msg=model.getMsg();*/
    		if((SysConstant.LOGIN_STATUS.Valid).equals(stutas)){
    			 //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(REQUEST_CURRENT_KEY, model);
                SessionUtils.setUser(model.getLoginUser());
                return true;
    		}
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null   //查看方法上是否有注解
                || handlerMethod.getBeanType().getAnnotation(Authorization.class) != null) {    //查看方法所在的Controller是否有注解
            //response.setStatus(unauthorizedErrorCode);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), SysConstant.ENCODING.UTF8));
            ResponseEntity ret=new ResponseEntity();
            ret.setStatus(SysConstant.LOGIN_STATUS.Invalid);
            ret.setError(SysConstant.SYS_CODE.TokenError);
            System.out.println(msg);
            ret.setMsg(msg);
            writer.write(JSONObject.toJSONString(ret));
            writer.close();
            return false;
        }
        //为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }
}
