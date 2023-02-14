package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

//@Component
@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	// 사용자가 작성한 게시글 정보를 받아서 데이터베이스에 저장
	@Override
	public void insertBoard(BoardDto board) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt2 = null;// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demoweb", 		// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3-1. SQL 작성 + 명령 객체 가져오기
			String sql = 
					"INSERT INTO board (title, writer, content) " +
					"VALUES (?, ?, ?)"; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());		// SQL의 1번째 ?를 대체할 데이터
			pstmt.setString(2, board.getWriter());		// SQL의 2번째 ?를 대체할 데이터
			pstmt.setString(3, board.getContent());		// SQL의 3번째 ?를 대체할 데이터
			
			// 4-1. 명령 실행
			pstmt.executeUpdate(); // executeUpdate : select 이외의 SQL에 사용하는 메서드
			
			// 3-2. 
			String sql2 = "SELECT LAST_INSERT_ID()"; // LAST_INSERT_ID() : 현재 연결에서 마지막으로 생성된 자동증가번호
			pstmt2 = conn.prepareStatement(sql2);
			
			// 4-1. 
			rs = pstmt2.executeQuery();
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			rs.next();
			int newBoardNo = rs.getInt(1); 	// 자동 증가 값 (새 글번호) 조회 결과 읽기
			board.setBoardNo(newBoardNo);	// 객체에 자동 증가 값 (새 글번호) 저장
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { pstmt2.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	// 모든 게시글 데이터 조회 및 반환 ( 목록 반환 )
	@Override
	public List<BoardDto> selectAllBoard() {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<BoardDto> boards = new ArrayList<>();		// 조회한 데이터를 저장할 DTO 객체
		
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
					"SELECT boardno, title, writer, readcount, regdate, deleted " +
					"FROM board " +
					"ORDER BY boardno DESC "; // 최신 글이 앞에 보이도록 조회
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				BoardDto board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setReadCount(rs.getInt(4));
				board.setRegDate(rs.getDate(5));
				board.setDeleted(rs.getBoolean(6));
				
				boards.add(board);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return boards;
	}
	
	@Override
	public List<BoardDto> selectBoardByPage(int from, int count) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<BoardDto> boards = new ArrayList<>();		// 조회한 데이터를 저장할 DTO 객체
		
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
					"SELECT boardno, title, writer, readcount, regdate, deleted " +
					"FROM board " +
					"ORDER BY boardno DESC " + // 최신 글이 앞에 보이도록 조회
					"LIMIT ?,? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, count);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				BoardDto board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setReadCount(rs.getInt(4));
				board.setRegDate(rs.getDate(5));
				board.setDeleted(rs.getBoolean(6));
				
				boards.add(board);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return boards;
	}
	
	@Override
	public int selectBoardCount() {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		int boardCount = 0;		// 조회한 데이터를 저장할 변수
		
		try {
			// 1. Driver 등록
			// DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결 및 연결 객체 가져오기
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demoweb",	 	// 데이터베이스 연결 정보
					"testuser", "mysql"); 						// 데이터베이스 계정 정보
			
			// 3. SQL 작성 + 명령 객체 가져오기
			String sql = "SELECT COUNT(*) FROM board ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
			
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			rs.next();	// 결과 집합의 다음 행으로 이동
			boardCount = rs.getInt(1);
	
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return boardCount;
	}
	
	// 글번호를 받아서 게시글 조회 및 반환 ( primary key 검색이므로 단일 객체 반환 )
	@Override
	public BoardDto selectBoardByBoardNo(int boardNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		BoardDto board = null;		// 조회한 데이터를 저장할 DTO 객체
		
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
					"SELECT boardno, title, writer, content, readcount, regdate " +
					"FROM board " +
					"WHERE boardno = ? AND deleted = FALSE ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setReadCount(rs.getInt(5));
				board.setRegDate(rs.getDate(6));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return board;
	}
	
	// 글번호를 받아서 조회수 증가 ( update )
	@Override
	public void updateBoardReadCount(int boardNo) {
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
					"UPDATE board  " +
					"SET readcount = readcount + 1 " +
					"WHERE boardno = ? AND deleted = FALSE "; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
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

	// 글번호를 받아서 글 삭제 (반환 없음)
	@Override
	public void deleteBoard(int boardNo) {
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
					"UPDATE board " +
					"SET deleted = TRUE " +
					"WHERE boardno = ?"; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void insertBoardAttach(BoardAttachDto attachment) {
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
					"INSERT INTO boardattach (boardno, userfilename, savedfilename) " +
					"VALUES (?, ?, ?)"; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachment.getBoardNo());
			pstmt.setString(2, attachment.getUserFileName());
			pstmt.setString(3, attachment.getSavedFileName());
			
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
	
	// 글번호를 받아서 첨부파일 정보 조회하고 목록으로 반환
	@Override
	public List<BoardAttachDto> selectBoardAttachByBoardNo(int boardNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		ArrayList<BoardAttachDto> attachments = new ArrayList<>();	// 조회한 데이터를 저장할 DTO 객체
		
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
					"SELECT attachno, boardno, userfilename, savedfilename, downloadcount " +
					"FROM boardattach " +
					"WHERE boardno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				BoardAttachDto attachment = new BoardAttachDto();
				attachment.setAttachNo(rs.getInt(1));
				attachment.setBoardNo(rs.getInt(2));
				attachment.setUserFileName(rs.getString(3));
				attachment.setSavedFileName(rs.getString(4));
				attachment.setDownloadCount(rs.getInt(5));
				
				attachments.add(attachment);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attachments;
	}
	
	// 첨부파일 번호를 받아서 첨부 파일 조회 및 단일 객체 반환 ( PK 검색 )
	@Override
	public BoardAttachDto selectBoardAttachByAttachNo(int attachNo) {
		Connection conn = null;			// 연결과 관련된 JDBC 호출 규격 ( 인터페이스 )
		PreparedStatement pstmt = null;	// 명령 실행과 관련된 JDBC 호출 규격 ( 인터페이스 )
		ResultSet rs = null;			// 결과 처리와 관련된 JDBC 호출 규격 ( 인터페이스 )
		
		BoardAttachDto attachment = null;	// 조회한 데이터를 저장할 DTO 객체
		
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
					"SELECT attachno, boardno, userfilename, savedfilename, downloadcount " +
					"FROM boardattach " +
					"WHERE attachno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			// 4. 명령 실행
			rs = pstmt.executeQuery(); // executeQuery : select 일 때 사용하는 메서드
						
			// 5. 결과 처리 (결과가 있다면 - SELECT 명령을 실행한 경우)
			while (rs.next()) {	// 결과 집합의 다음 행으로 이동
				attachment = new BoardAttachDto();
				attachment.setAttachNo(rs.getInt(1));
				attachment.setBoardNo(rs.getInt(2));
				attachment.setUserFileName(rs.getString(3));
				attachment.setSavedFileName(rs.getString(4));
				attachment.setDownloadCount(rs.getInt(5));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); // 개발 용도로 사용
		} finally {
			// 6. 연결 닫기
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attachment;
	}
	
	// 글번호, 제목, 내용을 받아서 글 수정 처리 (반환 X)
	@Override
	public void updateBoard(BoardDto board) {
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
					"UPDATE board " +
					"SET title = ?, content = ? " +
					"WHERE boardno = ? "; // ? : 나중에 채워질 영역 표시
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());			
			
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

}














