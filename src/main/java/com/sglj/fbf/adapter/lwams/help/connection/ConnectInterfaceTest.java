package com.sglj.fbf.adapter.lwams.help.connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.sglj.fbf.adapter.lwams.help.resp.NodeReader;
import com.sglj.fbf.utils.DateUtil;


public class ConnectInterfaceTest {

	public static void main(String[] args) {
//		String Url="http://120.26.238.139:8080/LWAMS/job_mgnt/jobgroup/interfaceAddJobGroup.json";
//		String Url="http://120.26.238.139:8080/LWAMS/job_mgnt/jobgroup/interfaceAddJobnet.json";
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/interfaceJobnetRegisterInfo.json";
//		String Url="http://120.26.238.139:8080/LWAMS/job_mgnt/tree/interfaceNodes.json";
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/interfaceRegister.json";
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/interfaceUnRegisterInfo.json";
//		String Url="http://120.26.238.139:8080/LWAMS/job_mgnt/jobgroup/interfaceDelete.json";
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_acc/interfaceAddAccount.json";
//		String Url="http://120.26.238.139:8080/LWAMS/job_graph_view/interfaceEditor";//8作业流程编辑页面调用
//		String Url="http://120.26.238.139:8080/LWAMS/windget/main/msg_list/interfaceUnProcessedList.json";//9未处理消息数据读取 
//		String Url="http://120.26.238.139:8080/LWAMS/windget/main/msg_list/interfaceShowMsgWin";//11消息页面调用 
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/task/interfaceRun.json";//12作业流程执行 
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/job_process/interfaceJobProcessList.json";//13作业流程实例列表数据读取
//		String Url="http://120.26.238.139:8080/LWAMS/job_graph_view/interfaceMonitor";//14作业流程执行页面调用  (前置条件掉用接口2)
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host/interfaceAddHost.json";//15主机的增加
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host/interfaceDelHost.json";//16主机的删除
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host/interfaceUpdateHost.json";//17-1主机属性的设置
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_acc/interfaceAddAccount.json";//17-2账号的增加
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_acc/interfaceUpdateAccount.json";//17-3主机属性的设置(host_group_uuid)
		
		
		
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_permission/interfaceSetPermission.json";//17-6主机属性的设置
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceAddGroupHost.json";//17-12所属机组的增加
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceRemoveGroupHost.json";//17-13所属机组的移除
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceGroupListOfHost.json";//17-14所属机组的查看
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host/interfaceHostList.json";//18:查看所有主机信息
		
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceAddHostGroup.json";//19:主机组管理增加
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceDelHostGroup.json";//20:主机组管理删除
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceEditHostGroup.json";//21:主机组管理编辑
//		String Url="http://120.26.238.139:8080/LWAMS/sys_mgnt/host_group/interfaceGroupList.json";//22:主机组管理查看
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/logs/interfaceLogs";//23:作业流程执行日志页面调用   			    (已改为页面调用)
//		String Url="http://120.26.238.139:8080/LWAMS/report_view/interfaceReport_page";//24:报表查询页面调用(参数)			(已改为页面调用)
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/bask-logs/interfaceGetLogsList.json";//25:作业流程下所有实例日志获取(path待确认)
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/task-logs/interfaceProcessLogsList.json";//26单个实例所有作业节点日志获取
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/task-logs/interfaceUnitLogsList.json";//27:单个作业节点日志获取
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/job_process/interfaceJobProcessStateList.json";//28:当前用户所有注册的作业流程下实例信息获取
		String Url="http://120.26.238.139:8080/LWAMS/jobnet/task-logs/interfaceLoadLogFile.file";//2929:作业节点日志文件下载 
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/export/interfaceExport_data";//30:作业流程导出
//		String Url="http://120.26.238.139:8080/LWAMS/jobnet/export/interfaceImport_data.json";//30:作业流程导出
		
		String date=DateUtil.getCurrentTimeByDefaultPattern();
		String fileName="2322";
//		Url=Url+"/"+date+"/"+fileName;
		Map<String, Object> map =new HashMap<String, Object>();
//		map.put("id", "3");
//		map.put("name", "长沙");
//		map.put("isTmplGroup", false);
//		map.put("account", "admin");
		
//		map.put("id", "3");
//		map.put("name", "子电网");
//		map.put("isTmpl", true);
//		map.put("account", "admin");
		
//		map.put("jobnet_uuid", "d16526ac-c083-4507-abea-eff08212c1e0");
//		map.put("account", "admin");
		
//		map.put("id", "4fe8517c-cb3c-4850-b2d3-8f3ada636ea5");
//		map.put("name", "CV");
//		map.put("category", "job_editor");
//		map.put("account", "admin");
		
//		map.put("jobnet_uuid", "d16526ac-c083-4507-abea-eff08212c1e0");
//		map.put("account", "admin");
		
//		map.put("uuid", "1");
//		map.put("type", "job_group");
//		map.put("account", "admin");
		
//		map.put("host_uuid", "1");
//		map.put("name", "1234");
//		map.put("passwd", "1234");
//		map.put("account", "admin");
		
//		map.put("jobnet_uuid", "d8108bca-3037-443d-971e-a8f41f042fcd");
//		map.put("unit_uuid", "c2288fad-1e68-4da3-8751-ee82f28c534f");
//		map.put("contrl_hosts", "hosts1_name:qqqq");
//		map.put("account", "admin");
		//9
//		map.put("page", 1);
//		map.put("rows", 10);
//		map.put("account", "admin");
		
//		map.put("account", "admin");
		
//		map.put("jobnet_uuid", "197f4876-231b-4194-8c01-4d9a9b9440f9");
//		map.put("account", "admin");
		//-13
//		map.put("jobnet_uuid", "197f4876-231b-4194-8c01-4d9a9b9440f9");
//		map.put("page", 2);
//		map.put("rows", 10);
//		map.put("account", "admin");
		//-14
//		map.put("jobnet_uuid", "4edb89b3-1661-4d24-a974-51ef4d471ad5");
//		map.put("unit_uuid", "8bdccd85-207d-4aa8-be76-447ebb0e25d5");
//		map.put("proc_uuid", "");
//		map.put("account", "admin");
		//	-15	
//		map.put("name", "长城宽带332");
//		map.put("ip", "121.41.52.158");
//		map.put("os_family", "windows");
//		map.put("os_version", "win_server_2008");
//		map.put("workdir", "C:/");
//		map.put("type", "agent");
//		map.put("remark", "长城快带，癫痫的网络");
//		map.put("account", "admin");
		//	-16	
//		map.put("uuid", "432ddbe6-cbd4-4f49-946d-f20caf04be9b");
//		map.put("account", "admin");
//		//17-1
//		map.put("uuid", "6ff8aa00-5fc4-4950-ad88-071d71427c6d");
//		map.put("name", "长城宽带2");
//		map.put("ip", "121.41.52.158");
//		map.put("os_family", "windows");
//		map.put("os_version", "win_server_2008");
//		map.put("workdir", "C:/123");
//		map.put("type_value", "biz");
//		map.put("remark", "长城快带，疯狂的网络");
//		map.put("account", "admin");
//		//17-2
//		map.put("host_uuid", "6ff8aa00-5fc4-4950-ad88-071d71427c6d");
//		map.put("name", "biz");
//		map.put("passwd", "123456");
//		map.put("account", "admin");
//		//17-3
//		map.put("host_uuid", "6ff8aa00-5fc4-4950-ad88-071d71427c6d");
//		map.put("id", "196");
//		map.put("name", "admin");
//		map.put("passwd", "123456");
//		map.put("account", "admin");
//		//17-6
//		map.put("host_uuid", "62638cf4-13de-4a24-bfac-d3993dc4245a");
//		map.put("permission_id", "");
//		map.put("label", "USE_IN_JOBNET");
//		map.put("owner_type", "USE_IN_JOBNET");
//		map.put("owner_uuid", "");
//		map.put("permission_allow", "USE_IN_JOBNET");
//		map.put("account", "admin");
//		//17-12、13
//		map.put("host_uuid", "62638cf4-13de-4a24-bfac-d3993dc4245a");
//		map.put("host_group_uuid", "e67138fe-0ba3-4135-8a9d-241fd96f37b3");
//		map.put("account", "admin");
//		//17-14
//		map.put("host_uuid", "62638cf4-13de-4a24-bfac-d3993dc4245a");
//		map.put("account", "admin");
//		//-18
//		map.put("page", 1);
//		map.put("rows", 10);
//		map.put("account", "admin");
//		//19
//		map.put("name", "groupCP2");
//		map.put("remark", "groupCP2CP2CP2");
//		map.put("account", "admin");
//		//-20
//		map.put("host_group_uuid", "2cae5e2c-1d7b-4be5-a8ad-5340926c9a45");
//		map.put("account", "admin");
//		//-21
//		map.put("host_group_uuid", "e67138fe-0ba3-4135-8a9d-241fd96f37b3");
//		map.put("name", "groupCP1");
//		map.put("remark", "是");
//		map.put("account", "admin");
//		//-22
//		map.put("page", 1);
//		map.put("rows", 10);
//		map.put("account", "admin");
//		//-23
//		map.put("account", "admin");
//		//-24
//		map.put("report_uuid", "7e39821a-a70f-4a07-aa95-56a5b5351ea6");
//		map.put("date", "");
//		map.put("inst_id", "");
//		map.put("account", "admin");
//		//-25
//		map.put("path", "/vars");
//		map.put("start_date_str", "2016-08-14");
//		map.put("end_date_str", "2016-11-14");
//		map.put("page", 1);
//		map.put("row", 20);
//		map.put("account", "admin");
//		//-26
//		map.put("process_uuid", "96e3dd00-eddc-493a-879f-138d18e0beb9");
//		map.put("page", 1);
//		map.put("rows", 2);
//		map.put("account", "admin");
//		//-27
//		map.put("unit_uuid", "7e39821a-a70f-4a07-aa95-56a5b5351ea6");
//		map.put("page", 1);
//		map.put("rows", 20);
//		map.put("account", "admin");
//		//-28
//		map.put("state", "CREATED,SCHEDULED,RUNNING,PAUSED,CRASHED,NORMALEND,ABNORMALEND");
//		map.put("startDate", "2016-08-14");
//		map.put("endDate", "2016-11-14");
//		map.put("page", 1);
//		map.put("rows", 20);
//		map.put("account", "admin");
//		//-29
		map.put("file_name", "");
		map.put("account", "admin");
		//30-1
//		map.put("node_uuid", "780c90ca-df96-43be-bd32-22a60a62659f");
//		map.put("type", "job_net");
//		map.put("account", "admin");
		//30-2
//		map.put("node_uuid", "985300a9-51d0-4647-bf4d-680f9e202bc1");
//		map.put("type", "job_net");
//		map.put("account", "admin");
		
//		String filePath="D://夜班-CTP开盘巡检.dat";

//		Map<String, Object> responseMap=ConnectionInfoUtils.postHttpFileIO(Url, map, filePath);
		Map<String, Object> responseMap=ConnectionInfoUtils.postHttp(Url, map);
		if(null!=responseMap){
			int h= (int) responseMap.get("status");
			if(HttpStatus.SC_OK==h ){
				String responseMsg=(java.lang.String) responseMap.get("responseMsg");
				System.out.println(responseMsg.trim());
//				@SuppressWarnings("unchecked")
//				Map<String , Object> respMap = JSONObject.parseObject(responseMsg,Map.class);//将建json对象转换为Person对象  
//				System.out.println(respMap.toString());
				List<NodeReader> netList=JSONObject.parseArray(responseMsg, NodeReader.class);
				if(null!=netList){
					System.out.println(netList.get(0).getUnit_uuid());
				}
				
				
				
			}
		}
	}
	
	
	
	
}
