import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class Ex02 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 직원의 이름 : ");
		String name = scanner.nextLine();		
		
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employees", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT emp_no, birth_date, first_name, last_name, gender, hire_date " +
					"FROM employees " +
					"WHERE lower(first_name) LIKE '%" + name + "%' OR " + // first_name LIKE '%abc%'
					"      lower(last_name) LIKE '%" + name + "%' ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				System.out.printf("[%d][%s][%s][%s][%s][%s]\n",
						rs.getInt(1),				// 현재 행의 1번째 열 데이터를 정수로 읽기
						rs.getDate(2).toString(),	// 현재 행의 2번째 열 데이터를 날짜로 읽기
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getDate(6).toString());
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

}
