package com.fujinlong.httpclienttest;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class ThreadTest {
	public static void main(String[] args) {
		List<Thread> list=new ArrayList<>();
		
		for(int i=0;i<10;i++){
			Thread t=new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			t.start();
			list.add(t);
		}
		
		for (Thread thread : list) {
			thread.stop();
		}
	}
	
}
