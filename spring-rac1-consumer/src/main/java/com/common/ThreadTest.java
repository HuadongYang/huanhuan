package com.common;

import com.service.SynDbTest;
public class ThreadTest implements Runnable  {
	
	private SynDbTest synDbTest;
	
	public ThreadTest(SynDbTest synDbTest){
		this.synDbTest=synDbTest;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		synDbTest.getIdByClassroomId();
		
	}

}
