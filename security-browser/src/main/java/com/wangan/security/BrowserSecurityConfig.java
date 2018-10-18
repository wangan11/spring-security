package com.wangan.security;

import com.wangan.security.core.properties.SecurityConstans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wangan on 2018/10/18
 * @description
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage(SecurityConstans.DEFAULT_LOGIN_REQ_URL)
				.loginProcessingUrl(SecurityConstans.DEFAULT_LOGIN_FORM_ULR);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyPasswordAuthenticationConfig(http);
		http.rememberMe().and().authorizeRequests().antMatchers(SecurityConstans.DEFAULT_LOGIN_REQ_URL,
				SecurityConstans.DEFAULT_LOGIN_PAGE_URL).permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}

}
