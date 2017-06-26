package com.sglj.fbf.crud.dao;

import java.util.List;

/**
 * 序列号生成器
 * @author guanhongwei
 *
 */
public interface CustomIDGenerator {
	
	/**
	 * 每次生成一个序列
	 * @return
	 */
	public String generate();

	/**
	 * 生成 count 个 序列
	 * @param count
	 * @return
	 */
	public List<String> generate(int count);

}
