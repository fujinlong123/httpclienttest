package com.fujinlong.httpclienttest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;

import org.apache.http.client.protocol.HttpClientContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class M163LoginExample {
	public static void main(String[] args) throws Exception {
		NashornScriptEngine engine = null;
		final HttpClientContext httpClientContext = HttpClientContext.create();

		engine = JsEngineFactory.getEnine(httpClientContext);

		try {

			/*
			 * engine.eval(
			 * "var sss=function(){print('ssss');}; var timeout=setTimeout(sss,1000);"
			 * ); engine.eval("print(timeout)");
			 * engine.eval("print(timeout);clearTimeout(timeout)"); engine.eval(
			 * "var iii=function(){print('iiii');}; var interval=setInterval(iii,1000);setInterval(iii,1000);"
			 * ); engine.eval("print(interval)");
			 * engine.eval("print(interval);clearAllTimers()");
			 */

			String src="http://mail.163.com/";
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

		Thread.sleep(10000);
		System.out.println("停止引擎");
		engine.eval("clearAllTimers();");
		ScriptContext sc = engine.getContext();

		engine.setContext(new SimpleScriptContext());
		ObjectResponse response = HttpUtils.get("https://mail.qq.com/cgi-bin/login?vt=passport&vm=wsk&delegate_url=",
				httpClientContext);
		String redirctUrl = response.getLastHeaderValue("Location");
		response = HttpUtils.get(redirctUrl, httpClientContext);
		Document doc = Jsoup.parse(response.getStringResponseBody(), redirctUrl);

		System.out.println("邮箱首页信息：" + response.getStringResponseBody());

		Element folderA = doc.getElementById("folder_1");

		URL url = new URL(new URL(redirctUrl), folderA.attr("href"));

		response = HttpUtils.get(url.toURI(), httpClientContext);

		System.out.println("邮箱列表页面信息：" + response.getStringResponseBody());

		doc = Jsoup.parse(response.getStringResponseBody(), redirctUrl);
		Elements eles = doc.select(".toarea");
		for (Element element : eles) {
			Elements innereles = element.select(".l");
			for (Element element2 : innereles) {
				System.out.println("提取信息：" + element2.outerHtml());
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
}
