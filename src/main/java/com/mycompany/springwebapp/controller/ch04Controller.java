package com.mycompany.springwebapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch04Form1;
import com.mycompany.springwebapp.dto.Ch04Form3;
import com.mycompany.springwebapp.validate.Ch04Form1Validator;
import com.mycompany.springwebapp.validate.Ch04Form3Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch04")
public class ch04Controller {
   @RequestMapping("/content") //이렇게 요청했을때
   public String content() {
      return "ch04/content"; //jsp로 포워드해서 실행한다.
   }  
   
   @InitBinder("ch04Form1") //초기의 데이터를 바인딩 하는 어노테이션 (테이터를 처음 받을때 지정되어 있는 내용대로 바인딩 데이터를 이용을 해라), 첫자를 소문자로 한 이름으로 저장을 한다.
   public void ch04Form1Validator(WebDataBinder binder) { //intitBinder 의 매개값은 항상 WebDataBinder binder
	   binder.setValidator(new Ch04Form1Validator());
   }
   
   @PostMapping("/method1")
   //pom.xml에 validation-api 라이브러리 의존 설정 필요
   //requset.setAttribute("ch04Form1", form1); 로 자동저장
   public String method1(@Valid Ch04Form1 form1, Errors errors) { //클라이언트에서 넘어온 객체를 여기다 저장 1번 자동적으로 request에 저장을 하게되는데 
	   //errors.rejectValue(...)한번 이라도 호출 되었다면 hasErrors() 는 true 를 리턴
	   if(errors.hasErrors()) {
		   return "ch04/content"; //리턴을 하게되면 밑에 코드는 사용하지 않게 된다. (에러가 발생할 경우)
	   }
	 //요청 처리 코드 (오류를 내지 않으면 밑에 코드가 실행 되면서 홈으로 가게 된다)
	   log.info("param1: "+form1.getParam1());
	   log.info("param2: "+form1.getParam2());
	   log.info("param3: "+form1.getParam3());
	   log.info("param4: "+form1.isParam4());
	   log.info("param5: "+form1.getParam5());
	   return "redirect:/";
   }
   
   
   @InitBinder("ch04Form3") //초기의 데이터를 바인딩 하는 어노테이션 (테이터를 처음 받을때 지정되어 있는 내용대로 바인딩 데이터를 이용을 해라), 첫자를 소문자로 한 이름으로 저장을 한다.
   public void ch04Form3Validator(WebDataBinder binder) { //intitBinder 의 매개값은 항상 WebDataBinder binder
	   binder.setValidator(new Ch04Form3Validator());
   }
   
   
   @PostMapping("/join")
   //pom.xml에 validation-api 라이브러리 의존 설정 필요
   //requset.setAttribute("ch04Form1", form1); 로 자동저장
   public String method3(@Valid Ch04Form3 form3, Errors errors) { //클라이언트에서 넘어온 객체를 여기다 저장 1번 자동적으로 request에 저장을 하게되는데 
	   //errors.rejectValue(...)한번 이라도 호출 되었다면 hasErrors() 는 true 를 리턴
	   if(errors.hasErrors()) {
		   return "ch04/content"; //리턴을 하게되면 밑에 코드는 사용하지 않게 된다. (에러가 발생할 경우)
	   }
	 //요청 처리 코드 (오류를 내지 않으면 밑에 코드가 실행 되면서 홈으로 가게 된다)
	   log.info("param1: "+form3.getMid());
	   log.info("param2: "+form3.getParam2());
	   log.info("param3: "+form3.getParam3());
	   log.info("param4: "+form3.getParam4());
	   return "redirect:/";
   }
 }
