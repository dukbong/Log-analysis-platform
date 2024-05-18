package com.example.kafkadashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkadashboard.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderServiceImpl;
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	// 주문생성
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
		logger.info("주문을 생성합니다.");
		orderServiceImpl.createOrder(orderDto);
		return ResponseEntity.ok().body("주문이 생성되었습니다.");
	}
	
	// 주문취소
	@PatchMapping("/{orderId}/cancel")
	public ResponseEntity<String> cancelOrder(@PathVariable String orderId) {
		orderServiceImpl.cancelOrder(Long.valueOf(orderId));
		return ResponseEntity.ok().body("주문이 취소되었습니다.");
	}
	
	// 전체 주문조회
	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderDto> orders = orderServiceImpl.getAllOrders();
		return ResponseEntity.ok().body(orders);
	}
	
	// 개별 주문조회
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
		OrderDto order = orderServiceImpl.getOrder(orderId);
		return ResponseEntity.ok().body(order);
	}
	
}

