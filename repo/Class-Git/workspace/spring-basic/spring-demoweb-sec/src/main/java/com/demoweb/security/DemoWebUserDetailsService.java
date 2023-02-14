package com.demoweb.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.demoweb.dto.MemberDto;
import com.demoweb.dto.RoleDto;
import com.demoweb.mapper.MemberMapper;

import lombok.Setter;

public class DemoWebUserDetailsService implements UserDetailsService {

	@Setter // 의존 주입 경로 ( setter method )
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails userDetails = null;
		
		MemberDto member = memberMapper.selectMemberById(username);
		if (member == null) {
			throw new UsernameNotFoundException(username);
		} else {
			List<RoleDto> roles = memberMapper.selectRolesByMemberId(username);
			userDetails = new DemoWebUserDetails(member, roles);
		}
		
		return userDetails;
	}

}







