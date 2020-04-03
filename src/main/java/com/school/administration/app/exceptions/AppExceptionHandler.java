package com.school.administration.app.exceptions;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.school.administration.app.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		String errorCode = "err99";
		String status = "failed";
		
		HttpStatus oke = HttpStatus.valueOf(200);
		Date date = Calendar.getInstance().getTime();
		
		ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
		
		return new ResponseEntity<>(errorMessage, oke);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		String errorCode = "0";
		String status = "failed";
		
		HttpStatus oke = HttpStatus.valueOf(200);
		Date date = Calendar.getInstance().getTime();
		
		ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
		
		return new ResponseEntity<>(errorMessage, oke);
	}
}
