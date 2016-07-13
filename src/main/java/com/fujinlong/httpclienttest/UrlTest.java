package com.fujinlong.httpclienttest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.utils.URLEncodedUtils;

public class UrlTest {
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		URL url1=new URL("https://xui.ptlogin2.qq.com/js/10167/c_login_2.js#dasdf");
		System.out.println(url1.getRef());
		//"".endsWith(suffix)
		URL url2=new URL(url1,"aa");
		System.out.println(url2.toString());
	}
}
