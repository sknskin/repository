import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// 데이터베이스에 테이블 만들기
// (winning-numbers.txt 내용을 참고해서 ...)
//		CREATE TABLE tbl_lotto
//		(
//			rnd int not null primary key, -- auto_increment 아님
//		    lottery_date datetime not null,
//		    no1 decimal(2, 0) not null, -- no1 numeric(2, 0) not null,
//		    no2 decimal(2, 0) not null,
//		    no3 decimal(2, 0) not null,
//		    no4 decimal(2, 0) not null,
//		    no5 decimal(2, 0) not null,
//		    no6 decimal(2, 0) not null,
//		    bonus decimal(2, 0) not null
//		);

public class ExLotto {

	public static void main(String[] args) {
		
		// winning-numbers.txt 파일 읽기
		// 파일의 데이터를 데이터베이스에 저장
		
		//1. 파일 읽기
		FileInputStream fis = null;		// 파일 데이터 읽기 기능 제공
		InputStreamReader isr = null;	// byte[] -> char[] (String)으로 변환 기능 제공
		BufferedReader br = null; 		// 한 행 단위로 텍스트 파일 읽기 기능 제공
		ArrayList<LottoDto> dataSet = new ArrayList<>(); // 읽은 데이터 저장 변수
		try {
			fis = new FileInputStream("winning-numbers.csv");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); // 날짜 <-> 특정형식문자열
			while (true) {
				String line = br.readLine();
				if (line == null) break; // EOF ( End of File )
				
				//System.out.println(line);
				String[] row = line.split(","); // "a,b,c" -> ["a", "b", "c"]
				
				LottoDto l = new LottoDto();
				l.setRnd(Integer.parseInt(row[0]));
				l.setLotteryDate(sdf.parse(row[1])); // "2022.08.27" -> Date
				l.setNo1(Integer.parseInt(row[2]));
				l.setNo2(Integer.parseInt(row[3]));
				l.setNo3(Integer.parseInt(row[4]));
				l.setNo4(Integer.parseInt(row[5]));
				l.setNo5(Integer.parseInt(row[6]));
				l.setNo6(Integer.parseInt(row[7]));
				l.setBonus(Integer.parseInt(row[8]));
				// System.out.println(l);
				dataSet.add(l);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { br.close(); } catch (Exception ex) {}
			try { isr.close(); } catch (Exception ex) {}
			try { fis.close(); } catch (Exception ex) {}
		}
		
		// 데이터 읽기 끝
		
		// 2. 데이터베이스에 저장		
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

}










