package com.example.kafkadashboard.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.kafkadashboard.serviceImpl.ErrorLogService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaErrorLogListener {

	private final ErrorLogService ErrorLogService;
	
	@KafkaListener(topics="error-logs",groupId = "error-group")
	public void errorListener(String message) {
		System.out.println("error-logs 리스너 동작");
		System.out.println(message);
		
		ErrorLogService.saveErrorLogMessage(message);
	}
	
}
