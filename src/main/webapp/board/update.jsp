<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
</head>
<body>
<h1>글 수정</h1>
<%
//update?num=1
//서버에 request "num" 요청 → 파라미터값을 가져와 변수 int num에 저장
int num = Integer.parseInt(request.getParameter("num"));

BoardDAO boardDAO = new BoardDAO();
BoardDTO getBoard = boardDAO.getBoard(num);
%>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="num" value="<%=num %>"><!-- updatePro에 값을 함께 넘겨줌 -->
<table border="1">
<tr><td>작성자</td><td><input type="text" name="name" value="<%=getBoard.getName() %>" readonly></td></tr>
<tr><td>제목</td><td><input type="text" name="subject" value="<%=getBoard.getSubject()%>"></td></tr>
<tr><td>내용</td><td><textarea name="content" rows="10" cols="20"><%=getBoard.getContent()%></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정"></td></tr>
</table>
글번호 : <%=num %>
</form>

</body>
</html>