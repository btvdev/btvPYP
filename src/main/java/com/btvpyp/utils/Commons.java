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
	public static final String DOMAINADDR = "http://jianbo.btv.com.cn/";
	
	//(当虹)
	public static final String FINGERPUSHADDR = "http://10.8.12.133:7702/api/fingerprint/v2/register/";    //指纹推送接口地址
	public static final String FINGERDELADDR = "http://10.8.12.133:7702/api/fingerprint/v2/delete";        //指纹删除接口地址
	public static final String TRIMCONTENTADDR = "http://10.8.12.133:7702/api/fingerprint/v2/trimcontent"; //自动碎片化接口地址
	
	public static final String UPLOADDIR = "Z:/videos/upload/";  //样本上载目录
	public static final String DEFAULTAGENT = "北京台";
	
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
	
	/*session存储变量*/
	public static final String SESSIONLOGDATE = "logDate";  //session存储变量：流水单全局日期
	public static final String SESSIONPID = "pid";          //session存储变量：频道号
	public static final String SESSIONREALNAME = "sessionRealName";
	
	/*接口日志文件存放地址*/
	public static final String MATCHLOGDIR = "E:/interfaceLog/matchData/";  //匹配记录日志文件
	public static final String PUSHDATADIR = "E:/interfaceLog/pushData/";   //推送数据记录日志文件
	public static final String PIECELOGDIR = "E:/interfaceLog/pieceLog/";  //匹配记录日志文件
	public static final String PIECEREQUESTLOGDIR = "E:/interfaceLog/pieceRequestLog/";  //匹配记录日志文件
	
	/*样本视频文件存放地址*/
	public static final String VIDEOSAVE = "Z:/videos/";   //样本上传视频文件保存根路径
	public static final String DHSAVE = "/mnt/data/remote/fingersharefolder/videos/";  //当虹对应的linux存储地址
	
	//播返单Excel文件存放的根目录
	public static final String BFDDIR = "F:/";
	/*与特雷森旧业务系统通信约定字符串 -start*/
	public static final String REQUESTURL_ADD = "http://10.1.121.105/WebAdvProgName/Service.asmx?op=addProgMsg";
	public static final String REQUESTURL_UPDATE = "http://10.1.121.105/WebAdvProgName/Service.asmx?op=updProgMsg";
	public static final String REQUESTURL_DELETE = "http://10.1.121.105/WebAdvProgName/Service.asmx?op=delProgMsg";
	/*与特雷森旧业务系统通信约定字符串 -end*/
}
