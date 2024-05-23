package com.example.kafkadashboard.serviceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kafkadashboard.entity.elasticsearch.ErrorLogMessage;
import com.example.kafkadashboard.repository.ErrorLogMessageRepository;

@ExtendWith(MockitoExtension.class)
public class ErrorLogServiceTest {

	@Mock
	private ErrorLogMessageRepository errorLogMessageRepository;
	
	@InjectMocks
	private ErrorLogService errorLogService;
	
	@Test
	public void saveErrorLogMessageTest() {
		// Given
		String message = "This is ErrorLogMessage";
		
		// When
		errorLogService.saveErrorLogMessage(message);
		
		// Then
		verify(errorLogMessageRepository, times(1)).save(Mockito.any(ErrorLogMessage.class));
	}
}
