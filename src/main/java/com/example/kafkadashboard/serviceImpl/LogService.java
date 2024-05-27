package com.example.kafkadashboard.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.kafkadashboard.entity.elasticsearch.LogMessage;
import com.example.kafkadashboard.repository.LogMessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class LogService {
	
    private final LogMessageRepository logMessageRepository;

    public void saveLogMessage(String message) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		String modifiedMessage = message.replaceAll(": \"([^\"]*)\"", ": '$1'")
                    .replaceAll("\\\"(default)\\\"", "'$1'");
    		modifiedMessage = modifiedMessage.replace("\\", "\\\\");
    		
    		JsonNode jsonNode = mapper.readTree(modifiedMessage);
    		
    		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
             LocalDateTime timestamp = LocalDateTime.parse(jsonNode.get("timestamp").asText(), formatter);
            
    		LogMessage logMessage = LogMessage.builder()
							                  .project(jsonNode.get("project").asText())
							                  .logCreateAt(timestamp.toString())
							                  .thread(jsonNode.get("thread").asText())
							                  .level(jsonNode.get("level").asText())
							                  .logger(jsonNode.get("logger").asText())
							                  .message(jsonNode.get("message").asText())
							                  .timestamp(LocalDateTime.now())
							                  .build();
    		
    		logMessageRepository.save(logMessage);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}