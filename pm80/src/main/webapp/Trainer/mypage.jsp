<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>My Page</h3>
	<a href='<c:url value="/Trainer/loginout/login.jsp" />'>로그아웃</a>
	<a href='<c:url value="/Trainer/delete/deleteTrainer.jsp" />'>회원삭제</a>
	<a href='<c:url value="/Trainer/update/updateselect.jsp" />'>회원정보수정</a>
	
</body>
</html>