package com.sglj.fbf.adapter.lwams;

import java.util.List;
import java.util.Map;

import com.sglj.fbf.adapter.lwams.help.req.JobGroup;
import com.sglj.fbf.adapter.lwams.help.req.ReqEntity;
import com.sglj.fbf.adapter.lwams.help.resp.NodeReader;


public interface LwamsInterface {
	
	/**
	 * 常量定义
	 * @author guanhongwei
	 *
	 */
	public interface Constant{
		/**
		 * 场景引擎访问地址组
		 */
		static String LWAMS_URL = "LWAMS_URL";
		/**
		 * 所有/已注册作业流程目录读取访问地址
		 */
		static String Nodes_URL= "Nodes_URL";
		
		/**
		 * 作业流程添加访问地址
		 */
		static String AddJobNet_URL = "AddJobNet_URL";
		
		/**
		 * 作业流程组添加访问地址
		 */
		static String AddJobGroup_URL = "AddJobGroup_URL";
		/**
		 * 获取注册获取去取消注册信息访问地址
		 */
		static String JobnetRegisterInfo_URL = "JobnetRegisterInfo_URL";
		/**
		 * 流程组注册访问地址
		 */
		static String Register_URL = "Register_URL";
		/**
		 * 流程组取消注册访问地址
		 */
		static String UnRegisterInfo_URL = "UnRegisterInfo_URL";
		/**
		 * 作业流程|作业流程组 删除访问地址
		 */
		static String DeleteJobNet_URL = "DeleteJobNet_URL";
		/**
		 * 作业流程|作业流程组 重命名访问地址
		 */
		static String RenameJobNet_URL = "RenameJobNet_URL";
		/**
		 * 作业流程编辑页面调用访问地址
		 */
		static String EditorGraph_View_URL = "EditorGraph_View_URL";
		/**
		 * 未处理消息数据读取访问地址
		 */
		static String UnProcessedList_URL = "UnProcessedList_URL";
		/**
		 * 已处理消息数据读取访问地址
		 */
		static String ProcessedList_URL = "ProcessedList_URL";
		/**
		 * 消息页面调用访问地址
		 */
		static String ShowMsgWin_URL = "ShowMsgWin_URL";
		/**
		 * 作业流程执行访问地址
		 */
		static String RunJobnet_URL = "RunJobnet_URL";
		/**
		 * 作业流程实例列表数据读取访问地址
		 */
		static String JobProcessList_URL = "JobProcessList_URL";
		/**
		 * 作业流程执行页面调用访问地址
		 */
		static String MonitorJob_Graph_URL = "MonitorJob_Graph_URL";
		/**
		 * 主机的增加访问地址
		 */
		static String AddHost_URL = "AddHost_URL";
		
		/**
		 * 主机的删除访问地址
		 */
		static String DelHost_URL = "DelHost_URL";
		/**
		 * 主机的编辑 主机属性的设置访问地址
		 */
		static String UPDATEHOST_URL = "UPDATEHOST_URL";
		/**
		 * 账户的增加访问地址
		 */
		static String ADDACCOUNT_URL = "ADDACCOUNT_URL";
		/**
		 * 账户的编辑访问地址
		 */
		static String UPDATEACCOUNT_URL = "UPDATEACCOUNT_URL";
		/**
		 * 账户的删除访问地址
		 */
		static String DELACCOUNT_URL = "DELACCOUNT_URL";
		/**
		 * 账户的查看访问地址
		 */
		static String ACCOUNTLIST_URL = "ACCOUNTLIST_URL";
		/**
		 * 所属机组的增加访问地址
		 */
		static String ADDGROUPHOST_URL = "ADDGROUPHOST_URL";
		/**
		 * 所属机组的移除访问地址
		 */
		static String REMOVEGROUPHOST_URL = "REMOVEGROUPHOST_URL";
		/**
		 * 所属机组的查看访问地址
		 */
		static String GROUPLISTOFHOST_URL = "GROUPLISTOFHOST_URL";
		/**
		 * 查看所有主机信息访问地址
		 */
		static String HOSTLIST_URL = "HOSTLIST_URL";
		/**
		 * 主机组管理增加访问地址
		 */
		static String ADDHOSTGROUP_URL = "ADDHOSTGROUP_URL";
		/**
		 * 主机组管理删除访问地址
		 */
		static String DELHOSTGROUP_URL = "DELHOSTGROUP_URL";
		/**
		 * 主机组管理编辑访问地址
		 */
		static String EDITHOSTGROUP_URL = "EDITHOSTGROUP_URL";
		/**
		 * 主机组管理查看访问地址
		 */
		static String GROUPLIST_URL = "GROUPLIST_URL";
		/**
		 * 作业流程导出访问地址
		 */
		static String EXPORT_DATA_URL = "EXPORT_DATA_URL";
		/**
		 * 作业流程导出访问地址
		 */
		static String IMPORT_DATA_URL = "IMPORT_DATA_URL";
		
		
		
	}
	/**
	 * (所有/已注册)作业流程目录读取
	 * @param req {category,id,name,account}
	 * @return  List<NodeReader>[{category:"",jobnet_uuid:"",unit_uuid:"",name:"",icon:"",isParent:true|false,attrs:{},menu:""|"n/a"}]
		isParent:是否作业流程组(isParent:是否是否作业流程组,icon 和 attrs 后台为获取到数据时则不会包含这两个属性)
	 */
	public List<NodeReader>  readInterfaceNodes(ReqEntity req);
	/**
	 * 作业流程添加
	 * @param req {id,name,isTmplGroup,account}
	 * @return  Map{success:true,uuid:””}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object>  addJobInterface(ReqEntity req);
	/**
	 * 作业流程组添加
	 * @param  {id,name,isTmplGroup,account}
	 * @return  Map{success:true,uuid:””}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> addJobGroupInterface(JobGroup jobGroup);
	/**
	 * 获取作业流程注册信息
	 * @param req {jobnet_uuid(id),account}
	 * @return Map{success: true,uuid:””,name:””,registered:true|false,group:”root” } |
	 * 			{success: true,uuid:””,name:””,registered:true|false,group:”group uuid”,group_id_path:”group_id_path” }
	 */
	public Map<String, Object> jobnetRegisterInfo(ReqEntity req);
	/**
	 * 流程组注册
	 * @param req {jobnet_uuid(uuid):作业流程uuid,account}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> registerInfo(ReqEntity req);
	/**
	 * 流程组取消注册
	 * @param req {jobnet_uuid(uuid):流程uuid,account}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object>  unRegisterInfo(ReqEntity req);
	/**
	 * 作业流程|作业流程组 删除
	 * @param req {uuid,type(job_group|job_net),account}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object>  deleteJobNetInfo(ReqEntity req);
	/**
	 * 作业流程|作业流程组 重命名
	 * @param req {uuid(作业流程|作业流程组 id),name(新的名称),type(job_group|job_net),account(登录用户名)}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object>  renameJobNetInfo(ReqEntity req);
	/**
	 * 作业流程编辑页面调用
	 * @param req {jobnet_uuid(uuid),unit_uuid,account(登录用户名)}
	 * @return LWAMS 系统作业流程编辑页面
	 */
	public String  editorGraphView(ReqEntity req);
	/**
	 * 未处理消息数据读取
	 * @param req {page:当前页码,rows:当前页码,account(登录用户名)}
	 * @return Map {total:0,rows:[{id:"",type:"消息提醒"|"数据填写"|...,time:"yyyy-MM-dd HH:mm:ss",sender:"",sender_uuid:"",sender_url:"",text:"",state:"未处理",isRead:true|false}],success:true}
	 */
	public Map<String, Object>  unProcessedList(ReqEntity req);
	/**
	 * 已处理消息数据读取
	 * @param req {page:当前页码,rows:当前页码,account(登录用户名)}
	 * @return Map{total:0,rows:[{id:"",type:"消息提醒"|"数据填写"|...,time:"yyyy-MM-dd HH:mm:ss",sender:"",sender_uuid:"",sender_url:"",text:"",opt_time:"yyyy-MM-dd HH:mm:ss"|"",opter:"处理人名字"|""}],success:true}
	 */
	public Map<String, Object>  processedList(ReqEntity req);
	/**
	 * 消息页面调用
	 * @param req {account(登录用户名)}
	 * @return String
	 */
	public String  showMsgWin(ReqEntity req);
	/**
	 * 作业流程执行
	 * @param req {jobnet_uuid:（uuid）作业流程 id,account(登录用户名)}
	 * @return Map:{process_uuid:””,success:true}|{ success:false, code:错误消息代码,message:错误消息}
	 */
	public Map<String, Object>  runJobnet(ReqEntity req);
	/**
	 * 作业流程实例列表数据读取
	 * @param req {jobnet_uuid(UUid):作业流程 id,page,rows,account(登录用户名)}
	 * @return Map{total:0,rows:[{uuid:"",name:"",time: "yyyy-MM-dd HH:mm:ss" ,statue:””}],success:true }
	 *				status:CREATED|SCHEDULED|RUNNING|PAUSED|CRASHED|NORMALEND|ABNORMALEND
	 */
	public Map<String, Object>  jobProcessList(ReqEntity req);
	/**
	 * 作业流程执行页面调用 (前置条件调用接口 已注册作业流程目录读取 获取)
	 * @param req {jobnet_uuid(UUid):作业流程 id,unit_uuid,proc_uuid,account(登录用户名)}
	 * @return LWAMS 系统作业流程执行页面
	 */
	public String  monitorJob(ReqEntity req);
	/**
	 * 主机的增加
	 * @param req {name:主机的名称。,ip主机的 id 地址。 eg： 121.41.52.158,os_family:主机的 OS 的类型,os_version:主机的 OS 的版本。,workdir工作的目录,type:更改的主机类型,remark:备注,account(登录用户名)}
	 * @return Map{success:true,uuid:””}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> addHost(ReqEntity req);
	/**
	 * 主机的删除
	 * @param req {uuid:主机 id，account}
	 * @return Map:{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> delHost(ReqEntity req);
	/**
	 * 主机属性的设置
	 * @param req {uuid:主机 id,name:更改的主机的名称。,ip:主机的 id,os_family:主机的 OS 的类型,os_version:主机的 OS 的版本。,workdir工作的目录,type_value(type):更改的主机类型,remark:备注,account(登录用户名)}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> updateHost(ReqEntity req);
	/**
	 * 账户的增加
	 * @param req {host_uuid:(uuid)主机 id,name:增加的用户名,passwd:增加的用户密码,account(登录用户名)}
	 * @return Map{success:true,id:新增账号id}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> addAccount(ReqEntity req);
	/**
	 * 账户的编辑
	 * @param req {id:账户ID,host_uuid:(uuid)主机 id,name:更改的用户名,passwd:更改的用户名密码,account(登录用户名)}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> updateAccount(ReqEntity req);
	/**
	 * 账户的删除
	 * @param req {id:账户ID,account(登录用户名)}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> delAccount(ReqEntity req);
	/**
	 * 账户的查看
	 * @param req {host_uuid:(uuid)主机id,account(登录用户名)}
	 * @return Map{
	 *		"total" : "",
	 *		"success" : "",
	 *		"rows" : [ {
	 *			"id" : "",
	 *			"name" : "",
	 * 			"pswd" :"",
	 *			"type" : "",
	 *		} ]
	 *	}
	 *	备注:rows可能为空
	 */
	public Map<String, Object> accountList(ReqEntity req);
	/**
	 * 所属机组的增加
	 * @param req {host_group_uuid:(id)主机组ID,host_uuid:(uuid)主机ID,account(登录用户名)}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> addGroupHost(ReqEntity req);
	/**
	 * 所属机组的移除
	 * @param req {host_group_uuid:(id)主机组ID,host_uuid:(uuid)主机ID,account(登录用户名)}
	 * @return Map{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> removeGroupHost(ReqEntity req);
	/**
	 * 所属机组的查看
	 * @param req {host_uuid:(uuid)主机ID,account(登录用户名)}
	 * @return Map{
	 *			"total" : "",
	 *			"success" : "",
	 *			"rows" : [{
	 *				“uuid”:"",
	 *				"name":"",
	 *				"hostNr":"",
	 *				"remark":"",
	 *				}]
	 *			}
	 *		备注:rows可能为空
	 */
	public Map<String, Object> groupListOfHost(ReqEntity req);
	/**
	 * 查看所有主机信息
	 * @param req {page:当前页码,rows:当前页面显示条数,account(登录用户名)}
	 * @return Map{
	 *			"total" : "",
	 *			"success" : "",
	 *			"rows" : [ {
	 *				"os" : "",
	 *				"remark" : "",
	 *				"workdir" : "",
	 *				"name" : "",
	 *				"type_value" : "",
	 *				"uuid" : "",
	 *				"type" : "",
	 *				"os_family" : "",
	 *				"os_version" : "",
	 *				"ip" : ""
	 *			}]
	 *		}
	 *		备注:rows可能为空
	 */
	public Map<String, Object> hostList(ReqEntity req);
	/**
	 * 主机组管理增加
	 * @param req {name:主机组名称,remark:主机组备注,account(登录用户名)}
	 * @return Map:{success:true,uuid:””}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> addHostGroup(ReqEntity req);
	/**
	 * 主机组管理删除
	 * @param req {host_group_uuid:(uuid)主机组ID,account(登录用户名)}
	 * @return Map:{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> delHostGroup(ReqEntity req);
	/**
	 * 主机组管理编辑
	 * @param req {host_group_uuid:(uuid)主机组ID,name:修改主机组名称,remark:备注,account(登录用户名)}
	 * @return Map:{success:true}|{success: false,code:错误代码,message:错误消息}
	 */
	public Map<String, Object> editHostGroup(ReqEntity req);
	/**
	 * 主机组管理查看
	 * @param req {page:当前页码,rows:当前页面显示条数,account(登录用户名)}
	 * @return Map调用成功:{
			"total" : "",
			"success" : "",
			"rows" : [ {
				"hostNr" : "",
				"remark" : "",
				"name" : "",
				"uuid" : ""
				} ]
			}
			备注:rows可能为空
	 */
	public Map<String, Object> groupList(ReqEntity req);
	/**
	 * 作业流程导出
	 * @param req {node_uuid:(uuid)作业流程 uuid,type:job_net(已赋值),account(登录用户名),file_name：导出文件名（uuid）}
	 * @return xml{存为dat类型文件}
	 */
	public String exportData(ReqEntity req);
	/**
	 * 作业流程导入
	 * @param req {node_uuid:(uuid)作业流程 uuid,type:job_net,account(登录用户名),filePath:文件路径}
	 * @return xml{存为dat类型文件}
	 */
	public Map<String, Object> importData(ReqEntity req);
	
	
	
	

}
