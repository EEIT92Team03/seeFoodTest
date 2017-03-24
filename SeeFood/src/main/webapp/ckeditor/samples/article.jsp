<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章發表</title>
<script src='<c:url value="/ckeditor/ckeditor.js"/>'></script>
<script src='<c:url value="/ckeditor/samples/js/sample.js"/>'></script>
<script src='<c:url value="/ckeditor/samples/js/jquery-3.1.1.js"/>'></script>
</head>
<body>
<body id="main">

<div>


	<form name='form' action='<c:url value="/js/article.controller"/>' method='post'>
		<textarea name="content" id="content" rows="10" cols="80"></textarea>
		<script>
			CKEDITOR.replace('content', 
		    {filebrowserImageUploadUrl : '<c:url value="/js/article1.controller"/>'});
		</script>
		<button type="submit">發表文章</button>
		<table><tr>${errors.noword}</tr><tr><h2>${message}</h2></tr></table>
	</form>
</div>


</body>
</html>