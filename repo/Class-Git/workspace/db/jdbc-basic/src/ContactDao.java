import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

	public void insertContact(Contact contact) {
		
		// 데이터베이스에서 데이터를 조회하고 employees에 채우는 작업 수행
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		// ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/market_db", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"INSERT INTO tbl_contact (name, phone, email) " + // no 컬럼은 자동증가처리
					"VALUES (?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getPhone());
			pstmt.setString(3, contact.getEmail());
			
			// 4. 명령 실행
			// rs = pstmt.executeQuery(); 	// select 일 때 사용하는 메서드
			pstmt.executeUpdate();		// insert, update, delete 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)		
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			// try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}

	public List<Contact> selectAllContacts() {
		
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<Contact> contacts = new ArrayList<>();
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/market_db", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT no, name, phone, email " +
					"FROM tbl_contact ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); 		// select 일 때 사용하는 메서드
			// pstmt.executeUpdate();		// insert, update, delete 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) { // 결과의 다음 행으로 이동 (끝이면 false)
				Contact c = new Contact();
				c.setNo(rs.getInt(1));			//현재 행의 1번째 컬럼 데이터 읽기
				c.setName(rs.getString(2));		//현재 행의 2번째 컬럼 데이터 읽기
				c.setPhone(rs.getString(3));
				c.setEmail(rs.getString(4));
				
				contacts.add(c); // 한 행의 데이터를 미리 준비한 목록에 추가
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return contacts;
	}

	public List<Contact> selectContactsByName(String name) {
		
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<Contact> contacts = new ArrayList<>();
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/market_db", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT no, name, phone, email " +
					"FROM tbl_contact " + 
					"WHERE name LIKE ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); 		// select 일 때 사용하는 메서드
			// pstmt.executeUpdate();		// insert, update, delete 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) { // 결과의 다음 행으로 이동 (끝이면 false)
				Contact c = new Contact();
				c.setNo(rs.getInt(1));			//현재 행의 1번째 컬럼 데이터 읽기
				c.setName(rs.getString(2));		//현재 행의 2번째 컬럼 데이터 읽기
				c.setPhone(rs.getString(3));
				c.setEmail(rs.getString(4));
				
				contacts.add(c); // 한 행의 데이터를 미리 준비한 목록에 추가
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return contacts;
	}

}
