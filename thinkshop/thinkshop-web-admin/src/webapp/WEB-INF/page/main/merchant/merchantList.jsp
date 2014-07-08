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
					$("#addMerchant").dialog({
						autoOpen:false,
						modal:true,
						title:"添加商户",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#addMerchant").html("");
						}
					});
					//弹出修改页面
					$("#modifyMerchant").dialog({
						autoOpen:false,
						modal:true,
						title:"修改商户",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#modifyMerchant").html("");
						}	
					});
				});
				
				
				
				//删除商户
				function deleteBank(id){
					if(confirm("确定删除吗?")){
							window.location.href = "<%=basePath%>merchant/delete.htm?id="+id;
						}else{
							return;
						}	
				}
				//添加商户
				function addMerchant(){
					var url="";
					$("#addMerchant").dialog('open');
					url="merchant/toadd.htm",
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#addMerchant").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#addMerchant").html(data);
						}
					}); 
				}
				//编辑商户
				function modifyMerchant(id,pic){
					var url="";
					$("#modifyMerchant").dialog('open');
					url="merchant/toadd.htm?id="+id+"&pic="+pic,
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#modifyMerchant").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#modifyMerchant").html(data);
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
						<div class="title1">商户信息</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #ffffff;text-align: center;">
				<div class="searchbox">
					<form id="search_form" action="merchant/search.htm" method="post">
						<ul>
							<li><label>商户类型名称:</label><input id="code"
								value="${merchant_type_name}" name="merchant_type_name" type="text" class="inp"/>
							</li>
							<li><label>商户名称:</label><input id="name"
								name="name" value="${name}" type="text" class="inp" />
							</li>
							<li><label>是否激活:</label>
							<select name="is_lock">
								<option value="">请选择</option>
								<c:forEach var="type" items="${allType}">
									<option value="${type.id}"<c:if test="${payTypes==type.id}">selected</c:if> >
									<c:if test="${type.value=='1'}">激活</c:if>
									<c:if test="${type.value=='0'}">未激活</c:if>
									</option>								
								</c:forEach>
							</select>
							</li>
							<li><input type="submit" class="searchbtn" value="查 询" /> &nbsp;&nbsp;&nbsp;
								<input type="reset" class="searchresetbtn" value="重置查询数据">
							</li>
						</ul>
					</form>
				</div>
				<div  style="clear:both;padding-right:50px;text-align:right;">				
						<input type="button" class="searchresetbtn" onclick="addMerchant();" value="添加商户"/>
					</div>
				<table class="table" id="content">
					<thead>
						<tr>
							<td>ID</td>
							<td>LOGO</td>
							<td>商户类型名称</td>
							<td>商户名称</td>
							<td>服务</td>
							<td>每人消费</td>
							<td>营业时间</td>
							<td>预定电话</td>
							<td>是否锁定</td>
							<td>是否入驻</td>
							<td>特色信息</td>
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
								<c:set var="id" value="${id+1}" />
								<td>
								<c:if test="${not empty bank.logo}"><img src="<%=basePath%>${bank.logo}" height="40" width="70"></c:if>
								<c:if test="${empty bank.logo}"><img src="<%=basePath%>images/no-picx.png" height="40" width="70"></c:if>
								</td>
								<td>${bank.merchant_type_name}</td>
								<td>${bank.name}</td>
								<td>${bank.service}</td>
								<td>
								${bank.per_fee}
								</td>
								<td>
								${bank.open_time}
								</td>
								<td>
								${bank.order_phone}
								</td>
								<td>
								<c:if test="${bank.is_lock=='1'}">已锁定</c:if>
								<c:if test="${bank.is_lock=='0'}">未锁定</c:if>
								</td>
								<td>
								<select name="is_join" disabled="disabled">
									<c:forEach items="${list}" var="join">
										<option value="${join.id}" <c:if test="${bank.is_join == join.id}">selected</c:if>>${join.name}</option>
									</c:forEach>
								</select>
								</td>
								<td>
								<span title="${bank.feature}"><nobr>${fn:substring(bank.feature,0,10)}...</nobr></span>
								</td>
								<td>
								<a href="javascript:void(0)" onclick="modifyMerchant('${bank.id}')"><img src='images/login/edit.gif'  alt="编辑" title="编辑"/></a>
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
	<div id="modifyMerchant" style="width:auto;min-height:0px;height:0px;">

	</div>	
	<div id="addMerchant" style="width:auto;min-height:0px;height:0px;">

	</div> 
</body>
</html>