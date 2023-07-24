package com.mycompany.springwebapp.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //실행 중일 때만 사용이 된다.
@Target({ElementType.METHOD}) //메소드 위에다 쓰는 어노테이션 이다.
public @interface Login { //auth라는 인터페이스를 만든후
	public String value() default "Login";
}