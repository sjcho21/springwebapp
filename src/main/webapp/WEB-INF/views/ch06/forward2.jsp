<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- header.jsp로 이동해서 실행하고, 그 결과를 삽입하는 개념  -->
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<div class="card m-2">
   <div class="card-header">forward2</div>
   <div class="card-body">
      <p><%=request.getAttribute("userName") %></p>
      <p>${userName}</p> <!-- 위와 동일한 코드 -->
   
   </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>