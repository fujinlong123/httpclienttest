package com.fujinlong.httpclienttest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlTest {
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		URL url1=new URL("https://xui.ptlogin2.qq.com/js/10167/c_login_2.js?max_age=604800&ptui_identifier=000DA76410FB2C55D84BB10A3F96DE0E62081FCB316D17EB9EB1F35B");
		System.out.println(url1.getPath());
		
		//"".endsWith(suffix)
		URL url2=new URL(url1,"aa");
		System.out.println(url2.toString());
	}
}
