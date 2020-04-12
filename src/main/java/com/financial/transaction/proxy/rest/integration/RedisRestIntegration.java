package com.financial.transaction.proxy.rest.integration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.financial.transaction.proxy.dto.CheckBalanceAvailableDTO;
import com.financial.transaction.proxy.exceptions.FinancialTransactionManegementException;

@Component
public class RedisRestIntegration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisRestIntegration.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${url-base.redis}")
	private String urlBase;

	public CheckBalanceAvailableDTO findClient(String numberCard, String password, BigDecimal value) throws FinancialTransactionManegementException {
		try {

			LOGGER.info("Buscar cliente");
			
			String url = urlBase + "clients/checkBalanceAvailable?numberCard=1234456785201450&password=b59c67bf196a4758191e42f76670ceba&value=1";
			
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("numberCard", numberCard);
			urlParams.put("password", password);
			urlParams.put("value", value.toString());
			
			ResponseEntity<CheckBalanceAvailableDTO> checkBalanceAvailableDTO = restTemplate.getForEntity(url, CheckBalanceAvailableDTO.class);			
			return checkBalanceAvailableDTO.getBody();

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao buscar Response {}", e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException("Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}		

}
