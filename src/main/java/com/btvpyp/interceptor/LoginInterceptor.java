package com.btvpyp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.btvpyp.model.TabUser;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String[] IGNORE_URI = {"/tabUser/toLogin","tabUser/notLogin","/tabUser/login","/tabUser/top","/tabUser/left","/tabUser/right","/resources","/sample/featureextractionresult","/add/samplematch"};
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
//        System.out.println(request.getRequestURI());
        if("/btvPYP/".equals(request.getRequestURI()) || "/btvPYP".equals(request.getRequestURI())){
        	flag = true;
        }
        String url = request.getRequestURL().toString();
//        System.out.println(">>>: " + url);
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
        	TabUser tabUser = (TabUser)request.getSession().getAttribute("tabUser");
        	if(null != tabUser){
        		flag = true;
        	}
        }
        if(flag == false){
        	response.sendRedirect("/btvPYP/tabUser/notLogin");
        }
        
        return flag;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    
    
}
