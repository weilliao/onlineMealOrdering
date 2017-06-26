package com.sglj.fbf.exception;

/**
 * 数据访问异常
 * 
 * @author guanhongwei
 * 
 */
public class DaoException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -566712544100723099L;

	/**
	 * @param messageKey
	 * @param message
	 */
	public DaoException(String messageKey, String message) {
		super(messageKey, message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

}
