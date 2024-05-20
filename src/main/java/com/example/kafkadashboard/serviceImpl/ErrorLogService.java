package com.example.kafkadashboard.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.kafkadashboard.entity.elasticsearch.ErrorLogMessage;
import com.example.kafkadashboard.repository.ErrorLogMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorLogService {

	private final ErrorLogMessageRepository errorLogMessageRepository;
	
	public void saveErrorLogMessage(String message) {
		ErrorLogMessage logMessage = ErrorLogMessage.builder().message(message).build();
		errorLogMessageRepository.save(logMessage);
	}
	
}
