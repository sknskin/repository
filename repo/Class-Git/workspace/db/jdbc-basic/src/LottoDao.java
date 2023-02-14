import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
	
	public void deleteAll() {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/market_db", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "DELETE FROM tbl_lotto ";			
			pstmt = conn.prepareStatement(sql);
			
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
	public void deleteByRnd(int rnd) {} // 도전 과제
	
	public void insertMany(List<LottoDto> dataSet) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/market_db", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"INSERT INTO tbl_lotto " + // 모든 컬럼 사용 -> 컬럼 생략
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";			
			pstmt = conn.prepareStatement(sql);
			
			for (LottoDto l : dataSet) {
				pstmt.clearParameters();// 기존에 등록된 값 모두 제거
				pstmt.setInt(1, l.getRnd());
				// java.util.Date -> java.sql.Date
				long tick = l.getLotteryDate().getTime(); // tick : 경과 시간 밀리세컨 
				java.sql.Date d = new java.sql.Date(tick);
				pstmt.setDate(2, d);  
				pstmt.setInt(3, l.getNo1());
				pstmt.setInt(4, l.getNo2());
				pstmt.setInt(5, l.getNo3());
				pstmt.setInt(6, l.getNo4());
				pstmt.setInt(7, l.getNo5());
				pstmt.setInt(8, l.getNo6());
				pstmt.setInt(9, l.getBonus());
				
				// 4. 명령 실행
				// rs = pstmt.executeQuery(); 	// select 일 때 사용하는 메서드
				pstmt.executeUpdate();		// insert, update, delete 일 때 사용하는 메서드
			}
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
	
	public List<LottoDto> selectLottoByRnd(int rnd) {
		
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<LottoDto> dataSet = new ArrayList<>();
		
		try {
			// 1. Driver 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/market_db", 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"SELECT rnd, lottery_date, no1, no2, no3, no4, no5, no6, bonus " +
					"FROM tbl_lotto " +
					"WHERE rnd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rnd);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); 		// select 일 때 사용하는 메서드
			// pstmt.executeUpdate();		// insert, update, delete 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) { // 결과의 다음 행으로 이동 (끝이면 false)
				LottoDto l = new LottoDto();
				l.setRnd(rs.getInt(1));
				l.setLotteryDate(rs.getDate(2));
				l.setNo1(rs.getInt(3));
				l.setNo2(rs.getInt(4));
				l.setNo3(rs.getInt(5));
				l.setNo4(rs.getInt(6));
				l.setNo5(rs.getInt(7));
				l.setNo6(rs.getInt(8));
				l.setBonus(rs.getInt(9));
				
				dataSet.add(l); // 한 행의 데이터를 미리 준비한 목록에 추가
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return dataSet;
	}

}
