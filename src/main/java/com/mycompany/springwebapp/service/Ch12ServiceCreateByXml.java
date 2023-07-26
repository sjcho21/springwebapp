package com.mycompany.springwebapp.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12ServiceCreateByXml {
	public Ch12ServiceCreateByXml() { //public 이 아니라 private 이 되면 기본 생성자가 private 이므로 오류가 난다.
			log.info("실행");
	}
	
	public static Ch12ServiceCreateByXml getInstance() { //getInstance 메소드를 통해서 객체를 만들고 리턴을 시킨다.
		log.info("실행");
		return new Ch12ServiceCreateByXml();
	}
	
	public Ch12ServiceCreateByXml getSelfObject() {
		log.info("실행");
		return new Ch12ServiceCreateByXml();
	}
	
	public void method1() {
		log.info("실행");
	}
	
	public void method2() {
		log.info("실행");
	}
}
