package com.btvpyp.advType.controller;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btvpyp.advType.model.TabAdvTypes;
import com.btvpyp.advType.service.TabAdvTypesService;
/**
 * 广告分类管理控制器
 * @author gaobo
 *
 */
@Controller
@RequestMapping("/tabAdvTypes")
public class TabAdvTypesController {
	@Autowired
	public TabAdvTypesService tabAdvTypesService;
	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model){
		TabAdvTypes ta = new TabAdvTypes();
		List<TabAdvTypes> advTypesList = tabAdvTypesService.selectAdvTypes(ta); 
		if(null != advTypesList && advTypesList.size()>0){
			for(int i=0;i<advTypesList.size();i++){
				Integer indexFatherId = advTypesList.get(i).getFatherId();
				for(int j=0;j<advTypesList.size();j++){
					if(indexFatherId == advTypesList.get(j).getTypeId()){
						advTypesList.get(i).setFatherName(advTypesList.get(j).getTypeName());
					}
				}
			}
		}
		
		model.addAttribute("advTypesList", advTypesList);
		return "pyp/advTypesList";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(@RequestParam String level, @RequestParam String typeName, Model model){
		TabAdvTypes ta = new TabAdvTypes();
		if(null != level && !"".equals(level)){
			ta.setLevel(Integer.parseInt(level));
		}
		if(null != typeName && !"".equals(typeName)){
			ta.setTypeName(typeName);
		}
		List<TabAdvTypes> advTypesList = tabAdvTypesService.selectAdvTypes(ta);
		
		if(null != advTypesList && advTypesList.size()>0){
			for(int i=0;i<advTypesList.size();i++){
				Integer indexFatherId = advTypesList.get(i).getFatherId();
				for(int j=0;j<advTypesList.size();j++){
					if(indexFatherId == advTypesList.get(j).getTypeId()){
						advTypesList.get(i).setFatherName(advTypesList.get(j).getTypeName());
					}
				}
			}
		}
		
		model.addAttribute("level", level);
		model.addAttribute("typeName", typeName);
		model.addAttribute("advTypesList", advTypesList);
		return "pyp/advTypesList";
	}
	/**
	 * 删除广告分类
	 * @param typeId
	 * @return
	 */
	@RequestMapping(value="/{typeId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String typeId){
		tabAdvTypesService.deleteTabAdvTypes(Integer.parseInt(typeId));
		return "redirect:/tabAdvTypes/search";
	}
	/**
	 * 跳转到新增广告分类页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		TabAdvTypes tabAdvTypes = new TabAdvTypes();
		model.addAttribute("tabAdvTypes", tabAdvTypes);
		return "pyp/addAdvTypes";
	}
	/**
	 * 新增广告分类
	 * @param tabAdvTypes
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(TabAdvTypes tabAdvTypes){
		Integer result = tabAdvTypesService.insertTabAdvTypes(tabAdvTypes);
		return "redirect:/tabAdvTypes/search";
	}
	/**
	 * 按分级查询
	 * @param level
	 * @return
	 */
	@RequestMapping(value="/selectByLevel",method=RequestMethod.POST)
	public @ResponseBody String selectByLevel(@RequestParam String level){
		List<TabAdvTypes> list = tabAdvTypesService.selectByLevel(Integer.parseInt(level)+1);
		return JSONArray.fromObject(list).toString();
	}
	/**
	 * 根据父级ID查询
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(value="/selectByFatherId",method=RequestMethod.POST)
	public @ResponseBody String selectByFatherId(@RequestParam String fatherId){
		TabAdvTypes tt = new TabAdvTypes();
		tt.setFatherId(Integer.parseInt(fatherId));
		List<TabAdvTypes> list = tabAdvTypesService.selectAdvTypes(tt);
		if(null != list && list.size()>0){
			for(int i=0;i<list.size();i++){
				Integer indexFatherId = list.get(i).getFatherId();
				for(int j=0;j<list.size();j++){
					if(indexFatherId == list.get(j).getTypeId()){
						list.get(i).setFatherName(list.get(j).getTypeName());
					}
				}
			}
		}
		return JSONArray.fromObject(list).toString();
	}
}