package com.hari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginMessage(@RequestParam String name, ModelMap model) {
		System.out.println("Name :"+name);
		model.put("name",name); //Setting value to model
		return "login";
	}
}
