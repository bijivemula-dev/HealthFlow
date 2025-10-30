package com.samarait.expception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.samarait.customException.EmailAlreadyExistException;
import com.samarait.customException.PatientNotFoundException;
import com.samarait.customException.PhoneNumberAlreadyExist;

@ControllerAdvice
public class GobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	   public ResponseEntity<ErrorResponse> handleResourceNotFound(
			   EmailAlreadyExistException ex) {
	        
	        ErrorResponse error = new ErrorResponse(
	            LocalDateTime.now(),
	            HttpStatus.NOT_FOUND.value(),
	            "Not Found",
	            ex.getMessage(),
	            "/api/errors/not-found" // Example error type URI
	        );
	        
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	
	
	
	@ExceptionHandler(PhoneNumberAlreadyExist.class)
	public ResponseEntity<String> handlePhoneNumberException(PhoneNumberAlreadyExist ex) {
		
		return ResponseEntity.ok().body(ex.getMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleIdException(PatientNotFoundException ex){
		
		ErrorResponse error = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				"Not Found",
				ex.getMessage(),
				"/api/errors/not-found"
				
				);
				
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

}
