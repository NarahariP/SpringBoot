package com.hari.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response,
			Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", exception.getLocalizedMessage());
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
}
