package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demoweb.dto.MemberDto;

public class MemberDao {
	
	// 1. 회원가입 : 사용자의 입력을 받아서(전달인자) DB에 저장
	public void insertMember(MemberDto member) {
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
					"INSERT INTO member (memberid, passwd, email) " +
					"VALUES (?, ?, ?)"; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());	// SQL의 1번째 ?를 대체할 데이터
			pstmt.setString(2, member.getPasswd());		// SQL의 2번째 ?를 대체할 데이터
			pstmt.setString(3, member.getEmail());		// SQL의 3번째 ?를 대체할 데이터
			
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

	
	// 2. 로그인 : 아이디와 패스워드를 받아서(전달인자) DB의 데이터 조회하고 반환 (return, 결과형)
	public MemberDto selectMemberByIdAndPasswd(String memberId, String passwd) {
		
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		MemberDto memberDto = null;		// 조회한 데이터를 저장할 DTO 객체
		
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
					"SELECT memberid, email, usertype, regdate, active " +
					"FROM member " +
					"WHERE memberid = ? " + // ? : 나중에 채워질 영역 표시
					"      AND " + 
					"      passwd = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);	// SQL의 1번째 ?를 대체할 데이터
			pstmt.setString(2, passwd);		// SQL의 2번째 ?를 대체할 데이터
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			if (rs.next()) {	// 결과 집합의 다음 행으로 이동
				memberDto = new MemberDto();
				memberDto.setMemberId(rs.getString(1));
				memberDto.setEmail(rs.getString(2));
				memberDto.setUserType(rs.getString(3));
				memberDto.setRegDate(rs.getDate(4));
				memberDto.setActive(rs.getBoolean(5));
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return memberDto;
	}
}
