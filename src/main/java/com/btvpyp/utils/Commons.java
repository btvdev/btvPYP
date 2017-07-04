package com.btvpyp.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量配置类
 * @author gaobo 2017-03-20
 *
 */
public class Commons {
	
	//服务器IP地址及域名地址
	public static String DOMAINSTR = "http://172.20.1.147/";
	public static String DOMAINADDR = "http://jianbo.btv.com.cn/";
	public static String PUSHADDR = "http://172.20.1.148:23390/digitalpixel/addsample?appkey=452DFCDAFEEA4AB8ACBF4A7D037424DE";
	
	/*样本推送北大接口返回值**********/
	public static String PUSHCODESUCCESS = "000000";//提取中
	public static String PUSHCODEFAIL = "000001";//提取失败
	
	/*操作日志KEY值*/
	public static String ADDSAMPLE = "新增样本";
	public static String DELSAMPLE = "删除样本";
	public static String UPDSAMPLE = "修改样本";
	public static String DELMATCH = "删除匹配流水单";
	public static String REPUSH = "样本重新推送";
	/*操作日志KEY值*/
	
	/*session存储变量*/
	public static String SESSIONLOGDATE = "logDate";  //session存储变量：流水单全局日期
	public static String SESSIONPID = "pid";          //session存储变量：频道号
	/*session存储变量*/
	
	/*接口日志文件存放地址*/
	public static String PUSHLOGDIR = "D:/interfaceLog/pushResult/";  //推送数据对方接口反馈记录日志文件
	public static String MATCHLOGDIR = "D:/interfaceLog/matchData/";  //匹配记录日志文件
	public static String PUSHDATADIR = "D:/interfaceLog/pushData/";   //推送数据记录日志文件
	/*接口日志文件存放地址*/
	
	/*样本视频文件存放地址*/
	public static String VIDEOSAVE = "E:/videos/";   //样本上传视频文件保存根路径
	/*样本视频文件存放地址*/
	
	public static String BFDDIR = "F:/";
	
	
}
