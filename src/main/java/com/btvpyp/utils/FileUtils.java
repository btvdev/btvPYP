package com.btvpyp.utils;

import java.io.File;
import java.io.IOException;
/**
 * 文件工具类
 * @author gaobo 2017-06-16
 *
 */
public class FileUtils {
	/**
	 * 遍历文件夹下面所有的文件名
	 * @param filePath
	 */
	public static void findFiles(String filePath){
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				findFiles(file.getAbsolutePath());
				System.out.println(file.getAbsolutePath());
			}else{
			      System.out.println(file.getAbsolutePath());
		    }
		}
	}
	
	/**
	 * 修改文件名(同目录下)
	 * @param oldFilePath 旧文件的绝对路径，如：E:/1.txt
	 * @param newFileName 新文件名，如：2.txt
	 * @return 修改后新文件的绝对路径，如：E:/2.txt
	 */
	public static String modifyFileName(String oldFilePath, String newFileName){
		File oldFile = new File(oldFilePath);
		if(!oldFile.exists()) {
		   try {
			   oldFile.createNewFile();
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
		}
//		System.out.println("修改前文件名称是："+oldFile.getName());
		String rootPath = oldFile.getParent();
//		System.out.println("根路径是："+rootPath);
		File newFile = new File(rootPath + File.separator + newFileName);
//		System.out.println("修改后文件名称是："+newFile.getName());
		if (oldFile.renameTo(newFile)) {
//		    System.out.println("修改成功!");
		} else {
//		    System.out.println("修改失败");
		}
		return (rootPath + File.separator + newFileName);
	}
	
	public static void main(String args[]){
		findFiles("D:/0628");
//		modifyFileName("E:/1.txt","2.txt");
	}
}
