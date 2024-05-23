package com.example.kafkadashboard.serviceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kafkadashboard.entity.elasticsearch.LogMessage;
import com.example.kafkadashboard.repository.LogMessageRepository;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {

	@Mock
	private LogMessageRepository logMessageRepository;
	
	@InjectMocks
	private LogService logService;
	
	@Test
	public void saveLogMessageTest() {
		// Given
		String message = "Test Log Message";
		
		// When
		logService.saveLogMessage(message);
		
		// Then
		verify(logMessageRepository, times(1)).save(any(LogMessage.class));
	}

}
