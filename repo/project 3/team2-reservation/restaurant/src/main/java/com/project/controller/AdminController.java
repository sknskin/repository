package com.project.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.common.Util;
import com.project.dto.OrderDto;
import com.project.dto.ProductDto;
import com.project.service.AdminService;
import com.project.service.MessageService;
import com.project.service.OrderService;
import com.project.service.ReservationService;

@Controller
@RequestMapping(path = { "/admin" })
public class AdminController {

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
	
	
	// Administrator page (Administrator statistics)
	@GetMapping(path = { "/adminStatistics" })
	public String adminStatistics(Model model) {

		List<BigDecimal[]> messageCount = messageService.selectMessageCount();
		List<BigDecimal[]> reservationCount = reservationService.selectReservationCount();
		List<BigDecimal[]> orderCount = orderService.selectOrderCount();
		List<ProductDto> products = adminService.selectProductByRank();
		
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("reservationCount", reservationCount);
		model.addAttribute("orderCount", orderCount);
		model.addAttribute("products", products);

		return "admin/adminStatistics";
	}
	
	// TEST CODE
	// Administrator statistics (Popular Top 10 Menu) # JPQL TEST
	@GetMapping(path = { "/test" })
	@ResponseBody
	public List<HashMap<String, Object>> adminStatistics2(Model model) {

		List<HashMap<String, Object>> products = adminService.selectProductByRank2();
		return products;
	}
	
	// Administrator Order
	@GetMapping(path = { "/adminOrder" })
	public String adminOrder(Model model) {
		
		return "admin/adminOrder";
	}

	// Administrator 
	@PostMapping(path = { "/get-order-by-month" })
	public OrderDto monthlyOrderData(int month) {
		
		OrderDto order = new OrderDto(); 
//		= orderService.findOrderByMonth(month);
		
		return order;
	}

	// Administrator page (Administrator menu)
	@GetMapping(path = { "/adminMenu" })
	public String adminMenu(Model model) {
		
		// ???????????? ?????? ????????????
		List<String> categories = adminService.findAllCategories(); 
		
		// ?????? ?????? ???
		List<ProductDto> products = adminService.findAllProducts();
		
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);

		return "admin/adminMenu";
	}

	// Register menu
	@PostMapping(path = { "/registerMenu" })
	public String registerMenu(ProductDto product, String categorySelboxDirect, MultipartHttpServletRequest req) {
		
		// ???????????? ??????
		MultipartFile attach = req.getFile("productImg");

		if (attach != null) { // ????????? ?????? ??????
			//ServletContext application = req.getServletContext();
			// String path = application.getRealPath("/resources/product-images");
			// String path = this.getClass().getResource("/resources/product-images").getPath();
			// path = "C:\\product-images\\";
			String path = System.getProperty("user.home") + File.separator + "product-images" + File.separator;
			String fileName = attach.getOriginalFilename(); // ?????? ?????? ????????????
			if (fileName != null && fileName.length() > 0) {
				String uniqueFileName = Util.makeUniqueFileName(fileName);

				try {
					attach.transferTo(new File(path, uniqueFileName)); // ?????? ??????
					product.setUserFileName(fileName);
					product.setSavedFileName(uniqueFileName);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		String prodCategory = null;
		
		if (categorySelboxDirect == null || categorySelboxDirect.length() == 0) {
			prodCategory = product.getProdCategory();
		} else {
			prodCategory = categorySelboxDirect;
			product.setProdCategory(prodCategory);
		}

		adminService.registerMenu(product);

		return "redirect:adminMenu";
	}
	
	// Delete Menu
	@PostMapping(path = { "/deleteMenu" })
	public String deleteMenu(int prodId) {
		
		// prodId??? ???????????? ????????? ??????
		adminService.deleteMenu(prodId);
		
		return "redirect:/admin/adminMenu";
	}

	// Get Menu Data For Modify Menu
	@PostMapping(path = { "/getMenuData" })
	@ResponseBody
	public ProductDto modifyMenuData(int prodId, Model model) {
		
		// prodId??? ???????????? ?????? ????????? ????????????
		ProductDto modifyProduct = adminService.findByProdId(prodId);
		
		// model.addAttribute("mp", modifyProduct);		
		// return "redirect:/admin/adminMenu";
		return modifyProduct;
		
	}
	
	// Modify Menu
	@PostMapping(path = { "/modifyMenu" })
	public String modifyMenu(ProductDto product, String prevUserFileName, String prevSavedFileName,String modifyCategorySelboxDirect, MultipartHttpServletRequest req) {
		
		MultipartFile attach = req.getFile("modifyProductImg");
		
//		if (attach != null && attach.getSize() > 0) { // ????????? ?????? ??????
		if (attach != null) { // ????????? ?????? ??????
			ServletContext application = req.getServletContext();
			String path = application.getRealPath("/resources/product-images");
			String fileName = attach.getOriginalFilename(); // ?????? ?????? ????????????
			if (fileName != null && fileName.length() > 0) {
				String uniqueFileName = Util.makeUniqueFileName(fileName);
	
				try {
					attach.transferTo(new File(path, uniqueFileName)); // ?????? ??????
					product.setUserFileName(fileName);
					product.setSavedFileName(uniqueFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				product.setUserFileName(prevUserFileName);
				product.setSavedFileName(prevSavedFileName);
			}
		}
		
		String prodCategory = null;
		
		if (modifyCategorySelboxDirect == null || modifyCategorySelboxDirect.length() == 0) {
			prodCategory = product.getProdCategory();
		} else {
			prodCategory = modifyCategorySelboxDirect;
			product.setProdCategory(prodCategory);
		}
	
		adminService.modifyMenu(product);
		
		return "redirect:/admin/adminMenu";
	}
	
	
}










