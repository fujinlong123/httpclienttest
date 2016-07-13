package com.fujinlong.httpclienttest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class SaveImg {
	public static void save(Object btytes,Object name,String src){
		System.out.println("图片Src:"+src);

		String verifyPath="D:/test/verifyImg"+name+".jpg";
		try {
			FileUtils.writeByteArrayToFile(new File(verifyPath), (byte[]) btytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
