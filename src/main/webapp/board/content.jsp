<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>content</title>
</head>
<body>
<h1>글 내용 보기</h1>
<%-- <% --%>
<!-- // //요청 주소 : content.jsp?num=1 -->
<!-- // //서버에 request에 "num"을 요청하여 파라미터값을 저장 → 가져와서 변수 int num에 저장 -->
<!-- // // int num = Integer.parseInt(request.getParameter("num")); -->
<!-- // int num = Integer.parseInt(request.getParameter("num")); -->
<!-- // //BoardDAO 객체생성 -->
<!-- // BoardDAO boardDAO = new BoardDAO(); -->
<!-- // BoardDTO boardDTO = boardDAO.getBoard(num); -->

<!-- // //조회수 -->
<!-- // boardDAO.updateReadCount(num); -->

<!-- // //로그인 표시 값 세션에서 가져오기 -->
<!-- // String id = (String)session.getAttribute("id"); -->
<%-- %> --%>
<%-- <h2>아이디 : <%=id %></h2> --%>
<!-- <table border="1"> -->
<%-- <tr><td>글번호</td><td><%=boardDTO.getNum() %></td></tr> --%>
<%-- <tr><td>작성자</td><td><%=boardDTO.getName() %></td></tr> --%>
<%-- <tr><td>조회수</td><td><%=boardDTO.getReadCount() %></td></tr> --%>
<%-- <tr><td>작성날짜</td><td><%=boardDTO.getDate() %></td></tr> --%>
<%-- <tr><td>제목</td><td><%=boardDTO.getSubject() %></td></tr> --%>
<%-- <tr><td>내용</td><td><%=boardDTO.getContent() %></td></tr> --%>
<!-- <tr><td colspan="2"><a href="list.jsp">목록 </a> -->
<%-- <% --%>
<!-- // //로그인이 있으며. 로그인 표시값, 글쓴이가 일치하면 글 수정, 삭제 -->
<!-- // if(id!=null){ -->
<!-- // 	if(id.equals("admin")||id.equals(boardDTO.getName())){ -->
<%-- %> --%>
<%-- 		<a href="update.jsp?num=<%=boardDTO.getNum() %>">글수정 </a> --%>
<%-- 		<a href="delete.jsp?num=<%=boardDTO.getNum() %>">글삭제 </a> --%>
<%-- <%		 --%>
<!-- // 	} -->
<!-- // } -->
<%-- %> --%>
<!-- </td></tr> -->
<!-- </table> -->

<!-- JSTL -->
<c:set var="boardDTO" value="${requestScope.boardDTO }"/>
<h3>로그인 : ${sessionScope.id}</h3>
<table border="1">
<tr><td>글번호</td><td>${boardDTO.num}</td></tr>
<tr><td>글쓴이</td><td>${boardDTO.name}</td></tr>
<tr><td>조회수</td><td>${boardDTO.readCount}</td></tr>
<tr><td>글쓴날짜</td><td>${boardDTO.date}</td></tr>
<tr><td>제목</td><td>${boardDTO.subject}</td></tr>
<tr><td>내용</td><td>${boardDTO.content}</td></tr>
<tr><td colspan="2">
<c:if test="${ ! empty sessionScope.id}">
	<c:if test="${sessionScope.id eq boardDTO.name}">
		<a href="update.bo?num=${boardDTO.num}">글수정</a> 
		<a href="delete.bo?num=${boardDTO.num}">글삭제</a>
	</c:if>
</c:if>
<a href="list.bo">글목록</a></td></tr>
</table>

</body>
</html>