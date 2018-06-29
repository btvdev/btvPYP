package com.btvpyp.excel.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.detail.dao.TabDetailDao;
import com.btvpyp.detail.model.TabDetail;
import com.btvpyp.excel.service.ExcelService;
import com.btvpyp.sample.dao.TabSampleDao;
import com.btvpyp.sample.model.TabSample;
import com.btvpyp.sampleMatch.dao.TabSampleMatchDao;
import com.btvpyp.sampleMatch.model.TabSampleMatch;
import com.btvpyp.utils.ExportUtil;
import com.btvpyp.utils.TimeUtil;
@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	public TabSampleMatchDao tabSampleMatchDao;
	
	@Autowired
	public TabSampleDao tabSampleDao;
	
	@Autowired
	public TabDetailDao tabDetailDao;
	/**
	 * 根据查询条件，导出广告+节目详单
	 */
	@Override
	public void exportDetail(String channel, String start, String end, ServletOutputStream outputStream) {
		TabDetail tabDetail = new TabDetail();
		if(null != channel && !"".equals(channel)) {
			tabDetail.setChannel(channel);
		}
		if(null != start && !"".equals(start)) {
			Long startLong = TimeUtil.stringToTimestamp(start, "yyyy-MM-dd HH:mm:ss");
			tabDetail.setStart(startLong.toString());
		}
		if(null != end && !"".equals(end)) {
			Long endLong = TimeUtil.stringToTimestamp(end, "yyyy-MM-dd HH:mm:ss");
			tabDetail.setEnd(endLong.toString());
		}
		List<TabDetail> tabDetailList = tabDetailDao.selectTabDetails(tabDetail);
		
		// 创建一个workbook 对应一个excel应用文件  
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = workBook.createSheet("详单");
        
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
        // 构建表头  
        XSSFRow headRow = sheet.createRow(0);  
        XSSFCell cell = null;
        String[] titles = {"节目名称","开始时间","结束时间","时长(秒)","频道"};
        for(int i = 0; i < titles.length; i++){
        	cell = headRow.createCell(i);  
            cell.setCellStyle(headStyle);  
            cell.setCellValue(titles[i]);
        }
     // 构建表体数据  
     	if(null != tabDetailList && tabDetailList.size()>0){
     		for(int j=0;j<tabDetailList.size();j++){
     			XSSFRow bodyRow = sheet.createRow(j + 1);
     			TabDetail t = tabDetailList.get(j);
     			
     			cell = bodyRow.createCell(0);  
	            cell.setCellStyle(bodyStyle);
	            cell.setCellValue(t.getProgramName());//节目名称
	            
	            cell = bodyRow.createCell(1);  
	            cell.setCellStyle(bodyStyle);
	            String startTime = TimeUtil.timestampToString(new Timestamp(Long.parseLong(t.getStartTime())));
	            cell.setCellValue(startTime);//开始时间
	            
	            cell = bodyRow.createCell(2);  
	            cell.setCellStyle(bodyStyle);
	            String endTime = TimeUtil.timestampToString(new Timestamp(Long.parseLong(t.getEndTime())));
	            cell.setCellValue(endTime);//结束时间
	            
	            cell = bodyRow.createCell(3);  
	            cell.setCellStyle(bodyStyle);
	            cell.setCellValue(t.getLength());//时长
	            
	            cell = bodyRow.createCell(4);  
	            cell.setCellStyle(bodyStyle);
	            cell.setCellValue(t.getChannel());//频道
	            
     		}
     	}
     	sheet.autoSizeColumn((short)0);
        sheet.autoSizeColumn((short)1);
        sheet.autoSizeColumn((short)2);
        sheet.autoSizeColumn((short)3);
        sheet.autoSizeColumn((short)4);
     	try {
			workBook.write(outputStream); 
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
            try  
            {  
                outputStream.close();  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        } 
	}
	/**
	 * 根据查询条件，将样本匹配流水单导出到Excel
	 * 
	 */
	@Override
	public void exportSampleMatch(String pid, String start, String end,
			String createrName,String mainName,String sampleId,ServletOutputStream outputStream) {
		TabSampleMatch tabSampleMatch = new TabSampleMatch();
		if(null != pid && !"".equals(pid)){
			tabSampleMatch.setPid(pid);
		}
		if(null != start && !"".equals(start)){
			tabSampleMatch.setStart(start);
		}
		if(null != end && !"".equals(end)){
			tabSampleMatch.setEnd(end);
		}
		if(null != createrName && !"".equals(createrName)){
			tabSampleMatch.setCreaterName(createrName);
		}
		if(null != mainName && !"".equals(mainName)){
			tabSampleMatch.setMainName(mainName);
		}
		if(null != sampleId && !"".equals(sampleId)){
			tabSampleMatch.setSampleId(sampleId);
		}
		List<TabSampleMatch> tList = tabSampleMatchDao.selectTabSampleMatchsExcel(tabSampleMatch);
		
		// 创建一个workbook 对应一个excel应用文件  
        XSSFWorkbook workBook = new XSSFWorkbook();  
        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = workBook.createSheet("样本匹配流水单");  
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
        // 构建表头  
        XSSFRow headRow = sheet.createRow(0);  
        XSSFCell cell = null;
        //表头
        /*String[] titles = {"节目代码","开始时间","结束时间","集数","长度","位置","备注","日期","频道","主名称","副名称"
        				  ,"磁带号","栏目类别","广告ID","录入人","录入日期"};*/
        String[] titles = {"品牌号","开始时间","集数","结束时间","时长","播出日期","频道","主名称","副名称","录入人"};
        for(int i = 0; i < titles.length; i++){
        	 cell = headRow.createCell(i);  
             cell.setCellStyle(headStyle);  
             cell.setCellValue(titles[i]);
        }
        // 构建表体数据  
		if(null != tList && tList.size()>0){
			for(int j=0;j<tList.size();j++){
				 XSSFRow bodyRow = sheet.createRow(j + 1);
				 TabSampleMatch tsm = tList.get(j);
				 /*
				 //广告部ID
	             cell = bodyRow.createCell(0);  
	             cell.setCellStyle(bodyStyle);
	             String advId = tsm.getAdvId();
	             if(null == advId) {
	            	 cell.setCellValue("");
	             }else {
	            	 cell.setCellValue(tsm.getAdvId());
	             }
	             */
	             //品牌号
	             cell = bodyRow.createCell(0);  
	             cell.setCellStyle(bodyStyle);
	             Integer brandId = tsm.getBrandId();
	             if(null == brandId) {
	            	 cell.setCellValue("");
	             }else {
	            	 cell.setCellValue(tsm.getBrandId());
	             }
	             //开始时间
	             cell = bodyRow.createCell(1);  
	             cell.setCellStyle(bodyStyle);
	             String timeS = tsm.getStartTime();
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             Timestamp ts = new Timestamp(Long.parseLong(timeS));      
	             String startTime = sdf.format(ts);
	             String[] timeSplitStart = startTime.split(" ");
	             cell.setCellValue(timeSplitStart[1]);
	             //集数
	             cell = bodyRow.createCell(2);  
	             cell.setCellStyle(bodyStyle);
	             Integer js = tsm.getJiNum();
	             if(null == js){
	            	 cell.setCellValue("0");
	             }else{
	            	 cell.setCellValue(js);
	             }
	             //结束时间
	             cell = bodyRow.createCell(3);  
	             cell.setCellStyle(bodyStyle);
	             String timeE = tsm.getEndTime();
	             Timestamp te = new Timestamp(Long.parseLong(timeE));      
	             String endTime = sdf.format(te);
	             String[] timeSplitEnd = endTime.split(" ");
	             cell.setCellValue(timeSplitEnd[1]);
	             
	             
	             
	             //时长
	             cell = bodyRow.createCell(4);  
	             cell.setCellStyle(bodyStyle);
	             Integer cd = tsm.getLength();
	             if(null == cd){
	            	 cell.setCellValue("0");
	             }else{
	            	 cell.setCellValue(cd);
	             }
	             //日期
	             cell = bodyRow.createCell(5);  
	             cell.setCellStyle(bodyStyle);
	             String dateSplit = timeSplitStart[0].replaceAll("-", "");
	             cell.setCellValue(dateSplit);
	             //频道
	             cell = bodyRow.createCell(6);  
	             cell.setCellStyle(bodyStyle);
	             String pid1 = tsm.getPid();
	             if(null == pid1) {
	            	 cell.setCellValue("");
	             }else {
	            	 cell.setCellValue(pid1);
	             }
	             //主名称
	             cell = bodyRow.createCell(7);  
	             cell.setCellStyle(bodyStyle);
	             String mainName1 = tsm.getMainName();
	             if(null == mainName1) {
	            	 cell.setCellValue("");
	             }else {
	            	 cell.setCellValue(mainName1);
	             }
	             //副名称
	             cell = bodyRow.createCell(8);  
	             cell.setCellStyle(bodyStyle);
	             String secondName1 = tsm.getSecondName();
	             if(null == secondName1){
	            	 cell.setCellValue("");
	             }else {
	            	 cell.setCellValue(secondName1);
	             }
	             //录入人
	             cell = bodyRow.createCell(9);  
	             cell.setCellStyle(bodyStyle);
	             String createrName1 = tsm.getCreaterName();
	             if(null == createrName1) {
	            	 cell.setCellValue("");
	             }else {
	            	 cell.setCellValue(createrName1);
	             }
			}
		}
		try {
			workBook.write(outputStream); 
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
            try  
            {  
                outputStream.close();  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        } 
	}

	@Override
	public void exportSample(String isAdv, String largeType, String middleType,
			String tinyType, String createrName, String mainName) {
		TabSample tabSample = new TabSample();
		if(null != isAdv && !"".equals(isAdv)){
			tabSample.setIsAdv(Integer.parseInt(isAdv));
		}
		if(null != largeType && !"".equals(largeType)){
			tabSample.setLargeType(largeType);
		}
		if(null != middleType && !"".equals(middleType)){
			tabSample.setMiddleType(middleType);
		}
		if(null != tinyType && !"".equals(tinyType)){
			tabSample.setTinyType(tinyType);
		}
		if(null != createrName && !"".equals(createrName)){
			tabSample.setCreaterName(createrName);
		}
		if(null != mainName && !"".equals(mainName)){
			tabSample.setMainName(mainName);
		}
		
		List<TabSample> tList = tabSampleDao.selectTabSamples(tabSample);
		
		// 创建一个workbook 对应一个excel应用文件  
        XSSFWorkbook workBook = new XSSFWorkbook();  
        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = workBook.createSheet("样本库导出");  
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
        // 构建表头  
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
		
        String[] titles = {};
	}
}
