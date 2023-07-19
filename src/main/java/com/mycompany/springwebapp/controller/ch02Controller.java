package com.mycompany.springwebapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch02")
public class ch02Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch02/content";
	}
	
	//@GetMapping("/method") //requestmapping 으로 되어 있으므로 get/post 방식 상관없이 요청 하게되면 무조건 실행한다.
	@RequestMapping(value="/method", method=RequestMethod.GET)
	public String method1(String bkind, int bno) {
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);
		return "ch02/content";
	}
	
	//@PostMapping("/method") 
	@RequestMapping(value="/method", method=RequestMethod.POST)
	public String method2(String bkind, int bno) {
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);
		return "ch02/content";
	}
	
	//@PutMapping("/method")
	@RequestMapping(value="/method", method=RequestMethod.PUT)	
	public void method3(@RequestBody String json, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject(json);
		String bkind = jsonObject.getString("bkind");
		int bno = jsonObject.getInt("bno");
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);
		
		JSONObject root = new JSONObject();
		root.put("result", "success");
		String responseJson = root.toString(); //{"result":"success"}
	
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();
		pw.close();
		
	}
	
	//@DeleteMapping("/method")
		@RequestMapping(value="/method", method=RequestMethod.DELETE)	
		public void method4(@RequestBody String json, HttpServletResponse response) throws Exception {
			JSONObject jsonObject = new JSONObject(json);
			int bno = jsonObject.getInt("bno");
			log.info("bno: " + bno);
			
			JSONObject root = new JSONObject();
			root.put("result", "success");
			String responseJson = root.toString(); //{"result":"success"}
		
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.print(responseJson);
			pw.flush();
			pw.close();
		}
}
