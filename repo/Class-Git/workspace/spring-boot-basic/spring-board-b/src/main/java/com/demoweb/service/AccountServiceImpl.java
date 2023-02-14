package com.demoweb.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demoweb.common.Util;
import com.demoweb.dto.MemberDto;
import com.demoweb.entity.MemberEntity;
import com.demoweb.mapper.MemberMapper;
import com.demoweb.repository.MemberRepository;

import lombok.Setter;

// @Component
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	// 1. 회원 가입 : 회원 데이터를 받아서 필요한 처리 ( 데이터베이스 저장은 Dao에 전달 )
	@Override
	public void registerMember(MemberDto member) {
		
		String passwd = Util.getHashedString(member.getPasswd(), "SHA-256");
		member.setPasswd(passwd); // 암호화된 패스워드를 멤버에 저장
		
		MemberEntity memberEntity = MemberEntity.builder()
												.memberId(member.getMemberId())
												.passwd(member.getPasswd())
												.email(member.getEmail())
//												.userType("user")
//												.regDate(new Date())
//												.active(true)
												.build();
		
		memberRepository.save(memberEntity); // 데이터베이스에 데이터 저장		
	}
	
	// 2. 로그인 : 아이디, 패스워드를 받아서 데이터베이스 조회 후 결과 반환
	@Override
	public MemberDto findMemberByIdAndPasswd(String memberId, String passwd) {
		
		passwd = Util.getHashedString(passwd, "SHA-256");
		MemberEntity memberEntity = memberRepository.findByMemberIdAndPasswd(memberId, passwd);
		
		return memberEntity != null ? memberEntity.exportMemberDto() : null;
		
	}

}
