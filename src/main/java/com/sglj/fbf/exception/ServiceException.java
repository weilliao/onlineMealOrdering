package com.sglj.fbf.exception;

/**
 * @author guanhongwei
 * 
 */
public class ServiceException extends SystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3703808880751925325L;

	/**
	 * @param messageKey
	 * @param message
	 */
	public ServiceException(String messageKey, String message) {
		super(messageKey, message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
