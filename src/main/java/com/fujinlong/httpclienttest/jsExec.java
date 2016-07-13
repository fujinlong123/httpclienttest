package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.script.ScriptException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.protocol.HttpClientContext;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class jsExec {
	public static void exec(URL baseUrl, Document doc, HttpClientContext context, NashornScriptEngine jsEngine) {
		Elements eles = doc.getElementsByTag("script");
		
		for (Element element : eles) {
			try {
				if (!StringUtil.isBlank(element.attr("src"))) {
					URL url =new URL(baseUrl, element.attr("src"));
			
					ObjectResponse text = HttpUtils.get(url.toURI(), context);
					System.out.println("执行的js内容："+text.getResponseBody());
					jsEngine.eval((String)text.getResponseBody());
				} else {
					System.out.println("执行的js内容："+element.html());
					jsEngine.eval(element.html());
					
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ScriptException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

	}
		

	
	public static void main(String[] args) {
		
	}
}
