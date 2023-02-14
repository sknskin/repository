package com.project.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class ProductEntity {
	
	public ProductDto exportProductDto() {
		ProductDto product = new ProductDto();
		product.setProdName(prodName);
		product.setProdCategory(prodCategory);
		product.setProdPrice(prodPrice);
		product.setProdDesc(prodDesc);
		product.setProdIngredients(prodIngredients);
		product.setUserFileName(userFileName);
		product.setSavedFileName(savedFileName);
		
		return product;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int prodId;
	
	@Column(length = 100, nullable = false)
	private String prodName;
	
	@Column(length = 100, nullable = false)
	private String prodCategory;

	@Column(nullable = false)
	private int prodPrice;
	
	@Column(length = 1000)
	private String prodDesc;
	
	@Column(length = 1000)
	private String prodIngredients;
	
	@Column(length = 100)
	private String userFileName;
	
	@Column(length = 100)
	private String savedFileName;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name="prodId")
	private Collection<OrderDetailEntity> orderProductDetails;

}

