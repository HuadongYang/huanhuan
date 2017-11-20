package com.Util;
//import org.joda.time.DateTime;
//import org.joda.time.LocalDate;
//import org.joda.time.LocalDateTime;
//import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Lucyar on 2017/11/20.
 */
public class DateUtils {

    /**
     * 时间工具类
     * Created by csf on 2015/5/16.
     *
     * @author csf
     */
    @SuppressWarnings(value = "unused")
        private DateUtils() {

        }

        private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
        private final static String FORMAT_YMD = "yyyy-MM-dd";

        public static boolean is8Date(String date) {
            if (StringUtils.isEmpty(date)) {
                return false;
            }
            String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
            Pattern pattern = Pattern.compile(eL);
            Matcher matcher = pattern.matcher(date);
            return matcher.matches();
        }

        public static String getSysDate(String format) {
            if (StringUtils.isEmpty(format)) {
                format = DEFAULT_FORMAT;
            }
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(new Date());
        }

        public static int getSysYear() {
            Calendar cal = Calendar.getInstance();
            return cal.get(Calendar.YEAR);
        }

        public static Date getDateByString(String date, String format) {
            if (StringUtils.isEmpty(format)) {
                format = DEFAULT_FORMAT;
            }
            if (StringUtils.isNotEmpty(date)) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                try {
                    return sdf.parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException("转换为日期类型错误：DATE：" + date + "  FORMAT:" + format);
                }
            } else {
                return null;
            }
        }

        public static String getFormatDate(Date date, String format) {
            if (StringUtils.isEmpty(format)) {
                format = DEFAULT_FORMAT;
            }
            if (date != null) {
                SimpleDateFormat df = new SimpleDateFormat(format);
                return df.format(date);
            } else {
                return null;
            }
        }

        public static Date getDayStartOfDate(Date date) {
            String formatDate = getFormatDate(date, "yyyy-MM-dd");
            return StringUtils.isEmpty(formatDate) ? null : getDateByString(formatDate + " 00:00:00", "");
        }

        public static Date getDayEndOfDate(Date date) {
            String formatDate = getFormatDate(date, "yyyy-MM-dd");
            return StringUtils.isEmpty(formatDate) ? null : getDateByString(formatDate + " 23:59:59", "");
        }

        /**
         * 一月的最后一天
         *
         * @param date 日期
         * @return 当月最后一天
         */
        public static Date getDayEndOfMonth(Date date) {
            if (date == null) {
                date = new Date();
            }
            Date dayStartOfMonth = getDayStartOfMonth(date);
            Calendar cal = Calendar.getInstance();

            cal.setTime(dayStartOfMonth);
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DATE, -1);
            return getDayEndOfDate(cal.getTime());
        }

        /**
         * 一月的第一天
         *
         * @param date 日期
         * @return 当月第一天
         */
        public static Date getDayStartOfMonth(Date date) {
            if (date == null) {
                date = new Date();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            return getDayStartOfDate(cal.getTime());
        }

        public static Date getDateByLongDate(Long millis) {
            if (millis == null) {
                return new Date();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(millis);
            return cal.getTime();

        }

        /**
         * 日期加减运算
         *
         * @param date 日期
         * @param day  加天数（减传负数)
         * @return 返回运算后日期
         */
        public static Date daysOperation(Date date, int day) {
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                //加天
                cal.add(Calendar.DATE, day);
                return cal.getTime();
            } else {
                return null;
            }
        }

        public static Date monthOperation(Date date, int month) {
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                //加天
                cal.add(Calendar.MONTH, month);
                return cal.getTime();
            } else {
                return null;
            }
        }

        public static Date hoursOperation(Date date, int hours) {
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                //加小时
                cal.add(Calendar.HOUR, hours);
                return cal.getTime();
            } else {
                return null;
            }
        }

        public static String secToTime(int time) {
            String timeStr;
            int hour;
            int minute;
            int second;
            if (time <= 0)
                return "00:00";
            else {
                minute = time / 60;
                if (minute < 60) {
                    second = time % 60;
                    timeStr = unitFormat(minute) + ":" + unitFormat(second);
                } else {
                    hour = minute / 60;
                    if (hour > 99)
                        return "99:59:59";
                    minute = minute % 60;
                    second = time - hour * 3600 - minute * 60;
                    timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
                }
            }
            return timeStr;
        }

        public static String unitFormat(int i) {
            String retStr;
            if (i >= 0 && i < 10)
                retStr = "0" + Integer.toString(i);
            else
                retStr = "" + i;
            return retStr;
        }

        /**
         * 获取当前日期对象
         *
         * @return 当前日期对象
         */
        public static Date now() {
            return new Date();
        }

        /**
         * 获取当前日期字符串
         *
         * @param format 日期格式
         * @return 当前日期字符串
         */
//        public static String now(String format) {
//            return format(now(), format);
//        }

        /**
         * 格式化日期对象
         *
         * @param date   日期对象
         * @param format 日期格式
         * @return 当前日期字符串
         */
//        public static String format(Date date, String format) {
//            return new DateTime(date).toString(format);
//        }

        /**
         * 格式化日期对象，格式为yyyy-MM-dd HH:mm:ss
         *
         * @param date 日期对象
         * @return 日期字符串
         */
//        public static String format(Date date) {
//            return new DateTime(date).toString(DEFAULT_FORMAT);
//        }

        /**
         * 格式化日期对象，格式为yyyy-MM-dd HH:mm:ss
         *
         * @param mills 毫秒
         * @return 日期字符串
         */
//        public static String format(Long mills) {
//            return new DateTime(mills).toString(DEFAULT_FORMAT);
//        }

        /**
         * 格式化日期对象
         *
         * @param mills   毫秒
         * @param pattern 格式
         * @return 日期字符串
         */
//        public static String format(Long mills, String pattern) {
//            return new DateTime(mills).toString(pattern);
//        }

        /**
         * 解析字符串日期(不含时间)
         *
         * @param date    字符日期
         * @param pattern 格式
         * @return LocalDate
         */
//        public static LocalDate parseDate(String date, String pattern) {
//            return LocalDate.parse(date, DateTimeFormat.forPattern(FORMAT_YMD));
//        }

        /**
         * 解析字符串日期(不含时间),格式:yyyy-MM-dd
         *
         * @param date 字符日期
         * @return LocalDate
         */
//        public static LocalDate parseDate(String date) {
//            return parseDate(date, FORMAT_YMD);
//        }

        /**
         * 解析字符串日期(不含时间)
         *
         * @param date 字符日期
         * @return Date
         */
//        public static Date parseDate2(String date, String pattern) {
//            return parseDate(date, pattern).toDate();
//        }

        /**
         * 解析字符串日期(不含时间),格式:yyyy-MM-dd
         *
         * @param date 字符日期
         * @return Date
         */
//        public static Date parseDate2(String date) {
//            return parseDate2(date, FORMAT_YMD);
//        }


        /**
         * 解析字符串日期时间(带时间)
         *
         * @param dateTime 字符日期时间
         * @param pattern  格式
         * @return LocalDateTime
         */
//        public static LocalDateTime parseDateTime(String dateTime, String pattern) {
//            return LocalDateTime.parse(dateTime, DateTimeFormat.forPattern(pattern));
//        }

        /**
         * 解析字符串日期时间(带时间),格式:yyyy-MM-dd HH:mm:ss
         *
         * @param dateTime 字符日期时间
         * @return LocalDateTime
         */
//        public static LocalDateTime parseDateTime(String dateTime) {
//            return parseDateTime(dateTime, DEFAULT_FORMAT);
//        }

        /**
         * 解析字符串日期时间(带时间)
         *
         * @param dateTime 字符日期时间
         * @param pattern  格式
         * @return Date
         */
//        public static Date parseDateTime2(String dateTime, String pattern) {
//            return parseDateTime(dateTime, pattern).toDate();
//        }

        /**
         * 解析字符串日期时间(带时间),格式:yyyy-MM-dd HH:mm:ss
         *
         * @param dateTime 字符日期时间
         * @return Date
         */
//        public static Date parseDateTime2(String dateTime) {
//            return parseDateTime2(dateTime, DEFAULT_FORMAT);
//        }

        /**
         * 当天剩余时间,秒
         *
         * @return int
         */
//        public static int remainSeconds() {
//            java.time.LocalDateTime midnight = java.time.LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
//            return (int) ChronoUnit.SECONDS.between(java.time.LocalDateTime.now(), midnight);
//        }
    }

