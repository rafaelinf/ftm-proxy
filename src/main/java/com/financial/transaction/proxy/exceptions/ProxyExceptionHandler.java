package com.financial.transaction.proxy.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProxyExceptionHandler {

    private Logger log = LoggerFactory.getLogger(ProxyExceptionHandler.class);

    @ExceptionHandler(FinancialTransactionManegementException.class)
    public void handlePostNotFound(HttpServletRequest request, Exception ex) {
        log.error("{} : {}", ex.getMessage(), request.getRequestURI());
    }
}