<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<%=basePath%>js/jui/css/ui-lightness/jquery-ui.css" rel="stylesheet" />	
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jui/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/DatePicker/WdatePicker.js" defer="defer"></script>
	<script type="text/javascript">
				$(document).ready(function(){
					//弹出添加页面
					$("#addMerchantType").dialog({
						autoOpen:false,
						modal:true,
						title:"添加商户类型",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#addMerchantType").html("");
						}
					});
					//弹出修改页面
					$("#modifyMerchantType").dialog({
						autoOpen:false,
						modal:true,
						title:"修改商户类型",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#modifyMerchantType").html("");
						}	
					});
				});
				
				
				
				//删除商户类型
				function deleteBank(id){
					if(confirm("确定删除吗?")){
							window.location.href = "<%=basePath%>type/delete.htm?id="+id;
						}else{
							return;
						}	
				}
				//添加商户类型
				function addMerchantType(){
// 					var url="";
// 					url="type/toadd.htm",
// 					window.location.href=url;
					var url="";
					$("#addMerchantType").dialog('open');
					url="type/toadd.htm",
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#addMerchantType").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#addMerchantType").html(data);
						}
					}); 
				}
				//编辑商户类型
				function modifyMerchantType(id,is_active,show_pic){
					var url="";
					$("#modifyMerchantType").dialog('open');
					url="type/toadd.htm?id="+id+"&is_active="+is_active+"&show_pic="+show_pic,
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#modifyMerchantType").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#modifyMerchantType").html(data);
						}
					}); 
				}
		</script>
</head>
<body style="background: #ffffff;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="leftpanel" style="height: 100%;">
					<div class="leftpane" style="height: 100%; padding-left: 0;">
						<div class="title1">商户类型信息</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #ffffff;text-align: center;">
				<div  style="clear:both;padding-right:50px;text-align:right;">				
						<input type="button" class="searchresetbtn" onclick="addMerchantType();" value="添加商户类型"/>
					</div>
				<table class="table" id="content">
					<thead>
						<tr>
							<td>ID</td>
							<td>类型图标</td>
							<td>商户类型名称</td>
							<td>是否激活</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="tablecontent">
						<c:if test="${empty bank.results}">
							<tr><td colspan="13">没有查到相关信息</td></tr>
						</c:if>
						<c:set var="id" value="1" />
						<c:forEach items="${bank.results}" var="bank">
							<tr>
								<td>${id}</td>
								<td>
								<c:if test="${not empty bank.show_pic}"><img src="<%=basePath%>${bank.show_pic}" height="40" width="70"></c:if>
								<c:if test="${empty bank.show_pic}"><img src="<%=basePath%>images/no-picx.png" height="40" width="70"></c:if>
								
								</td>
								<c:set var="id" value="${id+1}" />
								<td>${bank.name}</td>
								<td>
								<c:if test="${bank.is_active=='1'}">已激活</c:if>
								<c:if test="${bank.is_active=='0'}"><font color="red">未激活</font></c:if>
								</td>
								<td>
								<a href="javascript:void(0)" onclick="modifyMerchantType('${bank.id}','${bank.is_active}','${bank.show_pic}')"><img src='images/login/edit.gif'  alt="编辑" title="编辑"/></a>
								<a href="javascript:void(0)" onclick="deleteBank('${bank.id}')" ><img src='images/login/delete.gif'  alt="删除" title="删除"/></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="14" style="padding: 5px 0;"><jscn:pagination
									numEntries="10" currentPage="${bank.page.curPageNo}"
									pageSize="${bank.page.pageSize}" total="${bank.page.totalCount}"
									numEdgeEntries="1" pageUrl="${pageUrl}"></jscn:pagination>
							</td>
						</tr>
					</tfoot>
				</table>
			</td>
		</tr>
	</table>
	<!-- 弹出浮动层 -->
	<div id="modifyMerchantType" style="width:auto;min-height:0px;height:0px;">

	</div>	
	<div id="addMerchantType" style="width:auto;min-height:0px;height:0px;">

	</div> 
</body>
</html>