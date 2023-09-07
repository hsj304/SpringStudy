package com.sist.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter{

	/*
	 *	디스패처 	====> 	핸들러매핑 ====>  
	 *		   프리핸들	@겟매핑
	 *					  ㅣ
	 *					뷰리졸버
	 *					  ㅣ
	 *					 JSP
	 *
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("preHandle call");
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				String key=cookies[i].getName();
				if(key.equals("id")) {
					String id=cookies[i].getValue();
					request.setAttribute("id", id);
					break;
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	
	
}
