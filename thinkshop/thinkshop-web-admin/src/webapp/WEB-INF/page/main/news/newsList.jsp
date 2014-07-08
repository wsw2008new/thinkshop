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
					$("#addNews").dialog({
						autoOpen:false,
						modal:true,
						title:"添加新闻",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#addNews").html("");
						}
					});
					//弹出修改页面
					$("#modifyNews").dialog({
						autoOpen:false,
						modal:true,
						title:"修改新闻",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#modifyNews").html("");
						}	
					});
				});
				
				
				
				//删除新闻
				function deletenews(id){
					if(confirm("确定删除吗?")){
							window.location.href = "<%=basePath%>news/delete.htm?id="+id;
						}else{
							return;
						}	
				}
				//添加新闻
				function addNews(){
					var url="";
					$("#addNews").dialog('open');
					url="news/toadd.htm",
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#addNews").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#addNews").html(data);
						}
					}); 
				}
				//编辑新闻
				function modifyNews(id,pic){
					var url="";
					$("#modifyNews").dialog('open');
					url="news/toadd.htm?id="+id+"&pic="+pic,
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#modifyNews").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#modifyNews").html(data);
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
						<div class="title1">新闻中心</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #ffffff;text-align: center;">
				<div class="searchbox">
					<form id="search_form" action="news/search.htm" method="post">
						<ul>
							<li><label>新闻标题:</label><input id="title"
								value="${title}" name="title" type="text" class="inp"/>
							</li>
							<li><label>所属新闻类型:</label>
							<select name="newsType">
								<option value="">请选择</option>
								<c:forEach var="type" items="${newsType}">
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
						<input type="button" class="searchresetbtn" onclick="addNews();" value="添加新闻"/>
					</div>
				<table class="table" id="content">
					<thead>
						<tr>
							<td>ID</td>
							<td>标题</td>
							<td>新闻类型</td>
							<td>新闻来源</td>
							<td>报道者</td>
							<td>创建时间</td>
							<td>是否激活</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="tablecontent">
					<c:if test="${empty news.results}">
							<tr><td colspan="13">没有查到相关信息</td></tr>
						</c:if>
						<c:set var="id" value="1" />
						<c:forEach items="${news.results}" var="news">
							<tr>
								<td>${id}</td>
								<td>${news.title}</td>
								<td>${news.code}</td>
<!-- 								<td> -->
<%-- 								<jscn:PayTag flag="2" value="${news.payType}"></jscn:PayTag> --%>
<!-- 								</td> -->
								<td>${news.newsType}</td>
								<td>${news.fromWhere}</td>
								<td>${news.createTime}</td>
								<td>
								<c:if test="${news.isactive==1}">已激活</c:if>
								<c:if test="${news.isactive==0}">未激活</c:if>
								</td>
								<td>
								<a href="javascript:void(0)" onclick="modifyNews('${news.id}','${news.logoPath}')"><img src='images/login/edit.gif'  alt="编辑" title="编辑"/></a>
								<a href="javascript:void(0)" onclick="deletenews('${news.id}')" ><img src='images/login/delete.gif'  alt="删除" title="删除"/></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="14" style="padding: 5px 0;"><jscn:pagination
									numEntries="10" currentPage="${news.page.curPageNo}"
									pageSize="${news.page.pageSize}" total="${news.page.totalCount}"
									numEdgeEntries="1" pageUrl="${pageUrl}"></jscn:pagination>
							</td>
						</tr>
					</tfoot>
				</table>
			</td>
		</tr>
	</table>
	<!-- 弹出浮动层 -->
	<div id="modifyNews" style="width:auto;min-height:0px;height:0px;">

	</div>	
	<div id="addNews" style="width:auto;min-height:0px;height:0px;">

	</div> 
</body>
</html>