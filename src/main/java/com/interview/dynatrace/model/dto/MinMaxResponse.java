package com.interview.dynatrace.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class MinMaxResponse {
	private BigDecimal minValue;
	private BigDecimal maxValue;

	public MinMaxResponse(BigDecimal minValue, BigDecimal maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
}
