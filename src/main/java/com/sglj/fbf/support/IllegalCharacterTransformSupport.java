/**
 * 
 */
package com.sglj.fbf.support;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 特殊字符装换处理
 * @author guanhongwei
 *
 */
public final class IllegalCharacterTransformSupport {
	
	/**
	 * 特殊字符 -> 转义字符
	 * @param str 要过滤的内容
	 * @return
	 */
	public static String escape(String str) {
		if(StringUtils.isBlank(str)) return str;
		
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\t", "	");
		str = str.replaceAll("\r\n", "\n");
		str = str.replaceAll("\n", "<br/>");
		str = str.replaceAll("'", "&#39;");
		str = str.replaceAll("\\\\", "&#92;");
		str = str.replaceAll("\"", "&quot;");
		
		return str;
	}

	/**
	 * 转义字符 -> 特殊字符
	 * 
	 * @param str
	 *            要转换的内容
	 * @return
	 */
	public static String reverse(String str) {
		if(StringUtils.isBlank(str)) return str;
		
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("	", "\t");
		str = str.replaceAll("\n", "\r\n");
		str = str.replaceAll("<br/>", "\n");
		str = str.replaceAll("&#39;", "'");
		str = str.replaceAll("&#92;", "\\\\");
		str = str.replaceAll("＜", "<");
		str = str.replaceAll("＞", ">");
		str = str.replaceAll("＂", "\"");
		
		return str;
	}
	
	/**
	 * 半角字符 -> 全角字符
	 * @param str 待转字符
	 * @return
	 */
	public static String semiangleFully(String str) {
		if(StringUtils.isBlank(str)) return str;
		
		str = str.replaceAll("<", "＜");
		str = str.replaceAll(">", "＞");
		str = str.replaceAll("/", "／");
		str = str.replaceAll("\"", "＂");
		
		return str;
	}

}
