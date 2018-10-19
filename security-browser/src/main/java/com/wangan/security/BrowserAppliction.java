package com.wangan.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;

/**
 * @author wangan on 2018/10/18
 * @description
 */
 @SpringBootApplication
public class BrowserAppliction {

	public static void main(String[] args) {
		SpringApplication.run(BrowserAppliction.class,args);
	}


	@Bean
	public SessionStrategy getStratege(){
	 return new HttpSessionSessionStrategy();
	}
}
