<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>
<%
int num = Integer.parseInt(request.getParameter("num"));

BoardDAO boardDAO = new BoardDAO();
boardDAO.deleteBoard(boardDAO.getBoard(num));

response.sendRedirect("list.jsp");
%>
</body>
</html>