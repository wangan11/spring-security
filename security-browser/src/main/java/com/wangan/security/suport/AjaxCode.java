package com.wangan.security.suport;

/**
 * @author wangan on 2018/10/18
 * @description
 */
public enum  AjaxCode {

	OK(200, "操作成功!"),
	ERROR(500,"系统异常");

	private int code;
	private String message;

	AjaxCode(int code, String msg) {
		this.code=code;
		this.message=msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
