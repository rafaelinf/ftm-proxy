package com.financial.transaction.proxy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.transaction.proxy.dto.TransactionDTO;
import com.financial.transaction.proxy.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.proxy.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class ProxyController {
	
	@Autowired
	private TransactionService transactionService;

    @PostMapping("/save")
	public TransactionDTO save(@Valid @RequestBody TransactionDTO transactionDTO) throws FinancialTransactionManegementException {  	
    	transactionDTO = transactionService.save(transactionDTO);
    	return transactionDTO;
	}	
}
