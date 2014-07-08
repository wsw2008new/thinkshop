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
			var basePath = "<%=basePath%>";
			$(document).ready(function(){
				
				//弹出详情页面
				$("#list").dialog({
					autoOpen:false,
					modal:true,
					title:"日志信息详情",
					resizable:false,
					width:520,
					height:500,
					close:function(){
						$("#list").html("");
					}	
										
				});
				//重置查询数据
				$(".searchresetbtn").click(function(){
					$("#operatorId").val("");
					$("#operatorName").val("");
					$("#beginTime").val("");
					$("#endTime").val("");
				});
			});
			
			function prelist(id){
				if(id==null ||id==""){
					return ;
				}
				var url="";
				$("#list").dialog('open');
				url=basePath+"log/getlogbyid.htm?id="+id;
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#list").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#list").html(data);
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
						<div class="title1">系统日志</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #ffffff;text-align: center;">
				<div class="searchbox">
					<form id="search_form" action="log/querylogs.htm" method="post">
						<ul>
							<li><label>登陆账号:</label><input id="operatorId"
								value="${operatorId}" name="operatorId" type="text" class="inp"/>
							</li>
							<li><label>姓名:</label><input id="operatorName"
								name="operatorName" value="${operatorName}" type="text" class="inp" />
							</li>
							<li><label>开始时间:</label><input id="beginTime" name="beginTime" style="width: 90px;" class="inp" value="${beginTime}" type="text" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\',{d:0})}'});" />
							</li>
							<li><label>结束时间:</label><input id="endTime" name="endTime" class="inp" value="${endTime}"  style="width: 90px;" type="text" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\',{d:0})}'});" />
							</li>
							<li><input type="submit" class="searchbtn" value="查 询" /> &nbsp;&nbsp;&nbsp;
									<input type="button" class="searchresetbtn" value="重置查询数据">
							</li>
						</ul>
					</form>
				</div>
				<table class="table" id="content">
					<thead>
						<tr>
							<td>ID</td>
							<td>登录帐号</td>
							<td>姓名</td>
							<td>时间</td>
							<td style="width: 10%;">IP地址</td>
							<td style="width: 30%;">内容</td>
							<td style="width: 10%;">详细信息</td>
						</tr>
					</thead>
					<tbody id="tablecontent">
						<c:set var="id" value="1" />
						<c:forEach items="${log.results}" var="log">
							<tr>
								<td>${id}</td>
								<c:set var="id" value="${id+1}" />
								<td>${log.operatorId}</td>
								<td>${log.operatorName}</td>
								<td><fmt:formatDate value="${log.time}" type="both" /></td>
								<td>${log.ip}</td>
								<td>${fn:substring((log.content),0,20)}</td>
								<td><a class="prelist" id="${log.id}" href="javascript:void(0);" onclick="prelist(id);"><img src='images/login/look.gif'  title="详情" alt="详情"/></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="padding: 5px 0;"><jscn:pagination
									numEntries="10" currentPage="${log.page.curPageNo}"
									pageSize="${log.page.pageSize}" total="${log.page.totalCount}"
									numEdgeEntries="1" pageUrl="${pageUrl}"></jscn:pagination>
							</td>
						</tr>
					</tfoot>
				</table></td>
		</tr>
	</table>
	<!-- 弹出浮动层 -->
	<div id="list" style="width: auto; min-height: 0px; height: 0px;">
	</div>
</body>
</html>