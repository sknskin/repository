package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {

	
	
}
