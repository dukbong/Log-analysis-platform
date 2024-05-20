package com.example.kafkadashboard.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.kafkadashboard.entity.elasticsearch.ErrorLogMessage;
import com.example.kafkadashboard.repository.ErrorLogMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorLogService {

	private final ErrorLogMessageRepository errorLogMessageRepository;
	
	public void saveErrorLogMessage(String message) {
		ErrorLogMessage logMessage = ErrorLogMessage.builder().message(message).timestamp(formatTimestamp(LocalDateTime.now())).build();
		errorLogMessageRepository.save(logMessage);
	}
	
    private String formatTimestamp(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return dateTime.format(formatter);
    }
	
}
