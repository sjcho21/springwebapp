package com.mycompany.springwebapp.validate;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springwebapp.dto.Ch04Form1;
import com.mycompany.springwebapp.dto.Ch04Form3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04Form3Validator implements Validator {
	@Override
   public boolean supports(Class<?> clazz) {
      log.info("실행");
      boolean check = Ch04Form1.class.isAssignableFrom(clazz);
      return check;
   }

   @Override
   public void validate(Object target, Errors errors) {
      log.info("실행");
      Ch04Form3 ch04Form3 = (Ch04Form3) target;
      
      //mid 검사
      String mid = ch04Form3.getMid();
      if(mid == null || mid.equals("")) {
         errors.rejectValue("mid", "errors.form.required", "필수 입력(D)");
      } else if(mid.length() < 8) {
         errors.rejectValue("mid", "errors.form.minlength", new Object[] {8},"최소 8자 입력(D)");
      } else if(mid.length() > 15) {
         errors.rejectValue("mid", "errors.form.maxlength", new Object[] {15},"최대 15자 입력(D)");
      }
      
      //param4 검사
      String param4 = ch04Form3.getParam4();
      if(param4 == null || param4.equals("")) {
         errors.rejectValue("param2", "errors.form.required", "필수 입력(D)");
      } else {
         String regExp = "^(010|011)-[0-9]{3,4}-[0-9]{4}$";
         boolean result = Pattern.matches(regExp, param4);
         if(result == false) {
            errors.rejectValue("param2", "errors.form.format", "형식에 맞지 않음(D)");
         }
      }
      
      //param3 검사
      String param3 = ch04Form3.getParam3();
      if(param3 == null || param3.equals("")) {
         errors.rejectValue("param3", "errors.form.required", "필수 입력(D)");
      } else {
         String regExp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
         boolean result = Pattern.matches(regExp, param3);
         if(result == false) {
            errors.rejectValue("param3", "errors.form.format", "이메일 형식에 맞지 않음(D)");
         }
      }
   }
}