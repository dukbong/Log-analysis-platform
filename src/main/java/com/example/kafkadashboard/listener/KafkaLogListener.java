package com.example.kafkadashboard.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaLogListener {

	
	@KafkaListener(topics="test-logs",groupId = "log-group")
	public void logListener(String message) {
		System.out.println("test 중입니다.");
		System.out.println(message);
	}
}
