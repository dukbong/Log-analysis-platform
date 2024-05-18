package com.example.kafkadashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kafkadashboard.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
