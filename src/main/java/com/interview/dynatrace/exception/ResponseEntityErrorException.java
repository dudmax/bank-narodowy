package com.interview.dynatrace.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseEntityErrorException extends RuntimeException  {

	private HttpStatus statusCode;
	private String error;

	public ResponseEntityErrorException(HttpStatus statusCode, String error) {
		super(error);
		this.statusCode = statusCode;
		this.error = error;
	}
}
