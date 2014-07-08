<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../base.jsp" %>
<%@include file="../taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:forEach items="${menus}" var="children">
	<li onmouseover="doSelectMenu(this);" onmouseout="doOutMenu(this);"><a target="mainFrm" href="<%=menuBasePath%>${children.url }" onclick="clicka(this);">${children.title}</a></li>
</c:forEach>
</body>
</html>
