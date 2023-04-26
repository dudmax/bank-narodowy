package com.interview.dynatrace.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.interview.dynatrace.model.dto.MinMaxResponse;
import com.interview.dynatrace.service.IExchangesService;

@RestController
@RequestMapping("/exchanges")
public class ExchangesController {

	@Autowired
	IExchangesService exchangesServiceImpl;

	@RequestMapping(value = "/{code}/{date}", method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BigDecimal getAverageRateByDate(@PathVariable String code, @PathVariable LocalDate date) {
		return exchangesServiceImpl.getAverageRateByDate(code, date);
	}

	@RequestMapping(value = "{code}/minmax/{topCount}", method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MinMaxResponse getMinAndMaxAverageRateByLast(@PathVariable String code, @PathVariable int topCount) {
		return exchangesServiceImpl.getMinAndMaxAverageRateByLast(code, topCount);
	}

	@RequestMapping(value = "{code}/diff/{topCount}", method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BigDecimal getMaxDifferenceByLast(@PathVariable String code, @PathVariable int topCount) {
		return exchangesServiceImpl.getMaxDifferenceByLast(code, topCount);
	}
}
