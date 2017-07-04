package com.btvpyp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.btvpyp.model.TabOperLog;
import com.btvpyp.service.TabOperLogService;
import com.btvpyp.utils.Page;
/**
 * 操作日志管理控制器
 * @author gaobo
 *
 */
@Controller
@RequestMapping("/tabOperLog")
public class TabOperLogController {
	@Autowired
	private TabOperLogService tabOperLogService;
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String selectSample(Model model){
		TabOperLog t = new TabOperLog();
		t.setPageSize(20);
		Integer totalCount = tabOperLogService.getTotalCount(t);
		Page page = new Page(1, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabOperLog> operLogList = tabOperLogService.selectTabOperLogs(t);
		model.addAttribute("operLogList", operLogList);
		model.addAttribute("page", page);
		
		return "pyp/operLogList";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String selectSample(@RequestParam String start,@RequestParam String end, @RequestParam String objId, 
			@RequestParam String operLogName, @RequestParam Integer currPage, Model model){
		TabOperLog t = new TabOperLog();
		if(null != start && !"".equals(start)){
			t.setStart(start);
		}
		if(null != end && !"".equals(end)){
			t.setEnd(end);
		}
		if(null != objId && !"".equals(objId)){
			t.setObjId(objId);
		}
		if(null != operLogName && !"".equals(operLogName)){
			t.setOperLogName(operLogName);
		}
		t.setPageSize(20);
		
		Integer totalCount = tabOperLogService.getTotalCount(t);
		//通过Page构造器获得真分页所必须的字段值
		Page page = new Page(currPage, t.getPageSize(), totalCount);
		//startIndex用于真分页的limt sql查询
		t.setStartIndex(page.getStartIndex());
		List<TabOperLog> operLogList = tabOperLogService.selectTabOperLogs(t);
		
		model.addAttribute("operLogList", operLogList);
		model.addAttribute("page", page);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("objId", objId);
		model.addAttribute("operLogName", operLogName);
		
		return "pyp/operLogList";
	}
}
