<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%;">
		<div style="width: 400px; margin: auto;">
			<ul class="submitul">
				<li><span>账号：</span>${log.operatorId}</li>
				<li><span>姓名：</span>${log.operatorName}</li>
				<li><span>时间：</span> <fmt:formatDate value="${log.time}"
						type="both" />
				</li>
				<li><span>IP地址：</span>${log.ip}</li>
				<li><span>内容：</span><p style=" width:280px;float:left;word-break:break-all;">${log.content}</p></li>
			</ul>
			<div class="submitsbtnbox" style="clear:left;padding-left: 70px;">
				<input type="button" style="margin-left: 20px;" class="searchbtn"
					value="关闭" onclick="$('#list').dialog('close');" />
			</div>
		</div>
	</div>
</body>
</html>