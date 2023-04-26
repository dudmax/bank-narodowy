package com.interview.dynatrace.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.interview.dynatrace.exception.ResponseEntityErrorException;
import com.interview.dynatrace.model.RateSeries;
import com.interview.dynatrace.model.Tables;
import com.interview.dynatrace.model.dto.MinMaxResponse;
import com.interview.dynatrace.service.IExchangesService;
import com.interview.dynatrace.service.util.ExchangeUtils;

@Service("exchangesServiceImpl")
public class ExchangeServiceImpl implements IExchangesService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${nbp.api.url}")
	private String nbpApiUrl;

	@Override public BigDecimal getAverageRateByDate(String code, LocalDate date) {
		StringBuilder requestUrl = new StringBuilder()
				.append(nbpApiUrl)
				.append(Tables.A)
				.append("/")
				.append(code)
				.append("/")
				.append(date);
		ResponseEntity<RateSeries> rateSeriesResponseEntity = restTemplate.getForEntity(requestUrl.toString(), RateSeries.class);

		if (rateSeriesResponseEntity.getStatusCode() == HttpStatus.OK) {
			RateSeries rateSeriesArray = rateSeriesResponseEntity.getBody();
			if (rateSeriesArray != null) {
				return rateSeriesArray.getRates().get(0).getMidValue();
			} else {
				throw new ResponseEntityErrorException(HttpStatus.NOT_FOUND, "Not found");
			}
		} else {
			throw new ResponseEntityErrorException(rateSeriesResponseEntity.getStatusCode(), "Error during request");
		}
	}

	@Override public MinMaxResponse getMinAndMaxAverageRateByLast(String code, int topCount) {
		StringBuilder requestUrl = new StringBuilder()
				.append(nbpApiUrl)
				.append(Tables.A)
				.append("/")
				.append(code)
				.append("/last/")
				.append(topCount);
		ResponseEntity<RateSeries> rateSeriesResponseEntity = restTemplate.getForEntity(requestUrl.toString(), RateSeries.class);

		if (rateSeriesResponseEntity.getStatusCode() == HttpStatus.OK) {
			RateSeries rateSeriesArray = rateSeriesResponseEntity.getBody();
			if (rateSeriesArray != null && rateSeriesArray.getRates().size() > 0) {
				List<BigDecimal> minMaxValues = ExchangeUtils.calculateMinAndMaxAverageRate(rateSeriesArray.getRates());
				return new MinMaxResponse(minMaxValues.get(0), minMaxValues.get(1));
			} else {
				throw new ResponseEntityErrorException(HttpStatus.NOT_FOUND, "Not found");
			}
		} else {
			throw new ResponseEntityErrorException(rateSeriesResponseEntity.getStatusCode(), "Error during request");
		}
	}

	@Override public BigDecimal getMaxDifferenceByLast(String code, int topCount) {
		StringBuilder requestUrl = new StringBuilder()
				.append(nbpApiUrl)
				.append(Tables.C)
				.append("/")
				.append(code)
				.append("/last/")
				.append(topCount);
		ResponseEntity<RateSeries> rateSeriesResponseEntity = restTemplate.getForEntity(requestUrl.toString(), RateSeries.class);

		if (rateSeriesResponseEntity.getStatusCode() == HttpStatus.OK) {
			RateSeries rateSeriesArray = rateSeriesResponseEntity.getBody();
			if (rateSeriesArray != null && rateSeriesArray.getRates().size() > 0) {
				return ExchangeUtils.calculateMaxDifferenceByLast(rateSeriesArray.getRates());
			} else {
				throw new ResponseEntityErrorException(HttpStatus.NOT_FOUND, "Not found");
			}
		} else {
			throw new ResponseEntityErrorException(rateSeriesResponseEntity.getStatusCode(), "Error during request");
		}
	}
}
