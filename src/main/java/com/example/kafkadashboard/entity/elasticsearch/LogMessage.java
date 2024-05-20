package com.example.kafkadashboard.entity.elasticsearch;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.Getter;

@Document(indexName = "logs")
@Getter
public class LogMessage {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String message;

//    @Field(name = "@timestamp", type = FieldType.Date, format = DateFormat.date_hour_minute_second_fraction)
    @Field(name = "@timestamp", type = FieldType.Date, format = DateFormat.date_hour_minute)
    private LocalDateTime timestamp;

    @Builder
    public LogMessage(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
