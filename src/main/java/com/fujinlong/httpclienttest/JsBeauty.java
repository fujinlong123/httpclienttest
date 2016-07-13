package com.fujinlong.httpclienttest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class JsBeauty {
	public static String beauty(String src) throws FileNotFoundException, ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		NashornScriptEngine engine = (NashornScriptEngine) manager.getEngineByName("javascript");
		engine.compile(new FileReader(new File(QQMailLoginExample.class.getResource("jsbeauty.js").getPath()))).eval();
		engine.put("srcJs", src);
		engine.eval("var target=js_beautify(srcJs);");
		Object o = engine.get("target");
		return (String)o;

	}

	public static void main(String[] args) throws ScriptException, IOException {
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(JsBeauty.class.getResource("test.js").getPath()));
		BufferedReader bufferedReader = new BufferedReader(read);
		StringBuffer sb=new StringBuffer();
		String line=null;
		while((line=bufferedReader.readLine())!=null){
			sb.append(line);
		}
		JsBeauty.beauty(sb.toString());
	}
}
