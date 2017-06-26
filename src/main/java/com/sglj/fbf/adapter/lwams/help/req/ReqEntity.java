package com.sglj.fbf.adapter.lwams.help.req;

/**
 * 接口请求帮助类
 * @author liuming
 *
 */
public class ReqEntity {
	//(添加到顶级节点为:3,否则为流程作业组的 id)
	private String id;
	//作业流程组名称
	private String name;
	//是否是作业流程组模板(true|false)
	private boolean isTmplGroup =false;
	//登录用户名
	private String account;		
	//
	private String category;
	
	private String page;
	
	private String 	rows;
	
	private String unit_uuid;
	
	private String proc_uuid;
	//作业流程|作业流程组 id
	private String uuid;		
	//(job_group|job_net)
	private String type;		
	
	//主机的 id 地址。 eg： 121.41.52.158
	private String ip;
	//主机的 OS 的类型。 eg:windows|linux|mac_os_x......
	private String  os_family;
	//主机的 OS 的版本。eg:win_server_2008|
	private String os_version;
	//工作的目录。
	private String workdir;
	
	private String remark;
	//用户密码
	private String passwd;
	//时间搓
	private String dateToString;
	//文件名
	private String fileName;
	//文件路径
	private String filePath;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the isTmplGroup
	 */
	public boolean isTmplGroup() {
		return isTmplGroup;
	}
	/**
	 * @param isTmplGroup the isTmplGroup to set
	 */
	public void setTmplGroup(boolean isTmplGroup) {
		this.isTmplGroup = isTmplGroup;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}
	/**
	 * @return the unit_uuid
	 */
	public String getUnit_uuid() {
		return unit_uuid;
	}
	/**
	 * @param unit_uuid the unit_uuid to set
	 */
	public void setUnit_uuid(String unit_uuid) {
		this.unit_uuid = unit_uuid;
	}
	/**
	 * @return the proc_uuid
	 */
	public String getProc_uuid() {
		return proc_uuid;
	}
	/**
	 * @param proc_uuid the proc_uuid to set
	 */
	public void setProc_uuid(String proc_uuid) {
		this.proc_uuid = proc_uuid;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the os_family
	 */
	public String getOs_family() {
		return os_family;
	}
	/**
	 * @param os_family the os_family to set
	 */
	public void setOs_family(String os_family) {
		this.os_family = os_family;
	}
	/**
	 * @return the os_version
	 */
	public String getOs_version() {
		return os_version;
	}
	/**
	 * @param os_version the os_version to set
	 */
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	/**
	 * @return the workdir
	 */
	public String getWorkdir() {
		return workdir;
	}
	/**
	 * @param workdir the workdir to set
	 */
	public void setWorkdir(String workdir) {
		this.workdir = workdir;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * @return the dateToString
	 */
	public String getDateToString() {
		return dateToString;
	}
	/**
	 * @param dateToString the dateToString to set
	 */
	public void setDateToString(String dateToString) {
		this.dateToString = dateToString;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
