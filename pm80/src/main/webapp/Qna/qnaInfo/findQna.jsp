<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h3>게시글</h3>
	<form action="find.do" method="post"><br>
	<c:forEach var="qna" items="${qnaList}">&nbsp;&nbsp;
		<a href="selectNoQna.do?qnaNo=${qna.qnaNo}" ><br>
		${qna.qnaNo}.&nbsp;
		제목 : <input type="text" value="${qna.qnaTitle}">
		</a>
	</c:forEach>
		</form>
	<br>
	<br>
	
	<form action="add_qna.do" method="post">
	제목: <input type="text" name="qna_title"><br>
	내용: <input type="text" name="qna_content"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>
