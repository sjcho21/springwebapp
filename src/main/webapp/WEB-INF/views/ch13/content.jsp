<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/header.jsp"%>


<div class="card m-2">
   <div class="card-header">
      Service와 DAO(데이터 접근 객체)
   </div>
   <div class="card-body">
      <div class="mt-2">
         <a href="insertBoard" class="btn btn-outline-success btn-sm">게시물 삽입하기</a>
      </div>
      <div class="mt-2">
         <a href="getBoardList" class="btn btn-outline-success btn-sm">게시물 목록 가져오기</a>
      </div>
      <div class="mt-2">
         <a href="updateBoard" class="btn btn-outline-success btn-sm">게시물 변경하기</a>
      </div>
      <div class="mt-2">
         <a href="deleteBoard" class="btn btn-outline-success btn-sm">게시물 삭제하기</a>
      </div>
   </div>
</div>

<div class="card m-2">
   <div class="card-header">
      회원 서비스
   </div>
   <div class="card-body">
      <c:if test="${ch13Login == null}">
         <div class="mt-2">
            <a href="join" class="btn btn-outline-success btn-sm">회원가입</a>
         </div>
         <div class="mt-2">
            <a href="login" class="btn btn-outline-success btn-sm">로그인</a>
         </div>
      </c:if>
      <c:if test="${ch13Login != null}">
         <div class="mt-2">
            <a href="logout" class="btn btn-outline-danger btn-sm">로그아웃</a>
            (현재: ${ch13Login.mid}가 로그인됨)
         </div>
      </c:if>
   </div>
</div>

<div class="card m-2">
   <div class="card-header">
      게시판
   </div>
   <div class="card-body">
      <div class="mt-2">
         <a href="getBoardList" class="btn btn-outline-success btn-sm">게시물 목록 가져오기</a>
      </div>
   </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>