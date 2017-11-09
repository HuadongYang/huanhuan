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
			//��������
			newBatchNum = initializeBatchNum().substring(0, 6) + "0000";
		}else {
			//���º���λ
			newBatchNum = String.valueOf(Integer.valueOf(newBatchNum) + 1);
		}
		return newBatchNum;
	}
	
	/**
	 * ȥ���ݿ���������κ�
	 */
	public String queryMaxBatchNum(){
		//����������null��Ҫ��ʼ��initializeBatchNum()
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
	 * ���κų�ʼ��
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
