package com.btvpyp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.btvpyp.utils.StrUtils;

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
	/**
	 * 新增流水单
	 * @param lastEndTime   上一条流水单的结束时间
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addSampleMatch",method=RequestMethod.GET)
	public String addSampleMatch(@RequestParam String lastEndTime, Model model){
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		List<TabAdvTypes> advTypesList = tabAdvTypesService.selectByLevel(3);
		model.addAttribute("advTypesList", advTypesList);
		model.addAttribute("lastEndTime", lastEndTime);
		model.addAttribute("pid", pid);
		return "sampleMatch/addSampleMatch";
	}
	/**
	 * 新增流水单
	 * @param startTime     开始时间
	 * @param endTime       结束时间
	 * @param sampleIdHide  样本id
	 * @param mainName      主名称
	 * @param secondName    副标题
	 * @param length        时长
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addSampleMatch",method=RequestMethod.POST)
	public String addSampleMatch(@RequestParam String startTime,@RequestParam String endTime,
			@RequestParam String sampleIdHide, @RequestParam String mainName, 
			@RequestParam String secondName, @RequestParam String length, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		TabSample ts = null;
		TabSampleMatch tsm = new TabSampleMatch();
		tsm.setPid(pid);
		if(StrUtils.isNotEmptyStr(mainName)){
			tsm.setMainName(mainName);
		}
		if(StrUtils.isNotEmptyStr(secondName)){
			tsm.setSecondName(secondName);
		}
		if(StrUtils.isNotEmptyStr(startTime)){
			char[] cStart = startTime.toCharArray();
			startTime = logDate + " " + cStart[0]+cStart[1]+":"+cStart[2]+cStart[3]+":"+cStart[4]+cStart[5];
			Timestamp ttt = Timestamp.valueOf(startTime);
			tsm.setStartTime(ttt.getTime()+"");
		}
		if(StrUtils.isNotEmptyStr(endTime)){
			endTime = endTime.substring(1, endTime.length());
			char[] cEnd = endTime.toCharArray();
			endTime = logDate + " " + cEnd[0]+cEnd[1]+":"+cEnd[2]+cEnd[3]+":"+cEnd[4]+cEnd[5];
			Timestamp tttt = Timestamp.valueOf(endTime);
			tsm.setEndTime(tttt.getTime()+"");
		}
		if(StrUtils.isNotEmptyStr(length)){
			tsm.setLength(Integer.parseInt(length));
		}
		
		if(StrUtils.isNotEmptyStr(sampleIdHide)){
			ts = tabSampleService.selectObjById(sampleIdHide);
			tsm.setSampleId(ts.getSampleId());
			tsm.setIsAdv(ts.getIsAdv());
			tsm.setCreaterName(ts.getCreaterName());
			tsm.setCreateTime(ts.getCreateTime());
			tsm.setJiNum(ts.getJiNum());
			tsm.setSampleDate(ts.getSampleDate());
			tsm.setTapeNum(ts.getTapeNum());
			tsm.setColumnType(ts.getColumnType());
			tsm.setDeleteFlg(0);
		}
		tabSampleMatchService.insertTabSampleMatch(tsm);
		return "redirect:/tabSampleMatch/search";
	}
	
	//@RequestMapping(value="/addSampleMatch",method=RequestMethod.POST)
	public String addSampleMatch_bak(@RequestParam String startTime,@RequestParam String endTime,
			@RequestParam String jiNum,@RequestParam String length,@RequestParam String location,
			@RequestParam String comments,@RequestParam String sampleDate,@RequestParam String pidhide,
			@RequestParam String mainName,@RequestParam String secondName,@RequestParam String tapeNum,
			@RequestParam String columnType,@RequestParam String advId,@RequestParam String company,
			@RequestParam String largeType,@RequestParam String middleType,@RequestParam String tinyType,
			@RequestParam String isExists,MultipartFile advFile,@RequestParam String sampleIdHide, 
			@RequestParam String isAdvhide,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		TabSampleMatch tsm = new TabSampleMatch();
		TabSample ts = new TabSample();
		
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		logDate = logDate.replaceAll("-", "");
		
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
		
		//判断advFile是否为空，非空，则上传文件，并给tabSample对象对应字段赋值
		if(advFile.isEmpty()){
			ts.setIsAdv(0);
			tsm.setIsAdv(0);
		}else{
//			Map<String, String> resultMap = UploadUtils.uploadSampleFile(advFile);
//			if(null != resultMap){
//				ts.setFileAddr(resultMap.get("fileAddr"));
//				ts.setFileNetAddr(resultMap.get("fileNetAddr"));
//				ts.setIsAdv(1);//advFile则该值为1
//			}
		}
		
		ts.setExtFlag(0);//初始化提取标识
		ts.setLargeType(largeType);
		ts.setMiddleType(middleType);
		ts.setTinyType(tinyType);
		String sampleIdNew = tabSampleService.insertTabSample(ts);
		//将时间格式做转化，用来更改开始时间及结束时间的日期格式
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
	
	/**
	 * 日期格式转换
	 * @param oldTime
	 * @return
	 */
	public static String returnNewTime(String oldTime){
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		Timestamp tp = new Timestamp(Long.parseLong(oldTime));
		String newTime = sdf.format(tp);
		return newTime;
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
		
		//总条数
		Integer totalCount = tabSampleMatchService.getTotalCount(t);
		List<TabSampleMatch> sampleMatchList = tabSampleMatchService.selectTabSampleMatchs(t);
		sampleMatchList = merge(sampleMatchList);
		if(null != sampleMatchList && sampleMatchList.size()>0){
			for(int i=0;i<sampleMatchList.size();i++){
				//相邻两条流水单记录如果前后时间没有完全咬合的显示处理逻辑
				if(i!=0){
					String thisStart = returnNewTime(sampleMatchList.get(i).getStartTime());
					String lastEnd = returnNewTime(sampleMatchList.get(i-1).getEndTime());
					if(!thisStart.equals(lastEnd)){
						sampleMatchList.get(i).setNeedRed(1);
						//记录上一条匹配记录的结束时间，用于页面逻辑
						String timeLe = sampleMatchList.get(i-1).getEndTime();
						sampleMatchList.get(i).setLastEndTime(returnNewTime(timeLe));
					}else{
						sampleMatchList.get(i).setNeedRed(0);
					}
				}else{
					sampleMatchList.get(i).setNeedRed(0);
				}
				
				//更改日期在页面上的显示格式，业务方的习惯是,开始时间为HHmmss，结束时间为0HHmmss
				String timeS = sampleMatchList.get(i).getStartTime();
	            String startTime = returnNewTime(timeS);
	            sampleMatchList.get(i).setStartTimeJsp(startTime);
	            
	            String timeE = sampleMatchList.get(i).getEndTime();
	            String endTime = returnNewTime(timeE);
	            sampleMatchList.get(i).setEndTimeJsp("0" + endTime);
	            
	            sampleMatchList.get(i).setLength((Integer.parseInt(timeE) - Integer.parseInt(timeS))/1000);
	            //如果数据库中记录的时长为0，则自动用结束时间减去开始时间作为时长显示在列表中
//	            if(sampleMatchList.get(i).getLength() == null || sampleMatchList.get(i).getLength()==0){
//	            	sampleMatchList.get(i).setLength(Integer.parseInt(timeE) - Integer.parseInt(timeS));
//	            }
			}
		}
		
		model.addAttribute("sampleMatchList", sampleMatchList);
		model.addAttribute("totalCount", totalCount);
//		model.addAttribute("page", page);
		return "sampleMatch/sampleMatchList";
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
		Integer totalCount = tabSampleMatchService.getTotalCount(t);
		//通过Page构造器获得真分页所必须的字段值
//		Page page = new Page(currPage, t.getPageSize(), totalCount);
		//startIndex用于真分页的limt sql查询
//		t.setStartIndex(page.getStartIndex());
		List<TabSampleMatch> sampleMatchList = tabSampleMatchService.selectTabSampleMatchs(t);
		sampleMatchList = merge(sampleMatchList);
		if(null != sampleMatchList && sampleMatchList.size()>0){
			for(int i=0;i<sampleMatchList.size();i++){
				//相邻两条流水单记录如果前后时间没有完全咬合的显示处理逻辑
				if(i!=0){
					String thisStart = returnNewTime(sampleMatchList.get(i).getStartTime());
					String lastEnd = returnNewTime(sampleMatchList.get(i-1).getEndTime());
					if(!thisStart.equals(lastEnd)){
						sampleMatchList.get(i).setNeedRed(1);
						//记录上一条匹配记录的结束时间，用于页面逻辑
						String timeLe = sampleMatchList.get(i-1).getEndTime();
						sampleMatchList.get(i).setLastEndTime(returnNewTime(timeLe));
					}else{
						sampleMatchList.get(i).setNeedRed(0);
					}
				}else{
					sampleMatchList.get(i).setNeedRed(0);
				}
				
				//更改日期在页面上的显示格式，业务方的习惯是,开始时间为HHmmss，结束时间为0HHmmss
				String timeS = sampleMatchList.get(i).getStartTime();
	            String startTime = returnNewTime(timeS);
	            sampleMatchList.get(i).setStartTimeJsp(startTime);
	            
	            String timeE = sampleMatchList.get(i).getEndTime();
	            String endTime = returnNewTime(timeE);
	            sampleMatchList.get(i).setEndTimeJsp("0" + endTime);
	            Long lengthLong = (Long.parseLong(timeE) - Long.parseLong(timeS))/1000;
	            sampleMatchList.get(i).setLength(lengthLong.intValue());
	            //如果数据库中记录的时长为0，则自动用结束时间减去开始时间作为时长显示在列表中
//	            if(sampleMatchList.get(i).getLength() == null || sampleMatchList.get(i).getLength()==0){
//	            	sampleMatchList.get(i).setLength((Integer.parseInt(timeE) - Integer.parseInt(timeS))/1000);
//	            }
			}
		}
		
		model.addAttribute("sampleMatchList", sampleMatchList);
//		model.addAttribute("page", page);
		model.addAttribute("pid", pid);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("createrName", createrName);
		model.addAttribute("mainName", mainName);
		model.addAttribute("sampleId", sampleId);
		model.addAttribute("totalCount", totalCount);
		
		return "sampleMatch/sampleMatchList";
	}
	
	/**
	 *  在显示层处理由于北大算法缺陷而造成的同一广告播放记录时间不连续的问题
	 *  关于整合中断记录的算法：
		设置三个变量值，一个存样本id(defId)，一个存结束时间(defEnd)，一个存开始时间(defStart)
		另外声明一个新的List<TabSampleMatch> newList
		三个变量默认值都预置成oldList中第一个节点的值
		1.在迭代过程中，如果遇到一条记录的sampleId与defId不同，则new TanSampleMatch()并给对应字段赋值
		2.在迭代过程中，如果记录的sampleId与defId相同，则更新defEnd的值，并继续迭代
		3.另外迭代过程中要增加判断：是否已循环至最后一条记录
	 * @param oldList
	 * @return
	 */
	public List<TabSampleMatch> merge(List<TabSampleMatch> oldList){
		List<TabSampleMatch> newList = new ArrayList<TabSampleMatch>();
		/*
		 * 使用迭代器进行操作，这里有一个教训：
		 * 从ArrayList中循环的取对象并加以挑选后add进另外一个新的ArrayList中是有问题的
		 * 最后会只将旧的ArrayList中的最后一个对象add进新ArrayList中两次，做不到对象的全复制
		 **/
		if(null != oldList && oldList.size()>0) {
			Iterator<TabSampleMatch> tsms = oldList.iterator();
			
			String defId = oldList.get(0).getSampleId();
			String defStart = oldList.get(0).getStartTime();
			String defEnd = oldList.get(0).getEndTime();
			
			TabSampleMatch tsm = new TabSampleMatch();
			TabSample ts = new TabSample();
			
			while(tsms.hasNext()){
				tsm = tsms.next();
				if(!tsms.hasNext()){
					//如果迭代到最后一条记录
					if(tsm.getSampleId().equals(defId)){
						//如果最后一条记录的sampleId与defId相同，则更新defEnd，并将对象加入newList中
						defEnd = tsm.getEndTime();
						TabSampleMatch tsmNew = new TabSampleMatch();
						tsmNew.setSampleId(defId);
						tsmNew.setStartTime(defStart);
						tsmNew.setEndTime(defEnd);
						tsmNew = makeNewTabSample(defId, tsmNew);
						newList.add(tsmNew);
					}else{
						//如果最后一条记录的sampleId与defId不同，则先将倒数第二条记录加入newList，再将最后一条记录加入newList
						TabSampleMatch tsmNew = new TabSampleMatch();
						tsmNew.setSampleId(defId);
						tsmNew.setStartTime(defStart);
						tsmNew.setEndTime(defEnd);
						tsmNew = makeNewTabSample(defId, tsmNew);
						newList.add(tsmNew);
						
						defId = tsm.getSampleId();
					    defStart = tsm.getStartTime();
					    defEnd = tsm.getEndTime();
					    
					    TabSampleMatch tsmLast = new TabSampleMatch();
					    tsmLast.setSampleId(defId);
					    tsmLast.setStartTime(defStart);
					    tsmLast.setEndTime(defEnd);
					    tsmLast = makeNewTabSample(defId, tsmLast);
						newList.add(tsmLast);
					}
				}else{
					if(tsm.getSampleId().equals(defId)){
						//在迭代过程中，如果记录的sampleId与defId相同，则更新defEnd的值，并继续迭代
						defEnd = tsm.getEndTime();
					}else{
						//在迭代过程中，如果遇到一条记录的sampleId与defId不同，则new TanSampleMatch()并给对应字段赋值
						TabSampleMatch tsmNew = new TabSampleMatch();
						tsmNew.setSampleId(defId);
						tsmNew.setStartTime(defStart);
						tsmNew.setEndTime(defEnd);
						tsmNew = makeNewTabSample(defId, tsmNew);
						newList.add(tsmNew);
						
						defId = tsm.getSampleId();
					    defStart = tsm.getStartTime();
					    defEnd = tsm.getEndTime();
					}
				}
			}
		}
		
		return newList;
	}
	
	public TabSampleMatch makeNewTabSample(String sampleId, TabSampleMatch tsmNew){
		TabSample tabSample = new TabSample();
		tabSample = tabSampleService.selectObjById(sampleId);
		
		tsmNew.setPid(tabSample.getPid());
		tsmNew.setCreaterName(tabSample.getCreaterName());
		tsmNew.setIsAdv(tabSample.getIsAdv());
		tsmNew.setCreateTime(tabSample.getCreateTime());
		tsmNew.setMainName(tabSample.getMainName());
		tsmNew.setLength(tabSample.getLength());
		tsmNew.setJiNum(tabSample.getJiNum());
		tsmNew.setSampleDate(tabSample.getSampleDate());
		tsmNew.setTapeNum(tabSample.getTapeNum());
		tsmNew.setColumnType(tabSample.getColumnType());
		tsmNew.setSecondName(tabSample.getSecondName());
		
		return tsmNew;
	}
	
	@RequestMapping(value="/exportData",method=RequestMethod.POST) 
	public void exportExcel(HttpServletResponse response,@RequestParam String pid, @RequestParam String start,
			@RequestParam String end, @RequestParam String createrName,@RequestParam String mainName,@RequestParam String sampleId){
		
		HttpSession session = request.getSession();
		String pidDef = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		Long todayTimestamp = DateStringUtils.stringToTimestamp(logDate, "yyyy-MM-dd");
		
		//如果页面上未选定导出的频道和日期，则使用登录时选择的系统默认日期
		if(null == pid || "".equals(pid)){
			pid = pidDef;
		}
		if(null == start || "".equals(start)){
			start = todayTimestamp.toString();
		}else{
			start = Timestamp.valueOf(start).getTime() + "";
		}
		if(null != end && !"".equals(end)){
			end = Timestamp.valueOf(end).getTime() + "";
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
	/**
	 * 删除样本流水
	 * @param sampleMatchId  流水单ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{sampleMatchId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String sampleMatchId, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		TabSampleMatch tabSampleMatch = tabSampleMatchService.getOneById(Integer.parseInt(sampleMatchId));
		int result = tabSampleMatchService.deleteTabSampleMatch(tabSampleMatch);
		/*记录操作日志 -- start*/
		if(result > 0){
			tabOperLogService.insertTabOperLog(Commons.DELMATCH, tu.getUserName(), sampleMatchId);
		}
		/*记录操作日志 -- end*/
		return "redirect:/tabSampleMatch/search";
	}
	
	@RequestMapping(value="/{sampleMatchId}/update",method=RequestMethod.GET)
	public String update(@PathVariable String sampleMatchId, HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
//		String pidDef = (String)session.getAttribute(Commons.SESSIONPID);
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TabSampleMatch tabSampleMatch = tabSampleMatchService.getOneById(Integer.parseInt(sampleMatchId));
		
		String startTime = tabSampleMatch.getStartTime();
		Timestamp tp = new Timestamp(Long.parseLong(startTime));
		startTime = sdf.format(tp);
		tabSampleMatch.setStartTime(startTime);
		
		String endTime = tabSampleMatch.getEndTime();
		Timestamp tpe = new Timestamp(Long.parseLong(endTime));
		endTime = sdf.format(tpe);
		tabSampleMatch.setEndTime(endTime);
		
		model.addAttribute("tabSampleMatch", tabSampleMatch);
		model.addAttribute("logDate", logDate);
		return "sampleMatch/updateSampleMatch";
	}
	/**
	 * 修改流水单信息
	 * @param tabSampleMatch
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{sampleMatchId}/update",method=RequestMethod.POST)
	public String update(TabSampleMatch tabSampleMatch, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//将时间格式转化
		String start = tabSampleMatch.getStartTime();
		String end = tabSampleMatch.getEndTime();
		try {
			Long lStart = new Long(sdf.parse(start).getTime());
			Long lEnd = new Long(sdf.parse(end).getTime());
			tabSampleMatch.setStartTime(lStart.toString());
			tabSampleMatch.setEndTime(lEnd.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int result = tabSampleMatchService.updateTabSampleMatch(tabSampleMatch);
		/*记录操作日志 -- start*/
		if(result > 0){
			String objId = tabSampleMatch.getSampleMatchId().toString();
			tabOperLogService.insertTabOperLog(Commons.UPDMATCH, tu.getUserName(), objId);
		}
		/*记录操作日志 -- end*/
		return "redirect:/tabSampleMatch/search";
	}
	
	/**
	 * 按照业务习惯的时间格式，异步的计算结束时间
	 * @param request
	 * @param startTime  开始时间
	 * @param length     时长
	 * @return
	 */
	@RequestMapping(value="/ajaxGetEndtime",method=RequestMethod.POST)
	public @ResponseBody String ajaxGetEndtime(HttpServletRequest request, @RequestParam String startTime, 
			@RequestParam String length){
		HttpSession session = request.getSession();
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		logDate = logDate.replace("-", "");
		String fullStartTime = logDate + startTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
		long startTimestamp = 0L;
		long endTimestamp = 0L;
		try {
			startTimestamp = sdf.parse(fullStartTime).getTime();
			endTimestamp = startTimestamp + Long.parseLong(length)*1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp ts = new Timestamp(endTimestamp);
		String reStr = "";
		//业务上习惯在结束时间前面加一个0
		reStr += "{\"endtime\":"+"\""+"0" + sdf2.format(ts)+"\""+"}";
		return reStr;
	}
}
