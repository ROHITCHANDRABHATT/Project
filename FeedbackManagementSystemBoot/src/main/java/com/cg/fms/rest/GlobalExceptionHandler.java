package com.cg.fms.rest;

import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.fms.exception.ElementNotFoundException;
import com.cg.fms.exception.ValueInvalidException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ElementNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleElementNotFoundException(ElementNotFoundException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValueInvalidException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<Object> handleInvalidValueException(ValueInvalidException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getBindingResult().getFieldErrors().stream()
				.map(err->err.getDefaultMessage())
				.collect(Collectors.joining(", "));
		return new ResponseEntity<Object>(message,HttpStatus.BAD_REQUEST);
	}
}
