package com.chronno.survival.network.exception;

public class ServerStartupException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServerStartupException() {
		super();
	}

	public ServerStartupException(String message, Throwable cause) {
		super(message, cause);
	}
}
