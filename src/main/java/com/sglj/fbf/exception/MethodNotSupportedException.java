package com.sglj.fbf.exception;

/**
 * @author guanhongwei
 * 
 */
public class MethodNotSupportedException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5063017905997896247L;

	/**
	 * @param messageKey
	 * @param message
	 */
	public MethodNotSupportedException(String messageKey, String message) {
		super(messageKey, message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MethodNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MethodNotSupportedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MethodNotSupportedException(Throwable cause) {
		super(cause);
	}

}
