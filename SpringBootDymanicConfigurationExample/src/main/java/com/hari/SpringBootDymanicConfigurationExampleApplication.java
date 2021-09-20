package com.hari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootDymanicConfigurationExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDymanicConfigurationExampleApplication.class, args);
	}

	/**
	 * @return - this bean is going to be when we deploy our application in
	 *         production. Reason we added @Profile("prod")
	 */
	@Profile("prod")
	@Bean
	public String message() {
		return "message";
	}
}
