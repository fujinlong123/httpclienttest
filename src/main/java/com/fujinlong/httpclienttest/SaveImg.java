package com.fujinlong.httpclienttest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class SaveImg {
	public static void save(Object btytes,String src){
		System.out.println("保存图片："+src);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
		String verifyPath="D:/test/verifyImg"+sdf.format(new Date())+".jpg";
		try {
			FileUtils.writeByteArrayToFile(new File(verifyPath), (byte[]) btytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
