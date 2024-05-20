package com.example.kafkadashboard.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.kafkadashboard.entity.elasticsearch.LogMessage;
import com.example.kafkadashboard.repository.LogMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {

	private final LogMessageRepository logMessageRepository;
	
	public void saveLogMessage(String message) {
		LogMessage logMessage = LogMessage.builder().message(message).build();
		logMessageRepository.save(logMessage);
	}
	
}
