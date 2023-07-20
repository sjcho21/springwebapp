package com.mycompany.springwebapp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springwebapp.dto.Ch02Dto;
import com.mycompany.springwebapp.dto.Ch02FileInfo;
import com.mycompany.springwebapp.interceptor.Auth;
import com.mycompany.springwebapp.interceptor.Auth.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch02")
public class ch02Controller {
   
   @RequestMapping("/content")
   public String content() {
      return "ch02/content";
   }
   //위와 동일한 메소드!
   /*@RequestMapping("/content")
   public ModelAndView content() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("ch02/content");
      mav.addObject("data","홍길동");
      return mav;
   }*/
   
   //@GetMapping("/method")
   @RequestMapping(value="/method", method=RequestMethod.GET)
   public String getMethod(String bkind, int bno) {
      log.info("getMethod > bkind : " + bkind + " |  bno : " + bno);
      return "ch02/content";
   }
   
   //@PostMapping("/method")
   @RequestMapping(value="/method", method=RequestMethod.POST)
   public String postMethod(String bkind, int bno) {
      log.info("postMethod > bkind : " + bkind + " |  bno : " + bno);
      return "ch02/content";
   }
   
   
   /*//@RequestMapping(value="/method", method=RequestMethod.PUT)
   @PutMapping("/method")
   public void putMethod(@RequestBody String json, HttpServletResponse response) throws IOException {
      JSONObject jsonObject = new JSONObject(json);
      log.info("bkind : " + jsonObject.getString("bkind"));
      log.info("bno : " + jsonObject.getString("bno"));
      
      JSONObject root = new JSONObject();
      root.put("result", "success");
      String responseJson = root.toString();
      
      response.setContentType("application/json; charset=UTF-8");
      PrintWriter pw = response.getWriter();
      pw.print(responseJson);
      pw.flush();
      pw.close();
   }*/
   
   //@RequestMapping(value="/method", method=RequestMethod.PUT)
   @PutMapping("/method")
   public void putMethod(@RequestBody Ch02Dto dto, HttpServletResponse response) throws IOException {
      log.info("bkind : " + dto.getBkind());
      log.info("bno : " + dto.getBno());
      
      JSONObject root = new JSONObject();
      root.put("result", "success");
      String responseJson = root.toString();
      
      response.setContentType("application/json; charset=UTF-8");
      PrintWriter pw = response.getWriter();
      pw.print(responseJson);
      pw.flush();
      pw.close();
   }
   
   //@RequestMapping(value="/method", method=RequestMethod.DELETE)
   @DeleteMapping("/method")
   public void deleteMethod(@RequestBody String json, HttpServletResponse response) throws IOException {
      log.info("deleteMethod > " + json);
      
      JSONObject root = new JSONObject();
      root.put("result", "success");
      String responseJson = root.toString();
      
      response.setContentType("application/json; charset=UTF-8");
      PrintWriter pw = response.getWriter();
      pw.print(responseJson);
      pw.flush();
      pw.close();
   }
   
   
   @GetMapping("/ajax1")
   public String ajax1Method() {
      return "ch02/fragmentHtml";
   }
   
   @GetMapping("/ajax2")
   public void ajax2Method(HttpServletResponse response) throws IOException {
      JSONObject root = new JSONObject();
      root.put("fileName", "photo3.jpg");
      String responseJson = root.toString();
      
      response.setContentType("application/json; charset=UTF-8");
      PrintWriter pw = response.getWriter();
      pw.print(responseJson);
      pw.flush();
      pw.close();
   }
   
   @GetMapping(value="/ajax3", produces="application/json; charset=UTF-8")
   @ResponseBody   // 리턴 값을 응답 본문에 기입
   public String ajax3Method() {
      return new JSONObject().put("fileName", "photo4.jpg").toString();
   }
   
   @GetMapping(value="/ajax4", produces="application/json; charset=UTF-8")
   @ResponseBody   // 리턴 값을 응답 본문에 기입, jackson-databind 라이브러리 필수
   public Ch02FileInfo ajax4Method() {
      Ch02FileInfo fileinfo = new Ch02FileInfo();
      fileinfo.setFileName("photo5.jpg");
      return fileinfo;
   }
   
   @GetMapping("/fileDownload")
   public void fileDownload(HttpServletResponse response, HttpServletRequest request) throws Exception{ //호출할때 자동으로 들어온다.
      String fileName = "photo1.jpg";
      String filePath = "/resources/images/photo/"+fileName;
      filePath = request.getServletContext().getRealPath(filePath);
      log.info("filePath: "+filePath);
      
   //   InputStream is = new FileInputStream(filePath);
   //   BufferedInputStream bis = new BufferedInputStream(is); //성능향상을 위해 버퍼를 달아준다!
      
      //응답 헤드에 Content-Type 추가 
      String mimeType = request.getServletContext().getMimeType(filePath); 
      //파일의 확정명을 보고 바로 MimeType을 리턴해준다 ex)image/jpeg , application/pdf
      response.setContentType(mimeType);
      
      //한글 파일을 안깨지게끔 - 문자셋을 한번 변화를 줘야한다.
      //응답 헤드에 한글 이름의 파일명을 ISO-8859-1 문자셋으로 인코딩해서 추가
      String userAgent = request.getHeader("User-Agent");
      if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
         //IE
         fileName = URLEncoder.encode(fileName, "UTF-8"); //한글이 깨지지않게 변환
         log.info("IE: " + fileName);
      }else {
         //Chrome, Edge, FireFox, Safari
         fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1"); //한글이 깨지지않게 변환
         log.info("Chrome: " + fileName);
      }
      response.setHeader("Content-Disposition","attachment; filename=\""+ fileName +"\"");
      
      //응답 본문에 파일 데이터 싣기
      OutputStream os = response.getOutputStream();
      //InputStream is = new FileInputStream(filePath);
      Path path = Paths.get(filePath);
      Files.copy(path,os);
      os.flush();
      os.close();
   }
   
   @RequestMapping("/filterAndInterceptor")
   @Auth(Role.ADMIN)  // 이것으로 인해 이 메소드는 admin 권한이 있는 사람만 실행이 가능해짐
                  //디폴트는 유저인데 admin 일때만 사용
   public String adminMethod() {
      log.info("실행");
      return "ch02/adminPage";
   }
   
   
}