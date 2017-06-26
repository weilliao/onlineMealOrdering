package com.sglj.fbf.exception;

/**
 * @author guanhongwei
 */
public class ViewException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8425230982326613908L;

	/**
	 * @param messageKey
	 * @param message
	 */
	public ViewException(String messageKey, String message) {
		super(messageKey, message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ViewException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ViewException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ViewException(Throwable cause) {
		super(cause);
	}

}
