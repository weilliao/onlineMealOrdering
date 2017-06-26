package com.sglj.fbf.adapter.lwams.help.resp;

/**
 * 作业流程节点对象（单）
 * @author 刘明
 *
 */
public class NodeReader {
	
	private String category;
	//
	private String jobnet_uuid;
	
	private String unit_uuid;
	//作业流程组的名称(首次获取:作业监控,否则为流程作业组的 name)
	private String name;
	//icon 和 attrs 后台为获取到数据时则不会包含这两个属性
	private String icon;
	//是否是否作业流程组
	private String isParent ;
	
	private Attrs attrs ;
	
	private String menu ;
	
	public class Attrs {
		
		private String nodeType;

		/**
		 * @return the nodeType
		 */
		public String getNodeType() {
			return nodeType;
		}

		/**
		 * @param nodeType the nodeType to set
		 */
		public void setNodeType(String nodeType) {
			this.nodeType = nodeType;
		}
		
		
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
	 * @return the jobnet_uuid
	 */
	public String getJobnet_uuid() {
		return jobnet_uuid;
	}

	/**
	 * @param jobnet_uuid the jobnet_uuid to set
	 */
	public void setJobnet_uuid(String jobnet_uuid) {
		this.jobnet_uuid = jobnet_uuid;
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}

	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}


	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}

	/**
	 * @return the attrs
	 */
	public Attrs getAttrs() {
		return attrs;
	}

	/**
	 * @param attrs the attrs to set
	 */
	public void setAttrs(Attrs attrs) {
		this.attrs = attrs;
	}
	
	

}
