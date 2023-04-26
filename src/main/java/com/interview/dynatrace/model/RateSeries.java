package com.interview.dynatrace.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateSeries {
	@JsonProperty("table")
	private Tables table;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("code")
	private String code;

	@JsonProperty("rates")
	private List<Rate> rates;
}
