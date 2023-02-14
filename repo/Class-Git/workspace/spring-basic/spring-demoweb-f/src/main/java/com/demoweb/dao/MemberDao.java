package com.demoweb.dao;

import com.demoweb.dto.MemberDto;

public interface MemberDao {

	void insertMember(MemberDto member);

	MemberDto selectMemberByIdAndPasswd(String memberId, String passwd);

}