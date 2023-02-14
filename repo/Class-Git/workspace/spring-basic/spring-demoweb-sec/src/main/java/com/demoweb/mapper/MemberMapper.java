package com.demoweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demoweb.dto.MemberDto;
import com.demoweb.dto.RoleDto;

@Mapper // mapper interface 구현 객체 생성 자동으로 처리
public interface MemberMapper {
	
	@Insert("INSERT INTO member (memberid, passwd, email) " +
			"VALUES (#{ memberId }, #{ passwd }, #{ email }")
	void insertMember(MemberDto member);
	
	@Select("SELECT memberid, email, usertype, regdate, active " +
			"FROM member " +
			"WHERE memberid = #{ memberId } AND passwd = #{ passwd } AND active = TRUE ")
	MemberDto selectMemberByIdAndPasswd(@Param("memberId")String memberId, @Param("passwd") String passwd);

	@Select("SELECT memberid, passwd, email, usertype, regdate, active " +
			"FROM member " +
			"WHERE memberid = #{ memberId }")
	MemberDto selectMemberById(String memberId);

	@Select("SELECT r.roleno roleNo, r.rolename roleName " +
			"FROM role r " +
			"INNER JOIN member_role mr " +
			"ON r.roleno = mr.roleno " +
			"WHERE mr.memberid = #{ memberId }")
	List<RoleDto> selectRolesByMemberId(String memberId);
	
	

}
