<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>	
	<script type="text/javascript" src="<%=basePath%>js/pay.validate.js"></script>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var json={"flag":true};
		$(function(){
			$("#pid").blur(function(){
				flag=true;
				 var operatorId=$("#modifypwd_form").children("input[name='operatorId']").val();
				 var password=$(this).val();
				 $.post(
					basePath+"operator/isoperatorpwd.htm","operatorId="+operatorId+"&password="+password,
					function(data){
						if(data.message==0){
							$("#empwd").html("密码错误，请重新输入");
							flag=false;
						}
					},"json"
				 );
			});
			$("#pid").focus(function(){
				$("#empwd").html("");
				
			});
			$("#phd").focus(function(){
				$("#emdd").html("");
				
			});
			
			$("#modifypwd_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		            "prepassword":{
		        		required:true,
		        		byteRangeLength :[1,50],
						chrnum : true
		        	},
		            
		            "password": {				            	
		                required:true,
		                byteRangeLength :[5,50],
						chrnum : true
		            }
		   
		            
		        },
		        messages: {
		        	"prepassword":{
		        		required:"请输入原始密码"
		        	},
		            "password": {				            	
		                required:"新密码不能为空"
		            }
		            
		        }
		        ,
		        submitHandler:function(form){
		        	flag=true;
			        var password=$(":input[name='password']").val();
					var repeatPassword=$(":input[name='repeatpassword']").val();
					if(password!=repeatPassword){
						$("#emdd").html("输入密码不一致，请重新输入");
						flag=false;
					}
					if (flag == true) {
						
						if(window.confirm("确认修改吗?")){		
							form.submit();
						}else{
							return false;
						}
					}
			    }
		    });
	
		});
	
	</script>
<style type="text/css">
	.submitul li {
	height:30px;
	line-height:30px;
}
</style>
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
						<div class="title1">修改密码</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="background:#fff;">	
				<form id="modifypwd_form" name="modify_form" action="operator/modifypwd.htm" method="POST"> 
					<input type="hidden" name="operatorId"  value="${operator.operatorId}"/>
					<ul class="submitul" style="padding:40px 0 30px 100px;margin-top:0px;">
						<li style="border-bottom: 0px;"><span style="border-right:0px;">账号: </span><em style="font-size: 12px;margin-left: 5px;">${operator.operatorId}</em></li>
						<li style="border-bottom: 0px;"><span style="border-right:0px;">请输入原始密码: </span><input id="pid" type="password" name="prepassword"  class="inp" autocomplete="off" style="ime-mode:disabled;" onpaste="return false;"/><em id="empwd" style="color:red;font-size: 12px;margin-left: 5px;"></em></li>
						<li style="border-bottom: 0px;"><span style="border-right:0px;">请输入新密码: </span><input type="password" name="password" class="inp" autocomplete="off" style="ime-mode:disabled;" onpaste="return false;"/><em style="font-size: 12px;margin-left: 5px;">(密码可包含字母，数字，下划线)</em></li>
						<li style="border-bottom: 0px;"><span style="border-right:0px;">请再次确认新密码: </span><input id="phd" type="password" name="repeatpassword" class="inp" autocomplete="off" style="ime-mode:disabled;" onpaste="return false;"/><em id="emdd" style="color:red;font-size: 12px;margin-left: 5px;"></em></li>
					</ul>        
					<div class="submitsbtnbox"  style="padding-left:220px;">		      
						<input type="submit"  value="修改" class="searchbtn"/>
						<input type="button"  class="searchbtn" value="返回" onclick="javascript:history.go(-1)"/>
					</div>	
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
