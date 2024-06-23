<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
<h1>메인</h1>
<%
String id = (String)session.getAttribute("id");

%>


<%=session.getAttribute("id") %>님 환영합니다.<br>

<%
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date lastAccessTime = new Date(session.getLastAccessedTime());
String formattedTime = dateFormat.format(lastAccessTime);
%>

마지막으로 로그인한 시간 : <%=formattedTime %><br>
<a href="info.jsp">회원정보조회</a><br>
<a href="update.jsp">회원정보수정</a><br>
<a href="delete.jsp">회원정보삭제</a><br>
<hr>
<a href="../2)board/write.jsp">글쓰기</a><br>

<%
if(!(id==null) && id.equals("admin")){%>
		<a href="list.jsp">회원 목록</a><br>
<%
}%>
<a href="logout.jsp">로그아웃</a>
</body>
</html>