package com.project.dto;

import lombok.Data;

@Data
public class ProductDto {

	private int prodId;
	private String prodName;
	private String prodCategory;
	private int prodPrice;
	private String prodDesc;
	private String prodIngredients;
	private String userFileName;
	private String savedFileName;
	
}
