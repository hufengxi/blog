package com.app.appName.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	public static final String FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public  static  final  String INOUT_DAY = "yyyyMMdd";
	public  static  final  String INOUT_MONTH = "yyyyMM";
	public static Date now(){
		return new Date();
	}
	public static int nowYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static int nowMonth(){
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}

	public static int nowDay(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	public static int nowHour(){
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static Date parse(String source){
		return parse(source,DEFAULT_PATTERN);
	}
	
	public static Date parse(String source,String pattern){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
			simpleDateFormat.applyPattern(pattern);
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}
	public static String format(Date date){
		return format(date,DEFAULT_PATTERN);
	}
	
	public static String format(Date date,String pattern){
		if(date==null) return "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(date);
	}
	public static String shortNowDay(){
		return  format(new Date(),"yyyyMMdd");
	}
	public static String shortNowMonth(){
		return  format(new Date(),"yyyyMM");
	}
	public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern(DEFAULT_PATTERN);
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(simpleDateFormat.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(simpleDateFormat.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}

		return result;
	}


	public static Date addDay(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DAY_OF_YEAR, value);
		return now.getTime();
	}

	public static Date getAddDay(Date date, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DAY_OF_YEAR, value);
		return new Date(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE));
	}

	public static Long  dateStr2Timestamp(String dateStr){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(dateStr);
			return ts.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}

	}
	public static long getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTimeInMillis();
	}

	public static long getNowTime() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		return calendar.getTimeInMillis();
	}

	public static long  getTodayTime() {
		Calendar calendar = Calendar.getInstance();// 获取当天0时的日期
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	public static String getNowYear(){
		String result ="";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( System.currentTimeMillis());
		int year = cal.get(Calendar.YEAR);
		result=year+"";
		return result;
	}

	public static String getNowMonth(){
		String result ="";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( System.currentTimeMillis());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if(month <=9)
			result=year+"_0"+month;
		else
			result=year+"_"+month;
		return result;
	}
	public static Timestamp getNowTimestamp() {
		try {
			Timestamp ts = new Timestamp(new Date().getTime());
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Timestamp dateStr2Timestamp1(String dateStr) {
		try {
			Timestamp ts = Timestamp.valueOf(dateStr);
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getNowTime_YMD(){
		String result ="";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( System.currentTimeMillis());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(month <=9)
			result=year+"0"+month+""+day;
		else
			result=year+""+month+""+day;
		return result;
	}
}
