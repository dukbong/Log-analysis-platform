package com.example.kafkadashboard.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
	
	@Test
	public void cancelOrderTestFailure() {
		// Given
		Long testOrderId = 1L;
		when(orderRepository.findById(testOrderId)).thenReturn(Optional.empty());
		
		// When, Then
		IllegalArgumentException throwException = Assertions.assertThrows(IllegalArgumentException.class, () -> orderServiceImpl.cancelOrder(testOrderId));
		assertThat(throwException.getMessage()).isEqualTo("Not found OrderEntity - orderId : " + testOrderId);
		verify(orderRepository, times(1)).findById(testOrderId);
	}
	
	@Test
	public void getAllOrdersTest() {
		// Given
		OrderEntity test1 = OrderEntity.builder().productName("testName1").orderTime(LocalDateTime.now()).orderStatus(true).build();
		OrderEntity test2 = OrderEntity.builder().productName("testName2").orderTime(LocalDateTime.now()).orderStatus(false).build();
		OrderEntity test3 = OrderEntity.builder().productName("testName3").orderTime(LocalDateTime.now()).orderStatus(true).build();
		List<OrderEntity> testList = new ArrayList<>();
		testList.add(test1);
		testList.add(test2);
		testList.add(test3);
		
		when(orderRepository.findAll()).thenReturn(testList);
		
		// When
		List<OrderDto> result = orderServiceImpl.getAllOrders();
		
		// Then
		verify(orderRepository, times(1)).findAll();
		assertThat(result.size()).isEqualTo(3);
		
		for(int i = 0; i < result.size(); i++) {
			assertThat(result.get(i).getId()).isEqualTo(testList.get(i).getId());
			assertThat(result.get(i).getProductName()).isEqualTo(testList.get(i).getProductName());
			assertThat(result.get(i).getOrderTime()).isEqualTo(testList.get(i).getOrderTime());
			assertThat(result.get(i).getOrderStatus()).isEqualTo(testList.get(i).getOrderStatus());
		}
	}
	
	@Test
	public void getOrderTest() {
		// Given
		Long orderId = 1L;
		OrderEntity testOrderEntity = OrderEntity.builder().id(orderId).productName("testName").orderTime(LocalDateTime.now()).orderStatus(true).build();
		when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrderEntity));
		
		// When
		OrderDto result = orderServiceImpl.getOrder(orderId);
		
		// Then
		verify(orderRepository, times(1)).findById(orderId);
		assertThat(result.getId()).isEqualTo(testOrderEntity.getId());
		assertThat(result.getProductName()).isEqualTo(testOrderEntity.getProductName());
		assertThat(result.getOrderTime()).isEqualTo(testOrderEntity.getOrderTime());
		assertThat(result.getOrderStatus()).isEqualTo(testOrderEntity.getOrderStatus());
	}
	
	@Test
	public void getOrderTestFailure() {
		// Given
		Long orderId = 1L;
		when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
		
		// When, Then
		IllegalArgumentException throwException = Assertions.assertThrows(IllegalArgumentException.class, () -> orderServiceImpl.getOrder(orderId));
		assertThat(throwException.getMessage()).isEqualTo("Not found OrderEntity - orderId : " + orderId);
		verify(orderRepository, times(1)).findById(orderId);
	}
	
}
