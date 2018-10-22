package com.wangan.security.controller;

import com.wangan.security.Filter.ValidateCodeFilter;
import com.wangan.security.validate.ImageCode;
import com.wangan.security.validate.ImageCodeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
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

 	private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
 	private final String IMAGE_SESSION_KEY="IMAGE_SESSION_KEY";


	@RequestMapping("/get/ImageCode")
	public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServletOutputStream outputStream = response.getOutputStream();
		ImageCode imageCode = createImageCode(request, response);
		ImageIO.write(imageCode.getImage(),"JPEG",outputStream);
		outputStream.close();
	}



	public ImageCode createImageCode(HttpServletRequest request, HttpServletResponse response){
		ImageCode generate = generate(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request),IMAGE_SESSION_KEY,generate.getCode());
		return generate;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */

	public ImageCode generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
				200);
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				100);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		g.dispose();

		return new ImageCode(image, sRand);
	}

	/**
	 * 生成随机背景条纹
	 *
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
