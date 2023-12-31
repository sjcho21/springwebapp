package com.mycompany.springwebapp.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.exception.Ch10SoldOutException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch10")
public class Ch10Controller {
   
   @RequestMapping("/content")
   public String content() {
      return "ch10/content";
   }
   
   @GetMapping("/handlingException1")                        //controller 에서 try catch를 붙이지 않아도 되는 이유(예외 controlleradvice 에서 예외 내용이 바뀌게 되면 처리가 가능하다)
   public String handlingException1(String data) {
      try {
         if(data.equals("java")) {
            // NullPointerException 발생
         } 
      } catch(NullPointerException e) {
         return "ch10/500_null";
      }
      return "redirect:/ch10/content";
   }
   
   @GetMapping("/handlingException2")
   public String handlingException2(String data) {
      if(data.equals("java")) {
         // NullPointerException 발생
      } 
      return "redirect:/ch10/content";
   }
   
   @GetMapping("/handlingException3")
   public String handlingException3() {
      Object data = "abc";
      Date date = (Date) data;
      // ClassCastException 발생
      return "redirect:/ch10/content";
   }
   
   
   @GetMapping("/handlingException4")
   public String handlingException4() {
      int stock=0;
      if(stock == 0) {
    	  throw new Ch10SoldOutException("이벤트 상품 재고가 없음");
      }
      return "redirect:/ch10/content";
   }
   
   
   @GetMapping("/handlingException5")
   public String handlingException5() {
      String data = "abc";
     int number = Integer.parseInt(data);
     //NumberFormatException 발생
      return "redirect:/ch10/content";
   }
   
}