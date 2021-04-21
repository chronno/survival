package com.chronno.survival.network.exception;

import java.io.Serial;

public class CommunicationException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public CommunicationException() {
		super();
	}

	public CommunicationException(String message) {
		super(message);
	}

	public CommunicationException(String message, Throwable cause) {
		super(message, cause);
	}
}
