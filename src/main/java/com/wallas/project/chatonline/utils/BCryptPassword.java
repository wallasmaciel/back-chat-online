package com.wallas.project.chatonline.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class BCryptPassword {
	protected static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	public static String encode(String password){
        return passwordEncoder().encode(password);
    }
	
	public static boolean verifyPassword(CharSequence password, String hash) {
		return passwordEncoder().matches(password, hash);
	}
}
