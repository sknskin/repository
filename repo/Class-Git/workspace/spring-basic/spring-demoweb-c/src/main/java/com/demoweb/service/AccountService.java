package com.demoweb.service;

import com.demoweb.dto.MemberDto;

public interface AccountService {

	void registerMember(MemberDto member);
	MemberDto findMemberByIdAndPasswd(String memberId, String passwd);

}