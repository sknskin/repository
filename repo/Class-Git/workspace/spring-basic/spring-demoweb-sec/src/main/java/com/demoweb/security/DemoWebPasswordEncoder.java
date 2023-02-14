package com.demoweb.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.demoweb.common.Util;

public class DemoWebPasswordEncoder implements PasswordEncoder {

	private final String HASH_ALGORITHM = "SHA-256";

	@Override
	public String encode(CharSequence rawPassword) {
		return Util.getHashedString(rawPassword.toString(), HASH_ALGORITHM);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String hashedPasswd = Util.getHashedString(rawPassword.toString(), HASH_ALGORITHM);
		return hashedPasswd.equals(encodedPassword);
	}

	
}
