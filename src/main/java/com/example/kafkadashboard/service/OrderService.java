package com.example.kafkadashboard.service;

import java.util.List;

import com.example.kafkadashboard.dto.OrderDto;


public interface OrderService {

	void createOrder(OrderDto orderDto);

	void cancelOrder(Long orderId);

	List<OrderDto> getAllOrders();

	OrderDto getOrder(Long orderId);

}
