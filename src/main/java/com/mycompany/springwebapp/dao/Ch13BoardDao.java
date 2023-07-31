package com.mycompany.springwebapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

@Mapper
public interface Ch13BoardDao { //추상메서드 이름이 board.xml의 아이디랑 일치하여야 한다.
	public int insert(Ch13Board board); //컬럼의 값을 따로받지 말고 dto 로 받는다.
	public List<Ch13Board> selectByPage(Ch13Pager pager); //어느 매퍼 파일의 어느 쿼리문을 찾아서 실행 할 것인지
	public Ch13Board selectByBno(int bno);	
	public int updateByBno (Ch13Board board);
	public int deleteByBno(int bno); //몇개의 행이 삭제되었는지 알아야 하는경우도 있으므로 int로 
	public int count();
}

