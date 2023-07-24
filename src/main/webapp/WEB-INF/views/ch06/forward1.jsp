<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
   <div class="card-header">forward1</div>
   <div class="card-body">
      <c:if test="${loginStatus }">
         <!-- forward1.jsp에서 완전히 forward2.jsp로 이동하는 개념 -->
         <jsp:forward page="/WEB-INF/views/ch06/forward2.jsp"></jsp:forward>
      </c:if>
      
   
   </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>