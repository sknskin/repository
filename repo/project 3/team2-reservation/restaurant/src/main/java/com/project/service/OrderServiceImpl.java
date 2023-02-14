package com.project.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.OrderDetailDto;
import com.project.dto.OrderDto;
import com.project.entity.OrderDetailEntity;
import com.project.entity.OrderEntity;
import com.project.entity.ProductEntity;
import com.project.repository.OrderRepository;
import com.project.repository.ProductRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void saveOrder(OrderDto orderDto) {
		
		OrderEntity orderEntity = OrderEntity.builder().orderDate(orderDto.getOrderDate())
													   .tableId(orderDto.getTableId())
													   .orderTotalPrice(orderDto.getOrderTotalPrice())
													   .build();
		
		ArrayList<OrderDetailEntity> orderDetails = new ArrayList<>();
		int totalPrice = 0;
		
		for (OrderDetailDto orderDetailDto : orderDto.getOrderDetails()) {
			
			ProductEntity productEntity = productRepository.findById(orderDetailDto.getProdId()).orElse(null);
			
			OrderDetailEntity entity = OrderDetailEntity.builder()
														.orderCount(orderDetailDto.getOrderCount())
														.orderDetailPrice(orderDetailDto.getOrderDetailPrice())
														.order(orderEntity)
														.product(productEntity)
														.build();

			totalPrice += (entity.getOrderDetailPrice() * entity.getOrderCount());
			orderDetails.add(entity);
		}

		orderEntity.setOrderTotalPrice(totalPrice);		
		orderEntity.setOrderDetails(orderDetails);
		
		orderRepository.save(orderEntity);
	}
	
	// Administrator Order statistics
	@Override
	public List<BigDecimal[]> selectOrderCount() {
		
		List<BigDecimal[]> order = orderRepository.findOrderStatistics();
		
		return order;
	}
}
