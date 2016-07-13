package com.fujinlong.httpclienttest;

import javax.script.ScriptException;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jdk.nashorn.api.scripting.NashornScriptEngine;

public class BindFormToDocument {
	public static void bind(Document doc, NashornScriptEngine jsEngine) {
		Elements eles = doc.getElementsByTag("form");
		for (Element element : eles) {
			String name=element.attr("name");
			if(!StringUtil.isBlank(name)){
				try {
					jsEngine.put("formEle", element);
					jsEngine.eval("document['"+name+"']=makeNode(formEle)");
					Elements innereles =element.select("*");
					for (Element element2 : innereles) {
						String name2=element2.attr("name");
						if(!StringUtil.isBlank(name2)){
							jsEngine.put("innerformEle", element2);
							jsEngine.eval("document['"+name+"']['"+name2+"']=makeNode(innerformEle)");
						}
					}
					
				} catch (ScriptException e) {
					e.printStackTrace();
				}
			}
			
		}
		

	}
}
