package com.btvpyp.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btvpyp.model.MatchResultXmlBean;
import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabSampleMatch;
import com.btvpyp.service.TabSampleMatchService;
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.MathUtil;
import com.btvpyp.utils.TimeUtil;
import com.btvpyp.utils.XmlUtils;
/**
 * 样本匹配接口
 * @author gaobo
 *
 */
@Controller
public class SampleMatchInterfaceController {
	@Autowired
	private TabSampleMatchService tabSampleMatchService;
	@Autowired
	private TabSampleService tabSampleService;
	@RequestMapping(value="/add/samplematch/{sampleId}/{pid}/{starttime}/{endtime}",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> sampleMatch(@PathVariable String sampleId,@PathVariable String pid,@PathVariable long starttime,@PathVariable long endtime){
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		//读取匹配结果，并新增一条流水单记录---start
		TabSampleMatch tabSampleMatch=new TabSampleMatch();
		tabSampleMatch.setSampleId(sampleId);
		if(pid.equals("289_1")){
			pid = "BTV1";
		}
		if(pid.equals("289_2")){
			pid = "BTV2";
		}
		if(pid.equals("289_3")){
			pid = "BTV3";
		}
		if(pid.equals("289_4")){
			pid = "BTV4";
		}
		if(pid.equals("289_5")){
			pid = "BTV5";
		}
		if(pid.equals("289_6")){
			pid = "BTV6";
		}
		if(pid.equals("289_7")){
			pid = "BTV7";
		}
		if(pid.equals("289_8")){
			pid = "BTV8";
		}
		if(pid.equals("289_9")){
			pid = "BTV9";
		}
		if(pid.equals("289_10")){
			pid = "BTV10";
		}
		if(pid.equals("289_11")){
			pid = "BTV11";
		}
		tabSampleMatch.setPid(pid);
		
		String startTime = starttime / 1000 + "";
		String endTime = endtime / 1000 + "";
		tabSampleMatch.setStartTime(startTime);
		tabSampleMatch.setEndTime(endTime);
		//时长改为结束时间与开始时间自动做差的结果
		
		//时长计算方法：做差然后四舍五入，比如：29.8秒 ---> 30秒
		Long c = Long.parseLong(endTime) - Long.parseLong(startTime);
		String len = MathUtil.divisionRound(c, 1000d, "%.0f");
		tabSampleMatch.setLength(Integer.parseInt(len));
		
		tabSampleMatch.setIsAdv(1);//1：广告样本 0：非广告
		tabSampleMatch.setCreaterName("auto");
		tabSampleMatch.setCreateTime(new Timestamp(new Date().getTime()));
		
		/*****2017-06-19*** 流水单表增加了字段 */
		TabSample ts = new TabSample();
		ts = tabSampleService.selectObjById(sampleId);
		if(null != ts){
			tabSampleMatch.setMainName(ts.getMainName());
			tabSampleMatch.setSecondName(ts.getSecondName());
			tabSampleMatch.setJiNum(ts.getJiNum());
			tabSampleMatch.setSampleDate(ts.getSampleDate());
			tabSampleMatch.setTapeNum(ts.getTapeNum());
			tabSampleMatch.setColumnType(ts.getColumnType());
		}
		/*****2017-06-19****/
		
		Integer result = tabSampleMatchService.insertTabSampleMatch(tabSampleMatch);
		//读取匹配结果，并新增一条流水单记录---end
		
		//写入日志文件---start
		MatchResultXmlBean mrxb = new MatchResultXmlBean();
		mrxb.setSampleid(sampleId);
		mrxb.setPid(pid);
		mrxb.setStarttime(starttime / 1000 + "");
		mrxb.setEndtime(endtime / 1000 + "");
		mrxb.setMatchtime(TimeUtil.getCurrentTime());
		XmlUtils.createMatchXml(mrxb);
		//写入日志文件---end
		
		map.put("statusCode", "0");
		map.put("message", "推送成功");
		return map;	
	}
}
