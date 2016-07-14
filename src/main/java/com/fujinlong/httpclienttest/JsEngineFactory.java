package com.fujinlong.httpclienttest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.http.client.protocol.HttpClientContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class JsEngineFactory {
	public static NashornScriptEngine getEnine(HttpClientContext httpClientContext) throws FileNotFoundException, ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		final NashornScriptEngine engine = (NashornScriptEngine) manager.getEngineByName("javascript");
		engine.put("jsEngine", engine);
		engine.compile(new FileReader(new File(QQMailLoginExample.class.getResource("env.js").getPath()))).eval();
		engine.compile(new FileReader(new File(QQMailLoginExample.class.getResource("timeout.js").getPath()))).eval();
		engine.put("httpClientContext", httpClientContext);
		return engine;
	}
}
