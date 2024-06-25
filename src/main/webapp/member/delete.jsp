<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>
<h1>회원정보삭제</h1>
<%
/*
main.jsp에서 현 페이지(delete.jsp)로 이동할 때 아무런 값도 없이 이동
→ session에서 로그인 값 가져오기. String id 변수 저장
※request 유효범위 : 요청 정보가 처리되는 동안 유효
※session 유효범위 : 연결 정보를 저장. 페이지 상관없이 세션이 연결되어 있으면 유효
*/
// String id = (String)session.getAttribute("id");
%>
<form action="deletePro.me" method="post">
아이디 : <input type="text" name="id" value="${sessionScope.id }" readonly="readonly"><br>
비밀번호 : <input type="password" name=pw placeholder="비밀번호 입력 후 삭제"><br>
<input type="submit" value="회원정보삭제">
</form>
</body>
</html>