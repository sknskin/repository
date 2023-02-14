package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.ProductEntity;
import com.project.repository.ProductRepository;

@Service( "menuService" )
public class MenuServiceImpl implements MenuService {

	@Autowired
	private ProductRepository productRepository;
	
	// 각 태그(카테고리) 불러와서 products에 저장하기
	// 데이터베이스에 접근해서 Product 모두 불러오기
	@Override
	public List<ProductEntity> findAllProduct() {
		
		List<ProductEntity> products = productRepository.findAll();
	
		return products;
	}
	
	// 데이터베이스에 접근해서 ProdCategory 불러오기
	@Override
	public List<ProductEntity> findProductByProdCategory(String prodCategory) {
		
		List<ProductEntity> products = productRepository.findByProdCategory(prodCategory);
	
		return products;
	}
	
	// 데이터베이스에 접근해서
	@Override 
	public ProductEntity findProductByCategoryAndProdId(String prodCategory, int prodId, String type) {
		
		ProductEntity product = null;
		if (type.equals("next")) {
			// ProductEntity product = productRepository.findByProdId(prodId);
			List<ProductEntity> products = prodCategory.equals("all") ? 
					productRepository.findNextByProductId(prodId) :
					productRepository.findNextByCategoryAndProductId(prodCategory, prodId);
			if (products.size() > 0) {
				product = products.get(0);
			}
		} else if (type.equals("current")) {
			product = productRepository.findById(prodId).orElse(null);
		} else if (type.equals("prev")) {
			List<ProductEntity> products =  prodCategory.equals("all") ? 
					productRepository.findPrevByProductId(prodId) :
					productRepository.findPrevByCategoryAndProductId(prodCategory, prodId);
			if (products.size() > 0) {
				product = products.get(0);
			}
		}
		if (product == null) {
			product = productRepository.findById(prodId).orElse(null);
		}
		return product;
	}

	// 데이터베이스에 접근해서 ProdCategory 전체 불러오기
	@Override
	public List<String> findAllCategories() {
		
		List<String> categories = productRepository.findAllCategories();
		
		return categories;
	}
	
}