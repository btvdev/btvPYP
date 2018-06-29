package com.btvpyp.utils;

import java.util.HashMap;
import java.util.Map;

public class LibraryUtils {
	
	public final static Map<String, String> channelMap = new HashMap<String, String>();
	
	static {
		channelMap.put("BTV1", "06");   //卫视
		channelMap.put("BTV2", "21");   //文艺
		channelMap.put("BTV3", "27");   //科教
		channelMap.put("BTV4", "17");   //影视
		channelMap.put("BTV5", "24");   //财经
		channelMap.put("BTV6", "19");   //体育
		channelMap.put("BTV7", "25");   //生活
		channelMap.put("BTV8", "75");   //青年
		channelMap.put("BTV9", "81");   //新闻
		channelMap.put("BTV10", "86");  //卡酷
		channelMap.put("BTV11", "20");  //纪实
	}
	/**
	 * 将监播系统频道号与特雷森旧系统进行映射
	 * @param pid 监播系统频道号
	 * @return
	 */
	public static String channelMap(String pid) {
		String tlsNo = "";
		tlsNo = channelMap.get(pid);
		return tlsNo;
	}
}
