package com.fujinlong.httpclienttest;

public class Threadtest1 {
	public static void main(String[] args) {
		new Thread(new  Runnable() {
			public void run() {
				while(true){
					System.out.println("ok");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
