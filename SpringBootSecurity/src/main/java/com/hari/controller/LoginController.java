package com.hari.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String loginMessage(ModelMap model) {
		model.put("name",getLoggedInUser());
		return "welcome";
	}
	
	private String getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails){
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
}
