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
		var json={"flag":true};
		$(function(){
			$("#cid").blur(function(){
				flag=true;
				 var code=$(this).val();
				 $.post(
					basePath+"menu/ismenuexist.htm","code="+code,
					function(data){
						if(data.message==1){
							$("#em2").html("此菜单编码已存在");
							flag=false;
						}
					},"json"
				 );
			});
			$("#cid").focus(function(){
				$("#em2").html("");
			});
			
			$("#addmenu_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		        	"code":{
		        		required : true,
						byteRangeLength :[0,15],
						chrnum : true
		        			
		        	},
		        	"title":{
		        		required:true,
		        		byteRangeLength:[0,30],
						nobadchar:true
		        	},
		            "url": {
		            	byteRangeLength:[0,200]		                
		            }
		            
		            
		        },
		        messages: {
		        	"code":{
		        		required:"菜单编码不能为空"
		        			
		        	},
		        	"title":{
		        		required:"菜单名称不能为空"
		        	}
		            
		        }
		        ,
		        submitHandler:function(form){
						
					if(flag==true){
						if(window.confirm("确认添加吗?")){
					
						form.submit();					
						}else{
							return false;
						}
					}
			    }
		    });
			
		});
	
	</script>
</head>
<body>
	<form id="addmenu_form" action="menu/addmenu.htm" method="POST">
	<c:if test="${ok==1}">
	<input type="hidden" id="uki" name="parentCode" value="${menu.code}">
	<input type="hidden" name="menuLevel" value="${menu.menuLevel+1}">
	<input type="hidden" name="childrenCount" value="0">
	<input type="hidden" name="displayNum" value="${menu.childrenCount+1}">
	</c:if>
	<c:if test="${ok==2}">
	<input type="hidden" name="parentCode" value="0">
	<input type="hidden" name="menuLevel" value="1">
	<input type="hidden" name="childrenCount" value="0">
	<input type="hidden" name="displayNum" value="1">
	</c:if>
	<ul class="submitul2">
		<li><span>父菜单名称: </span>${menu.title}</li>
		<li><span>菜单编码: </span><input id="cid" type="text" name="code" class="inp"/><em id="em2" style="color:red;"></em><em>(请输入最多15位的菜单编码)</em></li>
		<li><span>菜单名称: </span><input type="text" name="title" class="inp"/><em>(请输入菜单名称)</em></li>
		<li><span>菜单地址: </span><input type="text" name="url" class="inp"/><em>(请输入菜单地址)</em></li>
	</ul>
	<div class="submitsbtnbox" style="text-align:center;padding: 18px;">
		<input type="button" value="保 存" class="searchbtn" onclick="addmenu();"/>
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addmenu').dialog('close');"/>
	</div>
</form>
</body>
</html>
