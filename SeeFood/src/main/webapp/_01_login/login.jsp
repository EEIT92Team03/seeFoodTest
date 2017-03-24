<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="" />

<title>Login</title>
</head>
<body>

<h3>Login</h3>

<form action="<c:url value="/secure/login.controller" />" method="get">
<table>
	<tr>
		<td>請輸入帳號/信箱 : </td>
		<td><input type="text" name="username" value="${param.username}"></td>
		<td>${ErrorMsgKey.iderror}</td>
	</tr>
	<tr>
		<td>密碼 : </td>
		<td><input type="password" name="password" value="${param.password}"></td>
		<td>${ErrorMsgKey.pwderror}${ErrorMsgKey.loginerror}</td>
	</tr>
	<tr>
		<td>　</td>
		<td align="right"><input type="submit" value="Login"></td>
	</tr>
</table>
</form>

</body>
</html>