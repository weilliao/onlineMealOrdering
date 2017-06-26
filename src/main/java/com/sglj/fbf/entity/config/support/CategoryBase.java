package com.sglj.fbf.entity.config.support;

import java.util.Collection;


/**
 * 大类代码需实现类
 * @author guanhongwei
 *
 */
public interface CategoryBase {

	/**
	 * 获取大类代码
	 * 
	 * @return
	 */
	public String getCode();

	/**
	 * 获取小类代码集合
	 * 
	 * @return
	 */
	public Collection<? extends CodeBase> getCodes();

	/**
	 * 根据编码值获取小类代码对象
	 * 
	 * @param codeValue
	 * @return
	 */
	public CodeBase getCode(String codeValue);

	/**
	 * 根据编码值获取编码文本
	 * 
	 * @param codeValue
	 * @return
	 */
	public String getCodeLable(String codeValue);

	/**
	 * 根据编码文本模糊匹配，获取小类代码集合
	 * 
	 * @param label
	 * @return
	 */
	public Collection<? extends CodeBase> getCodes(String label);

}
