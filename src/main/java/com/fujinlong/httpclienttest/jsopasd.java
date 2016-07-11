package com.fujinlong.httpclienttest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class jsopasd {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String text=HttpUtils.get("http://www.baidu.com");
		Document doc=Jsoup.parse(text);
		System.out.println(doc.html());
		Object o=doc.createElement("script");
		doc.getElementsByTag(tagName)
		System.out.println(doc.getElementById("ftCon").attr("type"));
		
	}
}
