package com.hari.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.configuration.BasicConfiguration;

@RestController
public class WelcomeController {

	/**
	 * This can be configured as in command line arguments as --welcome.message =
	 * message command line arguments are higher priority than properties file
	 * configuration
	 */
	@Value("${welcome.message}")
	private String welcomeMessage;

	@Autowired
	private BasicConfiguration basicConfiguration;

	@GetMapping("/welcome")
	public String getMessage() {
		return welcomeMessage;
	}

	@GetMapping("/dynamic-configuration")
	public Map<String, Object> dynamicConfiguration() {
		final Map<String, Object> map = new HashMap<>();
		map.put("message", basicConfiguration.getMessage());
		map.put("number", basicConfiguration.getNumber());
		map.put("value", basicConfiguration.isValue());
		return map;
	}
}
