package com.btvpyp.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * @author gaobo
 * 2017-08-23
 */
public class UploadUtils {
	
	public static Map<String, String> uploadSampleFile(MultipartFile advFile, String filePrefix){
		
		String uploadResult = "UploadSuccess";
		String filename = "";
		String fileAddr = "";
		String fileNetAddr = "";
		Map<String, String> map = new HashMap<String, String>();
		try {
			String realPath = Commons.VIDEOSAVE;//保存视频文件的根目录
			String originalFilename = "";
		
			originalFilename = advFile.getOriginalFilename();//原文件名
			if(advFile.getSize()>(1024*1024*2000)){//上传文件大小不能超过2GB
				uploadResult = "FileIsTooLarge";
			}
			
			//用日期离散生成存放目录
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	
			String dateDir = sdf.format(new Date())+"/";
			realPath += dateDir;
			
			int startIndex = originalFilename.lastIndexOf(".");  //获取 最后一个 . 在字符串中的位置
//			filename = "ADV_"+System.currentTimeMillis()+originalFilename.substring(startIndex);
			filename = filePrefix + originalFilename.substring(startIndex);
			fileAddr = realPath+filename;//文件在共享目录的存储路径
			fileNetAddr = Commons.DOMAINADDR + dateDir + filename;//生成文件网络链接地址
			//将上传的文件复制到目录路径下
			FileUtils.copyInputStreamToFile(advFile.getInputStream(), new File(realPath, filename));
		} catch (IOException e) {
			e.printStackTrace();
			uploadResult = "文件上传失败，请联系管理员";
		}
		map.put("uplaodResult", uploadResult);
		map.put("fileAddr", fileAddr);
		map.put("fileNetAddr", fileNetAddr);
		return map;
	}
}
