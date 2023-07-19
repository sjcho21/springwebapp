package com.mycompany.springwebapp.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //
public class HomeController {
	public HomeController(){
			log.info("실행");
	}
	
	@PostConstruct //postConstruct 어노테이션 => 이 객체가 생성되고 나서 자동적으로 실행되게 도와준다.
	public void method1() {	
		log.info("실행");
	}
	
	@PostConstruct //postConstruct 어노테이션 => 이 객체가 생성되고 나서 자동적으로 실행되게 도와준다.
	public void method2() {	
		log.info("실행");
	}
	
	@PreDestroy //객체가 없어지는 시점에서 실행 (어플리케이션이 종료가 될때 => 실행을 서버에서 안한다는 이야기)
	public void method3() {
		log.info("실행");
	}
		//http://localhost:8080/springwebapp/
		@RequestMapping("/") //conroller에 requestMapping 을 작성하면 anootation에서 정보를 보관해서 controller 에 쏴준다.
		public String index() {
			log.info("실행1");
			return "index";
		}
}
