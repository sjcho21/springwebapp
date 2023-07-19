package com.mycompany.springwebapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository //reopsitory 를 붙이게 되면 dao객체가 만들어 지게 된다.
public class Ch01MemberDao {
	public Ch01MemberDao() {
		log.info("실행");
	}
}
