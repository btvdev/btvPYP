package com.btvpyp.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btvpyp.system.model.TabMenu;
import com.btvpyp.system.model.TabUser;
import com.btvpyp.system.service.TabMenuService;
import com.btvpyp.system.service.TabOperLogService;
import com.btvpyp.system.service.TabUserService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.Des;
import com.btvpyp.utils.StrUtils;
/**
 * 用户管理控制器类
 * @author gaobo
 *
 */
@Controller
@RequestMapping("/tabUser")
public class TabUserController {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private TabUserService tabUserService;
	@Autowired
	private TabMenuService tabMenuService;
	@Autowired
	public TabOperLogService tabOperLogService;
	
	private TabUser tabUser;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model){
		TabUser tabUser = new TabUser();
		List<TabUser> userList = tabUserService.getUsers(tabUser);
		model.addAttribute("userList", userList);
		return "system/userList";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Model model, @RequestParam String userName, @RequestParam String realName){
		TabUser tabUser = new TabUser();
		if(StrUtils.isNotEmptyStr(userName)){
			tabUser.setUserName(userName);
		}
		if(StrUtils.isNotEmptyStr(realName)){
			tabUser.setRealName(realName);
		}
		List<TabUser> userList = tabUserService.getUsers(tabUser);
		model.addAttribute("userList", userList);
		model.addAttribute("userName", userName);
		model.addAttribute("realName", realName);
		return "system/userList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model){
		TabUser tabUser = new TabUser();
		List<TabMenu> menuList = tabMenuService.getMenusByLevel(1);
		model.addAttribute("menuList", menuList);
		model.addAttribute("tabUser", tabUser);
		return "system/addUser";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(TabUser tabUser, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		Integer result = tabUserService.insertUser(tabUser);
		if(null != result){
			//记录操作日志
			tabOperLogService.insertTabOperLog(Commons.ADDUSER, tu.getUserName(), tabUser.getUserName());
		}
		return "redirect:/tabUser/search";
	}
	
	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String userId, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		if(StrUtils.isNotEmptyStr(userId)){
			tabUserService.deleteUser(Integer.parseInt(userId));
			//记录操作日志
			tabOperLogService.insertTabOperLog(Commons.DELUSER, tu.getUserName(), userId); 
		}
		return "redirect:/tabUser/search";
	}
	
	@RequestMapping(value = "/{userId}/update", method = RequestMethod.GET)
	public String update(@PathVariable String userId, Model model){
		TabUser tabUser = tabUserService.getUserById(Integer.parseInt(userId));
		List<TabMenu> menuList = tabMenuService.getMenusByLevel(1);
		model.addAttribute("menuList", menuList);
		model.addAttribute("tabUser", tabUser);
		model.addAttribute("tabUser", tabUser);
		return "system/updateUser";
	}
	
	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
	public String update(TabUser tabUser, HttpServletRequest request){
		HttpSession session = request.getSession();
		TabUser tu = (TabUser)session.getAttribute("tabUser");
		if(null != tabUser){
			tabUserService.updateUser(tabUser);
			tabOperLogService.insertTabOperLog(Commons.DELUSER, tu.getUserName(), tabUser.getUserName());
		}
		return "redirect:/tabUser/search";
	}
	
	/**
	 * 跳转到登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(Model model){
		tabUser = new TabUser();
		model.addAttribute(tabUser);
		return "system/login";
	}
	
	/**
	 * 未登录提示页
	 * @return
	 */
	@RequestMapping(value = "/notLogin", method = RequestMethod.GET)
	public String notLogin(){
		return "system/notLogin";
	}
	/**
	 * 登录验证
	 * @param model
	 * @param userName 用户名
	 * @param password 密码
	 * @param pid      频道id
	 * @param logDate  日期
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam String userName,@RequestParam String password
			,@RequestParam String logDate){
		String returnStr = "";
		TabUser user = new TabUser();
//		Des desObj = new Des();
		if(null != userName && !"".equals(userName)) {
			user.setUserName(userName);
		}
		if(null != password && !"".equals(password)) {
			user.setPassword(password);
		}
//		if(null != userName && !"".equals(userName)){
//			user.setUserName(desObj.strDec(userName, Commons.DESKEY1, Commons.DESKEY2, Commons.DESKEY3));
//		}
//		if(null != password && !"".equals(password)){
//			user.setPassword(desObj.strDec(password, Commons.DESKEY1, Commons.DESKEY2, Commons.DESKEY3));
//		}
//		if(null != pid && !"".equals(pid)){
//			pid = desObj.strDec(pid, Commons.DESKEY1, Commons.DESKEY2, Commons.DESKEY3);
//		}
		
//		if(null != logDate && !"".equals(logDate)){
//			logDate = desObj.strDec(logDate, Commons.DESKEY1, Commons.DESKEY2, Commons.DESKEY3);
//		}
		user = tabUserService.selectUser(user);
		
		//如果登录验证成功，将user对象，频道id和日期写入session
		if(null != user){
			HttpSession session = request.getSession();
			session.setAttribute("tabUser", user);
//			session.setAttribute(Commons.SESSIONPID, pid);
			session.setAttribute(Commons.SESSIONREALNAME, user.getRealName());
			session.setAttribute(Commons.SESSIONLOGDATE, logDate);
			model.addAttribute("sessionRealName", user.getRealName());
			model.addAttribute("sessionlogDate",logDate);
			returnStr = "system/main";
		}else{
			returnStr = "pyp/index";
		}
		
		return returnStr;
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){
		HttpSession session = request.getSession();
		session.removeAttribute("tabUser");
		return "redirect:/tabUser/toLogin";
	}
	
	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public String top(Model model){
		HttpSession session = request.getSession();
		TabUser tabUser = (TabUser)session.getAttribute("tabUser");
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		model.addAttribute("sessionRealName", tabUser.getRealName());
		model.addAttribute("sessionlogDate",logDate);
		return "system/top";
	}
	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public String left(Model model){
		HttpSession session = request.getSession();
		TabUser tabUser = (TabUser)session.getAttribute("tabUser");
		String menus = tabUser.getMenus();
		List<String> menuIds = new ArrayList<String>();
		if(StrUtils.isNotEmptyStr(menus)){
			String[] menuArr = menus.split(",");
			for(String m:menuArr){
				menuIds.add(m);
			}
		}
		List<TabMenu> menuList = tabMenuService.getByIds(menuIds);
		model.addAttribute("menuList", menuList);
//		model.addAttribute("sessionUserName", tabUser.getUserName());
		return "system/left";
	}
	
	@RequestMapping(value="/ajaxGetMenus",method=RequestMethod.POST)
	public @ResponseBody String ajaxGetMenus(){
		HttpSession session = request.getSession();
		TabUser tabUser = (TabUser)session.getAttribute("tabUser");
		String menus = tabUser.getMenus();
		List<String> menuIds = new ArrayList<String>();
		if(StrUtils.isNotEmptyStr(menus)){
			String[] menuArr = menus.split(",");
			for(String m:menuArr){
				menuIds.add(m);
			}
		}
		List<TabMenu> menuList = tabMenuService.getByIds(menuIds);
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
            return objectMapper.writeValueAsString(menuList);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";
	}
	
	@RequestMapping(value = "/right", method = RequestMethod.GET)
	public String right(Model model){
		HttpSession session = request.getSession();
		String logDate = (String)session.getAttribute(Commons.SESSIONLOGDATE);
		model.addAttribute("start", logDate);
		return "system/right";
	}
}
