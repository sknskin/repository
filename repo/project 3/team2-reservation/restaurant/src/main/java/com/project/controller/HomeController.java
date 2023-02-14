package com.project.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.project.dto.AdminDto;
import com.project.dto.ProductDto;
import com.project.entity.ProductEntity;
import com.project.service.AdminService;
import com.project.service.MenuService;
import com.project.service.MessageService;
import com.project.service.OrderService;
import com.project.service.ReservationService;

@Controller
public class HomeController {

	@Autowired
	@Qualifier("adminService")
	private AdminService adminService; 
	
	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;
	
	@Autowired
	@Qualifier("reservationService")
	private ReservationService reservationService;
	
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	
	// home form _ 메인페이지 메뉴리스트 노출
	@GetMapping(path = { "/", "/home" })
	public String home(String prodCategory, Model model) {
		
		List<ProductEntity> products = null;
		
		if (prodCategory == null || prodCategory.length() == 0 ) {
			// return "redirect:/home";
			products = menuService.findAllProduct();
			
		} else {
			products = menuService.findProductByProdCategory(prodCategory);
			
		}
		
		// 모든 category 조회
		List<String> categories = menuService.findAllCategories();
		
		model.addAttribute("prodCategory", prodCategory);
		model.addAttribute("categories",categories);
		model.addAttribute("products",products);
		
		return "home";
	}

	// about form
	@GetMapping(path = { "/about" })
	public String about() {

		return "about";
	}

	// contact form
	@GetMapping(path = { "/contact" })
	public String contact() {

		return "contact";
	}

	// reservation form
	@GetMapping(path = { "/reservation" })
	public String reservation() {

		return "reservation/reservation";
	}

	// Administrator login form
	@GetMapping(path = { "/login" })
	public String admin(HttpSession session, Model model) {
		
		List<ProductDto> products = adminService.selectProductByRank();
		
		if (session.getAttribute("admin") != null) {
			model.addAttribute("products", products);
			return "admin/adminStatistics";
		} else {
			return "login";
		}
	}

	// Administrator logout
	@GetMapping(path = { "/logout" })
	public View logout(HttpSession session) {
		
		session.removeAttribute("admin");
		
		return new RedirectView("login");
	}
	
	// login -> Administrator main form
	@PostMapping(path = { "/adminStatistics" })
	public String adminPageByLogin(String id, String passwd, HttpSession session, Model model) {

		AdminDto admin = adminService.findMemberByIdAndPasswd(id, passwd);
		
		List<BigDecimal[]> messageCount = messageService.selectMessageCount();
		List<BigDecimal[]> reservationCount = reservationService.selectReservationCount();
		List<BigDecimal[]> orderCount = orderService.selectOrderCount();
		List<ProductDto> products = adminService.selectProductByRank();
		
		if (admin != null) {
			model.addAttribute("messageCount", messageCount);
			model.addAttribute("reservationCount", reservationCount);
			model.addAttribute("orderCount", orderCount);
			model.addAttribute("products", products);
			session.setAttribute("messageCount", messageCount);
			session.setAttribute("reservationCount", reservationCount);
			session.setAttribute("orderCount", orderCount);
			session.setAttribute("products", products);
			session.setAttribute("admin", admin);
			return "admin/adminStatistics";
		} else {
			model.addAttribute("loginfail", id);
			System.out.println("login fail");
			return "login";
		}
	}

}
