package com.mycompany.springwebapp.service;

import java.util.List;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

public interface Ch13BoardService { //메스드에 대한 추상화된 틀을 만들어 개발에 혼선이 없게끔 도와주는 역할을 한다. , 구현 클래스 타입이 바뀔수 있으므로
	public void write(Ch13Board board);
	public void remove(int bno); //게시판 번호에 대한 정보만 있으면 되기때문에 int형 변수를 파라미터로 부여한다.
	public void modify(Ch13Board board);
	public List<Ch13Board> getList(Ch13Pager pager);
	public Ch13Board getBoard(int bno);
	public void addHitCount(int bno); //게시물에 대한 조회수만 1증가
	public int getTotalBoardNum();

}
