<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<div>
<form id="frm">
<select id="key" name="key">
	<option value="all">전체검색</option>
	<option value="title">제목검색</option>
	<option value="content">내용검색</option>
</select>
<input type="text" id="val" name="val">&nbsp;
<input type="button" value="검색" onclick="Search()">
</form>
</div>

<table class="table" border="1">
			<tr>
				<th scope="col">아이디</th>
				<th scope="col">제목</th>
				<th scope="col">내용</th>
				<th scope="col">날짜</th>
				<th scope="col">조회수</th>
				<th scope="col">파일이름</th>
			</tr>
	<c:forEach items="${notices }" var="notice">
			<tr>
				<td scope="row">${notice.id}</td>
				<td scope="row">${notice.title}</td>
				<td scope="row">${notice.content}</td>
				<td scope="row">${notice.wdate}</td>
				<th scope="row">${notice.hit}</th>
				<th>${notice.fileName}</th>
			</tr>
	</c:forEach>
		</table>
</div>

</body>
</html>