package com.mycompany.springwebapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycompany.springwebapp.dto.Ch08Member;
import com.mycompany.springwebapp.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CH13LoginCheckInterceptor implements HandlerInterceptor {
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { //prehandle 컨트롤러 요청 전
	
	//log.info("실행");
	
	//요청 처리 메소드가 @Auth가 붙어있는지 확인
	HandlerMethod handlerMethod = (HandlerMethod) handler; 
	Login login = handlerMethod.getMethodAnnotation(Login.class); //auth의 클래스가 뭔지 알아내는 방법(user 인지 admin인지 들어가 있는지 없는지)
	
	//@Login이 붙어 있다면
	if(login != null) {
		HttpSession session = request.getSession(); //서버에 생성된 세션이 있다면 세션을 반환하고, 없다면 새 세션을 생성해서 반환 
		Ch13Member member = (Ch13Member)session.getAttribute("ch13Login");  //name를 이용해 세션의 값을 조회
		//로그인이 되었다면
		if(member != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath()+ "/ch13/login"); //로그인이 되어있지 않으면 로그인 페이지로 이동 ,http://localhost:8080/springwebapp/ch13/loginㅇ
			return false;
		}
	}else {
		//@Login이 붙어있지 않다면 (하던 작업 마저 해라)
		return true;
	}
  }
}
