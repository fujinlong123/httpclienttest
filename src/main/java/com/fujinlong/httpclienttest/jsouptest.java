package com.fujinlong.httpclienttest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class jsouptest {
	public static void main(String[] args)
			throws IOException, ScriptException, SAXException, ParserConfigurationException {
		ScriptEngineManager manager = new ScriptEngineManager();
		NashornScriptEngine engine = (NashornScriptEngine) manager.getEngineByName("javascript");
		engine.put("jsEngine", engine);
		engine.compile(new FileReader(new File(jsouptest.class.getResource("env.js").getPath()))).eval();

		engine.eval("print(new RegExp());");
		
		HttpClientContext context = HttpClientContext.create();
		engine.put("httpClientContext", context);
		String url = "https://mail.qq.com";
	
		String text = HttpUtils.get(url, context);
		Document doc = Jsoup.parse(text);
		Element element = doc.getElementById("login_frame");
		String src = element.attr("src"); 
		
		 String text1 = HttpUtils.get(src, context);
		 System.out.println("text1:"+text1);
			Document doddddc = Jsoup.parse(text1);
			System.out.println(doddddc.outerHtml());
			Object o =doddddc.select("#auth_low_login_enable");
			
		engine.eval("window.location='"+src+"';");
		List<Cookie> cookies=context.getCookieStore().getCookies();
		//System.out.println(CookieUtils.toStr(context));
		//Object o1=context.getCookieOrigin();
		
		
		
		///System.out.println(JSON.toJSONString(o1));

		
		engine.eval("document.getElementById('login_button').click()");
		
		/*
		 * Connection con = Jsoup.connect(url).userAgent(
		 * "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36"
		 * ); Document doc = con.get(); ScriptEngineManager manager = new
		 * ScriptEngineManager(); ScriptEngine engine =
		 * manager.getEngineByName("javascript"); engine.eval("var window={};");
		 * engine.eval("var navigator={};"); engine.eval(
		 * "navigator.userAgent='Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36';"
		 * );
		 * 
		 * Elements elements = doc.select("script"); for (Element element :
		 * elements) { System.out.println(element.html());
		 * engine.eval(element.html()); }
		 */
	}

	public static void xxx() {
		System.out.println("ok");
	}
}
