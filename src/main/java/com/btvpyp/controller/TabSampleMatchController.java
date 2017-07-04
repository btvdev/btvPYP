package com.btvpyp.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.btvpyp.model.TabAdvTypes;
import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabSampleMatch;
import com.btvpyp.model.TabUser;
import com.btvpyp.service.ExcelService;
import com.btvpyp.service.TabAdvTypesService;
import com.btvpyp.service.TabOperLogService;
import com.btvpyp.service.TabSampleMatchService;
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.DateStringUtils;
import com.btvpyp.utils.Page;

@Controller
@RequestMapping("/tabSampleMatch")
public class TabSampleMatchController {
	
	@Autowired
	public TabSampleMatchService tabSampleMatchService;
	
	@Autowired
	public TabSampleService tabSampleService;
	
	@Autowired
	public TabAdvTypesService tabAdvTypesService;
	
	@Autowired 
	public ExcelService excelService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	public TabOperLogService tabOperLogService;
	
	private static final String domainStr = "http://223.202.199.132/";
	
	@RequestMapping(value="/addSampleMatch",method=RequestMethod.GET)
	public String addSampleMatch(@RequestParam String lastEndTime, Model model){
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		List<TabAdvTypes> advTypesList = tabAdvTypesService.selectByLevel(3);
		model.addAttribute("advTypesList", advTypesList);
		model.addAttribute("lastEndTime", lastEndTime);
		model.addAttribute("pid", pid);
		return "pyp/addSampleMatch";
	}
	
	@RequestMapping(value="/addSampleMatch",method=RequestMethod.POST)
	public String addSampleMatch(@RequestParam String startTime,@RequestParam String endTime,
			@RequestParam String jiNum,@RequestParam String length,@RequestParam String location,@RequestParam String comments,
			@RequestParam String sampleDate,@RequestParam String pidhide,@RequestParam String mainName,@RequestParam String secondName,
			@RequestParam String tapeNum,@RequestParam String columnType,@RequestParam String advId,@RequestParam String company,
			@RequestParam String largeType,@RequestParam String middleType,@RequestParam String tinyType,
			@RequestParam String isExists,MultipartFile advFile,@RequestParam String isAdvhide,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		logDate = logDate.replaceAll("-", "");
		
		TabSampleMatch tsm = new TabSampleMatch();
		TabSample ts = new TabSample();
		
		ts.setFlag(1);
		ts.setJiNum(Integer.parseInt(jiNum));
		ts.setLength(Integer.parseInt(length));
		ts.setLocation(location);
		ts.setComments(comments);
		ts.setSampleDate(sampleDate);
		ts.setPid(pidhide);
		ts.setMainName(mainName);
		ts.setSecondName(secondName);
		ts.setTapeNum(tapeNum);
		ts.setColumnType(columnType);
		ts.setAdvId(advId);
		ts.setCreaterName(tu.getUserName());
		ts.setCreateTime(new Timestamp(System.currentTimeMillis()));
		ts.setLastModifier(tu.getUserName());
		ts.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
		ts.setCompany(company);
		
		/*保存文件 -- start*/
		String uploadResult = "上传成功";
		try {
			String realPath = Commons.VIDEOSAVE;//保存视频文件的根目录
			String originalFilename = null;
			
			if(advFile.isEmpty()){//如果没有上传文件，则默认为节目并非是广告
				ts.setIsAdv(0);
				tsm.setIsAdv(0);
				uploadResult = "请选择文件后上传";
			}else{
				originalFilename = advFile.getOriginalFilename();	//原文件名
				if(advFile.getSize()>(1024*1024*200)){
					uploadResult = "文件上传失败,文件最大不可超过200M";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");	//日期格式化
				String dateDir = sdf.format(new Date())+"/";
				realPath += dateDir;
				
				int startIndex = originalFilename.lastIndexOf(".");  //获取 最后一个 . 在字符串中的位置
				String filename = "ADV_"+System.currentTimeMillis()+originalFilename.substring(startIndex);	//截取文件的类型
				String fileAddr = realPath+filename;
				String fileNetAddr = Commons.DOMAINADDR + dateDir + filename;
				FileUtils.copyInputStreamToFile(advFile.getInputStream(), new File(realPath, filename));
				ts.setIsAdv(1);
				ts.setFileAddr(fileAddr);
				ts.setFileNetAddr(fileNetAddr);
				ts.setExtFlag(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
			uploadResult = "文件上传失败，请联系管理员";
		}
		/*保存文件 -- end*/
		
		ts.setLargeType(largeType);
		ts.setMiddleType(middleType);
		ts.setTinyType(tinyType);
		String sampleIdNew = tabSampleService.insertTabSample(ts);
		//将时间格式做一个转化	
		Long l1 = DateStringUtils.stringToTimestamp(logDate+startTime, "yyyyMMddHHmmss");
		Long l2 = DateStringUtils.stringToTimestamp(logDate+endTime.substring(1, endTime.length()-1), "yyyyMMddHHmmss");
		
		tsm.setSampleId(sampleIdNew);
		tsm.setPid(pid);
		tsm.setStartTime(l1.toString());
		tsm.setEndTime(l2.toString());
		tsm.setCreaterName(tu.getUserName());
		tsm.setCreateTime(new Timestamp(System.currentTimeMillis()));
		tsm.setMainName(mainName);
		tsm.setSecondName(secondName);
		tsm.setJiNum(Integer.parseInt(jiNum));
		tsm.setSampleDate(sampleDate);
		tsm.setTapeNum(tapeNum);
		tsm.setColumnType(columnType);
		
		tabSampleMatchService.insertTabSampleMatch(tsm);
		return "redirect:/tabSampleMatch/search";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String selectSampleMatchs(Model model){
		TabSampleMatch t = new TabSampleMatch();
		
		/* 查询列表的先决条件：用户登录时选定的日期，和频道号 */
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		Long todayTimestamp = DateStringUtils.stringToTimestamp(logDate, "yyyy-MM-dd");
		t.setPid(pid);
		t.setStart(todayTimestamp.toString());
		/* 查询列表的先决条件：用户登录时选定的日期，和频道号 */
		
		//暂时取消流水单分页
//		t.setPageSize(20);
//		Integer totalCount = tabSampleMatchService.getTotalCount(t);
//		Page page = new Page(1, t.getPageSize(), totalCount);
//		t.setStartIndex(page.getStartIndex());
		List<TabSampleMatch> sampleMatchList = tabSampleMatchService.selectTabSampleMatchs(t);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
		
		if(null != sampleMatchList && sampleMatchList.size()>0){
			for(int i=0;i<sampleMatchList.size();i++){
				String sid = sampleMatchList.get(i).getSampleId();
				if(i!=0){
					if(!sampleMatchList.get(i).getStartTime().equals(sampleMatchList.get(i-1).getEndTime())){
						sampleMatchList.get(i).setNeedRed(1);
						//记录上一条匹配记录的结束时间，用于页面逻辑
						String timeLe = sampleMatchList.get(i-1).getEndTime();
						Timestamp tp = new Timestamp(Long.parseLong(timeLe));
						sampleMatchList.get(i).setLastEndTime(sdf2.format(tp));
					}else{
						sampleMatchList.get(i).setNeedRed(0);
					}
				}else{
					sampleMatchList.get(i).setNeedRed(0);
				}
				
				
				String timeS = sampleMatchList.get(i).getStartTime();
	            Timestamp tp = new Timestamp(Long.parseLong(timeS));       
	            String startTime = sdf2.format(tp);
	            sampleMatchList.get(i).setStartTimeJsp(startTime);
	            
	            String timeE = sampleMatchList.get(i).getEndTime();
	            Timestamp tpe = new Timestamp(Long.parseLong(timeE));   
	            String endTime = sdf2.format(tpe);
	            sampleMatchList.get(i).setEndTimeJsp("0" + endTime);
	            
	            if(sampleMatchList.get(i).getLength() == null || sampleMatchList.get(i).getLength()==0){
	            	sampleMatchList.get(i).setLength(Integer.parseInt(endTime) - Integer.parseInt(startTime));
	            }
				
//				TabSample ts = tabSampleService.selectObjById(sid);
//				if(null != ts){
//					sampleMatchList.get(i).setTabSample(ts);
//				}
	            
	            
			}
		}
		
		model.addAttribute("sampleMatchList", sampleMatchList);
//		model.addAttribute("page", page);
		return "pyp/sampleMatchList";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String selectSampleMatchs(@RequestParam String pid, @RequestParam String start,
			@RequestParam String end, @RequestParam String createrName,@RequestParam String mainName,
			@RequestParam String sampleId, /*@RequestParam Integer currPage,*/ Model model){
		TabSampleMatch t = new TabSampleMatch();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
		if(null != pid && !"".equals(pid)){
			t.setPid(pid);
		} 
		if(null != start && !"".equals(start)){
			String startChange = "";
			try {
				Long ls = sdf.parse(start).getTime();
				ls = ls / 1000;
				startChange = ls.toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			t.setStart(startChange);
		}
		if(null != end && !"".equals(end)){
			String endChange = "";
			try {
				Long le = sdf.parse(end).getTime();
				le = le / 1000;
				endChange = le.toString();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			t.setEnd(endChange);
		}
		if(null != createrName && !"".equals(createrName)){
			t.setCreaterName(createrName.trim());
		}
		if(null != mainName && !"".equals(mainName)){
			t.setMainName(mainName.trim());
		}
		if(null != sampleId && !"".equals(sampleId)){
			t.setSampleId(sampleId.trim());
		}
		//暂时取消分页
//		t.setPageSize(20);
//		Integer totalCount = tabSampleMatchService.getTotalCount(t);
		//通过Page构造器获得真分页所必须的字段值
//		Page page = new Page(currPage, t.getPageSize(), totalCount);
		//startIndex用于真分页的limt sql查询
//		t.setStartIndex(page.getStartIndex());
		List<TabSampleMatch> sampleMatchList = tabSampleMatchService.selectTabSampleMatchs(t);
		if(null != sampleMatchList && sampleMatchList.size()>0){
			for(int i=0;i<sampleMatchList.size();i++){
				String sid = sampleMatchList.get(i).getSampleId();
				if(i!=0){
					if(!sampleMatchList.get(i).getStartTime().equals(sampleMatchList.get(i-1).getEndTime())){
						sampleMatchList.get(i).setNeedRed(1);
					}else{
						sampleMatchList.get(i).setNeedRed(0);
					}
				}else{
					sampleMatchList.get(i).setNeedRed(0);
				}
				
				String timeS = sampleMatchList.get(i).getStartTime();
	            Timestamp tp = new Timestamp(Long.parseLong(timeS));   
	            String startTime = sdf2.format(tp);
	            sampleMatchList.get(i).setStartTimeJsp(startTime);
	            
	            String timeE = sampleMatchList.get(i).getEndTime();
	            Timestamp tpe = new Timestamp(Long.parseLong(timeE));    
	            String endTime = sdf2.format(tpe);
	            sampleMatchList.get(i).setEndTimeJsp("0" + endTime);
	            
	            if(sampleMatchList.get(i).getLength() == null || sampleMatchList.get(i).getLength()==0){
	            	sampleMatchList.get(i).setLength(Integer.parseInt(endTime) - Integer.parseInt(startTime));
	            }
				
//				TabSample ts = tabSampleService.selectObjById(sid);
//				if(null != ts){
//					sampleMatchList.get(i).setTabSample(ts);
//				}
			}
		}
		
		model.addAttribute("sampleMatchList", sampleMatchList);
//		model.addAttribute("page", page);
//		model.addAttribute("pid", pid);
//		model.addAttribute("start", start);
//		model.addAttribute("end", end);
		model.addAttribute("createrName", createrName);
		model.addAttribute("mainName", mainName);
		model.addAttribute("sampleId", sampleId);
		
		return "pyp/sampleMatchList";
	}
	
	@RequestMapping(value="/exportData",method=RequestMethod.POST) 
	public void exportExcel(HttpServletResponse response,@RequestParam String pid, @RequestParam String start,
			@RequestParam String end, @RequestParam String createrName,@RequestParam String mainName,@RequestParam String sampleId){
		
		HttpSession session = request.getSession();
		String pidDef = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		Long todayTimestamp = DateStringUtils.stringToTimestamp(logDate, "yyyy-MM-dd");
		
		if(null == pid || "".equals(pid)){
			pid = pidDef;
		}
		if(null == start || "".equals(start)){
			start = todayTimestamp.toString();
		}
		
		response.setContentType("application/binary;charset=ISO8859_1");
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			String fileName = new String(("样本匹配流水单").getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			excelService.exportSampleMatch(pid, start, end, createrName,mainName,sampleId,outputStream);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/{sampleMatchId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String sampleMatchId, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		TabSampleMatch tabSampleMatch =  tabSampleMatchService.getOneById(Integer.parseInt(sampleMatchId));
		int result = tabSampleMatchService.deleteTabSampleMatch(tabSampleMatch);
		/*记录操作日志 -- start*/
		if(result > 0){
			String operLogName = Commons.DELMATCH;
			String operUserName = tu.getUserName();
			tabOperLogService.insertTabOperLog(operLogName, operUserName, sampleMatchId);
		}
		/*记录操作日志 -- end*/
		return "redirect:/tabSampleMatch/search";
	}
}
