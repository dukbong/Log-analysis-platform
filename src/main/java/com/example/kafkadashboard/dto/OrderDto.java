package com.example.kafkadashboard.dto;

import java.time.LocalDateTime;

import com.example.kafkadashboard.entity.OrderEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDto {
	
	private Long id;
	private String productName;
	private LocalDateTime orderTime;
	private Boolean orderStatus;
	
	@Builder
	public OrderDto(Long id, String productName, LocalDateTime orderTime, Boolean orderStatus) {
		this.id = id;
		this.productName = productName;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
	}
	
	public OrderEntity convertEntity() {
		return OrderEntity.builder().productName(this.productName)
							  .orderTime(this.orderTime)
							  .orderStatus(this.orderStatus)
							  .build();
	}
	
}
