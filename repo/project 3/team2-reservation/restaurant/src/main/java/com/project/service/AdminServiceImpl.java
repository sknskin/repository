package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.project.common.Util;
import com.project.dto.AdminDto;
import com.project.dto.ProductDto;
import com.project.entity.AdminEntity;
import com.project.entity.ProductEntity;
import com.project.repository.AdminRepository;
import com.project.repository.ProductRepository;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
	// Administrator login
	@Override
	public AdminDto findMemberByIdAndPasswd(String id, String passwd) {
		
		AdminEntity adminEntity = adminRepository.findByIdAndPasswd(id, passwd);
		
		return adminEntity != null ? adminEntity.exportAdminDto() : null;
	}
	
	// Administrator statistics (Recent Daily Sales)
//	@Override
//	public List<E> selectSalesByRecentDaily() {
//		
//		List<E> dailySalesList = productRepository.findSalesByRecentDaily();
//		ArrayList<E> dailySales = new ArrayList<>();
//		
//		for (Entity entity : dailySalesList) {
//			dailySales.add(entityToDto(entity));
//		}
//		
//		return dailySales;
//	}
	
	// Administrator statistics (Monthly Sales Statistics)
//	@Override
//	public List<E> selectSalesByMonthly() {
//		
//		List<E> monthlySalesList = productRepository.findSalesByMonthly();
//		ArrayList<E> monthlySales = new ArrayList<>();
//		
//		for (Entity entity : monthlySalesList) {
//			monthlySales.add(entityToDto(entity));
//		}
//		
//		return monthlySales;
//	}
	
	// Administrator statistics (Popular Top 10 Menu)
	@Override
	public List<ProductDto> selectProductByRank() {
		
		List<ProductEntity> productList = productRepository.findProductByRank();
		ArrayList<ProductDto> products = new ArrayList<>();
		
		for (ProductEntity productEntity : productList) {
			products.add(productEntityToDto(productEntity));
		}
		
		return products;
	}

	// TEST CODE
	// Administrator statistics (Popular Top 10 Menu) # JPQL TEST	
	@Override
	public List<HashMap<String, Object>> selectProductByRank2() {
		
		List<HashMap<String, Object>> productList = productRepository.findProductStatistics();

		return productList;
	}
	
	
	@Override
	public void registerMenu(ProductDto product) {

		ProductEntity productEntity = ProductEntity.builder()
												   .prodName(product.getProdName())
												   .prodCategory(product.getProdCategory())
												   .prodPrice(product.getProdPrice())
												   .prodDesc(product.getProdDesc())
												   .prodIngredients(product.getProdIngredients())
												   .userFileName(product.getUserFileName())
												   .savedFileName(product.getSavedFileName())
												   .build();

		productRepository.save(productEntity);
	}

	@Override
	public List<String> findAllCategories() {

		List<String> productList = productRepository.findAllCategories();
		
		return productList;
	}

	@Override
	public List<ProductDto> findAllProducts() {
		
		List<ProductEntity> productsEntity = productRepository.findAllproduct();
		ArrayList<ProductDto> products = new ArrayList<>();
		
		for (ProductEntity productEntity : productsEntity) {
			products.add(productEntityToDto(productEntity));
		}
		
		return products;
	}

	@Override
	public void deleteMenu(int prodId) {

		productRepository.deleteById(prodId);
		
	}

	@Override
	public ProductDto findByProdId(int prodId) {

		ProductEntity pe = productRepository.findById(prodId).orElse(null);
		ProductDto product = productEntityToDto(pe);
		
		return product;
	}

	@Override
	public void modifyMenu(ProductDto product) {

		ProductEntity pe = productDtoToEntity(product);
		productRepository.updateMenu(pe);

//		productRepository.updateMenu(product);
		
	}

}
