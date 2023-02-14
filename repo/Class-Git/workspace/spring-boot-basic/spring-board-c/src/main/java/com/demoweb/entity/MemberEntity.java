package com.demoweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demoweb.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tbl_member")
public class MemberEntity {
	
	public MemberEntity(MemberDto member) {
		this.memberId = member.getMemberId();
		this.passwd = member.getPasswd();
		this.email = member.getEmail();
		this.userType = member.getUserType();
		this.active = member.isActive();
	}
	
	public MemberDto exportMemberDto() {
		MemberDto member = new MemberDto();
		member.setMemberId(memberId);
		member.setPasswd(passwd);
		member.setEmail(email);
		member.setUserType(userType);
		member.setRegDate(regDate);
		member.setActive(active);
		
		return member;
	}

	@Id
	private String memberId;
	@Column
	private String passwd;
	@Column
	private String email;
	
	@Builder.Default
	@Column
	private String userType = "user";
	@Builder.Default
	@Column
	private Date regDate = new Date();
	@Builder.Default
	@Column
	private boolean active = true;
	
}
