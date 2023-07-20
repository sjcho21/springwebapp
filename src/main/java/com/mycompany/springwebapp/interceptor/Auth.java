package com.mycompany.springwebapp.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //실행 중일 때만 사용이 된다.
@Target({ElementType.METHOD}) //메소드 위에다 쓰는 어노테이션 이다.
public @interface Auth { //auth라는 인터페이스를 만든후
   public enum Role {USER, ADMIN}; //user,admin 쓰므로 enum 으로 해주고 객체이름은 role
   public Role value() default Role.USER; //value default 값을 유저로 준다.
}