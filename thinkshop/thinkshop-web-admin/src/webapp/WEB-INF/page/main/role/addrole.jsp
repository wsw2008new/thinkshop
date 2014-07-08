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
			$("#cid").focus(function(){
				$("#em2").html("");
			});
			
			$("#addrole_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		        	"code":{
		        		required : true,
						byteRangeLength :[0,20],
						chrnum : true
		        			
		        	},
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
		        	"code":{
		        		required:"角色编码不能为空"
		        			
		        	},
		        	"title":{
		        		required:"角色名称不能为空"
		        	}   
		        }
		        ,
		        submitHandler:function(form){
		        	var code=$("#addrole_form input[name=code]").val();
		    		var regu = /^ROLE_.*$/;
		    		if(!regu.test(code)){
		    			alert("角色编码须以'ROLE_'开头");
		    			return;
		    		}
		    		 var code=$("#cid").val();
					 $.post(
						basePath+"rolegl/isroleexist.htm","code="+code,
						function(data){
							if(data.message==1){
								$("#em2").html("此角色编码已存在");
								return;
							}
							else
							{
								if(window.confirm("确认添加吗?")){
									form.submit();					
								}else{
									return ;
								}
							}
						},"json"
					 );
		 	   }
		    });
			
		});
	
	</script>
</head>
<body>
	<form id="addrole_form" action="rolegl/addrole.htm" method="POST">
	<ul class="submitul2">
		<li><span>角色编码: </span><input id="cid" type="text" name="code" value="ROLE_" class="inp"/><em id="em2" style="color:red;"></em><em>(角色编码最多20位，且必须以"ROLE_"开头)</em></li>
		<li><span>角色名称: </span><input type="text" name="title" class="inp"/><em>(角色名称不超过50字节)</em></li>
		<li><span>备注: </span><input type="text" name="remark" class="inp"/><em>(备注不超过100字节)</em></li>
	</ul>
	<div class="submitsbtnbox" style="text-align:center;padding: 18px;">
		<input type="submit" value="保 存" class="searchbtn"/>
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addrole').dialog('close');"/>
	</div>
</form>
</body>
</html>
