package com.btvpyp.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		for(File file:files) {
			if(file.isDirectory()) {
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
	/**
	 * 获取文件名(不包含父级文件夹名字)
	 * @param path
	 */
	private static void getFileName(String path){   
        // 获得指定文件对象  
        File file = new File(path);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();   
        
        for(int i=0;i<array.length;i++) {   
            if(array[i].isFile()) {//如果是文件    
                System.out.println(array[i].getName());
            }
            if(array[i].isDirectory()) {
            	System.out.println(array[i].getName());
            }
        } 
    }
	
	/**
	 * 剪切文件
	 * @param source源文件(带文件名)
	 * @param destiny目标文件
	 * @throws IOException
	 */
	public static void cutFile(String source, String destiny){
		File sourceFile = new File(source);
		File destinyFile = new File(destiny);
        FileOutputStream fileOutputStream = null;  
        InputStream inputStream = null;  
        byte[] bytes = new byte[1024];  
        int temp = 0;  
        try {  
            inputStream = new FileInputStream(sourceFile);
            if(!destinyFile.exists()) {
            	destinyFile.getParentFile().mkdir();
                try {
                    //创建文件
                	destinyFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fileOutputStream = new FileOutputStream(destinyFile);  
            
            while((temp = inputStream.read(bytes)) != -1){  
                fileOutputStream.write(bytes, 0, temp);  
                fileOutputStream.flush();  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }catch (IOException e) {  
            e.printStackTrace();  
        }finally{ 
            if (inputStream != null) {  
                try {  
                    inputStream.close();
                    sourceFile.delete();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fileOutputStream != null) {  
                try {  
                    fileOutputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
          
    } 
	/**
	 * 获取视频文件的时长
	 * @param filePath
	 */
	public static String getMp4FileLength(String filePath) {
		String length = "";
		File source = new File(filePath);  
        Encoder encoder = new Encoder();  
        try {  
             MultimediaInfo m = encoder.getInfo(source);  
             long ls = m.getDuration();  
             length = MathUtil.divisionRound(ls, 1000d, "%.0f");
//             System.out.println(ls/1000+"秒!");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return length;
	}
	
	public static void main(String args[]){
		getFileName("E:/videos/upload/");
//		modifyFileName("E:/1.txt","2.txt");
	}
}
