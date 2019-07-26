package com.example.demo.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority{
	CLIENT;
	
	public static Roles convertString(String possibleRole) {
		Roles role;
		
		switch(possibleRole) {
		
		case "WITHDRAW": role = Roles.CLIENT;
		break;
		
		default: role = null;
		break;
	}
		return role;
	}

	@Override
	public String getAuthority() {
		return name();
	}
}
