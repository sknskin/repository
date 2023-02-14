package com.demoweb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demoweb.dto.MemberDto;
import com.demoweb.dto.RoleDto;

public class DemoWebUserDetails implements UserDetails {
	
	private MemberDto member;
	private List<RoleDto> roles;

	public DemoWebUserDetails(MemberDto member, List<RoleDto> roles) {
		this.member = member;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (RoleDto role : roles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
			authorities.add(authority);
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPasswd();
	}

	@Override
	public String getUsername() {
		return member.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return member.isActive();
	}

}
