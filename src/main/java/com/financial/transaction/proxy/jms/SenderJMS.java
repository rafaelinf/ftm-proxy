package com.financial.transaction.proxy.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SenderJMS {

	@Autowired
	JmsTemplate template;
	
	@Value("${jms.queue}")
	String jmsQueue;	
	
	public void sendMessage(String message) {
		template.convertAndSend(jmsQueue, message);
	}
	
}
