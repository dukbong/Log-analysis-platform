package com.example.kafkadashboard.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.kafkadashboard.entity.elasticsearch.LogMessage;

@Repository
public interface LogMessageRepository extends ElasticsearchRepository<LogMessage, String> {

}
