package com.mycompany.springwebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}

	@GetMapping("/getHeaderValue")
	public String getHeaderValue(@RequestHeader("User-Agent") String userAgent,
				HttpServletRequest request) { //매개변수로 header 값을 넘겨준다,헤더 이름하고 매개변수의 이름이 같으면 자동 저장 되지만 다르면 @requestHeader 안에 헤더값을 넣어준다. 
		log.info("실행");
		log.info("Client IP:" + request.getRemoteAddr()); //클라이언트의 주소 얻기 HttpServletRequest를 추가해준다.
		log.info("User-Agent: " + userAgent);
		return "redirect:/ch05/content";
	}
	
	@RequestMapping(value="/createCookie", method=RequestMethod.GET)
	public String createCookie(HttpServletResponse response) {
		log.info("실행");
		
		Cookie cookie = new Cookie("useremail", "summer@mycompany.com");
		cookie.setDomain("localhost"); // 쿠키를 재전송할 서버를 지정
		cookie.setPath("/"); 		   //쿠키를 재전송할 경로를 지정
		cookie.setMaxAge(30*60);	   //쿠키의 저장 기간 (단위:초) 메모리에서만 쿠키가 존재하기 때문에 브라우저를 종료하게 되면 쿠키가 다 날라간다, 시간을 지정안할 경우 : 브라우저 메모리 저장
		cookie.setHttpOnly(false);     //쿠키는 기본적으로 서버가 이용을 한다, true일 경우에는 서버만 이용을 하고 false 는 자바스크립트 에서 접근을 허용 
		cookie.setSecure(false); 	   //false: http, https 모두 쿠키를 재전송 true 면 https 만 사용 가능해진다. (보안이 된 상태에서만)
		response.addCookie(cookie);		
	
		return "redirect:/ch05/content";
		}
	
	@RequestMapping(value="/getCookie", method=RequestMethod.GET)
	public String getCookie(@CookieValue("useremail") String userEmail) { //이름이 다를 경우 쿠키의 이름을 명시해 준다.
		log.info("실행");
		log.info("useremail: " + userEmail);
		return "redirect:/ch05/content";
		}
}
