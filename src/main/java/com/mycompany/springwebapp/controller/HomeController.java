package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //
public class HomeController {
		//http://localhost:8080/springwebapp/
		@RequestMapping("/") //conroller에 requestMapping 을 작성하면 anootation에서 정보를 보관해서 controller 에 쏴준다.
		public String index() {
			log.info("실행1");
			return "index";
		}
}
