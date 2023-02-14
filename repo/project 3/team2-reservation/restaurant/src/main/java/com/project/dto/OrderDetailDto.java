package com.project.dto;

import lombok.Data;

@Data
public class OrderDetailDto {

	private int orderDetailId;
	private int orderId;
	private int prodId;
	private int orderCount;
	private int orderDetailPrice;
	
}
