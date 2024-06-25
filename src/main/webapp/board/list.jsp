<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
<h1>글 목록</h1>
<%
BoardDAO boardDAO = new BoardDAO();
ArrayList<BoardDTO> boardList = boardDAO.getBoardList();
%>
<table border="1">
<tr><td>번호</td><td>글쓴이</td><td>제목</td><td>조회수</td><td>작성일</td></tr>
<%
for(int i = 0; i<boardList.size();i++){
	BoardDTO boardDTO = boardList.get(i); 
%>
<tr><td><%=boardDTO.getNum() %></td>
	<td><%=boardDTO.getName() %></td>
	<td><a href="content.jsp?num=<%=boardDTO.getNum() %>"><%=boardDTO.getSubject() %></a></td>
	<td><%=boardDTO.getReadCount() %></td>
	<td><%=boardDTO.getDate() %></td></tr>
<%} %>
</table>
<a href="write.jsp">글쓰기</a>
</body>
</html>