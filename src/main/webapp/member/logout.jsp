<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
<h1>로그아웃</h1>
<%=session.getAttribute("id") %>님 또 뵙겠습니다.
<%
session.invalidate();
response.sendRedirect("login.jsp");
%>
</body>
</html>