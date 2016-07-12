package com.fujinlong.httpclienttest;

import java.util.List;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;

public class CookieUtils {
	public static String toStr(HttpClientContext context){
		List<Cookie> cookies=context.getCookieStore().getCookies();
		String str="";
		for (Cookie cookie : cookies) {
			str+=cookie.getName()+"="+cookie.getValue()+"; ";
		}
		if(str.length()>0){
			str=str.substring(0, str.length()-"; ".length());
		}
		return str;
		
	}
	
	
	
}
