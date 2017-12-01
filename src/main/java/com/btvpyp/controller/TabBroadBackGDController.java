package com.btvpyp.controller;

import java.util.ArrayList;
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
import com.btvpyp.model.TabUser;
import com.btvpyp.model.gd.TabBroadBackGD;
import com.btvpyp.service.TabBroadBackGDService;
import com.btvpyp.service.TabBroadBackService;
import com.btvpyp.service.TabOperLogService;
import com.btvpyp.utils.Commons;

@Controller
@RequestMapping("/tabBroadBackGD")
public class TabBroadBackGDController {
	
	@Autowired
	private TabBroadBackGDService tabBroadBackGDService;
	
	@Autowired
	private TabBroadBackService tabBroadBackService;
	
	@Autowired
	public TabOperLogService tabOperLogService;
	
	@RequestMapping(value="/gd",method=RequestMethod.GET)
	public String moveData(Model model){
		
		return "BFD/toMoveBFD";
	}
	
	@RequestMapping(value="/gd",method=RequestMethod.POST)
	public String moveData(@RequestParam String start, @RequestParam String end, 
			@RequestParam String pid, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		
		String gdContent = "";//拼接归档内容字符串，用于记录操作日志
		TabBroadBack t = new TabBroadBack();
		if(null != start && !"".equals(start)){
			start += " 00:00:00";
			t.setChooseDayS(start);
			gdContent += start;
		}
		if(null != end && !"".equals(end)){
			end += " 23:59:59";
			t.setChooseDayE(end);
			gdContent += "至"+end;
		}
		if(null != pid && !"".equals(pid)){
			t.setChannel(pid);
			gdContent += ",频道为："+pid;
		}
		gdContent += "的播返单数据";
		
		List<TabBroadBack> tlist = tabBroadBackService.selectForGd(t);
		List<String> idList = new ArrayList<String>();
		if(null != tlist){//查询出需要进行归档的数据，放入List，并复制数据，迭代插入归档表
			if(tlist.size() > 0){
				for(int i=0;i<tlist.size();i++){
					TabBroadBack tb = tlist.get(i);
					TabBroadBackGD tbg = new TabBroadBackGD();
					tbg.setBroadBackId(tb.getBroadBackId());
					tbg.setChannel(tb.getChannel());
					tbg.setCreateTime(tb.getCreateTime());
					tbg.setJmName(tb.getJmName());
					tbg.setLength(tb.getLength());
					tbg.setStarttime(tb.getStarttime());
					tbg.setEndtime(tb.getEndtime());
					int intRe = tabBroadBackGDService.insertTabBroadBackGD(tbg);
					if(intRe == 1){
						idList.add(tbg.getBroadBackId()+"");
					}
				}
			}
		}
		//在原表中批量删除
		if(idList.size() > 0){
			tabBroadBackService.batchRemove(idList);
		}
		tabOperLogService.insertTabOperLog(Commons.GDBFD, tu.getUserName(), gdContent); //归档播返单
		
		model.addAttribute(idList.size()+"", "tiaoshu");
		model.addAttribute(start, "start");
		model.addAttribute(end, "end");
		return "BFD/moveBFDResult";
	}
}
