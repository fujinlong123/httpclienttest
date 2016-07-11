package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class test {
	public static void main(String[] args) throws MalformedURLException {
		String text="adsadlfjsdk=\"asdfasdf\"";
		System.out.println();
		System.out.println(text.replaceAll("\"", "\\\\\""));
	URL url=	new java.net.URL(new URL("http://www.baidu.com"), "http://www.baidu.com");

	try {
		//url.openConnection().addRequestProperty(key, value);
		url.openConnection().connect();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		try {
			java.lang.Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
