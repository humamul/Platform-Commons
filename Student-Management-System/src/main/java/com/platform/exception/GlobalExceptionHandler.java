package com.platform.exception;

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

	@ExceptionHandler(EntryException.class)
	public ResponseEntity<MyErrorList> BookExceptionclass(EntryException e,WebRequest req){
		MyErrorList err = new MyErrorList();
		err.setDetails(req.getDescription(false));
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorList> normalException(Exception e,WebRequest req){
		MyErrorList err = new MyErrorList();
		err.setDetails(req.getDescription(false));
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorList> noHandleException(NoHandlerFoundException e,WebRequest req){
		MyErrorList err = new MyErrorList();
		err.setDetails(req.getDescription(false));
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorList> methodArgsInvalid(MethodArgumentNotValidException e,WebRequest wr) {
		MyErrorList err = new MyErrorList();
		err.setLocalDateTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(e.getBindingResult().getFieldError().getDefaultMessage());
	
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}
	

}