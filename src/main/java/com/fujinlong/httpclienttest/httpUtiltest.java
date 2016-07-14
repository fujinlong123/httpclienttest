package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.protocol.HttpClientContext;

public class httpUtiltest {
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		HttpClientContext context=HttpClientContext.create();
		ObjectResponse re=HttpUtils.get("http://www.baidu.com", context);
		System.out.println(re.getResponseBody());
	}
}
