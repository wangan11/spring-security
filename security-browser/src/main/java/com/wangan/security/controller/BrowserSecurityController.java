package com.wangan.security.controller;

import com.wangan.security.core.properties.SecurityConstans;
import com.wangan.security.suport.SimpleResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangan on 2018/10/18
 * @description
 */
@RestController
public class BrowserSecurityController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@RequestMapping(SecurityConstans.DEFAULT_LOGIN_REQ_URL)
	@ResponseBody
	public SimpleResponse reToLoginHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (StringUtils.endsWith(request.getRequestURI(), SecurityConstans.DEFAULT_LOGIN_REQ_URL) ){
			logger.info("跳转的url{}",request.getRequestURI());
			redirectStrategy.sendRedirect(request, response, SecurityConstans.DEFAULT_LOGIN_PAGE_URL);
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
	}
}
