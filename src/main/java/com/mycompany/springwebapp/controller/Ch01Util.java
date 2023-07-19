package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //spring framework 가 제공해주는 어노테이션 으로서 이것을 붙이게 되면 객체가 생성이 된다,
public class Ch01Util {
	public Ch01Util() {
		log.info("실행");
	}
}
