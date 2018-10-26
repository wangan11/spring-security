//package com.wangan.security;
//
//import com.wangan.security.qq.config.MerryyouSpringSocialConfigurer;
//import com.wangan.security.suport.SpringSecurityConstants;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurerAdapter;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.web.ProviderSignInUtils;
//import org.springframework.social.security.SpringSocialConfigurer;
//
////@Configuration
////@EnableSocial
//public class SocialConfig extends SocialConfigurerAdapter {
//
//	/**
//	 * 社交登录配类s
//	 *
//	 * @return
//	 */
//	@Bean
//	public SpringSocialConfigurer merryyouSocialSecurityConfig() {
//		String filterProcessesUrl = SpringSecurityConstants.DEFAULT_SOCIAL_QQ_PROCESS_URL;
//		MerryyouSpringSocialConfigurer configurer = new MerryyouSpringSocialConfigurer(filterProcessesUrl);
//		configurer.signupUrl(SpringSecurityConstants.DEFAULT_SIGN_UP);
//		return configurer;
//	}
//
//	/**
//	 * 处理注册流程的工具类
//	 * @param factoryLocator
//	 * @return
//	 */
//	@Bean
//	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator) {
//		return new ProviderSignInUtils(factoryLocator, getUsersConnectionRepository(factoryLocator));
//	}
//
//}
//
