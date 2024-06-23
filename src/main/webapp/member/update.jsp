<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
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
<title>update</title>
</head>
<body>
<h1>회원 정보 수정</h1>
비밀번호가 일치하면 이름 변경
<%
request.setCharacterEncoding("utf-8");

String id = (String)session.getAttribute("id");

MemberDAO memberDAO = new MemberDAO();
System.out.println("MemberDAO 메모리 주소 : " + memberDAO);

MemberDTO memberDTO = memberDAO.getMember(id);
System.out.println("리턴 받은 MemberDAO 메모리 주소 : " + memberDAO);

if(memberDTO!=null){
%>
<form action="updatePro.jsp" method="post">
아이디 : <input type="text" name="id" value="<%=memberDTO.getId() %>" readonly="readonly"><br>
비밀번호 : <input type="password" name="pw"><br>
이름 : <input type="text" name="name" value="<%=memberDTO.getName() %>"><br>
<input type="submit" value="회원정보수정">
</form>
<%} %>
</body>
</html>