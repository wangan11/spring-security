package com.wangan.security;

import com.wangan.security.Filter.ValidateCodeFilter;
import com.wangan.security.core.properties.SecurityConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author wangan on 2018/10/18
 * @description
 */
@Configuration

public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler springAuthentionSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler springAuthentionFailure;
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.addFilterBefore(new ValidateCodeFilter(),UsernamePasswordAuthenticationFilter.class)
		.formLogin()
				.loginPage(SecurityConstans.DEFAULT_LOGIN_REQ_URL)
				.loginProcessingUrl(SecurityConstans.DEFAULT_LOGIN_FORM_ULR).successHandler(springAuthentionSuccessHandler)
				.failureHandler(springAuthentionFailure);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyPasswordAuthenticationConfig(http);
		http.rememberMe().and().authorizeRequests().antMatchers(SecurityConstans.DEFAULT_LOGIN_REQ_URL,"/get/ImageCode",
				SecurityConstans.DEFAULT_LOGIN_PAGE_URL).permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}

}
