<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择操作菜单</title>
</head>
<body>
<c:if test="${menus!=null}">
	<c:forEach items="${menus}" var="m">
		<c:if test="${m.childrenCount>0}">
			<li id="${m.code}li" class="closed">
			<c:if test="${m.isCheck=='true'}">
				<input parentid="${m.parentCode}" type="checkbox" checked="checked" name="menuIds" value="${m.code}" onclick="checkChildren(this);"/>
			</c:if>
			<c:if test="${m.isCheck=='false'}">
				<input parentid="${m.parentCode}" type="checkbox"  name="menuIds" value="${m.code}" onclick="checkChildren(this);"/>
			</c:if>
			<span
				code="${m.code}" count="${m.childrenCount}" roleCode="${roleCode }" class="folder">${m.title}</span>
				<ul id="${m.code}ul"></ul>
			</li>
		</c:if>
		<c:if test="${m.childrenCount==0}">
			<li id="${m.code}li" class="f">
			<c:if test="${m.isCheck=='true'}">
				<input parentid="${m.parentCode}" type="checkbox" checked="checked" name="menuIds" value="${m.code}" onclick="checkChildren(this);"/>
			</c:if>
			<c:if test="${m.isCheck=='false'}">
				<input parentid="${m.parentCode}" type="checkbox"  name="menuIds" value="${m.code}" onclick="checkChildren(this);"/>
			</c:if>
			<span code="${m.code}"
				count="${m.childrenCount}" roleCode="${roleCode }" class="file"
				onclick="menuclick(this);"><a
					href="javaScript:void(0);">${m.title}</a></span>
				<ul id="${m.code}ul"></ul>	
			</li>
		</c:if>
	</c:forEach>
</c:if>
</body>
</html>