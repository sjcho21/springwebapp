package com.mycompany.springwebapp.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springwebapp.dto.Ch03Dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch03")
public class ch03Controller {
   
   @RequestMapping("/content") //이렇게 요청했을때
   public String content() {
      return "ch03/content"; //jsp로 포워드해서 실행한다.
   }  
   
   @GetMapping("/method1")
   public String method1( //기본적으로 매개변수는 다 string 이 원칙이나 날짜라는 기본 타입으로 받고 싶으면 
		   String param1, 
		   @RequestParam(defaultValue="0") int param2, //넘어 올때 다 문자열로 넘어옴으로  requestparam=> default 값을 줄때
		   @RequestParam(defaultValue="0.0") double param3, //값이 넘어오지 않을 경우를 대비하여 default value 를 주어야 한다
		   @RequestParam(defaultValue="false") boolean param4, 
		   @DateTimeFormat(pattern="yyyy-MM-dd") Date param5) /*넘어온 문자열을 날짜로 변환*/ {
	   log.error("param1: " + param1);
	   log.error("param2: " + param2);
	   log.error("param3: " + param3);
	   log.error("param4: " + param4);
	   log.error("param5: " + param5);
	   return "redirect:/ch03/content";
   }
   
   
   @GetMapping("/method2")
   public String method2( //기본적으로 매개변수는 다 string 이 원칙이나 날짜라는 기본 타입으로 받고 싶으면 
		   @RequestParam(value="param1") String name ,//param1로 넘어오는 값을 arg1 에 넣고 싶다
		   @RequestParam(value="param2", defaultValue="0") int arg2, //넘어 올때 다 문자열로 넘어옴으로  
		   @RequestParam(defaultValue="0.0") double param3, //값이 넘어오지 않을 경우를 대비하여 default value 를 주어야 한다
		   @RequestParam(defaultValue="false") boolean param4, 
		   @DateTimeFormat(pattern="yyyy-MM-dd") Date param5) /*넘어온 문자열을 날짜로 변환*/ {
	   log.error("등록한 회원의 이름: " + name);
	   log.error("arg2: " + arg2);
	   log.error("param3: " + param3);
	   log.error("param4: " + param4);
	   log.error("param5: " + param5);
	   return "redirect:/ch03/content";
   }
   
   @PostMapping("/method3")
   public String method3( //기본적으로 매개변수는 다 string 이 원칙이나 날짜라는 기본 타입으로 받고 싶으면 
		   @RequestParam(required=true)String param1, // required true 추가(문자열은 null이 들어오면 안되니 필수조건 true를 해준다(null이 들어오면 오류를 내겠다)
		   @RequestParam(defaultValue="0") int param2, 
		   @RequestParam(defaultValue="0.0") double param3,
		   @RequestParam(defaultValue="false") boolean param4, 
		   @DateTimeFormat(pattern="yyyy-MM-dd") Date param5) /*넘어온 문자열을 날짜로 변환*/ {
	   log.error("param1: " + param1);
	   log.error("parma2: " + param2);
	   log.error("param3: " + param3);
	   log.error("param4: " + param4);
	   log.error("param5: " + param5);
	   return "redirect:/ch03/content";
   }
   
   @RequestMapping("/method4")
   public void method4(Ch03Dto dto, HttpServletResponse response) throws Exception { //객체로 받을수 있다.
	   log.error("param1: " + dto.getParam1());
	   log.error("parma2: " + dto.getParam2());
	   log.error("param3: " + dto.getParam3());
	   log.error("param4: " + dto.isParam4()); //boolean은 is로 시작
	   log.error("param5: " + dto.getParam5());
	 
	   JSONObject root = new JSONObject();//제이슨 객체를 만든후
	   root.put("result", "success");//result, success 라는 제이슨 데이터를 넣어준다.
	   String json = root.toString();
	   
	   response.setContentType("application/json; charset=UTF-8");
	   PrintWriter pw = response.getWriter();
	   pw.print(json);
	   pw.flush();
	   pw.close();
   }
   
   @RequestMapping("/method5")
   public void method5(@RequestBody Ch03Dto dto, HttpServletResponse response) throws Exception { //객체로 받을수 있다.
	   log.error("param1: " + dto.getParam1());
	   log.error("parma2: " + dto.getParam2());
	   log.error("param3: " + dto.getParam3());
	   log.error("param4: " + dto.isParam4()); //boolean은 is로 시작
	   log.error("param5: " + dto.getParam5());
	 
	   JSONObject root = new JSONObject();
	   root.put("result", "success");
	   String json = root.toString();
	   
	   response.setContentType("application/json; charset=UTF-8");
	   PrintWriter pw = response.getWriter();
	   pw.print(json);
	   pw.flush();
	   pw.close();
   }
}