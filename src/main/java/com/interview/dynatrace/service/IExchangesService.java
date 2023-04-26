package com.interview.dynatrace.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.interview.dynatrace.model.dto.MinMaxResponse;

public interface IExchangesService {
	public BigDecimal getAverageRateByDate(String code, LocalDate date);

	public MinMaxResponse getMinAndMaxAverageRateByLast(String code, int topCount);

	public BigDecimal getMaxDifferenceByLast(String code, int topCount);
}
