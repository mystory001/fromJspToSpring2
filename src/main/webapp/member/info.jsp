<%@page import="com.mystroy001.mvcproject.domain.MemberDTO"%>
<%@page import="com.mystroy001.mvcproject.dao.MemberDAO"%>
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
<title>info</title>
</head>
<body>
<h1>회원 정보 조회</h1>
<%-- <% --%>
<!-- String id = (String)session.getAttribute("id"); -->

<!-- MemberDAO memberDAO = new MemberDAO(); -->

<!-- MemberDTO memberDTO = memberDAO.getMember(id); -->

<!-- request.getParameter()를 이용하면 String 값만 주고 받을 수 있음 -->
<!-- form 태그의 action으로 넘어온 값을 변경시킨 후 jsp페이지로 넘겨주기 위해서는 request.setAttribute()를 써서 넘겨주고,  -->
<!-- jsp페이지에서는 request.getAttribute()를 써서 받아와야함. -->
<!-- request.setAttribute()를 통해 넘겨준 값을 request.getAttribute()로 받아야함 -->
<!-- 받을 때 타입이 Object형이기 때문에 반드시 형 변환을 해줘야함. -->

<!-- request.getAttribute("name") : 요청된 name의 Value를 Object로 넘겨줌. 특정 요소노드 내에 특정한 속성값을 가져오는 메서드 -->
<!-- request.setAttribute("name",value) : 속성값을 변경시키는 메서드 -->


<!-- memberDTO = (MemberDTO)request.getAttribute("memberDTO"); -->
<%-- if(memberDTO != null){%> --%>
<%-- 아이디 : <%=memberDTO.getId() %><br> --%>
<%-- 비밀번호 : <%=memberDTO.getPw() %><br> --%>
<%-- 이름 : <%=memberDTO.getName() %><br> --%>
<%-- 가입날짜 : <%=memberDTO.getDate() %><br> --%>
<%-- <%} else { %> --%>
<!-- 올바르지 않은 접근입니다. -->
<%-- <% --%>
<!--  }  -->
<%-- %> --%>
<!-- <a href="main.me">main.me 이동</a> -->

<!-- JSTL -->
<%
// MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
%>
<c:set var="memberDTO" value="${requestScope.memberDTO }" />
<c:choose>
<c:when test="${!empty memberDTO }">
아이디 : ${memberDTO.id}<br>
비밀번호 : ${memberDTO.pw}<br>
이름 : ${memberDTO.name}<br>
가입날짜 : ${memberDTO.date}<br>
</c:when>
<c:otherwise>
아이디 없음
</c:otherwise>
</c:choose>
<a href="main.me">main.me 이동</a>
</body>
</html>