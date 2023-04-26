package com.interview.dynatrace.service.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import com.interview.dynatrace.model.Rate;

public class ExchangeUtils {

	public static List<BigDecimal> calculateMinAndMaxAverageRate(List<Rate> rates) {
		BigDecimal maxValue = BigDecimal.ZERO;
		BigDecimal minValue = BigDecimal.ZERO;
		minValue = rates.stream()
				.map(Rate::getMidValue)
				.min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);

		maxValue = rates.stream()
				.map(Rate::getMidValue)
				.max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);

		return Arrays.asList(minValue, maxValue);
	}

	public static BigDecimal calculateMaxDifferenceByLast(List<Rate> rates) {
		return rates.stream()
				.map(rate -> rate.getAskValue().subtract(rate.getBidValue()))
				.max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}
}
