<%@page import="com.mystroy001.mvcproject.dao.MemberDAO"%>
<%@page import="com.mystroy001.mvcproject.domain.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
<h1>회원목록</h1>
<%-- <% --%>
<!-- // request.setCharacterEncoding("utf-8"); -->

<!-- // //세션에 id 값을 가져오기 -->
<!-- // String id = (String)session.getAttribute("id"); -->

<!-- // if(id==null){ -->
<!-- // 	response.sendRedirect("login.me"); -->
<!-- // } else{ -->
<!-- // 	if(!id.equals("admin")){ -->
<!-- // 		response.sendRedirect("main.me"); -->
<!-- // 	} -->
<!-- // } -->

<!-- // MemberDAO memberDAO = new MemberDAO(); -->

<!-- // /* -->
<!-- // ArrayList -->
<!-- // 배열의 단점(초기메모리크기가 고정)을 보완한 객체배열 -->
<!-- // 메모리가 부족하면 100% 추가됨 -->
<!-- // java.util.ArrayList -->
<!-- // ArrayList 메서드() -->
<!-- // .size() : ArrayList의 데이터 개수 -->
<!-- // .add() : ArrayList의 데이터 저장(순서대로 값이 저장), 주소값에 추가 -->
<!-- // .get(index) : index의 데이터 가져오기 -->
<!-- // */ -->
<!-- // ArrayList<MemberDTO> memberList = memberDAO.getMemberList(); -->
<%-- %> --%>
<!-- <table border="1"> -->
<!-- <tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입날짜</td></tr> -->
<%-- <%for(int i = 0; i<memberList.size();i++){ %> --%>
<%-- <tr><td><%=memberList.get(i).getId() %></td><td><%=memberList.get(i).getPw() %></td><td><%=memberList.get(i).getName() %></td><td><%=memberList.get(i).getDate() %></td></tr> --%>
<%-- <%} %> --%>
<!-- </table> -->
<!-- <a href="main.me">메인으로</a><br> -->

<!-- JSTL -->
<c:if test="${empty sessionScope.id }">
<c:redirect url="login.me"/>
</c:if>
<c:if test="${!empty sessionScope.id }">
<c:if test="${!sessionScope.id == 'admin' }">
<c:redirect url="main.me" />
</c:if>
</c:if>
<c:set var="memberList" value="${sessionScope.memberList }" />
<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입날짜</td></tr>
<c:forEach var="memberDTO" items="${memberList }">
<tr><td>${memberDTO.id}</td>
<td>${memberDTO.pw}</td>
<td>${memberDTO.name}</td>
<td>${memberDTO.date}</td></tr>
</c:forEach>
</table>
</body>
</html>