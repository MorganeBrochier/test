package com.humanbooster.echeancier.exceptions;

public class PretHorsConditionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PretHorsConditionException(String message) {
		this.message = message;
	}

}
