<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		$("#browser").treeview({
			animated : "fast",
			control : "#sidetreecontrol",
			persist : "location",
			toggle : function() {
				$("li span").css("color", "black");
				$("li a").css("color", "black");
				$(this).children("span:eq(0)").css("color", "red");
				var code = $(this).children("span:eq(0)").attr("code");
				var count = $(this).children("span:eq(0)").attr("count");
				var roleCode = $(this).children("span:eq(0)").attr("roleCode");
				if (!$("#" + code + "ul").children("li").is("li")) {
					querychildrenmenus(roleCode, code, count);
				}
			}
		});
		$("#browser").children("li:eq(0)").children("ul:eq(0)").children(
				"form:eq(0)").children("li").each(function(index) {
			var code = $(this).children("span:eq(0)").attr("code");
			var count = $(this).children("span:eq(0)").attr("count");
			var roleCode = $(this).children("span:eq(0)").attr("roleCode");

			querychildrenmenus(roleCode, code, count);

			$("#" + code + "ul").removeAttr("style");
		});

	});
	function querychildrenmenus(roleCode, code, count) {
		var childrenCount = count;
		var id = code + "li";
		if (childrenCount > 0) {
			if ($("#" + id).children().hasClass("closed") == false
					&& $("#" + id).children().hasClass("f") == false
					&& $("#" + id).children().hasClass("treeindex") == false) {
				$.post(basePath + "role/getcheckmenus.htm", "parentCode="
						+ code + "&roleCode=" + roleCode, function(data) {
					var branches = $(data).appendTo("#" + code + "ul");
					$("#browser").treeview({
						add : branches
					});
				}, "html");

			}

		}

	}
	function menuclick(obj) {
		$("li span").css("color", "black");
		$("li a").css("color", "black");
		$(obj).children("a").css("color", "red");
		var code = $(obj).attr("code");
		var count = $(obj).attr("count");
		var count = $(obj).attr("count");
		var roleCode = $(obj).attr("roleCode");
		querychildrenmenus(roleCode, code, count);
	}

	function checkChildren(checkbox) {
		var n = 0;
		var parentid = $(checkbox).val();
		var check = $(checkbox).attr("checked");
		$($(":checkbox[name='menuIds']")).each(function(index) {
			//alert($(this).attr("parentid"));
			if (($(this).attr("parentid")) == parentid) {
				if (check == "checked") {
					$(this).attr("checked", "checked");
				} else {
					$(this).attr("checked", false);
				}
				checkchildren2(this);
			}

			if ($(checkbox).attr("parentid") == $(this).val()) {
				if ($(this).attr("checked") != "checked") {
					if (check == true) {
						$(this).attr("checked", "checked");
					} else {
						$(this).attr("checked", false);
					}
					checkparent2(this);
				}
				
			}

		});

	}
	
	function checkchildren2(checkbox)
	{
		var n = 0;
		var parentid = $(checkbox).val();
		var check = $(checkbox).attr("checked");
		$($(":checkbox[name='menuIds']")).each(function(index) {
			//alert($(this).attr("parentid"));
			if (($(this).attr("parentid")) == parentid) {
				if (check == "checked") {
					$(this).attr("checked", "checked");
				} else {
					$(this).attr("checked", false);
				}
				checkchildren2(this);
			}});
	}
	
	
	function checkparent2(checkbox)
	{
		var n = 0;
		var parentid = $(checkbox).val();
		var check = $(checkbox).attr("checked");
		$($(":checkbox[name='menuIds']")).each(function(index) {
			if ($(checkbox).attr("parentid") == $(this).val()) {
				if ($(this).attr("checked") != "checked") {
					if (check == true) {
						$(this).attr("checked", "checked");
					} else {
						$(this).attr("checked", false);
					}
					checkparent2(this);
				}
				
			}
			});
	}
	
</script>
<title>选择操作菜单</title>
</head>
<body>
	<ul class="filetree" id="browser">
		<c:if test="${menus!=null}">
			<li id="0li"><span code="0" count="1" class="treeindex">菜单树</span>
				<ul id="0ul">
					<form id="menuroleForm" action="role/addrolemenus.htm"
						method="post">
						<input type="hidden" name="roleCode" value="${roleCode}">
						<c:forEach items="${menus}" var="m">
							<c:if test="${m.childrenCount>0}">
								<li id="${m.code}li"><c:if
										test="${m.isCheck=='true'}">
										<input parentid="${m.parentCode}" type="checkbox"
										 checked="checked"
											value="${m.code}" name="menuIds"
											onclick="checkChildren(this);" />
									</c:if> <c:if test="${m.isCheck=='false'}">
										<input parentid="${m.parentCode}" type="checkbox"
										 value="${m.code}"
											name="menuIds" onclick="checkChildren(this);" />
									</c:if> <span code="${m.code}" count="${m.childrenCount}"
									roleCode="${roleCode }" class="folder">${m.title}</span>
									<ul id="${m.code}ul"></ul></li>
							</c:if>
							<c:if test="${m.childrenCount==0}">
								<li id="${m.code}li" class="f">
									<c:if test="${m.isCheck=='true'}">
										<input parentid="${m.parentCode}" type="checkbox"
											 checked="checked"
											name="menuIds" value="${m.code}"
											onclick="checkChildren(this);" />
									</c:if> <c:if test="${m.isCheck=='false'}">
										<input parentid="${m.parentCode}" type="checkbox"
											 name="menuIds"
											value="${m.code}" onclick="checkChildren(this);" />
									</c:if> <span code="${m.code}" count="${m.childrenCount}"
									roleCode="${roleCode }" class="file" onclick="menuclick(this);"><a
										href="javaScript:void(0);">${m.title}</a>
								</span>
									<ul id="${m.code}ul"></ul></li>
							</c:if>
						</c:forEach>
					</form>
				</ul></li>
		</c:if>
		<c:if test="${menus==null}">
			<li id="0li"><span code="0" count="1">菜单树</span></li>
		</c:if>
	</ul>
</body>
</html>