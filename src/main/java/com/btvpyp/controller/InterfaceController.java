package com.btvpyp.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabSampleMatch;
import com.btvpyp.model.xml.MatchResultXmlBean;
import com.btvpyp.service.TabSampleMatchService;
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.LogUtil;
import com.btvpyp.utils.MathUtil;
import com.btvpyp.utils.TimeUtil;
import com.btvpyp.utils.XmlUtils;

@Controller
public class InterfaceController {
	@Autowired
	private TabSampleService tabSampleService;
	@Autowired
	private TabSampleMatchService tabSampleMatchService;
	
	//将频道代号对应关系声明为静态成员变量
	private static Map<String, String> pids = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put("289_1", "BTV1");
			put("289_2", "BTV2");
			put("289_3", "BTV3");
			put("289_4", "BTV4");
			put("289_5", "BTV5");
			put("289_6", "BTV6");
			put("289_7", "BTV7");
			put("289_8", "BTV8");
			put("289_9", "BTV9");
			put("289_10", "BTV10");
			put("289_11", "BTV11");
		}
	};
	
	@RequestMapping(value="/add/samplematch/{sampleId}/{pid}/{starttime}/{endtime}",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> sampleMatch(@PathVariable String sampleId,@PathVariable String pid,@PathVariable long starttime,@PathVariable long endtime){
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		//读取匹配结果，并新增一条流水单记录---start
		TabSampleMatch tabSampleMatch=new TabSampleMatch();
		tabSampleMatch.setSampleId(sampleId);
		pid = pids.get(pid);
		tabSampleMatch.setPid(pid);
		
		String kaishi = starttime / 1000 + "";
		String jieshu = endtime / 1000 + "";
		tabSampleMatch.setStartTime(kaishi);
		tabSampleMatch.setEndTime(jieshu);
		//时长改为结束时间与开始时间自动做差的结果
		
		//时长计算方法：做差然后四舍五入，比如：29.8秒 ---> 30秒
		Long c = Long.parseLong(jieshu) - Long.parseLong(kaishi);
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
		mrxb.setStarttime(starttime + "");
		mrxb.setEndtime(endtime + "");
		mrxb.setMatchtime(TimeUtil.getCurrentTime());
		XmlUtils.createMatchXml(mrxb);
		//写入日志文件---end
		
		map.put("statusCode", "0");
		map.put("message", "推送成功");
		return map;	
	}
	
	/**
	 * 特征采集接口反馈
	 * @param sampleId
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/sample/featureextractionresult/{sampleId}/{status}",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> getFeatureExtractionResult(@PathVariable String sampleId,@PathVariable int status){
		Map<String,Object> map=new HashMap<String,Object>();
			if(sampleId==null){
				map.put("statusCode", "1");
				map.put("message", "sampleId不能为null");
				return map;
			}
			//status 0成功 1失败   extFlag 0:未提取 1：提取中 2：提取失败 3：提取成功
			TabSample sample=new TabSample();
			sample.setSampleId(sampleId);
			if(status==0){
				sample.setExtFlag(3);
			}else{
				sample.setExtFlag(2);
			}
			//将提取标识更新到数据库中
			tabSampleService.updateTabSample(sample);
			
			//将反馈接口记录进静态文件中---start
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dir = sdf.format(new Date());
			String fileName = sampleId + "_ext.txt";
			String saveUrl = Commons.PUSHLOGDIR + dir + "/";
			
			String content = "北大的匹配数据：    " + "\n";
			content += "sampleId=" + sampleId + "&";
			content += "status=" + status;	
			
			SimpleDateFormat stime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = stime.format(new Date());
			content += "\n"+"调用时间：" + time;
			LogUtil.saveSampleJson(content, saveUrl, fileName);
			//将反馈接口记录进静态文件中---end
			
			map.put("statusCode", "0");
			map.put("message", "反馈成功");
		return map;
	}
}
