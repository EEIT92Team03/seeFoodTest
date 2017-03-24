<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>看見食物-seeFood 食府</title>
</head>
<body>

	<h2>歡迎來到食府</h2>
	<h3>${mb.memberNicknName}             客官~您餓了嗎</h3>
	<h3><a href="<c:url value="/pages/article.jsp" />">發表文章</a></h3>
	<h3><a href="<c:url value="/_01_login/login.jsp" />">登入系統</a></h3>
	<h3><a href="<c:url value="/_03_listCoupon/maintain.jsp" />">優惠GO!</a></h3>
</body>
</html>