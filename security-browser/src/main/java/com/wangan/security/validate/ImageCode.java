package com.wangan.security.validate;

import java.awt.image.BufferedImage;

/**
 * @author wangan on 2018/10/19
 * @description
 */
public class ImageCode {

	private BufferedImage image;
	private String code;

	public ImageCode(BufferedImage image, String code) {
		this.image = image;
		this.code = code;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
