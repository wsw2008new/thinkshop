<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>	
	<script type="text/javascript" src="<%=basePath%>js/pay.validate.js"></script>
	<script type="text/javascript">
			$(function(){
				$("#modifyrole_form").validate({			
			    	errorElement: "em",	    	
			        rules: {
			        	"title":{
			        		required:true,
			        		byteRangeLength:[0,50],
							nobadchar:true
			        	},
			            "remark": {
			            	byteRangeLength:[0,100],
							nobadchar:true			                
			            }
			        },
			        messages: {
			        	"title":{
			        		required:"角色名称不能为空"
			        	}
			        },
			        submitHandler:function(form){
							if(window.confirm("确认修改吗?")){
							form.submit();					
							}else{
								return false;
							}
				    }
			    });
			});
	
	</script>
</head>
<body>
<form id="modifyrole_form" action="rolegl/modifyrole.htm" method="POST">
	<input type="hidden" name="code" value="${role.code}">
	
	<ul class="submitul2">
		<li><span>角色编码: </span>${role.code}</li>
		<li><span>角色名称名称: </span><input type="text" name="title" value="${role.title}" class="inp"/><em>(角色名称不超过50位)</em></li>
		<li><span>备注: </span><input type="text" name="remark" value="${role.remark}" class="inp"/><em>(备注不超过100字节)</em></li>
	</ul>
	<div class="submitsbtnbox" style="text-align:center;padding: 18px;">
		<input type="submit" value="保 存" class="searchbtn" />
		<input type="button"  id="toback" style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifyrole').dialog('close');"/>
	</div>
</form>
</body>
</html>
