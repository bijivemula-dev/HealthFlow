package com.samarait.customException;




public class EmailAlreadyExistException extends RuntimeException{
	
	
	public EmailAlreadyExistException(String message) {
		super(message);
	}

}
