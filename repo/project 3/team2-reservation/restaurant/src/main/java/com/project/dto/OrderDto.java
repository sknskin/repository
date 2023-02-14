package com.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {

	private int orderId;
	private String orderDate;
	private int tableId;
	private int orderTotalPrice;
	
	private List<OrderDetailDto> orderDetails;
	
}
