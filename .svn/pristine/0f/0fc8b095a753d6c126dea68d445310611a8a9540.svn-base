package com.btvpyp.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.btvpyp.system.model.TabMenu;
import com.btvpyp.system.service.TabMenuService;
import com.btvpyp.utils.StrUtils;

@Controller
@RequestMapping("/tabMenu")
public class TabMenuController {
	
	@Autowired
	private TabMenuService tabMenuService;
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model){
		TabMenu tabMenu = new TabMenu();
		List<TabMenu> menuList = tabMenuService.getMenus(tabMenu);
		if(null != menuList){
			if(menuList.size()>0){
				for(TabMenu t:menuList){
					if(t.getMenuLevel()==1){
						t.setFatherName(tabMenuService.getMenuById(t.getFatherId()).getMenuName());
					}
				}
			}
		}
		model.addAttribute("menuList", menuList);
		return "system/menuList";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(Model model, @RequestParam String menuLevel, @RequestParam String menuName){
		TabMenu tabMenu = new TabMenu();
		if(StrUtils.isNotEmptyStr(menuLevel)){
			tabMenu.setMenuLevel(Integer.parseInt(menuLevel));
		}
		if(StrUtils.isNotEmptyStr(menuName)){
			tabMenu.setMenuName(menuName);
		}
		List<TabMenu> menuList = tabMenuService.getMenus(tabMenu);
		if(null != menuList){
			if(menuList.size()>0){
				for(TabMenu t:menuList){
					if(t.getMenuLevel()==1){
						t.setFatherName(tabMenuService.getMenuById(t.getFatherId()).getMenuName());
					}
				}
			}
		}
		model.addAttribute("menuList", menuList);
		model.addAttribute("menuLevel", menuLevel);
		model.addAttribute("menuName", menuName);
		return "system/menuList";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		TabMenu tabMenu = new TabMenu();
		List<TabMenu> rootMenuList = tabMenuService.getMenusByLevel(0);
		model.addAttribute("tabMenu", tabMenu);
		model.addAttribute("rootMenuList", rootMenuList);
		return "system/addMenu";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(TabMenu tabMenu, HttpServletRequest request){
		tabMenuService.insertMenu(tabMenu);
		return "redirect:/tabMenu/search";
	}
	
	@RequestMapping(value="/{menuId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String menuId){
		tabMenuService.deleteMenu(Integer.parseInt(menuId));
		return "redirect:/tabMenu/search";
	}
}
