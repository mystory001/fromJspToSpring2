<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%-- <% --%>
<!-- String id = (String)session.getAttribute("id"); -->
<%-- %> --%>


<%-- <%=session.getAttribute("id") %>님 환영합니다.<br> --%>

<%-- <% --%>
<!-- SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); -->
<!-- Date lastAccessTime = new Date(session.getLastAccessedTime()); -->
<!-- String formattedTime = dateFormat.format(lastAccessTime); -->
<%-- %> --%>

<%-- 마지막으로 로그인한 시간 : <%=formattedTime %><br> --%>
<!-- <a href="info.me">회원정보조회</a><br> -->
<!-- <a href="update.me">회원정보수정</a><br> -->
<!-- <a href="delete.me">회원정보삭제</a><br> -->
<!-- <hr> -->
<!-- <a href="../board/write.me">글쓰기</a><br> -->

<%-- <% --%>
<%-- if(!(id==null) && id.equals("admin")){%> --%>
<!-- 		<a href="list.me">회원 목록</a><br> -->
<%-- <% --%>
<%-- }%> --%>
<!-- <a href="logout.me">로그아웃</a> -->

<!-- JSTL -->
<%
/*
JSTL(JSP Standard Tag Library)

EL(Expression Language, 표현·출력 언어)
<%=하나의 내용을 출력"%\> → ${출력 내용}
연산자		설명
empty		값이 null, 0이면 true를 반환
/, div		나누기
%, mod		나머지
&&, and		그리고
||, or		또는
!, not		부정
==,eq		숫자, 문자가 같음
!=,ne
>, gt
>=, ge
<, lt
<=, le
sessionScope : Session 영역에 사용하는 내장객체
=====================================================
core
기본적인 기능(컨트롤에 관계된 기능)들을 구현해 놓은 라이브러리
출력 태그 : <c:out>
변수 설정 : <c:set>
변수 삭제 : <c:remove>
예외 처리 : <c:catch>
조건 처리 : <c:if>, <c:choose> <c:when> <c:otherwise>
반복 처리 : <c:forEach>, <c:forTokens> for문과 StringTokenizer 객체 결합
페이지 처리 : <c:redirect>이동 , <c:url> 하이퍼링크 주소
=====================================================
fmt
국제화/형식화의 기능을 제공해주는 라이브러리. 다국어 내용처리, 날짜와 숫자 등을 처리하는 것
다국어 처리 : <fmt:requestEncoding>
날짜 포멧 : <fmt:formatData>
숫자 포멧 : <fmt.formatNumber>
*/
%>
<%
String id = (String)session.getAttribute("id");
%>
<%-- <c:if test="조건"> --%>
<c:if test="${empty sessionScope.id }"> 
<c:redirect url="login.me"/>
</c:if>
<h1><%=id %> == ${sessionScope.id }님 환영합니다.</h1>
<hr>
<a href="info.me">회원정보조회</a><br>
<a href="update.me">회원정보수정</a><br>
<a href="delete.me">회원정보삭제</a><br>
<c:if test="${! empty sessionScope.id}">
<c:if test="${sessionScope.id == 'admin' }">
<a href="list.me">회원 목록</a>
</c:if>
</c:if><br>
<hr>
<a href="write.bo">글 쓰기</a><br>
<a href="list.bo">글 목록</a><br>
<hr>
<a href="logout.me">로그아웃</a>
</body>
</html>