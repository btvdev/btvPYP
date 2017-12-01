package com.btvpyp.service.impl;

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

import com.btvpyp.dao.TabSampleDao;
import com.btvpyp.dao.TabSampleMatchDao;
import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabSampleMatch;
import com.btvpyp.service.ExcelService;
import com.btvpyp.utils.ExportUtil;
@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	public TabSampleMatchDao tabSampleMatchDao;
	
	@Autowired
	public TabSampleDao tabSampleDao;
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
		
		//(如果播出位置，备注这2项可以空，那么就不需要联表查询)
//		if(null != tList && tList.size()>0){
//			for(int i=0;i<tList.size();i++){
//				String sid = tList.get(i).getSampleId();
//				TabSample ts = tabSampleDao.selectObjById(sid);
//				if(null != ts){
//					tList.get(i).setTabSample(ts);
//				}
//			}
//		}
		
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
        String[] titles = {"节目代码","开始时间","结束时间","集数","长度","位置","备注","日期","频道","主名称","副名称"
        				  ,"磁带号","栏目类别","广告ID","录入人","录入日期"};
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
				 
//				 cell = bodyRow.createCell(0);  
//	             cell.setCellStyle(bodyStyle);
//	             cell.setCellValue(tsm.getTabSample().getFlag());
	             
	             cell = bodyRow.createCell(0);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue(tsm.getSampleId());//节目代码
	             
	             cell = bodyRow.createCell(1);  
	             cell.setCellStyle(bodyStyle);
	             String timeS = tsm.getStartTime();
	             SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	             Timestamp ts = new Timestamp(Long.parseLong(timeS));        
	             String startTime = sdf.format(ts);
	             cell.setCellValue(startTime);//开始时间
	             
	             cell = bodyRow.createCell(2);  
	             cell.setCellStyle(bodyStyle);
	             String timeE = tsm.getEndTime();
	             Timestamp te = new Timestamp(Long.parseLong("0" + timeE));      
	             String endTime = sdf.format(te);
	             cell.setCellValue("0" + endTime);//结束时间
	             
	             cell = bodyRow.createCell(3);  
	             cell.setCellStyle(bodyStyle);
	             Integer js = tsm.getJiNum();
	             if(null == js){
	            	 cell.setCellValue("");//集数
	             }else{
	            	 cell.setCellValue(js);	 
	             }
	             
	             
	             cell = bodyRow.createCell(4);  
	             cell.setCellStyle(bodyStyle);
	             Integer cd = tsm.getLength();
	             if(null == cd){
	            	 cell.setCellValue("");//长度
	             }else{
	            	 cell.setCellValue(cd);//长度
	             }
	             
	             cell = bodyRow.createCell(5);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue("");//位置暂时置空
	             
	             cell = bodyRow.createCell(6);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue("");//备注暂时置空
	             
	             cell = bodyRow.createCell(7);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue(tsm.getSampleDate());//日期
	             
	             cell = bodyRow.createCell(8);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue(tsm.getPid());//频道
	             
	             cell = bodyRow.createCell(9);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue(tsm.getMainName());//主名称
	             
	             cell = bodyRow.createCell(10);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue(tsm.getSecondName());//副名称
	             
	             cell = bodyRow.createCell(11);  
	             cell.setCellStyle(bodyStyle);
	             String cdh = tsm.getTapeNum();
	             if(null == cdh){
	            	 cell.setCellValue("");//磁带号
	             }else{
	            	 cell.setCellValue(cdh);//磁带号
	             }
	             
	             
	             cell = bodyRow.createCell(12);  
	             cell.setCellStyle(bodyStyle);
	             String lb = tsm.getColumnType();
	             if(lb.equals("adv")){
	            	 cell.setCellValue("广告");//栏目类别
	             }else if(lb.equals("jm")){
	            	 cell.setCellValue("节目");//栏目类别
	             }
	             
	             cell = bodyRow.createCell(13);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue("");//广告ID
	             
	             cell = bodyRow.createCell(14);  
	             cell.setCellStyle(bodyStyle);
	             cell.setCellValue(tsm.getCreaterName());//录入人
	             
	             cell = bodyRow.createCell(15);  
	             cell.setCellStyle(bodyStyle);
	             String time = tsm.getCreateTime().toString();
	             cell.setCellValue(time.substring(0, time.length()-2));//录入时间
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
