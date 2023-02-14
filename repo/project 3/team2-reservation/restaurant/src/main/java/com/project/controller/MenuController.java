package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.ProductEntity;
import com.project.service.MenuService;

@Controller
@RequestMapping(path = { "/menu" })
public class MenuController {
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	
	// 1,2 전체 메뉴 불러오고, 태그(카테고리) 버튼 클릭 시 해당 태그 메뉴 리스트 보여주기
	// 페이징 추가
	@GetMapping(path = { "/list" , "/" })
	public String aboutMenu(String prodCategory, Model model) {
	
		// 모든 메뉴 또는 카테고리 저장
		List<ProductEntity> products = null;
		
//		if (prodCategory == null || prodCategory.length() == 0 ) {
//			// return "redirect:/home";
//			products = menuService.findAllProduct();
//			
//		} else {
//			products = menuService.findProductByProdCategory(prodCategory);
//			
//		} 
		
		products = menuService.findAllProduct();

		// 모든 category 조회
		List<String> categories = menuService.findAllCategories();
	
		model.addAttribute("products", products);
		model.addAttribute("prodCategory", prodCategory);
		model.addAttribute("categories",categories);

		return "menu";

	}
		
	// 3. 사용자페이지_메뉴 디테일 정보 보기 
	@GetMapping(path = { "/menudetail" })
	public String detailMenu(@RequestParam(defaultValue = "0") int prodId,
							 @RequestParam(defaultValue = "current") String type,
							 @RequestParam(defaultValue = "all") String prodCategory, 
							 HttpSession session, Model model) {
	
		// 모든 메뉴 또는 카테고리 저장
//		List<ProductEntity> products = null;
//		
//		if (prodCategory == null || prodCategory.length() == 0 ) {
//			// return "redirect:/home";
//			products = menuService.findAllProduct();
//			
//		} else {
//			products = menuService.findProductByProdCategory(prodCategory);
//			
//		} 
		
		if (prodCategory.equals("SoftDrink")) {
			prodCategory = "Soft Drink";
		}
		
		ProductEntity product = menuService.findProductByCategoryAndProdId(prodCategory, prodId, type);
		
		// 모든 category 조회
		List<String> categories = menuService.findAllCategories();

		model.addAttribute("product", product);
		model.addAttribute("categories",categories);
		model.addAttribute("prodCategory", prodCategory);
		
		return "menudetail";
	}

}