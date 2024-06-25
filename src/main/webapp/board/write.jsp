<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write</title>
</head>
<body>
<h1>글쓰기</h1>
<form action="writePro.bo" method="post">
<table border="1">
<tr><td>이름</td><td><input type="text" name="name" value="${sessionScope.id }" readonly="readonly"></td></tr>
<tr><td>제목</td><td><input type="text" name="subject"></td></tr>
<tr><td>내용</td><td><textarea rows="20" cols="50"></textarea> </td></tr>
<tr><td colspan="2"><input type="submit" value="글쓰기"></td></tr>
</table>
<a href="list.bo">글목록</a>
</form>
<%
/*
	for(int i = 1; i<=100; i++){
		String subject = "제목 " + i;
		String content = "제목 " + i + "의 내용 " + i +"번째";
		
		try{
			String fileName = "auto_post_" + i + ".txt";
			FileWriter writer = new FileWriter(fileName);
			writer.write("제목 " + subject + "\n");
			writer.write("내용 " + content + "\n");
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
*/
%>
</body>
</html>