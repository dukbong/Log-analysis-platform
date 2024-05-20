package com.example.kafkadashboard.entity.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Builder;
import lombok.Getter;

@Document(indexName = "logs")
@Getter
public class LogMessage {
	@Id
	private String id;
	private String message;
	
	@Builder
	public LogMessage(String id, String message) {
		this.message = message;
	}
	
}
