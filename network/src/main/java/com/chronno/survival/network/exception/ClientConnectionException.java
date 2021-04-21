package com.chronno.survival.network.exception;

public class ClientConnectionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientConnectionException() {
		super();
	}

	public ClientConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
