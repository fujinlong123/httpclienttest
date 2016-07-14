package com.fujinlong.httpclienttest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.script.ScriptException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
    
    
    
    
    public static void test3(){
    	CloseableHttpClient client=HttpClients.custom().setMaxConnTotal(2).build();
    	HttpGet get=new HttpGet("http://www.huomaotv.cn/dota2/major.html?f=10662");
    	try {
    		for (int i = 0; i < 20; i++) {
    			System.out.println("b");
    			client.execute(get, new ResponseHandler<String>() {

    				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
    					// TODO Auto-generated method stub
    					return null;
    				}
    			});
    			System.out.println("a");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void test4(){
    	CloseableHttpClient client=HttpClients.custom().setMaxConnTotal(2).setConnectionTimeToLive(1, TimeUnit.SECONDS).setRetryHandler(new HttpRequestRetryHandler() {
			
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				System.out.println("HttpRequestRetryHandler");
				return true;
			}
		}).build();
    	RequestConfig config=RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(1000).build();
    	HttpGet get=new HttpGet("http://www.huodddddmaotv.cn/dota2/major.html?f=10662");
    	get.setConfig(config);
    	for (int i = 0; i < 200; i++) {
    		try {
    			System.out.println("i");
				HttpResponse response= client.execute(get);
				EntityUtils.consume(response.getEntity());
				System.out.println("b");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    }
    
    
    public static void test1(){
    	try {
			Socket st=new Socket("192.168.31.121",8980);
			InputStream  is=st.getInputStream();
			byte[] b=new byte[1024];
			int l=-1;
			while ((l=is.read(b))!=-1) {
				System.out.println(new String(b));
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	
    }
    
    public static void test2(){
    	try {
    		while (!Thread.currentThread().isInterrupted()) {
    			ServerSocket sst=new ServerSocket(8980);
    			sst.accept();
    			
			}
    		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	
    }
    
    
    
    
}
