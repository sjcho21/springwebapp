package com.mycompany.springwebapp.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Member;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.interceptor.Auth;
import com.mycompany.springwebapp.interceptor.Login;
import com.mycompany.springwebapp.interceptor.Auth.Role;
import com.mycompany.springwebapp.service.Ch13BoardService;
import com.mycompany.springwebapp.service.Ch13MemberService;
import com.mycompany.springwebapp.service.Ch13MemberService.JoinResult;
import com.mycompany.springwebapp.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
   
   /*@Resource
   private Ch13BoardDaoOldImpl boardDaoOld;
   
   @Autowired //resource 도 사용가능
   private Ch13BoardDao boardDao; //인터페이스인데 구현객체가 없다. -> mybatis 가 자동으로 생성한 구현객체라는 뜻
*/   
   
   @Resource
   private Ch13BoardService boardService;//Controller은 Service를 사용한다.
   
   @Autowired
   private Ch13MemberService memberService;
   
   //dispatcher 쪽에도 13-properties.xml 설정 파일이 있어야 됨
   //<context:property-placeholder ../> 가 WebApplicationContext 단위로 사용되기 때문
   
   @Value("${file.upload.dir}")
   private String fileUploadDir ;
   
   @RequestMapping("/content")
   public String content(HttpSession session) {
     log.info("실행");
      return "ch13/content";
   } 
   
   
   @GetMapping("/getBoardList")
   public String getBoardList(@RequestParam(defaultValue="1") int pageNo, Model model) {
      //int totalRows = boardDao.count();//전체 게시물 수
      int totalBoardNum = boardService.getTotalBoardNum();
      Ch13Pager pager = new Ch13Pager(10,5,totalBoardNum,pageNo);
      
     /* List<Ch13Board> list = boardDaoOld.selectAll(pager);*/
      //List<Ch13Board> list = boardDao.selectByPage(pager); 
      List<Ch13Board> list = boardService.getList(pager); 
      
      model.addAttribute("pager", pager);
      model.addAttribute("boards", list);
      
      
      return "ch13/boardList";
   }
   
   @GetMapping("detailBoard")
   public String detailBoard(int bno, Model model) {
      Ch13Board board = boardService.getBoard(bno); //서비스를 거친후 리턴 값을 board에다 담아준다
      model.addAttribute("board", board); //jsp에 뿌려주기 위해서 model 객체에 담는다.
      
      if(board.getBattachdata() != null) { //보드에 모든 값들이 들어가 있다.
    	  //0과 1로 구성된 바이너리 데이터를 base64 문자열로 변환
    	  String base64Img = Base64.getEncoder().encodeToString(board.getBattachdata());
    	  model.addAttribute("base64Img", base64Img);
    	  
      }
      
      return "ch13/detailBoard";
   }
   
   @GetMapping("/filedownload1")
   public void filedownload(int bno, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Ch13Board board = boardService.getBoard(bno);
	   
	  String fileOriginalName = board.getBattachoname();
	  String filesaveName = board.getBattachsname();
      String filePath = fileUploadDir + "/" + filesaveName;
      
      // 응답 헤드에 Content-Type 추가
      String mimeType = board.getBattachtype();
      response.setContentType(mimeType);
      
      // 응답 헤드에 한글 이름의 파일명을 ISO-8859-1 문자셋으로 인코딩해서 추가
      String userAgent = request.getHeader("User-Agent");
      if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
         // IE
    	  fileOriginalName = URLEncoder.encode(fileOriginalName, "UTF-8");
         log.info("IE : " + fileOriginalName);
         
      } else {
         // Chrome, Edge, FireFox, Safari
    	  fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
      }
      
       response.setHeader("Content-Disposition", "attachment; filename=\""+fileOriginalName+"\"");
      
       
      // 응답 본문에 파일 데이터 싣기
      OutputStream os = response.getOutputStream();   // 바이너리 데이터
      Path path = Paths.get(filePath);
      Files.copy(path, os);
      os.flush();
      os.close();
   }
   
   @GetMapping("/filedownload2")
   public void filedownload2(int bno, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   Ch13Board board = boardService.getBoard(bno);
	   
	  String fileOriginalName = board.getBattachoname();
      
      // 응답 헤드에 Content-Type 추가
      String mimeType = board.getBattachtype();
      response.setContentType(mimeType);
      
      // 응답 헤드에 한글 이름의 파일명을 ISO-8859-1 문자셋으로 인코딩해서 추가
      String userAgent = request.getHeader("User-Agent");
      if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
         // IE
    	  fileOriginalName = URLEncoder.encode(fileOriginalName, "UTF-8");
         log.info("IE : " + fileOriginalName);
         
      } else {
         // Chrome, Edge, FireFox, Safari
    	  fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
      }
      
       response.setHeader("Content-Disposition", "attachment; filename=\""+fileOriginalName+"\"");
      
       
      // 응답 본문에 파일 데이터 싣기
      OutputStream os = response.getOutputStream();   // 바이너리 데이터
      os.write(board.getBattachdata());
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
   
   @GetMapping("writeBoard") //get방식의 writeBoard
   @Login
   public String writeBoardForm() {
      
      return "ch13/writeBoardForm";
   }
   
   
   @PostMapping("/writeBoard") //post 방식의 writeBoard
   @Login
   public String writeBoard(Ch13Board board, HttpSession session) throws Exception {
      Ch13Member member = (Ch13Member) session.getAttribute("ch13Login");
      board.setMid(member.getMid());
      
      MultipartFile mf = board.getBattach();
      if(!mf.isEmpty()) {
         //브라우저에서 선택한 파일 이름으로 저장
         board.setBattachoname(mf.getOriginalFilename());
         //파일의 형식(MIME 타입)을 설정 (image/jpeg, image/png  등등)
         board.setBattachtype(mf.getContentType());
         
         //방법1 (첨부파일을 서버파일 시스템에 저장)
       
         String saveFileName = new Date().getTime() +"-" + mf.getOriginalFilename();
         board.setBattachsname(saveFileName);
         
         File file = new File(fileUploadDir  + "/" + saveFileName);
         mf.transferTo(file);
         
         
         
         //방법2 (첨부파일을 DB에 직접 저장)
        // board.setBattachdata(mf.getBytes()); //파일의 데이터를 byte 배열로 저장을 하는데 파일의 크기가 크면 클수록 메모리를 더 많이 차지한다 
      }
      
      boardService.write(board);
      
      //실제로 저장된 bno : 바로 board 에서 받아올수있다. 위에서 insert를 먼저 실행해야지 얻을수있다!
      log.info("저장된 bno: "+ board.getBno()); 
   
      return "redirect:/ch13/getBoardList";
   }
   
   @GetMapping("/updateBoard")
   public String updateBoard() {
      //Ch13Board board = boardDaoOld.selectByBno(1);
      //Ch13Board board = boardDao.selectByBno(27);
      Ch13Board board = boardService.getBoard(1);
      board.setBtitle("하하");
      board.setBcontent("업데이트 내용");
      
      boardService.modify(board);
      return "redirect:/ch13/content";
   }
   
   @GetMapping("/deleteBoard")
   public String deleteBoard(int bno) {
     // boardDaoOld.deleteByBno(bno);
     // boardDao.deleteByBno(bno);
      boardService.remove(bno);
      return "redirect:/ch13/content";
   }
   
   @GetMapping("/join")
   public String joinForm() {
      return "ch13/joinForm";
   }
   
   @PostMapping("/join")
   public String join( Ch13Member member, Model model) {
	   log.info("처리전의 member 값" + member); //처리전의 
      JoinResult result = memberService.join(member);
      log.info("처라후의 멤버값" + member);  //처리 후의 member 값
      log.info("처리후의 result" + result); //처리 전의 result 값 
      if(result == JoinResult.FAIL_DUPLICATED_MID) {
         String error = "중복된 MID가 존재합니다";
         model.addAttribute("error",error);
         return "ch13/joinForm";
      }else {
         memberService.join(member);
         return "redirect:/ch13/content";
      }
   }
   
   @GetMapping("/login") //이 get은 뭐고
   public String loginForm() {
      return "ch13/loginForm";
   }
   
   @PostMapping("/login") //post 는 무엇인가
   public String login(Ch13Member member, Model model, HttpSession session) {
      LoginResult result = memberService.login(member);
      String error = "";
      if(result == LoginResult.FAIL_MID) {
         error="MID가 없습니다.";
      }else if(result ==LoginResult.FAIL_ENABLED) {
         error="MID가 비활성화 되어 있습니다.";
      }else if(result ==LoginResult.FAIL_MPASSWORD) {
         error="MPASSWORD가 틀립니다.";
      }else {
         Ch13Member dbMember = memberService.getMember(member.getMid());
         session.setAttribute("ch13Login", dbMember);
         return "redirect:/ch13/content";
      }
      
      model.addAttribute("error", error);
      
      return "ch13/loginForm";
   }
   
   @GetMapping("/logout")
   public String logout(HttpSession session) {
      session.removeAttribute("ch13Login");
      return "redirect:/ch13/content";
   }
   
   
}