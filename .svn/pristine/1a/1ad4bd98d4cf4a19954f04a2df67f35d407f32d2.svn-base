package com.btvpyp.detail.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.btvpyp.broadBack.service.TabBroadBackService;
import com.btvpyp.detail.model.TabDetail;
import com.btvpyp.detail.service.TabDetailService;
import com.btvpyp.excel.service.ExcelService;
import com.btvpyp.sampleMatch.service.TabSampleMatchService;
import com.btvpyp.utils.TimeUtil;

@Controller
@RequestMapping("/tabDetail")
public class TabDetailController {
	
	@Autowired
	public TabSampleMatchService tabSampleMatchService;
	
	@Autowired
	private TabBroadBackService tabBroadBackService;
	
	@Autowired
	private TabDetailService tabDetailService;
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model){
		return "detail/search";
	}
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(Model model, @RequestParam String channel, 
			@RequestParam String start, @RequestParam String end){
		TabDetail tabDetail = new TabDetail();
		if(null != channel && !channel.equals("")) {
			tabDetail.setChannel(channel);
		}
		if(null != start && !start.equals("")) {
			String startTime = TimeUtil.stringToTimestamp(start, "yyyy-MM-dd HH:mm:ss").toString();
			tabDetail.setStart(startTime);
		}
		if(null != end && !end.equals("")) {
			String endTime = TimeUtil.stringToTimestamp(end, "yyyy-MM-dd HH:mm:ss").toString();
			tabDetail.setEnd(endTime);
		}
		List<TabDetail> detailList = tabDetailService.selectTabDetails(tabDetail);
		if(null != detailList && detailList.size()>0) {
			for(int i=0;i<detailList.size();i++) {
				if(i!=0) {
					Long thisStart = Long.parseLong(detailList.get(i).getStartTime());
					Long lastEnd = Long.parseLong(detailList.get(i-1).getEndTime());
					if(thisStart >lastEnd) { 
						detailList.get(i).setNeedRed(1);
					}else {
						detailList.get(i).setNeedRed(0);
					}
				} else {
					detailList.get(i).setNeedRed(0);
				}
			}
			
			for(int j=0;j<detailList.size();j++) {
				String tdStart = detailList.get(j).getStartTime();
				if(null != tdStart && !"".equals(tdStart)) {
					Timestamp ts = new Timestamp(Long.parseLong(tdStart));
					detailList.get(j).setStartTime(TimeUtil.timestampToString(ts));
				}
				
				String tdEnd = detailList.get(j).getEndTime();
				if(null != tdEnd && !"".equals(tdEnd)) {
					Timestamp ts = new Timestamp(Long.parseLong(tdEnd));
					detailList.get(j).setEndTime(TimeUtil.timestampToString(ts));
				}
				if(j != 0) {
					String lastEnd = detailList.get(j-1).getEndTime();
					detailList.get(j).setLastEndTime(lastEnd);
				}
			}
		}
		model.addAttribute("detailList", detailList);
		model.addAttribute("channel", channel);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return "detail/search";
	}
	
	
	@RequestMapping(value="/exportDetailExcel",method=RequestMethod.POST)
	public void exportExcel(HttpServletResponse response,@RequestParam String channel, 
			@RequestParam String start, @RequestParam String end) {
		HttpSession session = request.getSession();
		response.setContentType("application/binary;charset=ISO8859_1");
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			String fileName = new String(("节目+广告详单").getBytes(), "ISO8859_1");//文件名
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			excelService.exportDetail(channel, start, end, outputStream);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
