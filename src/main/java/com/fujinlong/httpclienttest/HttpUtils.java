package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.URI;
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

public class HttpUtils {
	private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36";
	private static RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
	private static CloseableHttpClient client = HttpClients.custom().setMaxConnTotal(100).setMaxConnPerRoute(20)
			.setUserAgent(userAgent).setDefaultRequestConfig(requestConfig).build();

	private static final ResponseHandler<StringResponse> responseHandler = new ResponseHandler<StringResponse>() {
		@Override
		public StringResponse handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
			int status = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			StringResponse stringResponse = new StringResponse();
			stringResponse.setStatusCode(status);
			Charset charset = ContentType.get(entity).getCharset();
			String mimeType=ContentType.get(entity).getMimeType();
			if (charset == null) {
				charset = Charset.defaultCharset();
			}
			stringResponse.setCharset(charset);
			stringResponse.setMimeType(mimeType);
			stringResponse.setResponseBody(entity != null ? EntityUtils.toString(entity) : null);
			return stringResponse;

		}

	};

	static ResponseHandler< byte[]> imgResponseHandler = new ResponseHandler< byte[]>() {

		public byte[] handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			byte[] b = entity != null ? EntityUtils.toByteArray(entity) : null;
			return b;
		}
	};

	
	
	public static StringResponse get(String url) throws ClientProtocolException, IOException {
		return get(url, null);
	}

	public static StringResponse get(URI url) throws ClientProtocolException, IOException {
		return get(url, null);
	}

	public static StringResponse get(String url, HttpClientContext context) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		StringResponse text = client.execute(get, responseHandler, context);

		return text;
	}

	public static StringResponse get(URI url, HttpClientContext context) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		StringResponse text = client.execute(get, responseHandler, context);

		return text;
	}

	public static byte[] getImg(String url) throws ClientProtocolException, IOException {
		return getImg(url, null);
	}

	public static byte[] getImg(String url, HttpClientContext context) throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		byte[] b = client.execute(get, imgResponseHandler, context);

		return b;
	}

}
