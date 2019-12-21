package com.hari.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("user1").password(encoder.encode("user1")).roles("USER").and()
				.withUser("admin").password(encoder.encode("admin")).roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.httpBasic().and().authorizeRequests().antMatchers("/serveys/**").hasRole("USER")
				.antMatchers("/users/**").hasRole("USER").antMatchers("/**").hasRole("ADMIN").anyRequest()
				.authenticated().and().formLogin();
	}
}
