package com.wangan.security.Filter;

import com.wangan.security.controller.ValidateController;
import com.wangan.security.core.properties.SecurityConstans;
import com.wangan.security.validate.ImageCode;
import com.wangan.security.validate.ImageCodeValidate;
import com.wangan.security.validate.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangan on 2018/10/19
 * @description
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter {

	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
	private final String IMAGE_SESSION_KEY="IMAGE_SESSION_KEY";

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(StringUtils.endsWith(SecurityConstans.DEFAULT_LOGIN_FORM_ULR,request.getRequestURI())){
			if(!validatImageCode(request)){
				throw new  ValidateCodeException("验证码不正确");
			}
		}
		doFilter(request,response,filterChain);
	}


	public boolean validatImageCode(HttpServletRequest request) throws ServletRequestBindingException {
		String  sessionImageCode = (String)sessionStrategy.getAttribute(new ServletWebRequest(request), IMAGE_SESSION_KEY);
		String stringParameter = ServletRequestUtils.getStringParameter(request, IMAGE_SESSION_KEY);
		if(sessionImageCode.equals(stringParameter)){
			return true;
		}
		return false;
	}

}
