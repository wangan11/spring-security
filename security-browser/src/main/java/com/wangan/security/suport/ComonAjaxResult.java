package com.wangan.security.suport;

/**
 * @author wangan on 2018/10/18
 * @description
 */
public class ComonAjaxResult {

	private int code;
	private String msg;


	public ComonAjaxResult(AjaxCode ajaxCode,String msg) {
		this.code=ajaxCode.getCode();
		this.msg = msg;
	}


	public ComonAjaxResult(AjaxCode ajaxCode) {
		this.code=ajaxCode.getCode();
		this.msg = ajaxCode.getMessage();
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
