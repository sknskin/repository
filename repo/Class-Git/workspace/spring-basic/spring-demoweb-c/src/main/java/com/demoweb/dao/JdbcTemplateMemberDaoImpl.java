package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class JdbcTemplateMemberDaoImpl implements MemberDao {
	
	@Setter
	private JdbcTemplate jdbcTemplate;
	
	// 1. 회원가입 : 사용자의 입력을 받아서(전달인자) DB에 저장
	@Override
	public void insertMember(MemberDto member) {
		
		String sql = 
				"INSERT INTO member (memberid, passwd, email) " +
				"VALUES (?, ?, ?)"; // ? : 나중에 채워질 영역 표시
		jdbcTemplate.update(sql, member.getMemberId(), member.getPasswd(), member.getEmail());
			
	}

	
	// 2. 로그인 : 아이디와 패스워드를 받아서(전달인자) DB의 데이터 조회하고 반환 (return, 결과형)
	@Override
	public MemberDto selectMemberByIdAndPasswd(String memberId, String passwd) {
	
		// 3. SQL 작성 + 명령 객체 가져오기
		String sql = 
				"SELECT memberid, email, usertype, regdate, active " +
				"FROM member " +
				"WHERE memberid = ? " + // ? : 나중에 채워질 영역 표시
				"      AND " + 
				"      passwd = ? ";
		
		MemberDto member = jdbcTemplate.queryForObject(
				sql, 
				// RowMapper 인터페이스를 구현하는 이름 없는 클래스 만들기 + 앞의 클래스의 인스턴스 만들기 (new)
				new RowMapper<MemberDto>() {
					@Override
					public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberDto memberDto = new MemberDto();
						memberDto.setMemberId(rs.getString(1));
						memberDto.setEmail(rs.getString(2));
						memberDto.setUserType(rs.getString(3));
						memberDto.setRegDate(rs.getDate(4));
						memberDto.setActive(rs.getBoolean(5));
						return memberDto;
					}
				},
				memberId, passwd);		
		
		return member;
	}
}
