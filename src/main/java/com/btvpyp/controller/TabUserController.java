package com.btvpyp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btvpyp.model.TabUser;
import com.btvpyp.service.TabUserService;
import com.btvpyp.utils.Commons;
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
	
	private TabUser tabUser;
	/**
	 * 跳转到登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(Model model){
		tabUser = new TabUser();
		model.addAttribute(tabUser);
		return "pyp/login";
	}
	/**
	 * 未登录提示页
	 * @return
	 */
	@RequestMapping(value = "/notLogin", method = RequestMethod.GET)
	public String notLogin(){
		return "pyp/notLogin";
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
			,@RequestParam String pid,@RequestParam String logDate){
		String returnStr = "";
		TabUser user = new TabUser();
		user.setUserName(userName);
		user.setPassword(password);
		user = tabUserService.selectUser(user);
		
		//如果登录验证成功，将user对象，频道id和日期写入session
		if(null != user){
			HttpSession session = request.getSession();
			session.setAttribute("tabUser", user);
			session.setAttribute(Commons.SESSIONPID, pid);
			session.setAttribute(Commons.SESSIONLOGDATE, logDate);
			returnStr = "pyp/main";
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
		return "pyp/top";
	}
	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public String left(Model model){
		HttpSession session = request.getSession();
		TabUser tabUser = (TabUser)session.getAttribute("tabUser");
		model.addAttribute("sessionUserName", tabUser.getUserName());
		return "pyp/left";
	}
	@RequestMapping(value = "/right", method = RequestMethod.GET)
	public String right(){
		return "pyp/right";
	}
}
