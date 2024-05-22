package com.example.kafkadashboard.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kafkadashboard.dto.OrderDto;
import com.example.kafkadashboard.entity.OrderEntity;
import com.example.kafkadashboard.repository.OrderRepository;
import com.example.kafkadashboard.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	
	@Override
	public void createOrder(OrderDto orderDto) {
		orderRepository.save(orderDto.convertEntity());
	}

	@Override
	@Transactional
	public void cancelOrder(Long orderId) {
		OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Not found OrderEntity - orderId : " + orderId));
		orderEntity.cancelOrder();
		orderRepository.save(orderEntity);
	}

	@Override
	public List<OrderDto> getAllOrders() {
		return orderRepository.findAll().stream().map(i -> i.convertDTO()).collect(Collectors.toList());
	}

	@Override
	public OrderDto getOrder(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Not found OrderEntity - orderId : " + orderId)).convertDTO();
	}

}
