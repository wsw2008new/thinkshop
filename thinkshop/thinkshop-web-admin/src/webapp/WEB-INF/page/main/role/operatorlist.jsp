<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
		<link type="text/css" href="<%=basePath%>js/jui/css/ui-lightness/jquery-ui.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jui/js/jquery-ui.min.js"></script>	
		<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>		
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
			$(document).ready(function(){
				bothChecked();
				
				$("#tablecontent1 a[name='xinxi']").mouseover(function(){
					$(this).css('color','red');
				});
				$("#tablecontent1 a[name='xinxi']").mouseout(function(){
					$(this).css('color','#087BB2');
				});
				
				//弹出修改页面
				$("#modify").dialog({
					autoOpen:false,
					modal:true,
					title:"操作员信息修改",
					resizable:false,
					width:700,
					height:400,
					close:function(){
						$("#modify").html("");
					}	
				});
				
				//弹出添加页面
				$("#add").dialog({
					autoOpen:false,
					modal:true,
					title:"添加操作员",
					resizable:false,
					width:700,
					height:450,
					close:function(){
						$("#add").html("");
					}										
				});
				
				//弹出信息界面
				$("#xinxi").dialog({
					autoOpen:false,
					modal:true,
					title:"操作员详细信息",
					resizable:false,
					width:450,
					height:350,
					close:function(){
						$("#xinxi").html("");
					}
										
				});
				
				//重置查询数据
				$(".searchresetbtn").click(function(){
					$("#operatorId").val("");
					$("#name").val("");
					$("select[name=status]").val("");
				});
				
			});
			//onclick="$('#modify').dialog('open');"
			function premodify(id){
				if(id==null ||id==""){
					return ;
				}
				if(id=="admin"){
					alert("不能修改管理员!");
					return ;
				}
				var url="";
				$("#modify").dialog('open');
				url="operator/tomodifyoperator.htm?operatorId="+id;
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#modify").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#modify").html(data);
					}
				}); 
			}
																													
			function preadd(){
				var url="";
				$("#add").dialog('open');
				url=basePath+"operator/toaddoperator.htm";
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#add").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#add").html(data);
					}
				}); 
			}

			
			
			//删除选中操作员
			function delAll(){
				var isChecked;
				var isAdmin=false;
				$(":checkbox").each(function(){
					if($(this).attr("checked") == "checked"){
						isChecked = true;
						if($(this).val()=="admin")
						{
							alert("不能删除管理员!");
							isAdmin=true;
						};
					};
					
				});
					if(isAdmin==false)
					{
						if(isChecked != true){
							alert("请至少选择一项");
							return;
						}
						if(window.confirm("确认删除吗?")){
						$("#del_form").submit();
						}else{
							return false;
						}
					}
					
			}
			
			//全选按钮
			function bothChecked(){
				$(".quanxuan").click(function(){
					if($(this).attr('sel') == "all"){
						$("#tablecontent1").children("tr").children("td:first-child").children("input[type='checkbox']")
							.attr("checked",true);
						$("#tablecontent1").children("tr").children("td:first-child").children("input[value='admin']")
						.attr("checked",false);
						$(this).attr('sel',"noAll");
						$(".quanxuan").html("全不选");
					}else{
						$("#tablecontent1").children("tr").children("td:first-child").children("input[type='checkbox']")
						 .attr("checked",false);
						$(this).attr('sel',"all");
						$(".quanxuan").html("全选");
					}
				});				
			 }
			
			
			//详细信息
			function getInf(id){
				var url="";		
				$("#xinxi").dialog('open');
				url=basePath+"operator/getoperator.htm?operatorId="+id;
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#xinxi").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#xinxi").html(data);
					}
				}); 
			}
			
			function isConfirm(id,url){
				if(id=='admin'){
					alert("不能删除管理员!");
					return false;
				}
				if(window.confirm('确认删除吗?')){
					var operatorId1=$("#search_form input[name=operatorId]").val();
					var name=$("#search_form input[name=name]").val();
					var status=$("#search_form select[name=status]").val();
					var curNo=$("#search_form input[name=curPageNo]").val();
					var pageSize=$("#search_form input[name=pageSize]").val();
					url=basePath+url+"?operatorId="+id+"&operatorId1="+operatorId1+"&name="+name+"&status="+status+"&curPageNo="+curNo+"&pageSize="+pageSize;
					window.location.href=url;
					return true;
						
				}else{
					return false;
				}
			}
			
			
						
		</script>
	</head>
	<body style="background:#fff;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td style="height:8px;background:#1791c2;"></td>
			</tr>
			<tr>
				<td>
					<div class="leftpanel" style="height:100%;">
						<div class="leftpane" style="height:100%;padding-left:0;">
							<div class="title1">操作员管理</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="background:#fff;text-align: center;">
					
					<div class="searchbox">
							<form id="search_form" action="operator/searchoperators.htm" method="post">
								<input type="hidden" name="curPageNo" value="${operators.page.curPageNo}"/>
								<input type="hidden" name="pageSize" value="${operators.page.pageSize}"/>
								<ul>
								<li><label>登陆账号:</label><input id="operatorId" value="${operatorId}" name="operatorId" type="text" class="inp"/></li>
								<li><label>姓名:</label><input id="name" name="name" value="${name}" type="text" class="inp"/></li>
								<li><label>状态:</label><select name="status">
								<option value="">---请选择---</option>
								<c:if test="${status=='1'}">
									<option value="1" selected="selected">正常</option>
									<option value="0">禁用</option>
								</c:if>
								<c:if test="${status=='0'}">
									<option value="1">正常</option>
									<option value="0"  selected="selected">禁用</option>
								</c:if>
								<c:if test="${status==''||status==null}">
									<option value="1">正常</option>
									<option value="0">禁用</option>
								</c:if>
								</select>
								</li>
								<li><input type="submit" class="searchbtn" value="查 询"/> <input type="button" class="searchresetbtn" value="重置查询数据"/></li>
								</ul>						
							</form>
					
					</div>
					<div  style="clear:both;padding-right:50px;text-align:right;">				
						<input type="button" class="searchresetbtn" onclick="preadd();" value="添加操作员"/><button class="quanxuan" sel="all">全 选</button>
				<button class="delAll" onclick="delAll();">删 除</button>
					</div>
					<table class="table" id="content">
						<thead>
							<tr>
								<td class="choosewidth">选择</td>
								<td>登录账号</td>
								<td>姓名</td>
								<td style="width:10%;">状态</td>
								<td style="width:10%;">修改</td>
								<td style="width:10%;">删除</td>
							</tr>
						</thead>
							<form action="operator/deleteoperators.htm" id="del_form" method="post">
								<tbody id="tablecontent1">
									<c:forEach items="${operators.results}" var="o">
										<tr>
											<td>
												<c:if test="${o.operatorId!='admin'}">
													<input type="checkbox"  name="ids" value="${o.operatorId}"/>
												</c:if>
											</td>
											<td><a href="javascript:void(0);" onclick="getInf(id);" name="xinxi" id="${o.operatorId}">${o.operatorId}</a></td>
											<td>${o.name}</td>
											<td><c:if test="${o.status==1}"><span style="color:green;">正常</span></c:if><c:if test="${o.status==0}"><span style="color:red;">禁用</span></c:if></td>
											<td><a class="premodify" id="${o.operatorId}" href="javascript:void(0);" onclick="premodify(id);">修改</a></td>
											<td><a class="del" id="${o.operatorId}" onclick=" return isConfirm(id,'operator/deleteoperator.htm');" href="javascript:void(0);" >删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</form>
													
						<tfoot>
							<tr>
								<td colspan="7" style="padding:5px 0;">
									<jscn:pagination numEntries="10" currentPage="${operators.page.curPageNo}" pageSize="${operators.page.pageSize}" total="${operators.page.totalCount}" numEdgeEntries="1" pageUrl="${pageUrl}"></jscn:pagination>
								</td>
							</tr>
						</tfoot> 
					</table>
				</td>
			</tr>
		</table>
	
		<!-- 弹出浮动层 -->
	<div id="modify" style="width:auto;min-height:0px;height:297px;">
	</div>	
	
	<div id="add" style="width:auto;min-height:0px;height:297px;">
	</div>	

	<div id="xinxi" style="width:auto;min-height:0px;height:297px;">
	</div>
	</body>
</html>