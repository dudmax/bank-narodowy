package com.interview.dynatrace.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
	@JsonProperty("no")
	private String number;

	@JsonProperty("effectiveDate")
	private LocalDate effectiveDate;

	@JsonProperty("mid")
	private BigDecimal midValue;

	@JsonProperty("bid")
	private BigDecimal bidValue;

	@JsonProperty("ask")
	private BigDecimal askValue;
}
