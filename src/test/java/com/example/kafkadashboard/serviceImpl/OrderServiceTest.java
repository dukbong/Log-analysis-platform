package com.example.kafkadashboard.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kafkadashboard.dto.OrderDto;
import com.example.kafkadashboard.entity.OrderEntity;
import com.example.kafkadashboard.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;
	
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Test
	public void createOrderTest() {
		// Given
		OrderDto testOrderDto = OrderDto.builder().productName("testName").orderTime(LocalDateTime.now()).orderStatus(true).build();
		
		// When
		orderServiceImpl.createOrder(testOrderDto);
		
		// Then
		verify(orderRepository, times(1)).save(Mockito.any(OrderEntity.class));
	}
	
	@Test
	public void cancelOrderTest() {
		// Given
		Long testOrderId = 1L;
		OrderEntity testEntity = OrderEntity.builder().id(1L).productName("testName").orderTime(LocalDateTime.now()).orderStatus(true).build();
		
		when(orderRepository.findById(testOrderId)).thenReturn(Optional.of(testEntity));
		
		// When
		orderServiceImpl.cancelOrder(1L);
		
		// Then
		verify(orderRepository, times(1)).save(Mockito.any(OrderEntity.class));
		verify(orderRepository, times(1)).findById(testOrderId);
		assertThat(testEntity.getOrderStatus()).isEqualTo(false);
	}
	
}
