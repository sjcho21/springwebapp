package com.mycompany.springwebapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch13BoardDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

@Service //dao가 db에서 받아온 데이터를 전달받아 가공하라는 의미
public class Ch13BoardServiceImpl implements Ch13BoardService{
   
   @Autowired
   private Ch13BoardDao boardDao; //생성자 주입방식(생성자에 @Autowired 를 붙혀서 컨테이너에서 주입 당한다.)
   
   /*@Autowired
   private Ch13BoardDao boardDaoOld;*/
   
   @Override
   public void write(Ch13Board board) { 
      //boardDaoOld.insert(board);
      boardDao.insert(board);
   }

   @Override
   public void remove(int bno) { //board.xml의 게시판 삭제 메소드를 호출한다.
      //boardDaoOld.deleteByBno(bno);
      boardDao.deleteByBno(bno); //int를 반환해야 하므로 return값에서 mapper의 메소드를 호출한다.
   }

   @Override
   public void modify(Ch13Board board) { //board서비스에 있는 메서드를 호출한다 
      //boardDaoOld.updateByBno(board);
      boardDao.updateByBno(board); //boardDao에 updateByBno 메서드를 호출한다. board매개 변수를 넘겨준다.
   }

   @Override
   public List<Ch13Board> getList(Ch13Pager pager) {
      //List<Ch13Board> boardList = boardDaoOld.selectByPage(pager);
      List<Ch13Board> boardList = boardDao.selectByPage(pager);
      return boardList;
   }

   @Override
   public Ch13Board getBoard(int bno) {
      //Ch13Board board = boardDaoOld.selectByBno(bno);
      Ch13Board board = boardDao.selectByBno(bno);
      return board;
   }

   @Override
   public void addHitCount(int bno) {  //리턴값이 없으므로 void
      //Ch13Board board = boardDaoOld.selectByBno(bno);
      Ch13Board board = boardDao.selectByBno(bno);
      board.setBhitcount(board.getBhitcount()+1);
      //boardDaoOld.updateByBno(board);
      boardDao.updateByBno(board);
   }
   
   @Override
   public int getTotalBoardNum() {
      int totalBoardNum = boardDao.count();
      return totalBoardNum;
   }

}