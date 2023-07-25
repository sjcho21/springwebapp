package com.mycompany.springwebapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch11Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch11")
public class Ch11Controller {
   @RequestMapping("/content")
   public String content(HttpSession session) {
      return "ch11/content";
   } 
   
   @GetMapping("/form1") //form을 제공  get과 post는 한쌍이고 경로가 같다 저장 이름이 같고 dto가 같다 
   public String form1(@ModelAttribute("member") Ch11Member member) {
	   member.setMid("summer");
	   member.setMname("홍길동");
	   member.setMpassword("12345");
	   member.setMnation("한국");
	   return "ch11/form1";
   }
   
   @PostMapping("/form1") //form의 데이터를 처리
   public String handleForm1(@ModelAttribute("member") Ch11Member member) { //form으로 부터 넘어오는 데이터를 받기위한 용도 모델attribur의 이름이 form1의 modelAttribute와 동일해야 한다.
	   log.info(member.toString());
	   return "redirect:/ch11/content";
   }
}