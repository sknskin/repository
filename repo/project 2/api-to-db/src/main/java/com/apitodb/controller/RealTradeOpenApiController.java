package com.apitodb.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.apitodb.dto.RealTradeDto;

@Controller
@RequestMapping(path = "/openapi")
public class RealTradeOpenApiController {

	@GetMapping(path = { "/realtrade" })
	public String searchRealTrade(Model model) {
		
		try {
			// 반복문 횟수 정하기 위한 코드
			StringBuilder urlBuilder2 = new StringBuilder("http://openapi.seoul.go.kr:8088");
			
			// URL
			urlBuilder2.append("/" + URLEncoder.encode("6d54535546726c613130377949757352", "UTF-8"));
			urlBuilder2.append("/" + URLEncoder.encode("xml", "UTF-8"));		
			urlBuilder2.append("/" + URLEncoder.encode("tbLnOpendataRtmsV", "UTF-8"));
			
			// 서비스명 (대소문자 구분 필수입니다.)
			urlBuilder2.append("/" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder2.append("/" + URLEncoder.encode("5", "UTF-8"));
	        
	        URL url2 = new URL(urlBuilder2.toString());
			HttpURLConnection connn = (HttpURLConnection) url2.openConnection();
	        connn.setRequestMethod("GET");
	        connn.setRequestProperty("Content-type", "application/xml");
	        
	        DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder2 = factory2.newDocumentBuilder();
	        
	        Document doc2 = builder2.parse(connn.getInputStream()); // xml 문자열 -> 객체 트리
	        
	        NodeList openRealTrade = doc2.getElementsByTagName("tbLnOpendataRtmsV");
	        // System.out.println(openRealTrade);
	        
	        Element node2 = (Element)openRealTrade.item(0);
	        // System.out.println(node2);
	        
	        double count = Integer.parseInt(node2.getElementsByTagName("list_total_count").item(0).getTextContent());
			double rCount = Math.ceil(count / 1000);
	        
			// for (int i = 0; i < rCount; i++) { // 데이터 조회 for문 시작
			for (int i = 0; i < 3; i++) {
				StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
	
				// URL
				urlBuilder.append("/" + URLEncoder.encode("6d54535546726c613130377949757352", "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode("xml", "UTF-8"));		
				urlBuilder.append("/" + URLEncoder.encode("tbLnOpendataRtmsV", "UTF-8"));
				
				// 서비스명 (대소문자 구분 필수입니다.)
				urlBuilder.append("/" + URLEncoder.encode(String.format("%d", 1 + (i * 1000)), "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode(String.format("%d", 1000 + (i * 1000)), "UTF-8"));
		        
				urlBuilder.append("/" + URLEncoder.encode("2022", "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode("", "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode("강서구", "UTF-8"));
				
		        URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/xml");
		        
		        
		        // API 내용 가져오기
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        
		        Document doc = builder.parse(conn.getInputStream()); // xml 문자열 -> 객체 트리
		        
		        NodeList rows = doc.getElementsByTagName("row");
		        
		        List<RealTradeDto> infos = new ArrayList<>();
		        
		        for (int j = 0; j < rows.getLength(); j++) {
		        
	
		        	Element node = (Element)rows.item(j);
		        	
		        	RealTradeDto info = new RealTradeDto();
		        	
		        	info.setAccYear(Integer.parseInt(node.getElementsByTagName("ACC_YEAR").item(0).getTextContent()));
		        	info.setSggNm(node.getElementsByTagName("SGG_NM").item(0).getTextContent());
		        	info.setBjdongNm(node.getElementsByTagName("BJDONG_NM").item(0).getTextContent());
		        	info.setBldgNm(node.getElementsByTagName("BLDG_NM").item(0).getTextContent());
		        	// info.setFloor(Integer.parseInt(node.getElementsByTagName("FLOOR").item(0).getTextContent()));
		        	info.setBldgArea(node.getElementsByTagName("BLDG_AREA").item(0).getTextContent());
		        	info.setTotArea(node.getElementsByTagName("TOT_AREA").item(0).getTextContent());
		        	info.setBuildYear(node.getElementsByTagName("BUILD_YEAR").item(0).getTextContent());
		        	info.setHouseType(node.getElementsByTagName("HOUSE_TYPE").item(0).getTextContent());
		        	info.setReqGbn(node.getElementsByTagName("REQ_GBN").item(0).getTextContent());
		        	
		        	infos.add(info);
		        }
		        
		        model.addAttribute("infos", infos);
		        
				// DB에 저장하는 코드
//				Connection conn2 = null; // 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
//				PreparedStatement pstmt = null; // 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
//
//				try {
//					// 1. Driver 등록
//					Class.forName("com.mysql.cj.jdbc.Driver");
//
//					// 2. 연결 및 연결 객체 가져오기
//					conn2 = DriverManager.getConnection("jdbc:mysql://43.201.107.251:3306/realestate", // 데이터베이스 연결 정보
//							"team2", "team2"); // 데이터베이스 계정 정보
//
//					// 3. SQL 작성 + 명령 객체 가져오기
//					for (int k = 0; k < infos.size(); k++) {
//						String sql = "INSERT INTO RealTrade (accYear, sggNm, bjdongNm, bldgNm, floor, bldgArea, totArea, buildYear, houseType, reqGbn) "
//								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "; // ? : 나중에 채워질 영역 표시
//						pstmt = conn2.prepareStatement(sql);
//						pstmt.setInt(1, infos.get(k).getAccYear());
//						pstmt.setString(2, infos.get(k).getSggNm());
//						pstmt.setString(3, infos.get(k).getBjdongNm());
//						pstmt.setString(4, infos.get(k).getBldgNm());
//						pstmt.setInt(5, infos.get(k).getFloor());
//						pstmt.setString(6, infos.get(k).getBldgArea());
//						pstmt.setString(7, infos.get(k).getTotArea());
//						pstmt.setString(8, infos.get(k).getBuildYear());
//						pstmt.setString(8, infos.get(k).getHouseType());
//						pstmt.setString(8, infos.get(k).getReqGbn());
//
//						// 4. 명령 실행
//						pstmt.executeUpdate(); // executeUpdate : select 이외의 SQL에 사용하는 메서드
//					}
//					
//				} catch (Exception ex) {
//					ex.printStackTrace(); // 개발 용도로 사용
//				} finally {
//					// 6. 연결 닫기
//					try { pstmt.close(); } catch (Exception ex) {}
//					try { conn2.close(); } catch (Exception ex) {}
//				}
			} // 데이터 조회 for문 끝
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "openapi/realtrade";

	}
}
