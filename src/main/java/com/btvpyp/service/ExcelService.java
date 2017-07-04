package com.btvpyp.service;

import javax.servlet.ServletOutputStream;


public interface ExcelService {
	public void exportSampleMatch(String pid, String start,	String end, String createrName,String mainName,String sampleId,ServletOutputStream outputStream);
	public void exportSample(String isAdv, String largeType,String middleType,String tinyType,String createrName,String mainName);
}
