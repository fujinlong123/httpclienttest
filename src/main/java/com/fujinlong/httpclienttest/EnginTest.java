package com.fujinlong.httpclienttest;
import java.io.FileNotFoundException;

import javax.script.ScriptException;

import org.apache.http.client.protocol.HttpClientContext;

import com.fujinlong.httpclienttest.JsEngineFactory;

public class EnginTest {
	public static void main(String[] args) {
		HttpClientContext httpClientContext = HttpClientContext.create();
		try {
			JsEngineFactory.getEnine(httpClientContext);
		} catch (FileNotFoundException | ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
