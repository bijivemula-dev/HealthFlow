package com.samarait.customException;

public class PhoneNumberAlreadyExist extends RuntimeException{

	public PhoneNumberAlreadyExist(String message) {
		super(message);
	}
}
