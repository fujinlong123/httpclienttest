package com.fujinlong.httpclienttest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.sun.corba.se.impl.orbutil.graph.Node;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class QQMailLoginExample {
	public static void main(String[] args) {
		 NashornScriptEngine engine = null;
		try {
			HttpClientContext httpClientContext = HttpClientContext.create();
			engine = JsEngineFactory.getEnine(httpClientContext);
			/*engine.eval("var sss=function(){print('ssss');}; var timeout=setTimeout(sss,1000);");
			engine.eval("print(timeout)");
			engine.eval("print(timeout);clearTimeout(timeout)");
			engine.eval("var iii=function(){print('iiii');}; var interval=setInterval(iii,1000);setInterval(iii,1000);");
			engine.eval("print(interval)");
			engine.eval("print(interval);clearAllTimers()");
			*/
			engine.eval("window.location='https://www.baidu.com/';");
			String mainurl = "https://mail.qq.com";
			ObjectResponse response = HttpUtils.get(mainurl, httpClientContext);
			//System.out.println(response.getResponseBody());
			Document doc = Jsoup.parse((String) response.getResponseBody());
			Element element = doc.getElementById("login_frame");
			
			String src = element.attr("src");
			engine.eval("window.location='" + src + "';");

			String username = "2782756454@qq.com";
			String password = "long19900123";

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
		// ObjectResponse response2 =
		// HttpUtils.get("https://mail.qq.com/cgi-bin/frame_html",
		// httpClientContext);
		// System.out.println("邮箱首页内容："+response2.getStringResponseBody());

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
