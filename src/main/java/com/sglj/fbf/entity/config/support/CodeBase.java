package com.sglj.fbf.entity.config.support;

/**
 * 小类代码需实现方法
 * @author guanhongwei
 *
 */
public interface CodeBase {

	/**
	 * 获取编码值
	 * 
	 * @return
	 */
	public String getValue();

	/**
	 * 设置编码值
	 * 
	 * @param value
	 */
	public void setValue(String value);

	/**
	 * 获取编码文本
	 * 
	 * @return
	 */
	public String getLabel();

	/**
	 * 设置编码文本
	 * 
	 * @param label
	 */
	public void setLabel(String label);

}
