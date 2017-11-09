package com.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class BatchNumTest {
	private static String batchNum = null;
	public Calendar calendar = Calendar.getInstance();
	
	public synchronized String getBatchNum(){
		if (batchNum == null) {
			batchNum = queryMaxBatchNum();
		}
		batchNum = getNewBatchNum(batchNum);
		return batchNum;
	}
	
	public String getNewBatchNum(String oldBatchNum){
		String newBatchNum = oldBatchNum;
		Integer oldyear = Integer.parseInt(oldBatchNum.substring(0,4));
		Integer oldMonth = Integer.parseInt(oldBatchNum.substring(4,6));
		if (getTodayYear() > oldyear || oldMonth != getTodayMonth()) {
			//更新年月
			newBatchNum = initializeBatchNum().substring(0, 6) + "0000";
		}else {
			//更新后四位
			newBatchNum = String.valueOf(Integer.valueOf(newBatchNum) + 1);
		}
		return newBatchNum;
	}
	
	/**
	 * 去数据库查最大的批次号
	 */
	public String queryMaxBatchNum(){
		//如果查出来是null，要初始化initializeBatchNum()
		return "2017110123";
	}
	
	public int getTodayMonth(){
		Date date = new Date();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	public int getTodayYear(){
		Date date = new Date();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * 批次号初始化
	 */
	public String initializeBatchNum(){
		int year = getTodayYear();
		int month = getTodayMonth();
		if (month < 10) {
			return year + "0" + month + "0000";
		}else {
			return year + "" + month + "0000";
		}
	}
	public static void main(String[] args){
		BatchNumTest ba = new BatchNumTest();
		String a = ba.getBatchNum();
		System.out.println(a);
	}
}
