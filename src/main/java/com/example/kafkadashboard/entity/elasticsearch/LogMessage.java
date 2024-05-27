package com.example.kafkadashboard.entity.elasticsearch;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(indexName = "logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LogMessage {
    @Id
    private String id;
    
    @Field(type = FieldType.Text)
    private String project;
    
    @Field(type = FieldType.Text)
    private String logCreateAt; // log 생성 시간
    
    @Field(type = FieldType.Text)
    private String thread;
    
    @Field(type = FieldType.Text)
    private String level;
    
    @Field(type = FieldType.Text)
    private String logger;

    @Field(type = FieldType.Text)
    private String message;

    // Elasticsearch에서 시간 별 집계를 하기 위해서 사용됩니다.
    @Field(name = "@timestamp", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime timestamp; // 엘라스틱 서치에 저장되는 시간

    @Builder
	public LogMessage(String project, String logCreateAt, String thread, String level, String logger,
			String message, LocalDateTime timestamp) {
		this.project = project;
		this.logCreateAt = logCreateAt;
		this.thread = thread;
		this.level = level;
		this.logger = logger;
		this.message = message;
		this.timestamp = timestamp;
	}
    
}
