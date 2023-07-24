package com.mycompany.springwebapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Ch04Form3 {
	private String mid; //파라미터의 이름과 필드의 이름이 동일할 경우에 자동으로 저장이 된다.
	private String param2;
	private String param3;
	private String param4;

}
