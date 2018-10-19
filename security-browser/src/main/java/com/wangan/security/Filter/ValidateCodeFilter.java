package com.wangan.security.Filter;

import com.wangan.security.core.properties.SecurityConstans;
import com.wangan.security.validate.ImageCode;
import com.wangan.security.validate.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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
public class ValidateCodeFilter extends OncePerRequestFilter {

	@Autowired
	private SessionStrategy sessionStrategy ;
	private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(StringUtils.endsWith(SecurityConstans.DEFAULT_LOGIN_FORM_ULR,request.getRequestURI())){
			String reqImageCode = ServletRequestUtils.getStringParameter(request, "imageCode");
			Object attribute = sessionStrategy.getAttribute(new ServletWebRequest(request), SESSION_KEY);
			ImageCode imageCode = (ImageCode) attribute;
			if(!imageCode.getCode().equals(reqImageCode)){
				throw new  ValidateCodeException("验证码不正确");
			}
		}
		doFilter(request,response,filterChain);
	}
}
