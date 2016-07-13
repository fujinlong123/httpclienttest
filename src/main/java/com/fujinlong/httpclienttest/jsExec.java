package com.fujinlong.httpclienttest;

import java.io.FileNotFoundException;
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
		String js="";
		for (Element element : eles) {
			try {
				if (!StringUtil.isBlank(element.attr("src"))) {
					URL url =new URL(baseUrl, element.attr("src"));
			
					ObjectResponse text = HttpUtils.get(url.toURI(), context);
					//System.out.println("执行的js内容："+text.getResponseBody());
					js=(String)text.getResponseBody();
					jsEngine.eval(js);
				} else {
					//System.out.println("执行的js内容："+element.html());
					js=element.html();
					jsEngine.eval(js);
					
				}
			} catch (Exception e) {
				try {
					System.err.println("执行js出错："+JsBeauty.beauty(js,false));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ScriptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				e.printStackTrace();
				
			}
		}

	}
		

	
	public static void main(String[] args) {
		
	}
}
