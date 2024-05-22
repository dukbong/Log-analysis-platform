package com.example.kafkadashboard.entity;

import java.time.LocalDateTime;

import com.example.kafkadashboard.dto.OrderDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name = "order_entity_gen", sequenceName = "order_entity_seq")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

	@Id
	@GeneratedValue(generator = "order_entity_gen")
	private Long id;
	
	private String productName;
	
	private LocalDateTime orderTime;
	
	private Boolean orderStatus;
	
	@Builder
	public OrderEntity(Long id, String productName, LocalDateTime orderTime, Boolean orderStatus) {
		this.productName = productName;
		this.orderTime = orderTime;
		this.orderStatus = orderStatus;
	}

	public void cancelOrder() {
		this.orderStatus = false;
	}

	public OrderDto convertDTO() {
		return OrderDto.builder().id(this.id).productName(this.productName).orderTime(this.orderTime).orderStatus(this.orderStatus).build();
	}
	
}
