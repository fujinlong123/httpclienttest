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
		String jsurl="";
		for (Element element : eles) {
			try {
				if (!StringUtil.isBlank(element.attr("src"))) {
					js="";
					URL url =new URL(baseUrl, element.attr("src"));
					jsurl=url.toString();
					if("http://ursdoccdn.nosdn.127.net/webzj_m163/message_2016052502.js".equals(jsurl)){
						System.out.println();
					}
					ObjectResponse text = HttpUtils.get(url.toURI(), context);

					js=(String)text.getResponseBody();
					if(LogConfig.showExecJs){
						System.out.println("执行的js内容："+text.getResponseBody());
					}
					jsEngine.eval(JsBeauty.beauty(js,false));
				} else {
					jsurl=baseUrl.toString();
					js=element.html();
					if(LogConfig.showExecJs){
						System.out.println("执行的js内容："+JsBeauty.beauty(js,true));
					}
					jsEngine.eval(JsBeauty.beauty(js,true));
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (ScriptException e) {
				try {
					if(LogConfig.showErrExecJs){
						System.err.println("执行js出错：路径:"+jsurl+"\n"+JsBeauty.beauty(js,true));
					}
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
