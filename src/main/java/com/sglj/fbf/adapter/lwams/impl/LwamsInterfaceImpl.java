package com.sglj.fbf.adapter.lwams.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sglj.fbf.adapter.lwams.LwamsInterface;
import com.sglj.fbf.adapter.lwams.help.connection.ConnectionInfoUtils;
import com.sglj.fbf.adapter.lwams.help.req.JobGroup;
import com.sglj.fbf.adapter.lwams.help.req.ReqEntity;
import com.sglj.fbf.adapter.lwams.help.resp.NodeReader;
import com.sglj.fbf.constant.AppCacheConstant;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.utils.DateUtil;

/**
 * 场景接口
 * @author guanhongwei
 *
 */
@Service("lwams.lwamsInterface")
public class LwamsInterfaceImpl implements LwamsInterface{
	
	private static Logger log = Logger.getLogger(LwamsInterface.class);
	@Override
	public List<NodeReader>  readInterfaceNodes(ReqEntity req) {
		String url = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.Nodes_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("id", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getCategory())){
				map.put("category", req.getCategory());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				List<NodeReader> respList = JSONObject.parseArray(responseMsg,NodeReader.class);//将建json对象转换为Person对象  
				log.info("readInterfaceNodes:调用success");
				return respList;
			}else{
				log.info("readInterfaceNodes:调用error");
				log.info("readInterfaceNodes:调用http返回为 ："+h);
			}
		}
		return null;
		
	}
	@Override
	public Map<String, Object> addJobInterface(ReqEntity req) {
		String url = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.AddJobNet_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("id", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
				map.put("isTmpl", req.isTmplGroup());
			
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}else{
				log.info("readInterfaceNodes:调用error");
				log.info("readInterfaceNodes:调用http返回为 ："+h);
			}
		}
		return null;
		
	}
	@Override
	public Map<String, Object> addJobGroupInterface(JobGroup jobGroup) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.AddJobGroup_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		jobGroup.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=jobGroup){
			if(StringUtils.isNoneBlank(jobGroup.getId())){
				map.put("id", jobGroup.getId());
			}
			if(StringUtils.isNoneBlank(jobGroup.getName())){
				map.put("name", jobGroup.getName());
			}
				map.put("isTmplGroup", jobGroup.isTmplGroup());
			
			if(StringUtils.isNoneBlank(jobGroup.getAccount())){
				map.put("account", jobGroup.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				log.info("addJobGroupInterface:调用success");
				return respMap;
			}else{
				log.info("addJobGroupInterface:调用error");
				log.info("addJobGroupInterface:调用http返回为 ："+h);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object>  jobnetRegisterInfo(ReqEntity req) {
		//方法返回对象/接口接受对象
//		RespEntity response=new RespEntity();
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.JobnetRegisterInfo_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("jobnet_uuid", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> registerInfo(ReqEntity req) {
		//方法返回对象/接口接受对象
//		RespEntity response=new RespEntity();
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.Register_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("jobnet_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> unRegisterInfo(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.UnRegisterInfo_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("jobnet_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteJobNetInfo(ReqEntity req) {
		//方法返回对象/接口接受对象
//		RespEntity response=new RespEntity();
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.DeleteJobNet_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getType())){
				map.put("type", req.getType());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> renameJobNetInfo(ReqEntity req) {
		//方法返回对象/接口接受对象
//		RespEntity response=new RespEntity();
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.RenameJobNet_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getType())){
				map.put("type", req.getType());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public String editorGraphView(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.EditorGraph_View_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("jobnet_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getUnit_uuid())){
				map.put("unit_uuid", req.getUnit_uuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				return responseMsg;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> unProcessedList(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.UnProcessedList_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getPage())){
				map.put("page", req.getPage());
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("rows", req.getRows());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> processedList(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.ProcessedList_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getPage())){
				map.put("page", req.getPage());
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("rows", req.getRows());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	@Override
	public String showMsgWin(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.ShowMsgWin_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				return responseMsg;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> runJobnet(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.JobProcessList_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("jobnet_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> jobProcessList(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.JobProcessList_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getPage())){
				map.put("page", req.getPage());
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("rows", req.getRows());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public String monitorJob(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.MonitorJob_Graph_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("jobnet_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getUnit_uuid())){
				map.put("unit_uuid", req.getUnit_uuid());
			}
			if(StringUtils.isNoneBlank(req.getProc_uuid())){
				map.put("proc_uuid", req.getProc_uuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				return responseMsg;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> addHost(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.AddHost_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getIp())){
				map.put("ip", req.getIp());
			}
			if(StringUtils.isNoneBlank(req.getOs_family())){
				map.put("os_family", req.getOs_family());
			}
			if(StringUtils.isNoneBlank(req.getOs_version())){
				map.put("os_version", req.getOs_version());
			}
			if(StringUtils.isNoneBlank(req.getWorkdir())){
				map.put("workdir", req.getWorkdir());
			}
			if(StringUtils.isNoneBlank(req.getType())){
				map.put("type", req.getType());
			}
			if(StringUtils.isNoneBlank(req.getRemark())){
				map.put("remark", req.getRemark());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> delHost(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.DelHost_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("uuid", req.getUuid());
			}
		}
		if(StringUtils.isNoneBlank(req.getAccount())){
			map.put("account", req.getAccount());
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> updateHost(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.UPDATEHOST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getIp())){
				map.put("ip", req.getIp());
			}
			if(StringUtils.isNoneBlank(req.getOs_family())){
				map.put("os_family", req.getOs_family());
			}
			if(StringUtils.isNoneBlank(req.getOs_version())){
				map.put("os_version", req.getOs_version());
			}
			if(StringUtils.isNoneBlank(req.getWorkdir())){
				map.put("workdir", req.getWorkdir());
			}
			if(StringUtils.isNoneBlank(req.getType())){
				map.put("type_value", req.getType());
			}
			if(StringUtils.isNoneBlank(req.getRemark())){
				map.put("remark", req.getRemark());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> addAccount(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.ADDACCOUNT_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getPasswd())){
				map.put("passwd", req.getPasswd());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> updateAccount(ReqEntity req) {
		
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.UPDATEACCOUNT_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("id", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getPasswd())){
				map.put("passwd", req.getPasswd());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> delAccount(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.DELACCOUNT_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("id", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> accountList(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.ACCOUNTLIST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> addGroupHost(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.ADDGROUPHOST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("host_group_uuid", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> removeGroupHost(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.REMOVEGROUPHOST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getId())){
				map.put("host_group_uuid", req.getId());
			}
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> groupListOfHost(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.GROUPLISTOFHOST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> hostList(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.HOSTLIST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getPage())){
				map.put("page", req.getPage());
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("rows", req.getRows());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> addHostGroup(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.ADDHOSTGROUP_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getRemark())){
				map.put("remark", req.getRemark());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> delHostGroup(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.DELHOSTGROUP_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_group_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> editHostGroup(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.EDITHOSTGROUP_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("host_group_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getName())){
				map.put("name", req.getName());
			}
			if(StringUtils.isNoneBlank(req.getRemark())){
				map.put("remark", req.getRemark());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> groupList(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.GROUPLIST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getPage())){
				map.put("page", req.getPage());
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("rows", req.getRows());
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}
	@Override
	public String exportData(ReqEntity req) {
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.GROUPLIST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		String fileName= "";
		//接口方法入参
		Map<String, Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("node_uuid", req.getUuid());
				fileName=req.getUuid();
			}
			if(StringUtils.isNoneBlank(req.getFileName())){
				fileName=req.getFileName();
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("type", BusiConstant.JOBNET);
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
		}
		//获取当前时间
		String date=DateUtil.getCurrentTimeByDefaultPattern();
		//组成url：http://ip:port/LWAMS/jobnet/export/interfaceExport_data/{timestamp}/{file_name}
		url=url+"/"+date+"/"+fileName;
		String responseMsg="";
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttp(url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				return responseMsg;
			}
		}
		return null;
	}
	@Override
	public Map<String, Object> importData(ReqEntity req) {
		//作业流程组添加访问地址
		String url=AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, LwamsInterface.Constant.GROUPLIST_URL);
		//登录用户名取设置数据库的数据
		String account = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(LwamsInterface.Constant.LWAMS_URL, BusiConstant.ACCOUNT_NAME);
		req.setAccount(account);
		String filePath="";
		Map<String , Object> map=new HashMap<String, Object>();
		if(null!=req){
			if(StringUtils.isNoneBlank(req.getUuid())){
				map.put("node_uuid", req.getUuid());
			}
			if(StringUtils.isNoneBlank(req.getRows())){
				map.put("type",  BusiConstant.JOBNET);
			}
			if(StringUtils.isNoneBlank(req.getAccount())){
				map.put("account", req.getAccount());
			}
			if(StringUtils.isNoneBlank(req.getFileName())){
				filePath=req.getFileName();
			}
		}
		Map<String , Object> responseMap = ConnectionInfoUtils.postHttpFileIO(url, map, filePath);
		String responseMsg="";
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h){
				responseMsg=(java.lang.String) responseMap.get("responseMsg");
				@SuppressWarnings("unchecked")
				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
				return respMap;
			}
		}
		return null;
	}

	

	

}
