<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="excelSave.do" method="post">
		<input type="text" name="no" size="4"> <input type="text"
			name="name" size="4"> <input type="text" name="id" size="4">
		<input type="text" name="pwd" size="4"> <input type="submit"
			value="전송">
	</form>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>아이디</th>
			<th>비번</th>
		</tr>
		
		<c:if test="${empty list }">
			<tr>
				<td colspan="5">글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="list" items="${list}">
			<tr>
				<td><c:out value="${list.no }"></c:out></td>
				<td><c:out value="${list.name }"></c:out></td>
				<td><c:out value="${list.id }"></c:out></td>
				<td><c:out value="${list.pwd }"></c:out></td>
			</tr>
		</c:forEach>
		<tr>
			<th colspan="5">Excel파일로 추가</th>
		</tr>
		<tr>
			<td colspan="4">
				<form enctype="multipart/form-data" method="post"
					action="excelUploadAjax.do">
					<input id="excelFile" type="file" name="excelFile" /> <input
						type="submit" value="전송">
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="3"></td>
			<th colspan="2">
				<form action="excelListSave.do" method="post">
					<input type="submit" value="Excel파일로 저장">
				</form>
			</th>
		</tr>
	</table>
</body>
</html>