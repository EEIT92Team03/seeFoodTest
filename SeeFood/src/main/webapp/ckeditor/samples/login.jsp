<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

                     <!-- login.jsp執行時先執行FindUser.javar以此檢查帳密 -->
                     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
</head>

<body>
	<form action="<c:url value="/login/login.do"/>" name="loginForm" method="post">
		<div>
			<table border="1px" style="border-collapse: collapse;">
				<tbody>
					<tr>
						<td>會員登入</td>
					</tr>
					<tr>
						<td>帳號:</td>
						<td><input type="text" name="userId"></td>
						<td>${Errors.errorId}</td>
					</tr>
					<tr>
						<td>密碼:</td>
						<td><input type="text" name="userPsd"></td>
						<td>${Errors.errorPsd}</td>
					</tr>
					<tr>
					<td><input type="submit" value="登入"></td>
					</tr>
					<tr>
					<td><a href="">註冊</a></td><td><a href="">忘記密碼</a></td>
					</tr>
					
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>