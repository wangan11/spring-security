package com.wangan.security;

import com.wangan.security.Filter.ValidateCodeFilter;
import com.wangan.security.core.properties.SecurityConstans;
import com.wangan.security.qq.config.MerryyouSpringSocialConfigurer;
import com.wangan.security.suport.SpringSecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


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
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ValidateCodeFilter validateCodeFilter;
	@Autowired
	private SpringSocialConfigurer merryyouSpringSocialConfigurer;
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin()
				.loginPage(SecurityConstans.DEFAULT_LOGIN_REQ_URL)
				.loginProcessingUrl(SecurityConstans.DEFAULT_LOGIN_FORM_ULR).successHandler(springAuthentionSuccessHandler)
				.failureHandler(springAuthentionFailure);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyPasswordAuthenticationConfig(http);
		http
		.apply(merryyouSpringSocialConfigurer).and()
		.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(100)
				.userDetailsService(userDetailsService).and().authorizeRequests().antMatchers(SecurityConstans.DEFAULT_LOGIN_REQ_URL, "/get/ImageCode",
				SpringSecurityConstants.DEFAULT_SIGN_UP,
				SecurityConstans.DEFAULT_LOGIN_PAGE_URL).permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}


	@Bean("springSessionStrategy")
	public  SessionStrategy creteSessionStrategy(){
		return new HttpSessionSessionStrategy();
	}
}
