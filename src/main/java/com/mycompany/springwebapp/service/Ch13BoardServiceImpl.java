package com.mycompany.springwebapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch13BoardDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

@Service
public class Ch13BoardServiceImpl implements Ch13BoardService{
   
   @Autowired
   private Ch13BoardDao boardDao;
   
   /*@Autowired
   private Ch13BoardDao boardDaoOld;*/
   
   @Override
   public void write(Ch13Board board) {
      //boardDaoOld.insert(board);
      boardDao.insert(board);
   }

   @Override
   public void remove(int bno) {
      //boardDaoOld.deleteByBno(bno);
      boardDao.deleteByBno(bno);
   }

   @Override
   public void modify(Ch13Board board) {
      //boardDaoOld.updateByBno(board);
      boardDao.updateByBno(board);
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
   public void addHitCount(int bno) {
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