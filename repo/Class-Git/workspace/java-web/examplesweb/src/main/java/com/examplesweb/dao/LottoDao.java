package com.examplesweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.examplesweb.dto.LottoDto;

public class LottoDao {
	
	// 번호 저장 : 사용자가 입력한 번호를 받아서 (전달인자) 데이터베이스에 저장
	public void insertLotto(LottoDto lottoDto) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )		
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demoweb", 		// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"INSERT INTO lotto " +
					"VALUES (?, ?, ?, ?, ?, ?)"; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lottoDto.getNo1());	// SQL의 1번째 ?를 대체할 데이터
			pstmt.setInt(2, lottoDto.getNo2());	// SQL의 2번째 ?를 대체할 데이터
			pstmt.setInt(3, lottoDto.getNo3());	// SQL의 3번째 ?를 대체할 데이터
			pstmt.setInt(4, lottoDto.getNo4());	// SQL의 4번째 ?를 대체할 데이터
			pstmt.setInt(5, lottoDto.getNo5());	// SQL의 5번째 ?를 대체할 데이터
			pstmt.setInt(6, lottoDto.getNo6());	// SQL의 6번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			pstmt.executeUpdate(); // executeUpdate : select 이외의 SQL에 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)				
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기			
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

	// 번호 조회 : 모든 번호 조회해서 반환
	public List<LottoDto> selectAllLotto() {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<LottoDto> lottos = new ArrayList<>();		// 조회한 데이터를 저장할 DTO 객체
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demoweb",	 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT no1, no2, no3, no4, no5, no6 " +
					"FROM lotto ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				LottoDto l = new LottoDto(); // 한 행의 데이터를 저장할 DTO 객체 생성
				l.setNo1(rs.getInt(1));
				l.setNo2(rs.getInt(2));
				l.setNo3(rs.getInt(3));
				l.setNo4(rs.getInt(4));
				l.setNo5(rs.getInt(5));
				l.setNo6(rs.getInt(6));
				
				lottos.add(l);	// 목록에 DTO 객체 추가
			}
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return lottos;
	}
}
