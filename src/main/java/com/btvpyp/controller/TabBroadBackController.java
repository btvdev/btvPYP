package com.btvpyp.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.btvpyp.model.TabBroadBack;
import com.btvpyp.service.TabBroadBackService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.TimeUtil;

/**
 * 播返单控制层
 * @author gaobo
 *
 */
@Controller
@RequestMapping("/tabBroadBack")
public class TabBroadBackController {
	
	@Autowired
	private TabBroadBackService tabBroadBackService;
	@Autowired
	private HttpServletRequest request;
	/**
	 * 查询播返单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model){
		//初始化进入列表时，默认从session取日期作为默认日期
		HttpSession session = request.getSession();
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE) + " 00:00:00";
		TabBroadBack t = new TabBroadBack();
		t.setChooseDayS(logDate);
		t.setChannel("BTV1");
		List<TabBroadBack> tList = tabBroadBackService.selectTabBroadBacks(t);
		model.addAttribute("tList", tList);
		model.addAttribute("channel", t.getChannel());
		return "BFD/broadBackList";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(Model model,@RequestParam String channel, @RequestParam String chooseDay, @RequestParam String jmName){
		TabBroadBack t = new TabBroadBack();
		if(null != channel && !channel.equals("")){
			t.setChannel(channel);
		}
		if(null != chooseDay && !chooseDay.equals("")){
			t.setChooseDay(chooseDay);
			t.setChooseDayS(chooseDay + " 00:00:00");
			Long endStamp = TimeUtil.stringToTimestamp(t.getChooseDayS(), "yyyy-MM-dd HH:mm:ss");
			endStamp += 1000*60*60*30;//台内播返单的习惯操作是提供头一天0点到第二天6点的，故查询单天的区间在30小时 
			Timestamp te = new Timestamp(endStamp);
			String endchoose = TimeUtil.timestampToString(te);
			t.setChooseDayE(endchoose);
		}
		if(null != jmName && !jmName.equals("")){
			t.setJmName(jmName);
		}
		
		List<TabBroadBack> tList = tabBroadBackService.selectTabBroadBacks(t);
		
		model.addAttribute("tList", tList);
		model.addAttribute("channel", channel);
		model.addAttribute("chooseDay", chooseDay);
		model.addAttribute("jmName", jmName);
		
		return "BFD/broadBackList";
	}
}
