package com.fujinlong.httpclienttest;

import java.nio.charset.Charset;

public class StringResponse {
	private Integer statusCode;
	private String responseBody;
	private String mimeType;
	private Charset charset;
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public Charset getCharset() {
		return charset;
	}
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	public StringResponse(Integer statusCode, String responseBody, Charset charset) {
		this.statusCode = statusCode;
		this.responseBody = responseBody;
		this.charset = charset;
	}
	
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public StringResponse(){}
	
}
