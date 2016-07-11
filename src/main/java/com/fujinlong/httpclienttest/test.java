package com.fujinlong.httpclienttest;

public class test {
	public static void main(String[] args) {
		String text="adsadlfjsdk=\"asdfasdf\"";
		System.out.println();
		System.out.println(text.replaceAll("\"", "\\\\\""));
		try {
			java.lang.Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
