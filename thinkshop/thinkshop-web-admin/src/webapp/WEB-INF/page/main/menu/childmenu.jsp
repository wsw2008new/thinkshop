<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<c:forEach items="${menus.results}" var="m">
<c:if test="${m.childrenCount>0}">
<li  id="${m.code}li"  class="closed"><span code="${m.code}" count="${m.childrenCount}"  class="folder">${m.title}</span><ul id="${m.code}ul"></ul></li>
</c:if>
<c:if test="${m.childrenCount==0}">
<li id="${m.code}li" class="f"><span code="${m.code}" count="${m.childrenCount}"   class="file" onclick="menuclick(this);"><a href="javaScript:void(0);">${m.title}</a></span><ul id="${m.code}ul"></ul></li>
</c:if>
</c:forEach>
