<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>		
<body>
<div  style="clear:both;padding-right:50px;text-align:right;">				
	<input type="button" style="margin-top:10px;" id="${parentCode}" class="searchresetbtn" onclick="preaddmenu(id);" value="添加功能菜单"/>
	<input type="button" style="margin-top: 10px;"
									sel="all" class="searchresetbtn"
									onclick="bothChecked(this);" value="全选" />
	<input type="button" style="margin-top: 10px;"
									 class="searchresetbtn"
									onclick="delAll('${parentCode}');" value="删除" />
</div>
<table class="table" style="margin: 40px;">
	<thead>
		<tr>
			<td class="choosewidth">选择</td>
			<td>菜单编码</td>
			<td>菜单名称</td>
			<td>菜单地址</td>
			<td>级别</td>
			<td style="width:10%;">修改</td>
			<td style="width:10%;">删除</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${menus.results}" var="m">
		<tr>
			<td><input type="checkbox" name="codes" value="${m.code}"/></td>
			<td>${m.code}</td>
			<td>${m.title}</td>
			<td>${m.url}</td>
			<td>${m.menuLevel}</td>
			<td><a href="javascript:void(0);" onclick="premodifymenu('${m.code}');">修改</a></td>
			<td><a onclick="return isConfirm('${m.code}','${m.parentCode}');" href="javascript:void(0);" >删除</a></td>
		</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			
		</tr>
	</tfoot>
</table>
</body>