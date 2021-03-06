package com.fujinlong.httpclienttest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.orbutil.graph.Node;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.internal.runtime.ScriptEnvironment;

public class QQMailLoginExample {
	public static void main(String[] args) throws Exception {
		NashornScriptEngine engine = null;
		final HttpClientContext httpClientContext = HttpClientContext.create();

		engine = JsEngineFactory.getEnine(httpClientContext);
		
		try {



			String mainurl = "https://mail.qq.com";
			ObjectResponse response = HttpUtils.get(mainurl, httpClientContext);
			
			Document doc = Jsoup.parse((String) response.getResponseBody());
			Element element = doc.getElementById("login_frame");

			String src = element.attr("src");
			engine.eval("window.location='" + src + "';");

			String username = "923444172@qq.com";
			String password = "lang::;;19931216";

			// 模拟用户操作输入用户名和密码
			engine.eval("document.getElementById('u').focus();");
			engine.eval("document.getElementById('u').keydown();");
			engine.eval("document.getElementById('u').value='" + username + "'");
			engine.eval("document.getElementById('u').keyup();");
			engine.eval("document.getElementById('u').blur()");

			engine.eval("document.getElementById('p').focus();");
			engine.eval("document.getElementById('p').keydown();");
			engine.eval("document.getElementById('p').value='" + password + "'");
			engine.eval("document.getElementById('p').keyup();");
			engine.eval("document.getElementById('p').blur()");
			engine.eval("document.getElementById('login_button').click();");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread.sleep(10000);
		System.out.println("停止引擎");
		engine.eval("clearAllTimers();");
		ScriptContext sc=engine.getContext();
	
		
		
		engine.setContext(new SimpleScriptContext());
		ObjectResponse response = HttpUtils.get("https://mail.qq.com/cgi-bin/login?vt=passport&vm=wsk&delegate_url=", httpClientContext);
		String redirctUrl=response.getLastHeaderValue("Location");
		response = HttpUtils.get(redirctUrl, httpClientContext);
		Document doc=Jsoup.parse(response.getStringResponseBody(),redirctUrl);
		
		System.out.println("邮箱首页信息："+response.getStringResponseBody());
	
		Element folderA=doc.getElementById("folder_1");
		
		URL url=new URL(new URL(redirctUrl), folderA.attr("href"));
		
		response = HttpUtils.get(url.toURI(), httpClientContext);
		
		System.out.println("邮箱列表页面信息："+response.getStringResponseBody());
		
		doc=Jsoup.parse(response.getStringResponseBody(),redirctUrl);
		Elements eles=doc.select(".toarea");
		for (Element element : eles) {
			Elements innereles= element.select(".l");
			for (Element element2 : innereles) {
				System.out.println("提取信息："+element2.outerHtml());
			}
			
			
			
		}
		
		
		
		while (true) {
			try {
				/*
				 * if (Kkk.needVerify) { System.out.println("请输入验证码：");
				 * BufferedReader strin = new BufferedReader(new
				 * InputStreamReader(System.in)); String str = strin.readLine();
				 * engine.eval("document.getElementById('verifycode').value='" +
				 * str + "'"); Kkk.needVerify=false; }else{
				 */
				BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
				String str = strin.readLine();
				if ("find".equalsIgnoreCase(str)) {
					System.out.println(Kkk.needVerify);
				} else {
					String ll = null;
					while ((ll = strin.readLine()) != null) {
						if ("e".equals(ll)) {
							break;
						}
						str += ll;
					}
					Object o = engine.eval(str);
					System.out.println(o);
				}
				Thread.sleep(1000);
				// }

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	public static void xxx() {
		System.out.println("ok");
	}
}
