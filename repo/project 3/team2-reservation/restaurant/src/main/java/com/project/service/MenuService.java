package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.entity.ProductEntity;

@Service
public interface MenuService {

	// 데이터베이스에 접근해서 메뉴 전체 데이터 불러오기
	List<ProductEntity> findAllProduct();
	
	// 데이터베이스에 접근해서 ProdCategory 데이터 불러오기
	List<ProductEntity> findProductByProdCategory(String prodCategory);

	// 데이터베이스에 접근해서 ProdId 데이터 불러오기
	//ProductEntity findProductsByProdId(int prodId);

	// 데이터베이스에 접근해서 ProdCategory 전체 데이터 불러오기
	List<String> findAllCategories();
	
	// 데이터베이스에 접근해서 ProdCategory, prodId 데이터 불러오기
	ProductEntity findProductByCategoryAndProdId(String prodCategory, int prodId, String type);

}
