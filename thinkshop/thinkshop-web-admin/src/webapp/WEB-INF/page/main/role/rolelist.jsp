<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="<%=basePath%>js/jtree/jquery.treeview.css" />
		<link type="text/css" href="<%=basePath%>js/jui/css/ui-lightness/jquery-ui.css" rel="stylesheet" />	
		<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jui/js/jquery-ui.min.js"></script>	
		<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>	
		<script type="text/javascript" src="<%=basePath%>js/jtree/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jtree/jquery.treeview.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jtree/jquery.treeview.edit.js"></script>
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
			$(function(){
				bothChecked();
				//弹出修改页面
				$("#modifyrole").dialog({
					autoOpen:false,
					modal:true,
					title:"角色修改",
					resizable:false,
					width:700,
					height:350,
					close:function(){
						$("#modifyrole").html("");
					}	
				});
				
				//弹出添加页面
				$("#addrole").dialog({
					autoOpen:false,
					modal:true,
					title:"添加角色",
					resizable:false,
					width:700,
					height:350,
					close:function(){
						$("#addrole").html("");
					}	
										
				});
				
				$("#modifyrolemenu").dialog({
					autoOpen:false,
					modal:true,
					title:"菜单角色修改",
					resizable:false,
					width:350,
					height:450,
					close:function(){
						$("#modifyrolemenu").html("");
					},
					buttons: {
						"提交": function() { 
							$("#menuroleForm").submit();
						}, 
						"取消": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				//弹出修改页面
				$("#modifyroleoperator").dialog({
					autoOpen:false,
					modal:true,
					title:"操作员角色修改",
					resizable:false,
					width:625,
					height:475,
					close:function(){
						$("#modifyroleoperator").html("");
					},
					buttons: {
					"提交": function() { 
						var id="";
						var code=$("input[name=rolecode]").val();
					//	var oo=$("#searchablems2side__dx").children().val();
						sel=document.getElementById("searchablems2side__dx");
						len=sel.options.length;
					//	alert(len);
						for(i=0;i<len;i++){
							id+=sel.options[i].value+",";
							
						}
						id+=code;
				//		alert(id);
						$.post(
							basePath+"rolegl/modifyroleoperator.htm?id="+id,
							function(rs){
								window.location.href=basePath+"rolegl/queryroles.htm";
								return true;
							}
						);
					}, 
					"取消": function() { 
						$(this).dialog("close"); 
					} 
				}
				});
				
				//重置查询数据
				$(".searchresetbtn").click(function(){
					$("#title").val("");
				});
			});
			
			function premodifyMenus(id){
				if(id==null ||id==""){
					return ;
				}
				
				var url="";
				$("#modifyrolemenu").dialog('open');
				url="role/getcheckmenus.htm?roleCode="+id+"&parentCode=0";
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#modifyrolemenu").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#modifyrolemenu").html(data);
					}
				}); 
			}
			
			function premodifyrole(id){
				if(id==null ||id==""){
					return ;
				}
				
				var url="";
				$("#modifyrole").dialog('open');
				url="rolegl/tomodifyrole.htm?code="+id;
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#modifyrole").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#modifyrole").html(data);
					}
				}); 
			}
			
			function preaddrole(id){
				var url="";
				$("#addrole").dialog('open');
				url="rolegl/toaddrole.htm",
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#addrole").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
						$("#addrole").html(data);
					}
				}); 
				
			}
			
			function premodifyroleoperator(id){
				if(id==null ||id==""){
					return ;
				}
				
				var url="";
				$("#modifyroleoperator").dialog('open');
				url="rolegl/tomodifyroleoperator.htm?code="+id;
				$.ajax({
					type: "POST",
					async: true,
					url: url,
					beforeSend:function(XHR){
					$("#modifyroleoperator").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
					},
					success: function(data){
					$("#modifyroleoperator").html(data);
					}
				}); 
			}

			//全选按钮
			  function bothChecked(){
				$(".quanxuan").click(function(){
					if($(this).attr('sel') === "all"){
						$("#tablecontent").children("tr").children("td:first-child").children("input[type='checkbox']")
							.attr("checked",true);
						$("#tablecontent").children("tr").children("td:first-child").children("input[value='ROLE_ADMIN']")
						.attr("checked",false);
						$(this).attr('sel',"noAll");
						$(".quanxuan").html("全不选");
					}else{
						$("#tablecontent").children("tr").children("td:first-child").children("input[type='checkbox']")
						 .attr("checked",false);
						$(this).attr('sel',"all");
						$(".quanxuan").html("全选");
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
							if($(this).val()=="ROLE_ADMIN")
							{
								alert("不能删除管理员权限组!");
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
							$("#delroles_form").submit();
						}else{
							return false;
						}
					}
						
				}
			

			function isConfirm(id){
				if(id=='ROLE_ADMIN'){
					alert("不能删除管理员权限组!");
					return false;
				}
				if(window.confirm('确认删除吗?')){
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
							<div class="title1">角色管理</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="background:#fff;text-align: center;">	
							
					<div class="searchbox">
							<form id="search_form" action="rolegl/queryroles.htm" method="post">		 
							<input type="hidden" name="pageSize" value="${roles.page.pageSize}"/>
							<ul>
							<li><label>角色名称:</label><input id="title" value="${title}" name="title" type="text" class="inp"/></li>
							<li><input type="submit" class="searchbtn" value="查 询"/> <input type="button" class="searchresetbtn" value="重置查询数据"/></li>
							</ul>		
										
							</form>
					
					</div>
					<div  style="clear:both;padding-right:50px;text-align:right;">				
						<input type="button" class="searchresetbtn" onclick="preaddrole();" value="添加角色"/>
					</div>
					<table class="table" id="content">
						<thead>
							<tr>
								<td class="choosewidth">选择</td>
								<td>编码</td>
								<td>名称</td>
								<td style="width:10%;">备注</td>
								<td style="width:10%;">操作员操作</td>
								<td style="width:10%;">菜单操作</td>
								<td style="width:10%;">修改</td>
								<td style="width:10%;">删除</td>
							</tr>
						</thead>
						<form action="rolegl/deleteroles.htm" id="delroles_form" method="post">	
						<tbody id="tablecontent">
												
							<c:forEach items="${roles.results}" var="r">
							<tr>
								<td>
									<c:if test="${r.code!='ROLE_ADMIN'}">
										<input type="checkbox"  name="ids" value="${r.code}"/>
									</c:if>
								</td>
								<td>${r.code}</td>
								<td>${r.title}</td>
								<td>${r.remark}</td>
								<td><a id="${r.code}" href="javascript:void(0);" onclick="premodifyroleoperator(id);">修改操作员</a></td>
								<td><a id="${r.code}" href="javascript:void(0);" onclick="premodifyMenus(id);">修改菜单</a></td>
								<td><a class="mod" id="${r.code}"  href="javascript:void(0);"  onclick="premodifyrole(id);">修改</a></td>
								<td><a class="del" id="${r.code}" onclick=" return isConfirm(id);" href="rolegl/deleterole.htm?code=${r.code}" >删除</a></td>
							</tr>
							</c:forEach>		
							</form>
						</tbody>
						
							
						<tfoot>
							<tr>
								<td colspan="8" style="padding:5px 0;">
									<jscn:pagination pageUrl="${pageUrl}" numEntries="10" currentPage="${roles.page.curPageNo}" pageSize="${roles.page.pageSize}" total="${roles.page.totalCount}" numEdgeEntries="1"></jscn:pagination>
								</td>
							</tr>
						</tfoot> 
					</table>
					<div class="operatebox" style="width:91%;">
			<div class="op-left" >
				<button class="quanxuan" sel="all">全 选</button>
				<button class="delAll" onclick="delAll();">删 除</button>
			</div>
		</div>
				</td>
			</tr>
		</table>
		
		<!-- 弹出浮动层 -->
	<div id="modifyrole" style="width:auto;min-height:0px;height:0px;">

	</div>	
	<div id="addrole" style="width:auto;min-height:0px;height:0px;">

	</div> 
	
	<div id="modifyroleoperator" style="width:auto;min-height:0px;height:0px;">

	</div>
	
	<div id="modifyrolemenu" style="width:auto;min-height:0px;height:0px;">

	</div>
		
	</body>
</html>