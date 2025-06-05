package com.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException msg) {

		return new ResponseEntity(msg.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PrductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFoundException(PrductNotFoundException msg) {

		return new ResponseEntity(msg.getMessage(), HttpStatus.NOT_FOUND);
	}

}
