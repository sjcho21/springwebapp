package com.mycompany.springwebapp.dto;

import java.sql.Blob;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Ch13Board {
	private int bno;
	private String btitle;
	private String bcontent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bdate;
	private String mid;
	private int bhitcount;
	
	//client => controller
	private MultipartFile battach;
	
	//client => controller => dao => db
	private String battachoname;
	private String battachtype;
	
	//방법1: 서버 파일 시스템에 파일로 저장
	private String battachsname;
	
	//방법2: DB에 BLOB으로 저장
	//byte[] <- MyBatis -> Blob  sql 에서는 blob 만 사용가능 한데 mybatis 를 거치면 blob은 byte로 byte는 blob 으로 타입 변환이 이루어 지므로  sql에 blob으로 넘기기 위한 장치 ->
	private byte[] battachdata;
	
}
