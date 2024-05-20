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
                .timestamp(LocalDateTime.now())
                .build();
        logMessageRepository.save(logMessage);
    }

    // Grafana에서 집계합수를 쓰기 위해서 분까지만 작성
    private String formatTimestamp(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTime.format(formatter);
    }
}