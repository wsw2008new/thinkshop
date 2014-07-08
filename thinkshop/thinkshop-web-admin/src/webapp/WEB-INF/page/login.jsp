<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付后台登录</title>
<%@include file="base.jsp" %>
<%@include file="taglib.jsp" %>
<meta name="description"
	content="" />
<meta name="keywords"
	content="" />
<link href="<%=basePath%>css/manage1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript">
if (self.location != top.location) {
	  top.location.href = self.location.href;
	}
$(function() {
	$("#loginform").validate({
		submitHandler : function(form) {
			var username=$(":input[name='j_username']").val();
			if(username=="")
			{
				$(".loginbox1-center2").children("div").remove();
				$(".loginbox1-center2").prepend("<div style='color:red;text-align:center;'>用户名不能为空</div>");
				return;
			}
			
			var passsword=$(":input[name='j_password']").val();
			if(passsword=="")
			{
				$(".loginbox1-center2").children("div").remove();
				$(".loginbox1-center2").prepend("<div style='color:red;text-align:center;'>密码不能为空</div>");
				return;
			}
			
// 			var validateCode=$(":input[name='validateCode']").val();
// 			if(validateCode=="")
// 			{
// 				$(".loginbox1-center2").children("div").remove();
// 				$(".loginbox1-center2").prepend("<div style='color:red;text-align:center;'>验证码不能为空</div>");
// 				return;
// 			}
			
			$.post(basePath + "operator/isoperatorexist.htm",
					"operatorId=" + username, function(data) {
						if (data.message == 0) {
							$(".loginbox1-center2").children("div").remove();
							$(".loginbox1-center2").prepend("<div style='color:red;text-align:center;'>用户名不存在，请重新输入</div>");
							return;
						}
						else
						{
							form.submit();	
						}
			}, "json");
			
			
		}
	});

});
</script>

</head>
<body style="background:#014C85 url(images/manage/bg.gif) repeat-x;">
		<div id="loginbox1">
			<div class="loginbox1-top"></div>
			<div class="loginbox1-left"></div>
			<div class="center1">
				<div class="loginbox1-center"></div>
				<form id="loginform" action="j_spring_security_check" method="post">
					<div class="loginbox1-center2">
						<c:if test="${error!=null}">
							<div style="color:red;text-align:center;">${error}</div>
						</c:if>
						<c:if test="${error==null||error==''}">
							<c:if test="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION']!=null}">
								<div style="color:red;text-align:center;">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</div>
							</c:if>
						</c:if>
						<ul>
							<li>用户名 <input type="text" class="text1" name="j_username"/></li>
							<li>密　码 <input type="password" class="text1" name="j_password" autocomplete="off" style="ime-mode:disabled;" onpaste="return false;"/></li>
<!-- 							<li>验证码 <input type="text" class="text1" style="width:55px;" name="validateCode"/> <img id="imgValidateCode" src="getValidateCode.htm"/> <a href="javascript:refreshValidateCode();">换一张</a></li> -->
							<li style="padding-top:10px;"><input type="submit"  class="btn1" value="登 录"/> <input type="reset" class="btn1" value="重 置"/></li>
						</ul>
					</div>
				</form>
			</div>
			<div class="loginbox1-right"></div>
			<div class="loginbox1-bottom"></div>
		</div>
	</body>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	function refreshValidateCode() {
		var imgs = document.images['imgValidateCode'];
		imgs.src = "getValidateCode.htm?" + Math.random();
	}
</script>
</html>