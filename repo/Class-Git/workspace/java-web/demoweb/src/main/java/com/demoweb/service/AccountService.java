package com.demoweb.service;

import com.demoweb.common.Util;
import com.demoweb.dao.MemberDao;
import com.demoweb.dto.MemberDto;

public class AccountService {
	
	// 1. 회원 가입 : 회원 데이터를 받아서 필요한 처리 ( 데이터베이스 저장은 Dao에 전달 )
	public void registerMember(MemberDto member) {
		
		MemberDao memberDao = new MemberDao();
		String passwd = Util.getHashedString(member.getPasswd(), "SHA-256");
		member.setPasswd(passwd); // 암호화된 패스워드를 멤버에 저장
		memberDao.insertMember(member);
		
	}
	
	// 2. 로그인 : 아이디, 패스워드를 받아서 데이터베이스 조회 후 결과 반환
	public MemberDto findMemberByIdAndPasswd(String memberId, String passwd) {
		
		MemberDao memberDao = new MemberDao();
		passwd = Util.getHashedString(passwd, "SHA-256");
		MemberDto memberDto = memberDao.selectMemberByIdAndPasswd(memberId, passwd);
		return memberDto;
		
	}

}
