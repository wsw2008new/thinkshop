<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../taglib.jsp" %>
<%@include file="../base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
	function getTime(){
		
		var time = new Date();
		var day = time.getDay();
		switch(day){
			case 1 : day = "星期一";
			break;
			case 2 : day = "星期二";
			break;
			case 3 : day = "星期三";
			break;
			case 4 : day = "星期四";
			break;
			case 5 : day = "星期五";
			break;
			case 6 : day = "星期六";
			break;
			case 7 : day = "星期日";
			break;
		} 
		var h = time.getHours();
		var mi = time.getMinutes();
		if(mi<10){
			mi = "0" + mi;
		}
		var s = time.getSeconds();
		if(s<10){
			s = "0"+ s;
		}
		
		var sTime  = h+":"+mi+":"+s+"&nbsp;&nbsp;"+day;
		document.getElementById("timer").innerHTML = sTime;
		setTimeout("getTime()",1000);
	}
	
	
			
	
</script>
	</head>
	<body onload="getTime();">
		<div id="header">
			<div id="logo"><a><img src="images/manage/logo1.jpg"/></a></div>
			<div id="quicklink">
				<div class="quicklink-left">
					<div class="quicklink-right">
						<ul>
							<!-- <li><a href="#" target='blank'>支付首页</a></li> -->
							<li><a   class="psw" id="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.operator.operatorId}"  target='mainFrm' href="operator/tomodifypwd.htm?operatorId=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.operator.operatorId}" >修改密码</a></li>
							<li class="last"><a href="${basePath}j_spring_security_logout">退出</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="pub">
				<div class="pub-left">
					<div class="pub-right">
						<div class="publeft">
							<ul>
								<li>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.operator.name}:
								<%
									Calendar cal = Calendar.getInstance();
									int hour = cal.get(Calendar.HOUR_OF_DAY);
									if(hour >= 6 && hour < 9){
										out.print("早上好！");
									}else if(hour >= 9&& hour<12){
										out.print("上午好！");
									}else if(hour >=12&&hour < 13){
										out.print("中午好！");
									}else if(hour >=13 && hour <18){
										out.print("下午好！");
									}else{
										out.print("晚上好！");
									}
									
								%>
								<!--上次登录时间：<fmt:formatDate value="${sessionScope.lastTime}" type="both"></fmt:formatDate>
								上次登录IP：${sessionScope.lastIp}  -->
								</li>
								<!--<li class="infoa"><a href="">1条未读</a></li>  -->
							</ul>
						</div>
						<div class="pubright">
							当前日期: <fmt:formatDate value="<%=new Date() %>" type="date" dateStyle="long"></fmt:formatDate>&nbsp;<span id="timer"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</body>
</html>