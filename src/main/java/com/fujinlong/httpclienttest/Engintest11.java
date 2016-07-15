package com.fujinlong.httpclienttest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.util.Asserts;

public class Engintest11 {
	public static void main(String[] args) throws Exception {
		HttpClientContext hcc=HttpClientContext.create();

		ScriptEngine e=JsEngineFactory.getEnine(hcc);
		e.eval("print(aaa)");
		
		// e.eval("while(true){}");
		List list =new ArrayList();
		for (int i = 0; i < 100; i++) {
		//	list.add(JsEngineFactory.getEnine(hcc));
		}
		
		System.out.println(11);
		throw new RuntimeException("");
	}
}
