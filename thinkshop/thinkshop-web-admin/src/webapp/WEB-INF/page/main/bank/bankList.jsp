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
					$("#addBank").dialog({
						autoOpen:false,
						modal:true,
						title:"添加支付银行",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#addBank").html("");
						}
					});
					//弹出修改页面
					$("#modifyBank").dialog({
						autoOpen:false,
						modal:true,
						title:"修改支付银行",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#modifyBank").html("");
						}	
					});
				});
				
				
				
				//删除支付银行
				function deleteBank(id){
					if(confirm("确定删除吗?")){
							window.location.href = "<%=basePath%>bank/delete.htm?id="+id;
						}else{
							return;
						}	
				}
				//添加支付银行
				function addBank(){
					var url="";
					$("#addBank").dialog('open');
					url="bank/toadd.htm",
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#addBank").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#addBank").html(data);
						}
					}); 
				}
				//编辑支付银行
				function modifyBank(id,pic){
					var url="";
					$("#modifyBank").dialog('open');
					url="bank/toadd.htm?id="+id+"&pic="+pic,
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#modifyBank").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#modifyBank").html(data);
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
						<div class="title1">支付银行信息</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #ffffff;text-align: center;">
				<div class="searchbox">
					<form id="search_form" action="bank/search.htm" method="post">
						<ul>
							<li><label>支付银行编码:</label><input id="code"
								value="${code}" name="code" type="text" class="inp"/>
							</li>
							<li><label>支付银行名称:</label><input id="name"
								name="name" value="${name}" type="text" class="inp" />
							</li>
							<li><label>所属支付方式:</label>
							<select name="payType">
								<option value="">请选择</option>
								<c:forEach var="type" items="${payType}">
									<option value="${type.id }"<c:if test="${payTypes==type.id}">selected</c:if> >${type.value }</option>								
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
						<input type="button" class="searchresetbtn" onclick="addBank();" value="添加支付银行"/>
					</div>
				<table class="table" id="content">
					<thead>
						<tr>
							<td>ID</td>
							<td>支付银行名称</td>
							<td>支付银行编码</td>
							<td>所属支付方式</td>
							<td>手续费率</td>
							<td>是否支持借记卡</td>
							<td>是否支持信用卡</td>
							<td>是否支持企业网银</td>
							<td>支付银行Logo</td>
							<td>支付银行网站</td>
							<td>指定排序</td>
							<td>是否激活</td>
							<td>备注信息</td>
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
								<td>${bank.name}</td>
								<td>${bank.code}</td>
								<td>
								<jscn:PayTag flag="2" value="${bank.payType}"></jscn:PayTag>
								</td>
								<td>${bank.rate}</td>
								<td>
								<c:if test="${bank.isDebit==true}">是</c:if>
								<c:if test="${bank.isDebit==false}">否</c:if>
								</td>
								<td>
								<c:if test="${bank.isCredit==true}">是</c:if>
								<c:if test="${bank.isCredit==false}">否</c:if>
								</td>
								<td>
								<c:if test="${bank.isEnterpise==true}">是</c:if>
								<c:if test="${bank.isEnterpise==false}">否</c:if>
								</td>
								<td>
<%-- 								<a href="<%=basePath%>/bank/downLogo.htm?path='<%=basePath%>${bank.logoPath}'"></a> --%>
								<img alt="" src="<%=basePath%>images/bank_ico/${bank.logoPath}" width="50" height="20">
								</td>
								<td>${bank.webSite}</td>
								<td>${bank.dispalyOrder}</td>
								<td>
								<c:if test="${bank.isActive==true}">已激活</c:if>
								<c:if test="${bank.isActive==false}">未激活</c:if>
								</td>
								<td>
								<span title="${bank.remark}"><nobr>${fn:substring(bank.remark,0,10)}...</nobr></span>
								</td>
								<td>
								<a href="javascript:void(0)" onclick="modifyBank('${bank.id}','${bank.logoPath}')"><img src='images/login/edit.gif'  alt="编辑" title="编辑"/></a>
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
	<div id="modifyBank" style="width:auto;min-height:0px;height:0px;">

	</div>	
	<div id="addBank" style="width:auto;min-height:0px;height:0px;">

	</div> 
</body>
</html>