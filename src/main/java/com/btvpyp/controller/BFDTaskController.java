package com.btvpyp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.btvpyp.model.TabBroadBack;
import com.btvpyp.service.TabBroadBackService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.ImportExcelUtil;
import com.btvpyp.utils.TimeUtil;
/**
 * 播返单定时任务类
 * @author gaobo
 *
 */
public class BFDTaskController {
	
	@Autowired
	private TabBroadBackService tabBroadBackService;
	
	/**
	 * 从目录里的Excel定时获取播返单数据
	 * 目前执行时间为每天早上8点
	 */
	@Scheduled(cron = "0 0 8 * * ?")
	public void getBroadBack(){
		System.out.println("-----------------------------------");
		String filePath = Commons.BFDDIR + TimeUtil.getMMdd(); //当天播返单存放的目录
		String preDay = TimeUtil.getLastOneDay();
		String pid = "";
		/*遍历当天播返单目录下的文件*/
		File root = new File(filePath);
		File[] files = root.listFiles();
		
		for(File file:files){ //对各个文件进行频道匹配，读取内容并入库
			String fileAllName = file.getAbsolutePath();
			System.out.println("读取文件：" + fileAllName);
			if(fileAllName.contains("播返单")){
				pid = matchPid(fileAllName);//频道匹配
				
				/*读取Excel*/
				InputStream in =null;
				List<List<Object>> listob = null;
				try {
					in = new FileInputStream(file);
					listob = new ImportExcelUtil().getDataByExcel(in,fileAllName);
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(null != listob){
					if(listob.size()>0){
						//解析listob，填装对象字段值
						for (int i = 0; i < listob.size(); i++) {
							List<Object> lo = listob.get(i);
							TabBroadBack t = setObjValue(lo, pid, preDay);
							tabBroadBackService.insertTabBroadBack(t);
						}
					}
				}	
			}
		}
		/*遍历当天播返单目录下的文件*/
	}
	
	public TabBroadBack setObjValue(List<Object> lo, String pid, String preDay){
		TabBroadBack tb = new TabBroadBack();
		tb.setChannel(pid);
		tb.setJmName(String.valueOf(lo.get(12)));
		
		/* 计算出开始时间及真实时间戳，但是
		 * 由于第二天6点以前的节目播返单也在这里
		 * 而且第二天6点 = 今天30点，所以做一下特殊处理
		 */
		String start = String.valueOf(lo.get(9)); //从单元格读出，例如29:56:00.00
		start = start.substring(0, preDay.length()-2);//去掉秒数后面的小数点
		tb.setStarttime(start); 
		
		//拆分时分秒，并判断是否存在24点之后的时间数据
		String[] array = start.split(":");
		int hour = Integer.parseInt(array[0]);
		String realHour = "" + hour;
		if(hour > 24){ //如果有24点之后的小时数，则与24做差，同时preDay加一天
			hour = hour - 24;
			//日期上加一天
			preDay = TimeUtil.getAfterOneDay(preDay);
		}
		
		if(hour < 10){
			realHour = "0" + hour;
		}
		String minute = array[1];
		String second = array[2];
		String realHms = realHour + ":" + minute + ":" + second;//比如：28:40:20，则转化成04:40:20
		
		String realDay = preDay + " " + realHms; //得出真实时间的字符串
		tb.setStarttime(realDay);
		Long startStamp = TimeUtil.stringToTimestamp(realDay, "yyyy-MM-dd HH:mm:ss"); //得出真实时间的时间戳
		
		/*计算时长*/
		String len = String.valueOf(lo.get(13));
		len = len.substring(0, len.length()-3); //去掉小数点及后面的两位数
		int length = TimeUtil.getSeconds(len);
		tb.setLength(length);
		
		Long endstartStamp = startStamp + length*1000;//计算出结束时间的时间戳
		String realEnd = TimeUtil.timestampToString(new Timestamp(endstartStamp));
		tb.setEndtime(realEnd);
		
		tb.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		return tb;
	}
	
	//根据播返单文件名的包含信息匹配频道ID
	public String matchPid(String fileAllName){
		String pid = "";
		if(fileAllName.contains("卫视")){
			pid = "BTV1";
		}
		if(fileAllName.contains("文艺")){
			pid = "BTV2";
		}
		if(fileAllName.contains("科教")){
			pid = "BTV3";
		}
		if(fileAllName.contains("null") || fileAllName.contains("影视")){
			pid = "BTV4";
		}
		if(fileAllName.contains("财经")){
			pid = "BTV5";
		}
		if(fileAllName.contains("体育")){
			pid = "BTV6";
		}
		if(fileAllName.contains("生活")){
			pid = "BTV7";
		}
		if(fileAllName.contains("青年")){
			pid = "BTV8";
		}
		if(fileAllName.contains("新闻")){
			pid = "BTV9";
		}
		if(fileAllName.contains("少儿")){
			pid = "BTV10";
		}
		if(fileAllName.contains("纪实")){
			pid = "BTV11";
		}
		return pid;
	}
	
}
