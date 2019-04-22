package com.stackroute.moviecruiserserverapplication.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {

	private String message;

	public MovieAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MovieAlreadyExistException [message=" + message + "]";
	}


}
