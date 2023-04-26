package com.interview.dynatrace.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExchangeExceptionHandler {

	@ExceptionHandler(value = ResponseEntityErrorException.class)
	ResponseEntity<ErrorResponseDTO> handleMyRestTemplateException(ResponseEntityErrorException ex, HttpServletRequest request) {
		return new ResponseEntity<>(new ErrorResponseDTO(ex, request.getRequestURI()), ex.getStatusCode());
	}
}
