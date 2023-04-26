package com.interview.dynatrace.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class ExchangeResponseErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
			String errorMessage = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
					.lines()
					.collect(Collectors.joining(System.lineSeparator()));

			throw new ResponseEntityErrorException(response.getStatusCode(), errorMessage);
		}
	}
}
