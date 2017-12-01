package com.btvpyp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.btvpyp.model.SampleNames;
import com.btvpyp.model.TabAdvTypes;
import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabUser;
import com.btvpyp.service.ExcelService;
import com.btvpyp.service.TabAdvTypesService;
import com.btvpyp.service.TabOperLogService;
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.KeyGen;
import com.btvpyp.utils.Page;
import com.btvpyp.utils.UploadUtils;
/**
 * 样本管理控制器
 * @author gaobo
 *
 */
@Controller
@RequestMapping("/tabSample")
public class TabSampleController {
	@Autowired
	public TabSampleService tabSampleService;
	
	@Autowired
	public TabAdvTypesService tabAdvTypesService;
	
	@Autowired 
	public ExcelService excelService;
	
	@Autowired
	public TabOperLogService tabOperLogService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value="/{advId}/getAdvById",method=RequestMethod.GET)
	public String getAdvById(Model model, @PathVariable String advId){
		TabSample tabSample = new TabSample();
		if(null != advId && !"".equals(advId)){
			tabSample = tabSampleService.selectObjById(advId);
		}
		model.addAttribute("tabSample", tabSample);
		return "sample/viewSample";
	}
	
	@RequestMapping(value="/searchAdv",method=RequestMethod.GET)
	public String selectSampleAdv(Model model){
		TabSample t = new TabSample();
		t.setIsAdv(1);
		t.setPageSize(20);
		Integer totalCount = tabSampleService.getTotalCount(t);
		Page page = new Page(1, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabSample> sampleList = tabSampleService.selectTabSamples(t);
		List<TabAdvTypes> advTypesListL = tabAdvTypesService.selectByLevel(3);
		List<TabAdvTypes> advTypesListM = tabAdvTypesService.selectByLevel(2);
		List<TabAdvTypes> advTypesListT = tabAdvTypesService.selectByLevel(1);
		
		model.addAttribute("isAdv", 1);
		model.addAttribute("sampleList", sampleList);
		model.addAttribute("advTypesListL", advTypesListL);
		model.addAttribute("advTypesListM", advTypesListM);
		model.addAttribute("advTypesListT", advTypesListT);
		model.addAttribute("page", page);
		model.addAttribute("totalCount", totalCount);//总条数
		
		return "sample/sampleList";
	}
	@RequestMapping(value="/searchLm",method=RequestMethod.GET)
	public String selectSampleLm(Model model){
		TabSample t = new TabSample();
		t.setIsAdv(0);
		t.setPageSize(20);
		Integer totalCount = tabSampleService.getTotalCount(t);
		Page page = new Page(1, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabSample> sampleList = tabSampleService.selectTabSamples(t);
		List<TabAdvTypes> advTypesListL = tabAdvTypesService.selectByLevel(3);
		List<TabAdvTypes> advTypesListM = tabAdvTypesService.selectByLevel(2);
		List<TabAdvTypes> advTypesListT = tabAdvTypesService.selectByLevel(1);
		
		model.addAttribute("isAdv", 0);
		model.addAttribute("sampleList", sampleList);
		model.addAttribute("advTypesListL", advTypesListL);
		model.addAttribute("advTypesListM", advTypesListM);
		model.addAttribute("advTypesListT", advTypesListT);
		model.addAttribute("page", page);
		model.addAttribute("totalCount", totalCount);//总条数
		
		return "sample/sampleLmList";
	}
	/**
	 * 样本列表条件查询
	 * @param mainName     主名称
	 * @param isAdv        是否为广告
	 * @param largeType    大类
	 * @param middleType   中类
	 * @param tinyType	        小类
	 * @param createrName  创建人
	 * @param currPage     当前页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchAdv",method=RequestMethod.POST)
	public String selectSampleAdv(@RequestParam String mainName,@RequestParam String largeType,
			@RequestParam String middleType, @RequestParam String tinyType,@RequestParam String createrName,@RequestParam Integer currPage, Model model){
		
		TabSample t = new TabSample();
		t.setIsAdv(1);
		if(null != mainName && !"".equals(mainName)){
			t.setMainName(mainName.trim());
		}
		if(null != largeType && !"".equals(largeType)){
			t.setLargeType(largeType);
		}
		if(null != middleType && !"".equals(middleType)){
			t.setMiddleType(middleType);
		}
		if(null != tinyType && !"".equals(tinyType)){
			t.setMiddleType(tinyType);
		}
		if(null != createrName && !"".equals(createrName)){
			t.setCreaterName(createrName.trim());
		}
		t.setPageSize(20);
		Integer totalCount = tabSampleService.getTotalCount(t);
		Page page = new Page(currPage, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabSample> sampleList = tabSampleService.selectTabSamples(t);
		model.addAttribute("sampleList", sampleList);
		
		List<TabAdvTypes> advTypesListL = tabAdvTypesService.selectByLevel(3);
		List<TabAdvTypes> advTypesListM = tabAdvTypesService.selectByLevel(2);
		List<TabAdvTypes> advTypesListT = tabAdvTypesService.selectByLevel(1);
	
		model.addAttribute("advTypesListL", advTypesListL);
		model.addAttribute("advTypesListM", advTypesListM);
		model.addAttribute("advTypesListT", advTypesListT);
		model.addAttribute("page", page);
		model.addAttribute("mainName", mainName);
		model.addAttribute("createrName", createrName);
		model.addAttribute("largeType", largeType);
		model.addAttribute("middleType", middleType);
		model.addAttribute("tinyType", tinyType);
		model.addAttribute("totalCount", totalCount);//总条数
		return "sample/sampleList";
	}
	
	@RequestMapping(value="/searchLm",method=RequestMethod.POST)
	public String selectSampleLm(@RequestParam String mainName,@RequestParam String largeType,
			@RequestParam String middleType, @RequestParam String tinyType,@RequestParam String createrName,@RequestParam Integer currPage, Model model){
		
		TabSample t = new TabSample();
		t.setIsAdv(0);
		if(null != mainName && !"".equals(mainName)){
			t.setMainName(mainName.trim());
		}
		if(null != largeType && !"".equals(largeType)){
			t.setLargeType(largeType);
		}
		if(null != middleType && !"".equals(middleType)){
			t.setMiddleType(middleType);
		}
		if(null != tinyType && !"".equals(tinyType)){
			t.setMiddleType(tinyType);
		}
		if(null != createrName && !"".equals(createrName)){
			t.setCreaterName(createrName.trim());
		}
		t.setPageSize(20);
		Integer totalCount = tabSampleService.getTotalCount(t);
		Page page = new Page(currPage, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabSample> sampleList = tabSampleService.selectTabSamples(t);
		model.addAttribute("sampleList", sampleList);
		
		List<TabAdvTypes> advTypesListL = tabAdvTypesService.selectByLevel(3);
		List<TabAdvTypes> advTypesListM = tabAdvTypesService.selectByLevel(2);
		List<TabAdvTypes> advTypesListT = tabAdvTypesService.selectByLevel(1);
	
		model.addAttribute("advTypesListL", advTypesListL);
		model.addAttribute("advTypesListM", advTypesListM);
		model.addAttribute("advTypesListT", advTypesListT);
		model.addAttribute("page", page);
		model.addAttribute("mainName", mainName);
		model.addAttribute("createrName", createrName);
		model.addAttribute("largeType", largeType);
		model.addAttribute("middleType", middleType);
		model.addAttribute("tinyType", tinyType);
		model.addAttribute("totalCount", totalCount);//总条数
		return "sample/sampleLmList";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		HttpSession session = request.getSession();
		String pid = (String)session.getAttribute(Commons.SESSIONPID);
		TabSample tabSample = new TabSample();
		List<TabAdvTypes> advTypesList = tabAdvTypesService.selectByLevel(3);
		model.addAttribute("advTypesList", advTypesList);
		model.addAttribute("tabSample", tabSample);
		model.addAttribute("pid", pid);
		
		return "sample/addSample";
	}
	/**
	 * 添加样本
	 * @param tabSample
	 * @param advFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(TabSample tabSample,MultipartFile advFile,HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		String sampleId = KeyGen.getADVId();
		tabSample.setSampleId(sampleId);
		if(advFile.isEmpty()){
			tabSample.setIsAdv(0);
		}else{
			String filePrefix = tabSample.getSampleId() + "_" + tabSample.getMainName();
			Map<String, String> map = UploadUtils.uploadSampleFile(advFile, filePrefix);
			tabSample.setIsAdv(1);
			tabSample.setFileAddr(map.get("fileAddr"));
			tabSample.setFileNetAddr(map.get("fileNetAddr"));
			tabSample.setExtFlag(0);
		}
		
		/*数据库插入样本记录 -- start*/
		tabSample.setCreaterName(tu.getUserName());
		tabSample.setCreateTime(new Timestamp(System.currentTimeMillis()));
		tabSample.setFlag(1);
		String result = tabSampleService.insertTabSample(tabSample);
		/*数据库插入样本记录 -- end*/
		
		/*记录操作日志 -- start*/
		if(!"".equals(result)){
			tabOperLogService.insertTabOperLog(Commons.ADDSAMPLE, tu.getUserName(), result);
		}
		/*记录操作日志 -- end*/
		return "redirect:/tabSample/search";
	}
	/**
	 * 样本批量重推
	 * @param reIds 样本id列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{reIds}/rePush", method=RequestMethod.GET)
	public String rePush(@PathVariable String reIds,HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		
		String[] ids = reIds.split(",");
		if(ids.length>0){
			for(int i=0;i<ids.length;i++){
				String sampleId = ids[i];
				tabSampleService.rePush(sampleId);
				tabOperLogService.insertTabOperLog(Commons.REPUSH, tu.getUserName(), sampleId); //记录操作日志
			}
		}
		return "redirect:/tabSample/unpushList";
	}
	
	/**
	 * 查询未推送样本列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpushList",method=RequestMethod.GET)
	public String unpushList(Model model){
		TabSample t = new TabSample();
		
		t.setPageSize(20);
		Integer totalCount = tabSampleService.getUnpushTotalCount(t);
		Page page = new Page(1, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabSample> sampleList = tabSampleService.selectUnpushTabSamples(t);
		List<TabAdvTypes> advTypesListL = tabAdvTypesService.selectByLevel(3);
		List<TabAdvTypes> advTypesListM = tabAdvTypesService.selectByLevel(2);
		List<TabAdvTypes> advTypesListT = tabAdvTypesService.selectByLevel(1);
		model.addAttribute("sampleList", sampleList);
		model.addAttribute("advTypesListL", advTypesListL);
		model.addAttribute("advTypesListM", advTypesListM);
		model.addAttribute("advTypesListT", advTypesListT);
		model.addAttribute("page", page);
		return "sample/rePushList";
	}
	/**
	 * 查询未推送样本列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/unpushList",method=RequestMethod.POST)
	public String unpushList(@RequestParam String mainName,@RequestParam String isAdv,@RequestParam String largeType,
			@RequestParam String middleType, @RequestParam String tinyType,@RequestParam String createrName,@RequestParam Integer currPage, Model model){
		
		TabSample t = new TabSample();
		if(null != mainName && !"".equals(mainName)){
			t.setMainName(mainName.trim());
		}
		if(null != isAdv && !"".equals(isAdv)){
			t.setIsAdv(Integer.parseInt(isAdv));
		}
		if(null != largeType && !"".equals(largeType)){
			t.setLargeType(largeType);
		}
		if(null != middleType && !"".equals(middleType)){
			t.setMiddleType(middleType);
		}
		if(null != tinyType && !"".equals(tinyType)){
			t.setMiddleType(tinyType);
		}
		if(null != createrName && !"".equals(createrName)){
			t.setCreaterName(createrName.trim());
		}
		t.setPageSize(20);
		Integer totalCount = tabSampleService.getUnpushTotalCount(t);
		Page page = new Page(currPage, t.getPageSize(), totalCount);
		t.setStartIndex(page.getStartIndex());
		List<TabSample> sampleList = tabSampleService.selectUnpushTabSamples(t);
		model.addAttribute("sampleList", sampleList);
		
		List<TabAdvTypes> advTypesListL = tabAdvTypesService.selectByLevel(3);
		List<TabAdvTypes> advTypesListM = tabAdvTypesService.selectByLevel(2);
		List<TabAdvTypes> advTypesListT = tabAdvTypesService.selectByLevel(1);
	
		model.addAttribute("advTypesListL", advTypesListL);
		model.addAttribute("advTypesListM", advTypesListM);
		model.addAttribute("advTypesListT", advTypesListT);
		model.addAttribute("page", page);
		model.addAttribute("mainName", mainName);
		model.addAttribute("isAdv", isAdv);
		model.addAttribute("createrName", createrName);
		model.addAttribute("largeType", largeType);
		model.addAttribute("middleType", middleType);
		model.addAttribute("tinyType", tinyType);
		return "sample/rePushList";
	}
	
	@RequestMapping(value="/{sampleId}/update", method=RequestMethod.GET)
	public String update(@PathVariable String sampleId, Model model){
		TabSample tabSample = tabSampleService.selectObjById(sampleId);
		List<TabAdvTypes> advTypesList = tabAdvTypesService.selectByLevel(3);
		model.addAttribute("advTypesList", advTypesList);
		model.addAttribute("tabSample", tabSample);
		return "sample/updateSample";
	}
	
	@RequestMapping(value="/{sampleId}/update", method=RequestMethod.POST)
	public String update(TabSample tabSample,HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		tabSample.setLastModifier(tu.getUserName());
		tabSample.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
		int result = tabSampleService.updateTabSample(tabSample);
		/*记录操作日志 -- start*/
		if(result > 0){
			tabOperLogService.insertTabOperLog(Commons.UPDSAMPLE, tu.getUserName(), tabSample.getSampleId());
		}
		/*记录操作日志 -- end*/
		return "redirect:/tabSample/search";
	}
	
	/**
	 * 逻辑删除
	 * @param sampleId
	 * @return
	 */
	@RequestMapping(value="/{sampleId}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable String sampleId, HttpServletRequest request){
		int result = tabSampleService.deleteTabSample(sampleId);
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		/*记录操作日志 -- start*/
		if(result > 0){
			tabOperLogService.insertTabOperLog(Commons.DELSAMPLE, tu.getUserName(), sampleId);
		}
		/*记录操作日志 -- end*/
		return "redirect:/tabSample/search";
	}
	/**
	 * 导出Excel数据
	 * @param response
	 * @param isAdv
	 * @param largeType
	 * @param middleType
	 * @param tinyType
	 * @param createrName
	 * @param mainName
	 */
	@RequestMapping(value="/exportData",method=RequestMethod.POST)
	public void exportExcel(HttpServletResponse response,@RequestParam String isAdv, @RequestParam String largeType,
			@RequestParam String middleType,@RequestParam String tinyType, @RequestParam String createrName,@RequestParam String mainName){
		response.setContentType("application/binary;charset=ISO8859_1");
		try {
			ServletOutputStream outputStream = response.getOutputStream();  
			String fileName = new String(("样本匹配流水单").getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
			excelService.exportSample(isAdv, largeType, middleType, tinyType, createrName, mainName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 异步根据样本ID得到样本数据
	 * @param sampleId  样本ID
	 * @return
	 */
	@RequestMapping(value="/ajaxFindSampleById",method=RequestMethod.POST)
	public @ResponseBody String ajaxFindSampleById(String sampleId){
		if(null != sampleId && !"".equals(sampleId)){
			TabSample tabSample = new TabSample();
			tabSample = tabSampleService.selectObjById(sampleId);
			return JSONArray.fromObject(tabSample).toString();
		}
		return "";
	}
	@RequestMapping(value="/ajaxFindSamples",method=RequestMethod.GET)
	public @ResponseBody String ajaxFindSamples(){
		List<TabSample> tlist = new ArrayList<TabSample>();
		String content = "";
		content += "[";
		TabSample t = new TabSample();
		tlist = tabSampleService.selectTabSamples(t);
		if(null != tlist && tlist.size()>0){
			for(int i=0;i<tlist.size();i++){
				content += "\""+tlist.get(i).getLength()+"#"
						+tlist.get(i).getMainName()+"#"
						+tlist.get(i).getSecondName()+"#"
						+tlist.get(i).getSampleId()+"\"";
				if(i<(tlist.size()-1)){
					content += ",";
				}
			}
		}
		content += "]";
		SampleNames sn = new SampleNames();
		sn.setNames(content);
		return JSONArray.fromObject(content).toString();
	}
	
}
