package com.btvpyp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class LogUtil {
	
	public static void saveSampleJson(String jsonStr, String saveUrl, String fileName){
		File saveFile = new File(new File(saveUrl),fileName);
		if (!saveFile.getParentFile().exists()){
			saveFile.getParentFile().mkdirs();
		}
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(saveUrl + fileName),"UTF-8");
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
