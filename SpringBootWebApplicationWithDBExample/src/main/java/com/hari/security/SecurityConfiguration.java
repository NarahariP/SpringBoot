package com.hari.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Spring 4 this works
	 *
	 * @Autowired protected void
	 *            configureGlobalSecurity(AuthenticationManagerBuilder auth) throws
	 *            Exception { auth.inMemoryAuthentication()
	 *            .withUser("hari").password("hari") .roles("USER", "ADMIN"); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll().antMatchers("/", "/*todo*/**")
				.access("hasRole('ADMIN')").and().formLogin();

		/**
		 * The following are added to work with H2 console and disabling security for
		 * h2-console its running via frames so we are disabling frameOptions
		 */
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("hari").password(encoder.encode("hari")).roles("ADMIN");
	}

}
