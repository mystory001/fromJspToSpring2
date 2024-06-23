<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
<h1>회원목록</h1>
<%
request.setCharacterEncoding("utf-8");

//세션에 id 값을 가져오기
String id = (String)session.getAttribute("id");

if(id==null){
	response.sendRedirect("login.jsp");
} else{
	if(!id.equals("admin")){
		response.sendRedirect("main.jsp");
	}
}

MemberDAO memberDAO = new MemberDAO();

/*
ArrayList
배열의 단점(초기메모리크기가 고정)을 보완한 객체배열
메모리가 부족하면 100% 추가됨
java.util.ArrayList
ArrayList 메서드()
.size() : ArrayList의 데이터 개수
.add() : ArrayList의 데이터 저장(순서대로 값이 저장), 주소값에 추가
.get(index) : index의 데이터 가져오기
*/
ArrayList<MemberDTO> memberList = memberDAO.getMemberList();
%>
<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입날짜</td></tr>
<%for(int i = 0; i<memberList.size();i++){ %>
<tr><td><%=memberList.get(i).getId() %></td><td><%=memberList.get(i).getPw() %></td><td><%=memberList.get(i).getName() %></td><td><%=memberList.get(i).getDate() %></td></tr>
<%} %>
</table>
<a href="main.jsp">메인으로</a><br>
</body>
</html>