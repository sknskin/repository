package com.demoweb.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.demoweb.dto.MemberDto;

// MemberMapper.xml과 패키지.이름이 동일한 인터페이스
public interface MemberMapper {

	// MemberMapper.xml의 명령 노드와 동일한 메서드 만들기
	void insertMember(MemberDto member);
	MemberDto selectMemberByIdAndPasswd(@Param("memberId") String memberId, @Param("passwd") String passwd);
	
}
