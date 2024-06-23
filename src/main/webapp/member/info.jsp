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
<title>info</title>
</head>
<body>
<h1>회원 정보 조회</h1>
<%
request.setCharacterEncoding("utf-8");

//main.jsp에서 info.jsp로 이동할 때 값을 들고오지 않음
//연결정보를 저장하는 session 객체에 로그인 표시(ket,value)저장
//session에서 로그인 표시값 가져오기 → String id 변수에 저장
String id = (String)session.getAttribute("id");
//session.getAttribute(String name);
//세션에 저장된 값을 얻어오기
//반환되는 값이 Object형이기 때문에 명시적으로 형 변환을 해야함

//MemberDAO 객체 생성
MemberDAO memberDAO = new MemberDAO();
MemberDTO memberDTO = memberDAO.getMember(id);
System.out.println("리턴 받은 MemberDTO의 메모리 주소 : " + memberDTO);
if(memberDTO!=null){
%>
아이디 : <%=memberDTO.getId() %><br>
비밀번호 : <%=memberDTO.getPw() %><br>
이름 : <%=memberDTO.getName() %><br>
가입날짜 : <%=memberDTO.getDate() %><br>
<%} else { %>
올바르지 않은 접근입니다.
<%
}
%>

<a href="main.jsp">main.jsp로 이동</a>
</body>
</html>