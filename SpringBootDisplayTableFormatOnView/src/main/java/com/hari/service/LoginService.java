package com.hari.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validate(String name, String password) {
		return ("hari".equalsIgnoreCase(name) && "hari".equalsIgnoreCase(password))
				|| ("test".equalsIgnoreCase(name) && "test".equalsIgnoreCase(password));
	}

	
}
