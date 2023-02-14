package com.project.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

	// order statistics
	@Query(value = "select a.c_month, count(*) cnt " +
				   "from " +
				   "( " + 
				        "select substr(order_date, 6, 2) c_month " +
				        "from tbl_order " +
				   ") a " +
				   "group by a.c_month ", 
		   nativeQuery = true)
	List<BigDecimal[]> findOrderStatistics();
	
	
}
