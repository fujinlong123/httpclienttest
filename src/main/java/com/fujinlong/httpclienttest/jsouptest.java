package com.fujinlong.httpclienttest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class jsouptest {
	public static void main(String[] args)
			throws IOException, ScriptException, SAXException, ParserConfigurationException {
		ScriptEngineManager manager = new ScriptEngineManager();
		NashornScriptEngine engine = (NashornScriptEngine) manager.getEngineByName("javascript");
		engine.compile(new FileReader(new File(jsouptest.class.getResource("env.js").getPath()))).eval();

		
		
		HttpClientContext context = HttpClientContext.create();
		//engine.put("HttpUtils", "com.fujinlong.httpclienttest.HttpUtils");
		engine.put("httpClientContext", context);
		String url = "https://mail.qq.com";

		String text = HttpUtils.get(url, context);
		CookieStore cookieStore = context.getCookieStore();
		Document doc = Jsoup.parse(text);
		Element element = doc.getElementById("login_frame");
		String src = element.attr("src");

		text = HttpUtils.get(src, context);
		System.out.println(text);
		//

		doc = Jsoup.parse(text,"https://xui.ptlogin2.qq.com");
	
		doc.getAllElements().remove(doc.select("script"));
		Elements elements = doc.select("script");
		elements.remove();

		engine.put("dom123", doc);
		engine.eval("window.document=new DOMDocument('',dom123)");
		engine.eval("print(document)");
		engine.eval("window.location='https://xui.ptlogin2.qq.com'");
		
		engine.eval("var kkkk=document.getElementById('u');");
		engine.eval("kkkk.checked='ddddd';");
		engine.eval("print(kkkk);");
		for (Element element2 : elements) {
			System.out.println(element2.outerHtml());
			engine.eval(element2.html());

		}
		engine.eval("print(document.createElement('img')+'xdfsadfsadfsaf888888888');");
		engine.eval("document.createElement('img').src='';");
		engine.eval("new Image;");
		
		engine.eval("loadJs();");
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
