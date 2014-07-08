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
			$("#modify_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		            "name": {
		            	required : true,
						byteRangeLength :[1,50],
						nobadchar:true		                
		            },
		            
		            "areaName": {				            	
		            	byteRangeLength:[0,20],
						nobadchar:true
		            },
		            "teleNum":{
		            	phone:true
			        },
		            "mobileNum":{
		            	mobile:true
			        },
		            "jobName":{
		            	byteRangeLength:[0,40],
						nobadchar:true
			         },
		            "remark":{
		            	byteRangeLength:[0,100]			
			         }
		        },
		        messages: {
		        	"name": {
		                required:"姓名不能为空"
		            }
		        }
		        ,
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

<form id="modify_form" name="modi_form" action="operator/modifyoperator.htm" method="POST"> 
	<input type="hidden" name="operatorId"  value="${operator.operatorId}">
	<input type="hidden" name="password"  value="${operator.password}">
	<ul class="submitul2">
		<li><span>姓名: </span><input type="text" name="name" value='${operator.name}' class="inp"/><em>(名字可包含中文，字母，下划线)</em></li>
		<li><span>电话号码: </span><input type="text" name="teleNum" value='${operator.teleNum}' class="inp"/><em>(请按照此格式写:(区号-)电话号码)</em></li>
		<li><span>手机号码: </span><input type="text" name="mobileNum" value="${operator.mobileNum}" class="inp"/><em>(请输入11位正确手机号码)</em></li>
		<li><span>职位名称: </span><input type="text" name="jobName" value="${operator.jobName}" class="inp"/></li>
		<li><span>状态:</span><select name="status"><c:if test="${operator.status==1}"><option selected="selected" value="1" >正常</option><option value="0">禁用</option></c:if><c:if test="${operator.status==0}"><option selected="selected" value="0" >禁用</option><option value="1">正常</option></c:if></select></li>
		<li style="height:auto;line-height:normal;"><span>备注: </span><textarea name="remark" rows="6" cols="13" class="inpl">${operator.remark}</textarea><br></li>
		<li><span>&nbsp;</span><em>(请将字数控制在50字)</em></li>
	</ul>        
	<div class="submitsbtnbox"  style="text-align:center;">		      
		<input type="submit"  value="修 改" class="searchbtn"/>
		<input type="button" style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modify').dialog('close');"/>
	</div>	
</form>

</body>
</html>
