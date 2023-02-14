package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class MyBatisBoardDaoImpl implements BoardDao {
	
	private final String namespace = "com.demoweb.mapper.BoardMapper.";
	
	@Setter
	private SqlSessionTemplate sqlSession;
	
	// 사용자가 작성한 게시글 정보를 받아서 데이터베이스에 저장
	@Override
	public void insertBoard(BoardDto board) {
		// board.getBoardNo() : 0
		sqlSession.insert(namespace + "insertBoard2", board);
		// board.getBoardNo() : 새로 등록된 게시글 번호
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
		
		HashMap<String, Object> params = new HashMap<>();
		params.put("from", from);
		params.put("count", count);
		
		List<BoardDto> boards = 
				sqlSession.selectList(namespace + "selectBoardByPage", params);
		
		return boards;
	}
	
	@Override
	public int selectBoardCount() {
		
		int boardCount = sqlSession.selectOne(namespace + "selectBoardCount");
		
		return boardCount;
	}
	
	// 글번호를 받아서 게시글 조회 및 반환 ( primary key 검색이므로 단일 객체 반환 )
	@Override
	public BoardDto selectBoardByBoardNo(int boardNo) {
		
		BoardDto board = sqlSession.selectOne(namespace + "selectBoardByBoardNo", boardNo);
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
		
		List<BoardAttachDto> attachments = 
				sqlSession.selectList(namespace + "selectBoardAttachByBoardNo", boardNo);		
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














