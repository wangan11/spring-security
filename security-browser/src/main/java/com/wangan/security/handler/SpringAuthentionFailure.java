package com.wangan.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangan.security.suport.AjaxCode;
import com.wangan.security.suport.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangan on 2018/10/19
 * @description
 */
@Component("springAuthentionFailure")
public class SpringAuthentionFailure implements AuthenticationFailureHandler {

	private Logger logger= LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败");
		response.setContentType("application/json;charset=utf-8");
		logger.info(objectMapper.writeValueAsString(exception.getMessage()));
		response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(AjaxCode.ERROR,exception.getMessage())));
	}
}
