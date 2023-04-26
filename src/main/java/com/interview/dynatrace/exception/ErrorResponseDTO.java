package com.interview.dynatrace.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponseDTO {

	private int status;

	private String error;

	private String message;

	public ErrorResponseDTO(ResponseEntityErrorException ex, String path) {
		this.status = ex.getStatusCode().value();
		this.error = ex.getStatusCode().getReasonPhrase();
		this.message = ex.getError();
	}
}
