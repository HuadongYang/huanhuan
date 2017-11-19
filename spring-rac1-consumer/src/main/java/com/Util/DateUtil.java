package com.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String DATE_FORMATE = "yyyy-MM-dd";
	public static String MONTH_FORMATE = "yyyy-MM";
	public static String YEAR_FORMATE = "yyyy";
	
	
	/**
     * 返回指定日期的前一天
     *
     * @param date
     * @return
     */
	public static Date dayBefore(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-1);
		return calendar.getTime();
	}
	
	/**
     * 返回指定日期的后一天
     *
     * @param date
     * @return
     */
	public static Date dayAfter(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,1);
		return calendar.getTime();
	}
	
	
	/**
     * 日期转字符串
     *
     * @param dateFormate, date
     * @return
     */
	public static String dateToString(String dateFormate, Date date){
		SimpleDateFormat sp=new SimpleDateFormat(dateFormate);
		return sp.format(date);
	}
	
	/**
     * 日期转字符串
     *
     * @param dataString, formate
     * @return
     */
	public static Date stringToDate(String dataString, String formate) throws ParseException{
		SimpleDateFormat sdf  =   new  SimpleDateFormat(formate);
		return sdf.parse(dataString);
	} 
	
	
	/**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }
    
    /**
     * 返回指定日期的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                                    getQuarterOfYear(date));
    }
    
    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }
    
    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }
    
    /**
     * 返回指定日期的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }
    /**
     * 返回指定日期的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    
    public static Integer dayPoor(Date date1, Date date2){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date1);
    	int day1 = calendar.get(Calendar.DAY_OF_MONTH);
    	calendar.setTime(date2);
    	int day2 = calendar.get(Calendar.DAY_OF_MONTH);
    	return Math.abs(day1 - day2);
    }

}
