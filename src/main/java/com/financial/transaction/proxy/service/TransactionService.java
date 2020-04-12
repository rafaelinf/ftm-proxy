package com.financial.transaction.proxy.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.transaction.proxy.dto.CheckBalanceAvailableDTO;
import com.financial.transaction.proxy.dto.TransactionDTO;
import com.financial.transaction.proxy.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.proxy.jms.SenderJMS;
import com.financial.transaction.proxy.rest.integration.RedisRestIntegration;

@Service
public class TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private SenderJMS senderJMS;
	
	@Autowired
	private RedisRestIntegration redisRestIntegration;
	
	public TransactionDTO save(TransactionDTO transactionDTO) throws FinancialTransactionManegementException {
		
		LOGGER.info("Buscar cliente por numberCard");

		try {
			
			CheckBalanceAvailableDTO checkBalanceAvailableDTO = redisRestIntegration.findClient(transactionDTO.getNumberCard(), transactionDTO.getPassword(), transactionDTO.getValue());
			if(checkBalanceAvailableDTO.isAvailability()) {
				transactionDTO.setDateOperation(new Date());
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(transactionDTO);
				senderJMS.sendMessage(json);
				return transactionDTO;				
			}
						
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
