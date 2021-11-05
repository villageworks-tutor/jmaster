<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>Show All Item</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>NO</th>
			<th>商品名</th>
			<th>価格</th>
		</tr>
		<c:forEach items="${items}" var="item">
		<tr>
			<td>${item.code}</td>
			<td>${item.name}</td>
			<td>${item.price}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>