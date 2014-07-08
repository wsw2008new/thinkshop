<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
</head>
<body>
<% 
  request.setAttribute("vEnter", "\n"); 
%> 
<form id="xinxi_form" name="xin_form" method="post"> 
	<ul class="submitul2" style="padding-left:50px;">
		<li><span>账号:</span>${operator.operatorId}</li>
		<li><span>姓名: </span>${operator.name}</li>
		<li><span>电话号码: </span>${operator.teleNum}</li>
		<li><span>手机号码: </span>${operator.mobileNum}</li>
		<li><span>职位名称:</span> ${operator.jobName}</li>
		<li><span>状态:</span><c:if test="${operator.status==1}">正常</c:if><c:if test="${operator.status==0}">禁用</c:if></li>
		<li><span>最后登录时间:</span>${operator.lastTime}</li>
		<li><span>最后登录IP:</span>${operator.lastIp}</li>
		<li style="height:auto;line-height:normal;"><span>备注: </span><p style="width:230px;float:left;word-break:break-all;">${fn:replace(operator.remark,vEnter,'<br/>') }</p></li>
	</ul>
	<div class="submitsbtnbox" style="text-align:center;padding: 28px;">		      
		<input type="button"  class="searchbtn" value="关闭" onclick="$('#xinxi').dialog('close');"/>
	</div>	
</form>

</body>
</html>
