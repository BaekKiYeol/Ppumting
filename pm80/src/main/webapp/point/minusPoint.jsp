<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- 커스텀태그 --%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buy PT Trainer</title>
</head>
<body>

  	<form action="minusPoint" method="post">
	<fieldset>
    <legend>PT를 함께 하실 트레이너를 선택해 주세요.</legend>
    <input type="radio" name="contact" value="박상규">박상규
    <input type="radio" name="contact" value="이태영">이태영
    <input type="radio" name="contact" value="이성호">이성호
    <br>
    </fieldset>
    <br>
    <br>
    <fieldset>
    <legend>PT 기간을 선택해 주세요.</legend>
    <input type="radio" name="price" value="300000" >1달 - 300,000원 
    <input type="radio" name="price" value="400000" >2달 - 400,000원 
    <input type="radio" name="price" value="500000" >3달 - 500,000원 
    </fieldset>
    <br>
    <fieldset>
    <legend>결제하기</legend>
		아이디 : <input type="text" name="userId"/><br>
    	<button type="submit">결제하기</button>
  </fieldset>
	</form>
	
</body>
</html>