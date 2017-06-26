package com.sglj.fbf.adapter.lwams.help.req;
/**
 * 作业流程组
 * @author liuming
 *
 */
public class JobGroup {
	
	//(添加到顶级节点为:3,否则为流程作业组的 id)
	private String id;
	//作业流程组名称
	private String name;
	//是否是作业流程组模板(true|false)
	private boolean isTmplGroup =false;
	//登录用户名
	private String account;
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
	
	

}
