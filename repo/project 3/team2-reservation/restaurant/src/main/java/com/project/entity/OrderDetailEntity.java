package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_order_detail")
public class OrderDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderDetailId;

	@Column(nullable = false)
	private int orderCount;
	
	@Column(nullable = false)
	private int orderDetailPrice;
	
	@ManyToOne()
	@JoinColumn(name="orderId")
	private OrderEntity order;
	
	@ManyToOne()
	@JoinColumn(name="prodId")
	private ProductEntity product;
	
}







