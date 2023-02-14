package com.project.service;

import java.util.HashMap;
import java.util.List;

import com.project.dto.AdminDto;
import com.project.dto.ProductDto;
import com.project.entity.ProductEntity;

public interface AdminService {

	
	public default ProductDto productEntityToDto(ProductEntity productEntity) {
		
		ProductDto productDto = new ProductDto();
		
		productDto.setProdId(productEntity.getProdId());
		productDto.setProdCategory(productEntity.getProdCategory());
		productDto.setProdName(productEntity.getProdName());
		productDto.setProdDesc(productEntity.getProdDesc());
		productDto.setProdIngredients(productEntity.getProdIngredients());
		productDto.setProdPrice(productEntity.getProdPrice());
		productDto.setSavedFileName(productEntity.getSavedFileName());
		productDto.setUserFileName(productEntity.getUserFileName());
		
		return productDto;
	}
	
	public default ProductEntity productDtoToEntity(ProductDto productDto) {
		
		ProductEntity productEntity = ProductEntity.builder()
												   .prodId(productDto.getProdId())
												   .prodCategory(productDto.getProdCategory())
												   .prodName(productDto.getProdName())
												   .prodDesc(productDto.getProdDesc())
												   .prodIngredients(productDto.getProdIngredients())
												   .prodPrice(productDto.getProdPrice())
												   .savedFileName(productDto.getSavedFileName())
												   .userFileName(productDto.getUserFileName())
												   .build();
		
		return productEntity;
	}
	
	// Administrator login
	AdminDto findMemberByIdAndPasswd(String id, String passwd);
	
	// Administrator statistics (Recent Daily Sales)
//	List<E> selectSalesByRecentDaily();
	
	// Administrator statistics (Monthly Sales Statistics)
//	List<E> selectSalesByMonthly();
	
	// Administrator statistics (Popular Top 10 Menu)
	List<ProductDto> selectProductByRank();
	
	// TEST CODE
	// Administrator statistics (Popular Top 10 Menu) # JPQL TEST
	List<HashMap<String, Object>> selectProductByRank2();


	// 메뉴 등록
	void registerMenu(ProductDto product);

	// 카테고리 목록 불러오기
	List<String> findAllCategories();

	// 상품 목록 불러오기
	List<ProductDto> findAllProducts();

	// 메뉴 삭제
	void deleteMenu(int prodId);

	ProductDto findByProdId(int prodId);

	void modifyMenu(ProductDto product);

	
	
	
}
