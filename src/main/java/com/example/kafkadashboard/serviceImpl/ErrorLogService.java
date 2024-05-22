package com.example.kafkadashboard.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.kafkadashboard.entity.elasticsearch.ErrorLogMessage;
import com.example.kafkadashboard.repository.ErrorLogMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorLogService {

	private final ErrorLogMessageRepository errorLogMessageRepository;
	
	public void saveErrorLogMessage(String message) {
		ErrorLogMessage logMessage = ErrorLogMessage.builder().message(message).timestamp(LocalDateTime.now()).build();
		errorLogMessageRepository.save(logMessage);
	}
	
}