package com.demoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoweb.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

	MemberEntity findByMemberIdAndPasswd(String memberid, String passwd);
	
}
