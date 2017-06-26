package com.sglj.fbf.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统异常基类
 * 
 * @author guanhongwei
 */
public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920525998514862910L;

	/** 错误代码 */
	private String messageKey;

	/** 错误信息 */
	private String message;

	/** 错误路径 */
	private String catalog;

	/**
	 * @param message
	 */
	public SystemException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * @param messageKey
	 * @param message
	 */
	public SystemException(String messageKey, String message) {
		this.messageKey = messageKey;
		this.message = message;
	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey
	 *            the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the catalog
	 */
	public String getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog
	 *            the catalog to set
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public void print() {
		System.out.println(toString());
	}

}
