package com.fujinlong.httpclienttest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.utils.URLEncodedUtils;

public class URLConnecttest {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://ursdoccdn.nosdn.127.net/webzj_m163/message_2016052502.js");
		InputStream is = url.openStream();
		byte[] b=new byte[1024];
		int l=-1;
		while((l=is.read(b))!=-1){
			System.out.println(new String(b,0,l));
		}

	}
}
