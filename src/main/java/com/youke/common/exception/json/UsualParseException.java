package com.youke.common.exception.json;

public class UsualParseException extends Exception {

	private static final long serialVersionUID = -6742700862488130969L;

	public UsualParseException() {
		super();
	}

	public UsualParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsualParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsualParseException(String message) {
		super(message);
	}

	public UsualParseException(Throwable cause) {
		super(cause);
	}

}
