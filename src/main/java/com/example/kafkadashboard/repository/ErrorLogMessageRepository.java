package com.example.kafkadashboard.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.kafkadashboard.entity.elasticsearch.ErrorLogMessage;

@Repository
public interface ErrorLogMessageRepository extends ElasticsearchRepository<ErrorLogMessage, String> {

}
