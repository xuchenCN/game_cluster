package org.mmo.server.common.exception;

public class TensorHuskyRuntimeException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -950691928293167226L;

	public TensorHuskyRuntimeException(Throwable cause) {
		super(cause);
	}

	public TensorHuskyRuntimeException(String message) {
		super(message);
	}

	public TensorHuskyRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
