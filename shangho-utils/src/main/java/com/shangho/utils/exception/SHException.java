package com.shangho.utils.exception;

public class SHException extends Exception {
	private static final long serialVersionUID = 9214879297429562534L;
	private int errorCode;

	public SHException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public SHException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public SHException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public int getErrorCode() {
		return errorCode;
	}
}
