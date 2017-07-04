package com.btvpyp.controller;
//特征采集结果反馈接口
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
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.LogUtil;
@Controller
public class SampleInterfaceController {
	@Autowired
	private TabSampleService tabSampleService;
	
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
