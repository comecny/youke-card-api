package com.youke.common.exception.db;

public abstract class BaseDBException extends RuntimeException {

	private static final long serialVersionUID = -8894720229103007197L;

	public BaseDBException() {
		super();
	}

	public BaseDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseDBException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseDBException(String message) {
		super(message);
	}

	public BaseDBException(Throwable cause) {
		super(cause);
	}

}
