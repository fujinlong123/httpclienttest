package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class httpclienttest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		test();
	}

	public static void test() throws ClientProtocolException, IOException {
		// http://ursdoccdn.nosdn.127.net/webzj_m163/message_2016052502.js
		CloseableHttpClient client = HttpClients.createDefault();
		//String str="http://cp.127.net/cte/cp?1468576590269";
		String str="http://ursdoccdn.nosdn.127.net/webzj_m163/message_2016052502.js";
		HttpGet hg = new HttpGet(str);
		CloseableHttpResponse response = client.execute(hg);

		HttpEntity entity = response.getEntity();
		InputStream is= entity.getContent();
		byte[] b=new byte[1024];
		int l=0;
	
		while((l=is.read(b))!=-1){
			System.out.println(new String(b,0,l));
		}
		

	}
}
