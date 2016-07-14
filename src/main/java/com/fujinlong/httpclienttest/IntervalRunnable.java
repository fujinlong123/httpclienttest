package com.fujinlong.httpclienttest;

public abstract class IntervalRunnable implements Runnable {
	private boolean isStop=false;

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	
	public static void main(String[] args) {
		new IntervalRunnable() {
			
			@Override
			public void run() {
				System.out.println(this.isStop());
				
			}
		};
	}

	
	
}

