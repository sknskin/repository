package com.project.service;

import java.math.BigDecimal;
import java.util.List;

import com.project.dto.OrderDto;

public interface OrderService {
	
	void saveOrder(OrderDto order);

	// Administrator statistics order count
	List<BigDecimal[]> selectOrderCount();
}
