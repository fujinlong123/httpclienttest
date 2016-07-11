package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class jsopasd {
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		
		String text1=HttpUtils.get(new java.net.URL(new URL("http://www.baidu.com"), "http://www.baidu.com").toURI());
		System.out.println(text1);
		String text=HttpUtils.get("http://www.baidu.com");
		Document doc=Jsoup.parse(text);
		System.out.println(doc.html());
		Object o=doc.createElement("script");
		doc.childNodes().size();
		System.out.println(doc.getElementById("ftCon").attr("type"));
		
	}
}
