package com.school.administration.app.exceptions;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpHeaders;
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
		HttpStatus badRequest = HttpStatus.valueOf(400);
		
		Date date = Calendar.getInstance().getTime();
		
		ErrorMessage errorMessage = new ErrorMessage(date, badRequest, ex.getLocalizedMessage());
		
		return new ResponseEntity<>(errorMessage, badRequest);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		HttpStatus internalServerError = HttpStatus.valueOf(500);
		
		Date date = Calendar.getInstance().getTime();
		
		ErrorMessage errorMessage = new ErrorMessage(date, internalServerError, ex.getLocalizedMessage());
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), internalServerError);
	}
}
