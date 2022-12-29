package com.apitodb.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.apitodb.dto.AptInfoDto;

@Controller
@RequestMapping(path = "/openapi")
public class AptInfoOpenApiController {

	// DB에 API 데이터 넣기
	@GetMapping(path = { "/aptInfo" })
	public String searchAptInfo(Model model) {

		try {
			// 반복문 횟수 정하기 위한 코드
			StringBuilder urlBuilder2 = new StringBuilder("http://openapi.seoul.go.kr:8088");

			// URL
			urlBuilder2.append("/" + URLEncoder.encode("52796a6a767772793838637770714b", "UTF-8"));
			urlBuilder2.append("/" + URLEncoder.encode("xml", "UTF-8"));
			urlBuilder2.append("/" + URLEncoder.encode("OpenAptInfo", "UTF-8"));

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

			NodeList openAptInfo = doc2.getElementsByTagName("OpenAptInfo");
			System.out.println(openAptInfo);

			Element node2 = (Element) openAptInfo.item(0);

			double count = Integer.parseInt(node2.getElementsByTagName("list_total_count").item(0).getTextContent());
			double rCount = Math.ceil(count / 1000);

			for (int i = 0; i < rCount; i++) { // 데이터 조회 for문 시작
				StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

				// URL
				urlBuilder.append("/" + URLEncoder.encode("52796a6a767772793838637770714b", "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode("xml", "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode("OpenAptInfo", "UTF-8"));

				// 서비스명 (대소문자 구분 필수입니다.)
				urlBuilder.append("/" + URLEncoder.encode(String.format("%d", 1 + (i * 1000)), "UTF-8"));
				urlBuilder.append("/" + URLEncoder.encode(String.format("%d", 1000 + (i * 1000)), "UTF-8"));

				URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/xml");

				// API 내용 가져오기
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document doc = builder.parse(conn.getInputStream()); // xml 문자열 -> 객체 트리

				NodeList rows = doc.getElementsByTagName("row");

				List<AptInfoDto> infos = new ArrayList<AptInfoDto>();

				for (int j = 0; j < rows.getLength(); j++) {

					Element node = (Element) rows.item(j);

					AptInfoDto info = new AptInfoDto();

					info.setAptNm(node.getElementsByTagName("APT_NM").item(0).getTextContent());
					info.setAdresGu(node.getElementsByTagName("ADRES_GU").item(0).getTextContent());
					info.setAdresDong(node.getElementsByTagName("ADRES_DONG").item(0).getTextContent());
					info.setHshldrTy(node.getElementsByTagName("HSHLDR_TY").item(0).getTextContent());
					info.setGnrlManagectManageStle(
							node.getElementsByTagName("GNRL_MANAGECT_MANAGE_STLE").item(0).getTextContent());
					info.setCrrdprTy(node.getElementsByTagName("CRRDPR_TY").item(0).getTextContent());
					info.setHeatMthd(node.getElementsByTagName("HEAT_MTHD").item(0).getTextContent());
					String dongCnt = node.getElementsByTagName("ALL_DONG_CO").item(0).getTextContent();
					info.setAllDongCo(Integer.parseInt(dongCnt == "" ? "0" : dongCnt));

					infos.add(info);
				}

				model.addAttribute("infos", infos);

				// DB에 저장하는 코드
				Connection conn2 = null; // 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
				PreparedStatement pstmt = null; // 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )

				try {
					// 1. Driver 등록
					Class.forName("com.mysql.cj.jdbc.Driver");

					// 2. 연결 및 연결 객체 가져오기
					conn2 = DriverManager.getConnection("jdbc:mysql://43.201.107.251:3306/realestate", // 데이터베이스 연결 정보
							"team2", "team2"); // 데이터베이스 계정 정보

					// 3. SQL 작성 + 명령 객체 가져오기
					for (int k = 0; k < infos.size(); k++) {
						String sql = "INSERT INTO AptInfo (aptNm, adresGu, adresDong, hshldrTy, gnrlManagectManageStle, crrdprTy, heatMthd, allDongCo) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "; // ? : 나중에 채워질 영역 표시
						pstmt = conn2.prepareStatement(sql);
						pstmt.setString(1, infos.get(k).getAptNm());
						pstmt.setString(2, infos.get(k).getAdresGu());
						pstmt.setString(3, infos.get(k).getAdresDong());
						pstmt.setString(4, infos.get(k).getHshldrTy());
						pstmt.setString(5, infos.get(k).getGnrlManagectManageStle());
						pstmt.setString(6, infos.get(k).getCrrdprTy());
						pstmt.setString(7, infos.get(k).getHeatMthd());
						pstmt.setInt(8, infos.get(k).getAllDongCo());

						// 4. 명령 실행
						pstmt.executeUpdate(); // executeUpdate : select 이외의 SQL에 사용하는 메서드
					}

				} catch (Exception ex) {
					ex.printStackTrace(); // 개발 용도로 사용
				} finally {
					// 6. 연결 닫기
					try {
						pstmt.close();
					} catch (Exception ex) {
					}
					try {
						conn2.close();
					} catch (Exception ex) {
					}
				}
			} // 데이터 조회 for문 끝

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "openapi/aptInfo";

	}

	// 아파트 통계 차트 데이터 가져오기
	@CrossOrigin
	@ResponseBody
	@GetMapping(path = { "/loadAptInfoCount" })
	public HashMap<String, Object> searchAptInfoCountData() {

		HashMap<String, Object> data = new HashMap<>();

		// DB에 저장하는 코드
		Connection conn = null; // 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null; // 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;

		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://43.201.107.251:3306/realestate", // 데이터베이스 연결 정보
					"team2", "team2"); // 데이터베이스 계정 정보

			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = " SELECT COUNT(CASE WHEN hshldrTy = '분양' then 1 end), "
					+ "	   COUNT(CASE WHEN hshldrTy = '임대' then 1 end), "
					+ "       COUNT(CASE WHEN hshldrTy = '영구임대' then 1 end), "
					+ "       COUNT(CASE WHEN hshldrTY = '임대+분양' then 1 end), "
					+ "       COUNT(CASE WHEN hshldrTy = '' OR hshldrTy = '기타' then 1 end), "
					+ "       COUNT(CASE WHEN gnrlManagectManageStle = '직영' then 1 end), "
					+ "       COUNT(CASE WHEN gnrlManagectManageStle = '위탁관리' then 1 end), "
					+ "       COUNT(CASE WHEN gnrlManagectManageStle = '자치관리' then 1 end), "
					+ "       COUNT(CASE WHEN gnrlManagectManageStle = '직영+위탁' then 1 end), "
					+ "       COUNT(CASE WHEN gnrlManagectManageStle = '' then 1 end), "
					+ "       COUNT(CASE WHEN crrdprTy = '계단식' then 1 end), "
					+ "       COUNT(CASE WHEN crrdprTy = '복도식' then 1 end), "
					+ "       COUNT(CASE WHEN crrdprTy = '타워형' then 1 end), "
					+ "       COUNT(CASE WHEN crrdprTy = '혼합식' then 1 end), "
					+ "       COUNT(CASE WHEN crrdprTy = '' OR crrdprTy= '기타' then 1 end), "
					+ "       COUNT(CASE WHEN heatMthd = '지역난방' then 1 end), "
					+ "       COUNT(CASE WHEN heatMthd = '중앙난방' then 1 end), "
					+ "       COUNT(CASE WHEN heatMthd = '개별난방' then 1 end), "
					+ "       COUNT(CASE WHEN heatMthd = '' OR heatMthd = '기타' then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 0 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 1 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 2  then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 3 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 4 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 5 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 6 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 7 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 8 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 9 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco = 10 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco >= 11 AND allDongco <= 20 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco >= 21 AND allDongco <= 30 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco >= 31 AND allDongco <= 40 then 1 end), "
					+ "       COUNT(CASE WHEN allDongco >= 41 then 1 end) " + "FROM AptInfo ";
			pstmt = conn.prepareStatement(sql);

			// 4. 명령 실행
			rs = pstmt.executeQuery();
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) { // 결과 집합의 다음 행으로 이동

				for (int i = 1; i <= 34; i++) {
					data.put("data" + i, rs.getInt(i));
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
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

		return data;

	}

	// DB에 있는 아파트 목록 데이터 불러오기
	@CrossOrigin
	@ResponseBody
	@GetMapping(path = { "/loadAptList" })
	public List<AptInfoDto> searchAptListData(String keyword) {

		List<AptInfoDto> infos = new ArrayList<>();

		// DB에 저장하는 코드
		Connection conn = null; // 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null; // 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;

		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://43.201.107.251:3306/realestate", // 데이터베이스 연결 정보
					"team2", "team2"); // 데이터베이스 계정 정보

			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "SELECT aptNm, adresGu, adresDong FROM AptInfo ";

			String[] columns = { "aptNm", "adresGu", "adresDong" };

			if (keyword == null) {
				sql += "ORDER BY adresGu, adresDong, aptNm ";
			} else {
				if (keyword != null && keyword.length() > 0) {
					sql += " WHERE " + columns[0] + " LIKE ? ";
					for (int i = 1; i < columns.length; i++) {
						sql += "OR " + columns[i] + " LIKE ? ";
					}
					sql += "ORDER BY adresGu, adresDong, aptNm ";
				}
			}

			pstmt = conn.prepareStatement(sql);

			if (keyword != null && keyword.length() > 0) {
				for (int i = 0; i < columns.length; i++) {
					pstmt.setString(i + 1, "%" + keyword + "%");
				}
			}

			// 4. 명령 실행
			rs = pstmt.executeQuery();

			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) { // 결과 집합의 다음 행으로 이동
				AptInfoDto info = new AptInfoDto();

				info.setAptNm(rs.getString(1));
				info.setAdresGu(rs.getString(2));
				info.setAdresDong(rs.getString(3));
				infos.add(info);
			}
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
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

		return infos;

	}

	// 메인페이지용
	@CrossOrigin
	@ResponseBody
	@GetMapping(path = { "/loadAllAptCount" })
	public HashMap<String, Object> loadAllAptCount() {

		HashMap<String, Object> allAptCount = new HashMap<>();

		// DB에 저장하는 코드
		Connection conn = null; // 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null; // 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;

		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection("jdbc:mysql://43.201.107.251:3306/realestate", // 데이터베이스 연결 정보
					"team2", "team2"); // 데이터베이스 계정 정보

			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "SELECT COUNT(*) FROM AptInfo ";
			pstmt = conn.prepareStatement(sql);

			// 4. 명령 실행
			rs = pstmt.executeQuery();
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) { // 결과 집합의 다음 행으로 이동
				
				DecimalFormat commaFormat = new DecimalFormat("###,###");
				String count = commaFormat.format(rs.getInt(1));
				
				allAptCount.put("dataCount", count);

			}
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
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

		return allAptCount;

	}

}
