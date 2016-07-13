package com.fujinlong.httpclienttest;

import java.io.IOException;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class jsengine {
	public static void main(String[] args) throws ScriptException {
		
	
		
		ScriptEngineManager manager = new ScriptEngineManager();
		NashornScriptEngine engine = (NashornScriptEngine)manager.getEngineByName("javascript");
		engine.eval("''.toLowerCase()");
		
	Bindings b=	engine.getBindings(ScriptContext.ENGINE_SCOPE);
	
	try {
		javax.xml.parsers.
		DocumentBuilderFactory.newInstance()
			.newDocumentBuilder().parse("");
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		engine.eval("var alert=function(s){print(s);};");
	
		engine.eval("window.alert('xx');");
		
		ScriptEngine engine1 = manager.getEngineByName("javascript");
		
		//engine1.eval("alert('xxasdfasdf');", sc);
		//Double hour = (Double)engine.eval("print('ok');");
		//System.out.println(hour);
	}
}
