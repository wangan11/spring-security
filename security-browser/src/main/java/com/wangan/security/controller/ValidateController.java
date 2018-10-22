package com.wangan.security.controller;

import com.wangan.security.Filter.ValidateCodeFilter;
import com.wangan.security.validate.ImageCode;
import com.wangan.security.validate.ImageCodeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author wangan on 2018/10/19
 * @description
 */
 @RestController
public class ValidateController {
	@Autowired
	private ImageCodeValidate imageCodeValidate;


	@RequestMapping("/get/ImageCode")
	public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServletOutputStream outputStream = response.getOutputStream();
		ImageCode imageCode = imageCodeValidate.createImageCode(request, response);
		ImageIO.write(imageCode.getImage(),"JPEG",outputStream);
		outputStream.close();
	}




}
