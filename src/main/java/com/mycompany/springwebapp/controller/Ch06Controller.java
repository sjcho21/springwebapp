package com.mycompany.springwebapp.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
   @RequestMapping("/content")
   public String content() {
      log.info("실행");
      return "ch06/content";
   }
   
   @GetMapping("/forward")
   public String forward(HttpServletRequest request) {//객체를 요청할때 request에 저장하겠다.
      request.setAttribute("loginStatus", true); //로그인status 에 true를 담아준다.
      request.setAttribute("userName", "홍길동");
      return "ch06/forward1";
   }
   
   @GetMapping("/redirect")
   public String redirect(HttpServletRequest request, HttpSession session ) throws Exception {
      String userName = "홍길동";
      userName = URLEncoder.encode(userName,"UTF-8"); //한글로

      session.setAttribute("userId", "summer"); //summer를 userId에 담는다.
      
      return "redirect:/ch06/getValue?userName="+userName;//이런 get방식으로 전달해줄수도 있다
   }
   
   //요청받은 데이터를 받아오는 두가지 방법
   @GetMapping("/getValue") 
   public String getValue(String userName, HttpServletRequest request, HttpSession session) {
      log.info("userName: "+userName); //get방식으로 
      log.info("userName: "+request.getParameter("userName")); //세션의 정보를 읽어오는 방식으로
      log.info("userId: "+request.getAttribute("userId")); //세션의 정보를 읽어오는 방식으로
      return "ch06/content";
   }
}