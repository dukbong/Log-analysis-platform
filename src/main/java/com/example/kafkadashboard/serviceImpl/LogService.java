package com.example.kafkadashboard.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.kafkadashboard.entity.elasticsearch.LogMessage;
import com.example.kafkadashboard.repository.LogMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogMessageRepository logMessageRepository;

    public void saveLogMessage(String message) {
        LogMessage logMessage = LogMessage.builder()
                .message(message)
                .timestamp(formatTimestamp(LocalDateTime.now()))
                .build();
        logMessageRepository.save(logMessage);
    }

    private String formatTimestamp(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return dateTime.format(formatter);
    }
}