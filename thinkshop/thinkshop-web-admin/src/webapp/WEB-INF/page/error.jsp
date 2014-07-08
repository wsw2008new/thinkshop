<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
</head>
<body style="background:#fff;">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td style="height:8px;background:#1791c2;"></td>
			</tr>
			<tr>
				<td>
					<div class="leftpanel" style="height:100%;">
						<div class="leftpane" style="height:100%;padding-left:0;">
							<div class="title1">错误信息</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="background:#fff;">
					<div class="error">
						系统错误${exception.message}
					</div>
				</td>
			</tr>
		</table>
		
	</body>
</html>