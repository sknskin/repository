package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.demoweb.dto.MemberDto;

import lombok.Setter;

public class MyBatisMemberDaoImpl implements MemberDao {
	
	@Setter
	private SqlSessionTemplate sqlSession;
	
	// 1. 회원가입 : 사용자의 입력을 받아서(전달인자) DB에 저장
	@Override
	public void insertMember(MemberDto member) {
		
		// insert method : mapper.xml 파일의 <insert 항목 호출
		sqlSession.insert("com.demoweb.mapper.MemberMapper.insertMember", member);
			
	}
	
	// 2. 로그인 : 아이디와 패스워드를 받아서(전달인자) DB의 데이터 조회하고 반환 (return, 결과형)
	@Override
	public MemberDto selectMemberByIdAndPasswd(String memberId, String passwd) {

		HashMap<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("passwd", passwd);
		
		MemberDto member = 
				sqlSession.selectOne("com.demoweb.mapper.MemberMapper.selectMemberByIdAndPasswd", params);
		
		return member;
	}
}






