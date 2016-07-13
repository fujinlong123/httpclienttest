package com.fujinlong.httpclienttest;

import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class ObjectResponse {
	private Integer statusCode;
	private Object responseBody;
	private String mimeType;
	private Charset charset;

	private boolean isString = false;
	private boolean isBinary = false;

	
	private HttpResponse httpResponse;
	

	
	public String getLastHeaderValue(String headerName){
		Header header=httpResponse.getLastHeader(headerName);
		if(header==null){
			return null;
		}
		return header.getValue();
	}
	
	
	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}
	
	

	public boolean isString() {
		return isString;
	}

	public void setString(boolean isString) {
		this.isString = isString;
	}

	public boolean isBinary() {
		return isBinary;
	}

	public void setBinary(boolean isBinary) {
		this.isBinary = isBinary;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getStringResponseBody() {
		return (String) responseBody;
	}

	public byte[] getBinaryResponseBody() {
		return (byte[]) responseBody;
	}

	
	public ObjectResponse() {
	}
}
