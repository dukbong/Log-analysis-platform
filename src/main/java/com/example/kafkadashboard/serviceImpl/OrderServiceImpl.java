package com.example.kafkadashboard.serviceImpl;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import com.example.kafkadashboard.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public void createOrder(OrderDto orderDto) {
		
	}

	@Override
	public void cancelOrder(Long orderId) {
		
	}

	@Override
	public List<OrderDto> getAllOrders() {
		return null;
	}

	@Override
	public OrderDto getOrder(Long orderId) {
		return null;
	}

}
