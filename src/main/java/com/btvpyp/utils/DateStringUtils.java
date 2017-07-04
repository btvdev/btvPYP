package com.btvpyp.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author gaobo 2016-02-14
 * 
 * JDK版本：1.6
 * Java Data与String相互转化的工具类
 */
public class DateStringUtils {
	/**
	 * 获取当前时间戳并转成String格式
	 * @return
	 */
	public String getNowtimestamp(){
		Long time = System.currentTimeMillis();
		return time.toString();
	}
	
	/**
	 * 根据当前时间获取yyyyMMdd格式的字符串，通常用于做目录存储时间离散
	 * @return
	 */
	public static String getNowYmd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(new Date());
		return time;
	}
	
	/**
	 * 将特定格式的字符串转化成时间戳
	 * @param dateStr 如：2016-01-01
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
	
}
