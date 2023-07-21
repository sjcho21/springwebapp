package com.mycompany.springwebapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Ch04Form1 {
	private String param1; //파라미터의 이름과 필드의 이름이 동일핳 경우에 자동으로 저장이 된다.
	private String param2;
	private String param3;
	private boolean param4;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date param5;
}
