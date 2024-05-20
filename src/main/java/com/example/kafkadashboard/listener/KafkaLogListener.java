package com.example.kafkadashboard.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.kafkadashboard.serviceImpl.LogService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaLogListener {

	private final LogService logService;
	
	@KafkaListener(topics="test-logs",groupId = "log-group")
	public void logListener(String message) {
		System.out.println("test-logs 리스너 동작");
		System.out.println(message);
		
		logService.saveLogMessage(message);
	}
	
}
