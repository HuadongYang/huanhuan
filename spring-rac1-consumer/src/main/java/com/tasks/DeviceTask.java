package com.tasks;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.service.TimingInsertService;

@Component
public class DeviceTask {
	
	@Autowired
	private TimingInsertService timingInsertService;
	
//	@Scheduled(cron="0/15 * * * * ?")
//	public void dayTask() throws IOException{
//		System.out.println("start1");
//		timingInsertService.dayTask();
//		System.out.println("end1");
//	}
	
//	@Scheduled(cron="0/15 * * * * ?")
//	public void monthTask() throws IOException{
//		System.out.println("start2");
//		timingInsertService.monthTask();
//		System.out.println("end2");
//	}
	
/*	@Scheduled(cron="0/15 * * * * ?")
	public void quaterTask() throws IOException{
		System.out.println("start3");
		timingInsertService.quaterTask();
		System.out.println("end3");
	}*/
}
