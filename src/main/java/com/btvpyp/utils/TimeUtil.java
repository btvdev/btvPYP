package com.btvpyp.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	//常用于文件夹创建的日期离散
	public static String getTimeDir(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dir = sdf.format(new Date());
		return dir;
	}
	
	//获取当前时间的标准格式
	public static String getCurrentTime(){
		SimpleDateFormat stime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = stime.format(new Date());
		return time;
	}
	
	public static String getMMdd(){
		SimpleDateFormat stime = new SimpleDateFormat("MMdd");
		String time = stime.format(new Date());
		return time;
	}
	
	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static String getLastOneDay(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long timestamp = System.currentTimeMillis();
		long preday = timestamp - 1000*60*60*24;
		return sdf.format(preday);
	}
	
	/**
	 * 
	 * @param curr 当时的日期，如2017-06-29
	 * @return
	 */
	public static String getAfterOneDay(String curr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long ts = null;
		try {
			ts = sdf.parse(curr).getTime();
		}catch (ParseException e) {
		    e.printStackTrace();
		}
		ts += 1000*60*60*24;
		
		return sdf.format(ts);
	}
	
	/**
	 * 将日期字符串按照格式转化成时间戳
	 * @param dateStr  日期字符串
	 * @param format   格式
	 * @return
	 */
	public static Long stringToTimestamp(String dateStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Long ts = null;
		try {
			ts = sdf.parse(dateStr).getTime();
		}catch (ParseException e) {
		    e.printStackTrace();
		}
		return ts;
	}
	/**
	 * 将时间戳转换成字符串
	 * @param ts
	 * @return
	 */
	public static String timestampToString(Timestamp ts){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "";
		time = sdf.format(ts);
		return time;
	}
	
	/**
	 * 根据字符串计算出秒数
	 * 比如：00:20:00 得出结果：1200
	 * @return
	 */
	public static int getSeconds(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdf.format(new Date());
		String today1 = today + " " + time;
		String today2 = today + " " + "00:00:00";
		try {
			long stamp1 = sdf1.parse(today1).getTime();
			long stamp2 = sdf1.parse(today2).getTime();
			long cha = stamp1 - stamp2;
			
			return (int)cha / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static void main(String args[]){
		
	}
	
}
