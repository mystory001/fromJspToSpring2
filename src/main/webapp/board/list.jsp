<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
<h1>글 목록</h1>
<c:set var="pageDTO" value="${requestScope.pageDTO }"/>
<%-- <% --%>
<!-- // BoardDAO boardDAO = new BoardDAO(); -->
<!-- // ArrayList<BoardDTO> boardList = boardDAO.getBoardList(); -->
<%-- %> --%>
<!-- <table border="1"> -->
<!-- <tr><td>번호</td><td>글쓴이</td><td>제목</td><td>조회수</td><td>작성일</td></tr> -->
<%-- <% --%>
<!-- // for(int i = 0; i<boardList.size();i++){ -->
<!-- // 	BoardDTO boardDTO = boardList.get(i);  -->
<%-- %> --%>
<%-- <tr><td><%=boardDTO.getNum() %></td> --%>
<%-- 	<td><%=boardDTO.getName() %></td> --%>
<%-- 	<td><a href="content.jsp?num=<%=boardDTO.getNum() %>"><%=boardDTO.getSubject() %></a></td> --%>
<%-- 	<td><%=boardDTO.getReadCount() %></td> --%>
<%-- 	<td><%=boardDTO.getDate() %></td></tr> --%>
<%-- <%} %> --%>
<!-- </table> -->
<!-- <a href="write.jsp">글쓰기</a> -->

<!-- JSTL -->
<table border="1">
<tr><td>번호</td><td>글쓴이</td><td>제목</td><td>조회수</td><td>작성일</td></tr>
<c:set var="boardList" value="${requestScope.boardList }" />
<c:forEach var="boardDTO" items="${boardList }">
<tr><td>${boardDTO.num }</td>
	<td>${boardDTO.name }</td>
	<td><a href="content.bo?num=${boardDTO.num}">${boardDTO.subject }</a></td>
	<td>${boardDTO.readCount }</td>
	<td>${boardDTO.date }</td></tr>
</c:forEach>
</table>
<a href="main.me">메인으로 이동</a><br>
<!-- <a href="list.bo?pageNum=1">1</a> -->
<!-- <a href="list.bo?pageNum=2">2</a> -->
<!-- <a href="list.bo?pageNum=3">3</a> -->
</body>
</html>