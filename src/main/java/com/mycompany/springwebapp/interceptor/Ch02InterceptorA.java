package com.mycompany.springwebapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch02InterceptorA implements HandlerInterceptor{//핸들러 안에는 prehand 전처리(컨트롤러 시작전), posthandle(후처리), afterhandel(완료처리)

	@Override //전처리
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("실행");
		return true;
	}
	
	/*@Override //후처리
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//log.info("실행");
	}*/
	
	/*@Override //완료처리
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//log.info("실행");
	}*/
}
