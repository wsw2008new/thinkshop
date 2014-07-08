<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/pay.validate.js"></script>
<script type="text/javascript">
	var json = {
		"flag" : true
	};
	$(function() {
		$("#oid").blur(
				function() {
					flag = true;
					var operatorId = $(this).val();
					$.post(basePath + "operator/isoperatorexist.htm",
							"operatorId=" + operatorId, function(data) {
								if (data.message == 1) {
									$("#emname").html("此账号名已存在");
									$("#submit").attr("disabled", true);
									flag = false;
								}
							}, "json");
				});
		$("#oid").focus(function() {
			$("#emname").html("");
			$("#submit").attr("disabled", false);
		});


		$("#add_form").validate({
						
			errorElement : "em",
			rules : {
				"operatorId" : {
					required : true,
					byteRangeLength :[5,20],
					chrnum : true

				},
				"password" : {
					required : true,
					byteRangeLength :[5,50],
					chrnum : true
				},
				"name" : {
					required : true,
					byteRangeLength :[1,50],
					nobadchar:true
				},
				"teleNum" : {
					phone:true
				},
				"mobileNum" : {
					mobile:true
				},
				"jobName" : {
					byteRangeLength:[0,40],
					nobadchar:true
				},
				"remark" : {
					byteRangeLength:[0,100]
				}
			},
			messages : {
				"operatorId" : {
					required : "账号名不能为空"

				},
				"password" : {
					required : "密码不能为空"
				},
				"name" : {
					required : "姓名不能为空"
				},
				"remark" : {
					byteRangeLength:"备注为50个字"
				}

			},
			submitHandler : function(form) {
				flag = true;
				var password = $(":input[name='password']").val();
				var repeatPassword = $(":input[name='repeatPassword']").val();
				if (password != repeatPassword) {
					$("#ed").html("输入密码不一致");
					flag = false;
				}
				if (flag == true) {
					
					if (window.confirm("确认添加吗?")) {
						form.submit();
					} else {
						return false;
					}
				}
			}
		});

	});
	
</script>
</head>
<body>
	<form id="add_form" action="operator/addoperator.htm" method="POST">
		<ul class="submitul2">
			<li><span>账号: </span><input id="oid" type="text"
				name="operatorId" class="inp" /><em id="emname" style="color: red;"></em><em>(账号名长度为5到20位)</em>
			</li>
			<li><span>密码: </span><input type="password" name="password"
				class="inp" /><em>(请输入至少5位的密码)</em>
			</li>
			<li><span>确认密码: </span><input type="password"
				name="repeatPassword" class="inp" /><em id="ed" style="color:red;"></em><em>(确认输入密码)</em>
			</li>
			<li><span>姓名: </span><input type="text" name="name" class="inp" /><em>(请输入姓名)</em>
			</li>
			<li><span>电话号码: </span><input type="text" name="teleNum"
				class="inp" /><em>(请按照此格式写:区号-电话号码)</em>
			</li>
			<li><span>手机号码: </span><input type="text" name="mobileNum"
				class="inp" /><em>(请输入11位正确手机号码)</em>
			</li>
			<li><span>职位名称: </span><input type="text" name="jobName"
				class="inp" />
			</li>
			<li><span>状态:</span>
				<select name="status">
					<option selected="selected" value="1" >正常</option>
					<option value="0">禁用</option>
				</select>
			</li>
			<li>
				<span>角色: </span><c:forEach items="${roles}" var="r"><input type="checkbox" name="roleCodes" value="${r.code}"></input>${r.title}</c:forEach>
			</li>
			<li style="height: auto; line-height: normal;"><span>备注:
			</span>
			<textarea cols="15" style="width: 300px;" rows="5" name="remark" class="inpl"></textarea><em>(请将字数控制在50字)</em>
			</li>
		</ul>
		<div class="submitsbtnbox" style="text-align: center;padding: 18px;">
			<input type="submit" id="submit" value="保 存" class="searchbtn" /> <input
				type="button" style="margin-left: 20px;" class="searchbtn"
				value="关闭" onclick="$('#add').dialog('close');" />
		</div>
	</form>
</body>
</html>
