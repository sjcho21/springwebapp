package com.mycompany.springwebapp.service;

import java.util.List;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

public interface Ch13BoardService {
	public void write(Ch13Board board);
	public void remove(int bno);
	public void modify(Ch13Board board);
	public List<Ch13Board> getList(Ch13Pager pager);
	public Ch13Board getBoard(int bno);
	public void addHitCount(int bno); //게시물에 대한 조회수만 1증가
	public int getTotalBoardNum();

}
