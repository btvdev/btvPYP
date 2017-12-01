package com.btvpyp.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量配置类
 * @author gaobo 2017-03-20
 *
 */
public class Commons {
	
	//DES密钥字符串，系统约定值
	public static final String DESKEY1 = "937660";
	public static final String DESKEY2 = "157471";
	public static final String DESKEY3 = "393572";
	
	//服务器IP地址及域名地址
	public static final String DOMAINSTR = "http://172.28.48.2/";
	public static final String DOMAINADDR = "http://jianbo.btv.com.cn/";
	public static final String PUSHADDR = "http://172.28.48.3:23390/digitalpixel/addsample?appkey=452DFCDAFEEA4AB8ACBF4A7D037424DE";
	
	/*样本推送北大接口返回值**********/
	public static final String PUSHCODESUCCESS = "000000";//提取中
	public static final String PUSHCODEFAIL = "000001";//提取失败
	
	/*操作日志KEY值*/
	public static final String ADDSAMPLE = "新增样本";
	public static final String DELSAMPLE = "删除样本";
	public static final String UPDSAMPLE = "修改样本";
	public static final String DELMATCH = "删除匹配流水单";
	public static final String UPDMATCH = "修改流水单";
	public static final String REPUSH = "样本重新推送";
	public static final String GDBFD = "归档播返单";
	public static final String ADDUSER = "新增用户";
	public static final String DELUSER = "删除用户";
	/*操作日志KEY值*/
	
	/*session存储变量*/
	public static final String SESSIONLOGDATE = "logDate";  //session存储变量：流水单全局日期
	public static final String SESSIONPID = "pid";          //session存储变量：频道号
	/*session存储变量*/
	
	/*接口日志文件存放地址*/
	public static final String PUSHLOGDIR = "E:/interfaceLog/pushResult/";  //推送数据对方接口反馈记录日志文件
	public static final String MATCHLOGDIR = "E:/interfaceLog/matchData/";  //匹配记录日志文件
	public static final String PUSHDATADIR = "E:/interfaceLog/pushData/";   //推送数据记录日志文件
	/*接口日志文件存放地址*/
	
	/*样本视频文件存放地址*/
	public static final String VIDEOSAVE = "E:/videos/";   //样本上传视频文件保存根路径
	/*样本视频文件存放地址*/
	
	//播返单Excel文件存放的根目录
	public static final String BFDDIR = "F:/";
	
	
}
