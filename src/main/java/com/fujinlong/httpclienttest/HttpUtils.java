package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36";
	private static RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
	private static CloseableHttpClient client = HttpClients.custom().setMaxConnTotal(100).setMaxConnPerRoute(20)
			.setUserAgent(userAgent).setDefaultRequestConfig(requestConfig).build();

	static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			Header[] h1 = response.getAllHeaders();
			System.out.println(h1);
			String text = entity != null ? EntityUtils.toString(entity,"UTF-8") : null;
			return text;
		}
	};

	public static String get(String url) throws ClientProtocolException, IOException {
		return get(url,null);
	}

	public static String get(URI url) throws ClientProtocolException, IOException {
		return get(url,null);
	}
	
	public static String get(String url,HttpClientContext context) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		String text=client.execute(get, responseHandler,context);
		
		return text;
	}
	
	public static String get(URI url,HttpClientContext context) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		String text=client.execute(get, responseHandler,context);
		
		return text;
	}

}
