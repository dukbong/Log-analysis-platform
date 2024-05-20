package com.example.kafkadashboard.entity.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Builder;
import lombok.Getter;

@Document(indexName = "errors")
@Getter
public class ErrorLogMessage {
	@Id
	private String id;
	private String message;
	
	@Builder
	public ErrorLogMessage(String id, String message) {
		this.message = message;
	}
	
}
