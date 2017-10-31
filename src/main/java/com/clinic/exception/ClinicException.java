package com.clinic.exception;

public class ClinicException  extends Exception {
	
	private static final long serialVersionUID = -8391660852381483225L;

	public ClinicException(Throwable th, String message) {
		super(message, th);
	}

	public ClinicException(String message) {
		super(message);
	}

}
