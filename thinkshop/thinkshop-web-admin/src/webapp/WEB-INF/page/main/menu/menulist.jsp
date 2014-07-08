<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath%>css/manage1.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>js/jtree/jquery.treeview.css" />
<link type="text/css"
	href="<%=basePath%>js/jui/css/ui-lightness/jquery-ui.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-form.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jui/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jtree/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jtree/jquery.treeview.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jtree/jquery.treeview.edit.js"></script>
<script type="text/javascript">
			var basePath="<%=basePath%>";
	$(function() {
		$("#browser").treeview({
			animated : "fast",
			control : "#sidetreecontrol",
			persist : "location",
			toggle : function() {
				$("li span").css("color","black"); 
				$("li a").css("color","black"); 
				$(this).children("span:eq(0)").css("color","red"); 
				var code = $(this).children("span:eq(0)").attr("code");
				var count = $(this).children("span:eq(0)").attr("count");
				if (!$("#" + code + "ul").children("li").is("li")) {
					querychildrenmenus(code, count);
				}
				querychildrentable(code, count);
			}

		});
		//弹出添加页面
		$("#addmenu").dialog({
			autoOpen : false,
			modal : true,
			title : "添加功能菜单",
			resizable : false,
			width : 700,
			height : 432,
			close : function() {
				$("#addmenu").html("");
			}
		});

		//弹出修改页面
		$("#modifymenu").dialog({
			autoOpen : false,
			modal : true,
			title : "修改功能菜单",
			resizable : false,
			width : 700,
			height : 400,
			close : function() {
				$("#modifymenu").html("");
			}
		});
	});

	function menuclick(obj) {
		$("li span").css("color","black"); 
		$("li a").css("color","black"); 
		$(obj).children("a").css("color","red"); 
		var code = $(obj).attr("code");
		var count = $(obj).attr("count");
		querychildrenmenus(code, count); 
		querychildrentable(code, count);
	}

	function querychildrenmenus(code, count) {
		var childrenCount = count;
		var id = code + "li";
		if (childrenCount > 0) {
			if ($("#" + id).children().hasClass("closed") == false
					&& $("#" + id).children().hasClass("f") == false) {
				$.post(basePath + "menu/querychildrenmenus.htm", "parentCode="
						+ code, function(data) {
					var oldbranches = $("#" + code + "ul").children("li").children("span");
					//注释掉 wangyu
// 					$("#browser").treeview({
// 						remove : oldbranches
// 					});
					
					var branches = $(data).appendTo("#" + code + "ul");
					$("#browser").treeview({
						add : branches
					});
				}, "html");

			}

		}

	}

	function querychildrentable(code, count) {
		var childrenCount = count;
		$.post(basePath + "menu/loadchildrenmenus.htm", "parentCode=" + code,
				function(data) {
					$("#menucontent").html("");
					$("#menucontent").html(data);
				}, "html"

		);
		

	}

	//弹出添加页面
	function preaddmenu(id) {
		if (id == null || id == "") {
			return;
		}

		var url = "";
		$("#addmenu").dialog('open');
		url = "menu/getmenu.htm?code=" + id;
		$
				.ajax({
					type : "POST",
					async : true,
					url : url,
					beforeSend : function(XHR) {
						$("#addmenu")
								.html(
										"<p style='text-align:center;padding-top:100px;'><img src='"+basePath+"images/login/load_img.gif' width='20'/>&nbsp;数据加载中...</p>");
					},
					success : function(data) {
						$("#addmenu").html(data);
					}
				});
	}

	//弹出修改页面
	function premodifymenu(id) {
		if (id == null || id == "") {
			return;
		}

		var url = "";
		$("#modifymenu").dialog('open');
		url = "menu/tomodifymenu.htm?code=" + id;
		$
				.ajax({
					type : "POST",
					async : true,
					url : url,
					beforeSend : function(XHR) {
						$("#modifymenu")
								.html(
										"<p style='text-align:center;padding-top:100px;'><img src='"+basePath+"images/login/load_img.gif' width='20'/>&nbsp;数据加载中...</p>");
					},
					success : function(data) {
						$("#modifymenu").html(data);
					}
				});
	}

	function modifymenu() {
		var nobadchar = /^([a-zA-Z0-9_\u4e00-\u9fa5]+)$/;
		var title1=$("#modifymenu_form input[name=title]").val();
		var titlelength=title1.length;
		for ( var i = 0; i < title1.length; i++) {
			if ( title1.charCodeAt(i) > 127) {
				titlelength++;
			}
		}
		if(titlelength>30){
			alert("菜单名称长度不能超过30位");
			return false;
		}	
		
		
		if(title1==""){
			alert("菜单名称不能为空");
			return false;
		}
		if(!nobadchar.test(title1)){
			alert("菜单名称只能是中文，数字，字母或下划线");
			return false;
		}
		
		var url1=$("#modifymenu_form input[name=url]").val();
		if(url1!=null&&url1!=""){
			if(url1 .length>200){
				alert("拼音长度不能超过200位");
				return false;
			}	
		}
		
		var displayNum1=$("#modifymenu_form input[name=displayNum]").val();
		var regu = /^[0-9]{0,2}$/;
		if(!regu.test(displayNum1)){
			alert("排序号为2位整数");
			return false;
		}
		if(displayNum1 .length>2){
			alert("排序号为2位整数");
			return false;
		}	
		
		var tmp = $("#modifymenu_form").serialize();
		$.post(basePath + "menu/modifymenu.htm", tmp, function(rs) {
			var menu = rs.menu;
			var code = menu.code;
			var title = menu.title;
			var childrenCount = menu.childrenCount;
			var liid = code + "li";
			var ulid = code + "ul";
			var parentCode = menu.parentCode;
			var parentCount = rs.parentCount;

			$("li#" + liid).children("span").text(title);
			querychildrentable(parentCode, parentCount);
		}, "json");
		$("#modifymenu").dialog('close');
	}

	function addmenu() {
		var chrnum = /^([a-zA-Z0-9_]+)$/; 
		var code1=$("#addmenu_form input[name=code]").val();
		if(code1 .length>15){
			alert("菜单编码长度不能超过15位");
			return false;
		}	
		if(code1==""){
			alert("菜单编码不能为空");
			return false;
		}
		if(!chrnum.test(code1)){
			alert("菜单编码只能是数字，字母或下划线");
			return false;
		}
		
		var nobadchar = /^([a-zA-Z0-9_\u4e00-\u9fa5]+)$/;
		var title1=$("#addmenu_form input[name=title]").val();
		var titlelength=title1.length;
		for ( var i = 0; i < title1.length; i++) {
			if ( title1.charCodeAt(i) > 127) {
				titlelength++;
			}
		}
		
		if(title1==""){
			alert("菜单名称不能为空");
			return false;
		}
		if(!nobadchar.test(title1)){
			alert("菜单名称只能是中文，数字，字母或下划线");
			return false;
		}
		if(titlelength>30){
			alert("菜单名称长度不能超过30位");
			return false;
		}	
		
		var url1=$("#addmenu_form input[name=url]").val();
		if(url1!=null&&url1!=""){
			if(url1 .length>200){
				alert("菜单地址不能超过200位");
				return false;
			}	
		}
		
		if(flag==false){
			return false;
		}
		
		var tmp = $("#addmenu_form").serialize();
		$
				.post(
						basePath + "menu/addmenu.htm",
						tmp,
						function(rs) {
							var menu = rs.menu;
							var code = menu.code;
							var title = menu.title;
							var childrenCount = menu.childrenCount;
							var liid = code + "li";
							var ulid = code + "ul";
							var parentCode = menu.parentCode;
							var parentCount = rs.parentCount;

							
							if(parentCount>1||parentCode==0)
							{
								var branches = $(
										"<li  id="+liid+" class='f'><span  code="
												+ code
												+ " count="
												+ parentCount
												+ "  class='file' onclick='menuclick(this);'><a href='javaScript:void(0);'>"
												+ title + "</a></span><ul id='"+code+"ul'></ul></li>")
										.appendTo("#" + parentCode + "ul");
								$("#browser").treeview({
									add : branches
								});
								$("li#" + parentCode + "li").children("span").attr(
										"count", parentCount);
							}
							else
							{
								$("li#" + parentCode + "li").children("span").attr(
										"count", parentCount);
								$("li#" + parentCode + "li").children("span")
								.removeClass("file");
								$("li#" + parentCode + "li").children("span")
								.addClass("folder");
								$("li#" + parentCode + "li").children("span").removeAttr("onclick");
								var parentTitle=$("li#" + parentCode + "li").children("span").children("a").text();
								$("li#" + parentCode + "li").children("span").children("a").remove();
								$("li#" + parentCode + "li").children("span").text(parentTitle);
								var branches = $(
										"<li  id="+liid+" class='f'><span  code="
												+ code
												+ " count="
												+ parentCount
												+ "  class='file' onclick='menuclick(this);'><a href='javaScript:void(0);'>"
												+ title + "</a></span><ul id='"+code+"ul'></ul></li>").appendTo("#" + parentCode + "ul");
								$("#browser").treeview({
									add : branches
								});
							}
							querychildrentable(parentCode, parentCount);
						}, "json");
		$("#addmenu").dialog('close');
	}

	function deletemenu(codes,parentCode) {
		$
				.post(
						basePath + "menu/deletemenu.htm",
						"codes=" + codes+"&parentCode="+parentCode,
						function(rs) {
							var codes = rs.codes;
							for ( var i = 0; i < codes.length; i++) {
								var code = codes[i];
								var liid = code + "li";
								var ulid = code + "ul";
								
								var parentCount = $("#" + liid).parent("ul")
										.parent("li").children("span").attr(
												"count");
								$("#" + liid).parent("ul")
								.parent("li").children("span").attr(
										"count",parentCount-1);
								if(parentCount==1)
								{
									$("#" + liid).parent("ul")
									.parent("li").children("span")
									.removeClass("folder");
									$("#" + liid).parent("ul")
									.parent("li").children("span")
											.addClass("file");
								}
								var oldbranches = $("#" + liid);
								oldbranches.remove();
							}
							querychildrentable(parentCode, parentCount);
						}, "json");
		$("#addmenu").dialog('close');
	}
	
	function delAll(parentCode){
		var isChecked;
		var codes=new Array();
		var a="";
		$(":checkbox").each(function(i){
			if($(this).attr("checked") == "checked"){
				isChecked = true;
				a+=$(this).val()+",";
			};
		});
			if(isChecked != true){
				alert("请至少选择一项");
				return;
			}
			if(window.confirm("确认删除吗?")){
				codes=a.split(",");
				deletemenu(codes,parentCode);
			}else{
				return false;
			}
	}
	
	function bothChecked(obj){
			if($(obj).attr('sel') == "all"){
				$(":checkbox").attr("checked",true);
				$(obj).attr('sel',"noAll");
				$(obj).val("全不选");
			}else{
				$(":checkbox").attr("checked",false);
				$(obj).attr('sel',"all");
				$(obj).val("全选");
			}
					
	 }

	function isConfirm(codes,parentCode) {
		if (window.confirm('确认删除吗?')) {
			deletemenu(codes,parentCode);
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body style="background: #fff;">
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td style="height: 8px; background: #1791c2;"></td>
		</tr>
		<tr>
			<td style="height: 33px;">
				<div class="leftpanel">
					<div class="leftpane" style="padding-left: 0;">
						<div class="title1">菜单管理</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #fff; vertical-align: top;">
				<table style="width: 100%; font-size: 12px;">
					<tr>
						<td style="width: 170px; padding: 25px 15px; vertical-align: top;">
							<ul class="filetree" id="browser">
								<c:if test="${menus.results!=null}">
									<li id="0li"><span code="0" count="1" class="treeindex">菜单树</span>
										<ul id="0ul">
											<c:forEach items="${menus.results}" var="m">
												<c:if test="${m.childrenCount>0}">
													<li id="${m.code}li" class="closed"><span
														code="${m.code}" count="${m.childrenCount}" class="folder">${m.title}</span>
														<ul id="${m.code}ul"></ul>
													</li>
												</c:if>
												<c:if test="${m.childrenCount==0}">
													<li id="${m.code}li" class="f"><span code="${m.code}"
														count="${m.childrenCount}" class="file"
														onclick="menuclick(this);"><a
															href="javaScript:void(0);">${m.title}</a></span>
														<ul id="${m.code}ul"></ul>	
													</li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
								</c:if>
								<c:if test="${menus.results==null}">
									<li id="0li" onclick="spanClick(this);"><span code="0"
										count="1">菜单树</span>
									</li>
								</c:if>
							</ul></td>
						<td style="vertical-align: top;text-align: center;" id="menucontent">
							<div style="clear: both; padding-right: 50px; text-align: right;">
								<input type="button" style="margin-top: 5px;"
									id="${parentCode}" class="searchresetbtn"
									onclick="preaddmenu(id);" value="添加功能菜单" />
								<input type="button" style="margin-top: 10px;"
									sel="all" class="searchresetbtn"
									onclick="bothChecked(this);" value="全选" />
								<input type="button" style="margin-top: 10px;"
									 class="searchresetbtn"
									onclick="delAll('${parentCode}');" value="删除" />
							</div>
							<table class="table" style="margin: 20px;">
								<thead>
									<tr>
										<td class="choosewidth">选择</td>
										<td>菜单编码</td>
										<td>菜单名称</td>
										<td>菜单地址</td>
										<td style="width: 10%;">修改</td>
										<td style="width: 10%;">删除</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${menus.results}" var="m">
										<tr>
											<td><input type="checkbox" name="codes"
												value="${m.code}" />
											</td>
											<td>${m.code}</td>
											<td>${m.title}</td>
											<td>${m.url}</td>
											<td><a href="javascript:void(0);"
												onclick="premodifymenu('${m.code}');">修改</a>
											</td>
											<td><a onclick="return isConfirm('${m.code}','${m.parentCode}');"
												href="javascript:void(0);">删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>


									</tr>
								</tfoot>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
	<!-- 弹出层 -->
	<div id="addmenu" style="width: auto; min-height: 0px; height: 297px;">

	</div>

	<div id="modifymenu"
		style="width: auto; min-height: 0px; height: 297px;"></div>
</body>
</html>