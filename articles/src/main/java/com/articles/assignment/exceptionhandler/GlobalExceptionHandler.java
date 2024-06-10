package com.articles.assignment.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.articles.assignment.exception.APINotFoundException;
import com.articles.assignment.exception.DatesMismatchException;
import com.articles.assignment.exception.InvalidDateFormatException;
import com.articles.assignment.exception.NoRecordsFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = APINotFoundException.class)
	public ResponseEntity<Object> customException(APINotFoundException exception) {
		return new ResponseEntity<>("Source news API either wrong or not available", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NoRecordsFoundException.class)
	public ResponseEntity<Object> customException(NoRecordsFoundException exception) {
		return new ResponseEntity<>("No Records found for given criteria", HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(value = InvalidDateFormatException.class)
	public ResponseEntity<Object> customException(InvalidDateFormatException exception) {
		return new ResponseEntity<>("Please provide TO and FROM dates in yyyy/MM/dd format", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = DatesMismatchException.class)
	public ResponseEntity<Object> customException(DatesMismatchException exception) {
		return new ResponseEntity<>("FROM date should be lesser or equal to TO date", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
		return new ResponseEntity<>("Exception occured: "+ exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
