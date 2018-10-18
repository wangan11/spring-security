package com.wangan.security.suport;

/**
 * @author wangan on 2018/10/18
 * @description
 */
public class SimpleResponse<T> extends ComonAjaxResult {

	private T data;

	public SimpleResponse(T data) {
		super(AjaxCode.OK);
		this.data = data;
	}

	public SimpleResponse(AjaxCode ajaxCode, T data) {
		super(ajaxCode);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
