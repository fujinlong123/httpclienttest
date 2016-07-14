package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.sun.jndi.toolkit.url.Uri;

public class HttpUtils {
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36";
	private static RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(false)
			.setCookieSpec(CookieSpecs.STANDARD).build();
	private static CloseableHttpClient client = HttpClients.custom().setMaxConnTotal(100).setMaxConnPerRoute(20)
			.setUserAgent(userAgent).setDefaultRequestConfig(requestConfig).build();

	private static final ResponseHandler<ObjectResponse> responseHandler = new ResponseHandler<ObjectResponse>() {
		@Override
		public ObjectResponse handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
			System.out.println("response头信息：" + JSON.toJSONString(response.getAllHeaders()));
			System.out.println("responseStatusLine头信息：" + JSON.toJSONString(response.getStatusLine()));
			int status = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			ObjectResponse objectResponse = new ObjectResponse();
			objectResponse.setHttpResponse(response);
			objectResponse.setStatusCode(status);
			Charset charset = ContentType.get(entity).getCharset();
			String mimeType = ContentType.get(entity).getMimeType();
			if (charset == null) {
				charset = Charset.defaultCharset();
			}
			objectResponse.setCharset(charset);
			objectResponse.setMimeType(mimeType);
			if ("image/bmp".equals(mimeType)) {
				objectResponse.setBinary(true);
				System.out.println("请求内容：" + objectResponse.getStatusCode() + ":" + objectResponse.getCharset()
				+ ":" + objectResponse.getMimeType() + ":字节内容不显示" );
				objectResponse.setResponseBody(entity != null ? EntityUtils.toByteArray(entity) : null);
			} else {
				objectResponse.setString(true);
				String text = entity != null ? EntityUtils.toString(entity, charset) : null;
				if (LogConfig.showResponseBody) {
					System.out.println("请求内容：" + objectResponse.getStatusCode() + ":" + objectResponse.getCharset()
							+ ":" + objectResponse.getMimeType() + ":" + text);
				}
				objectResponse.setResponseBody(text);
			}
			return objectResponse;

		}

	};

	public static ObjectResponse get(String url, HttpClientContext context)
			throws ClientProtocolException, IOException, URISyntaxException {
		return get(new URI(url), context);
	}

	public static ObjectResponse get(URI url, HttpClientContext context) throws ClientProtocolException, IOException {
		// System.out.println("请求上下文：" + JSON.toJSONString(context));
		System.out.println("请求链接：" + url.toString());
		if (context.getCookieStore() != null) {
			System.out.println("cookie信息：" + JSON.toJSONString(context.getCookieStore().getCookies()));
		}
		if (url.toString().contains("check_sig")) {
			// System.out.println();
		}
		HttpGet get = new HttpGet(url);
		ObjectResponse text = client.execute(get, responseHandler, context);
		return text;
	}

}
