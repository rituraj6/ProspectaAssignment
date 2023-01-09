package com.prospecta.Exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyError> categoryExceptionHandler(CategoryException pe ,WebRequest wr){
		MyError error = new MyError();
		error.setLdt(LocalDateTime.now());
		error.setMessage(pe.getMessage());
		error.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> NoHandlerFoundException(NoHandlerFoundException e, WebRequest wr) {
		MyError ed = new MyError(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyError>(ed, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> ExceptionHandler(Exception e, WebRequest wr) {
		MyError ed = new MyError(LocalDateTime.now(), e.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyError>(ed, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		MyError err = new MyError(LocalDateTime.now(), "Validation Error",
				me.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}


}
