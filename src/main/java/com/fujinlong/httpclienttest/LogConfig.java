package com.fujinlong.httpclienttest;

public class LogConfig {
	public static boolean showResponseBody=true;
	public static boolean showExecJs=false;
	public static boolean showErrExecJs=true;
	public static boolean showTimeout=true;
	
	
	
	public static void timeoutPrint(Object str){
		if(showTimeout){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
			System.out.println(str);
		}
	}
}
