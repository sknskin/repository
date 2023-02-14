package com.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dto.OrderDto;
import com.project.dto.ProductDto;
import com.project.service.OrderService;

@Controller
@RequestMapping(path = { "/connectDatabase" })
public class TabletController {
	
	@Autowired
	private OrderService orderService;
	
//	@Autowired
//	private ReservationService reservationService;
	

	// Spring -> React 상품 리스트 조회
	@CrossOrigin
	@ResponseBody
	@GetMapping(path = { "/loadProductList" })
	public List<ProductDto> searchProductList() {

		List<ProductDto> productLists = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@3.36.132.159:1521:xe", "team2", "team2");

			String sql = "SELECT * FROM TBL_PRODUCT ";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDto productList = new ProductDto();

				productList.setProdId(rs.getInt(1));
				productList.setProdCategory(rs.getString(2));
				productList.setProdDesc(rs.getString(3));
				productList.setProdIngredients(rs.getString(4));
				productList.setProdName(rs.getString(5));
				productList.setProdPrice(rs.getInt(6));
				productList.setSavedFileName(rs.getString(7));
				productList.setUserFileName(rs.getString(8));

				productLists.add(productList);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				pstmt.close();
			} catch (Exception ex) {
			}
			try {
				conn.close();
			} catch (Exception ex) {
			}
		}

		return productLists;
	}
	
	// React -> Spring 주문 등록
	// RequestBody 사용해서 Json 데이터 읽기
	@CrossOrigin
	@PostMapping(path = { "/saveOrder" })
	@ResponseBody
    public OrderDto saveOrderRequest(@RequestBody OrderDto orderDto) {
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		Date date = java.sql.Timestamp.valueOf(localDateTime);
		orderDto.setOrderDate(sdf.format(date));
		orderService.saveOrder(orderDto);
        
        return orderDto;
    }
	
	// before ocr test, check reservation info and user reservation info number
//	@CrossOrigin
//	@PostMapping(path = { "/find-reservation-by-name" })
//	public String selectReservationByName(String resName) {
//		
//		// fixed TableNo (each table fixed int value)
//		final int resTableId = 1;
//		reservationService.selectReservationByNameAndTableId(resName, resTableId);
//		
//		return "identification";
//	}
	

	// @GetMapping(path = { "/find-reservation-by-number" })
}
