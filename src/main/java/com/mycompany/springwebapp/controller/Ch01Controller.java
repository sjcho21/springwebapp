package com.mycompany.springwebapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch01") //이 컨트롤러는 ch01 이 선택이 된다.
@Log4j

public class Ch01Controller {
	//private static final Logger log = LoggerFactory.getLogger(Ch01Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch01/content"; //redirect 로 이것이 실행되어 응답이 같다
	}
	
	@RequestMapping("/button1")
	public String button1() {
		log.info("실행");
		return "ch01/content";
	}
	
	
	@RequestMapping("/button2")
	public String button2() {
		log.info("실행");
		return "redirect:/ch01/content";
	}
}
