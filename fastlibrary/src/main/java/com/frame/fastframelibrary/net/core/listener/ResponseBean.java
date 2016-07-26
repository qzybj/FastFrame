package com.frame.fastframelibrary.net.core.listener;

public  class ResponseBean<T> {
	private int code = -1;
	private T data = null;
	private String msg = "";
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
