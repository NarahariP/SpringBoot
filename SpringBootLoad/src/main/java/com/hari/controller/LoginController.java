package com.hari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hari.service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginMessage(ModelMap model) {
		//model.put("name",name); Setting value to model
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String welcomePage(ModelMap model, @RequestParam String name,
			@RequestParam String password) {
		boolean isValid = loginService.validate(name, password);
		System.out.println(isValid);
		if(!isValid) {
			model.put("message", "Invalid Credentials!");
			return "login";
		}
		model.put("name",name); //Setting value to model - This is added into @SessionAttributes that means it's available entire session 
		return "welcome";
	}
	
}
