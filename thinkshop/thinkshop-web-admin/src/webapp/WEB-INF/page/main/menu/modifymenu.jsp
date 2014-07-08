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
			
			$("#modifymenu_form").validate({			
		    	errorElement: "em",	    	
		        rules: {	 
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
		        	"title":{
		        		required:"菜单名称不能为空"
		        	}
		            
		        }
		  //      ,
		   //     submitHandler:function(form){
		   //     	var parentCode=$("#uki").val();
			//		var childrenCount=$("#ukc").val();
			//		if(window.confirm("确认修改吗?")){
			//	
			//			form.submit();		
				//		$("#toback").click();					
			//			alert("#"+parentCode+","+childrenCount);
				//		$("#"+parentCode+","+childrenCount).click();
			//		}else{
			//			return false;
			//		}
			  //  }
		   // });
			
		});
	
	</script>
</head>
<body>
<form id="modifymenu_form" action="menu/modifymenu.htm" method="POST">
	<input type="hidden" name="code" value="${menu.code}">
	<input type="hidden" id="uki" name="parentCode" value="${menu.parentCode}">
	<input type="hidden" name="menuLevel" value="${menu.menuLevel}">
	<input type="hidden" name="childrenCount" value="${menu.childrenCount}">
	<ul class="submitul2">
		<li><span>父菜单名称: </span>${parent.title}</li>
		<li><span>菜单编码: </span>${menu.code}</li>
		<li><span>菜单名称: </span><input type="text" name="title" value="${menu.title}" class="inp"/><em>(菜单名称不为空，最多30位)</em></li>
		<li><span>菜单地址: </span><input type="text" name="url" value="${menu.url}" class="inpl"/></li>
		<li><span>排序号: </span><input type="text" name="displayNum" value="${menu.displayNum}" class="inp"/><em>(排序号为为小与100的整数)</em></li>
	</ul>
	<div class="submitsbtnbox" style="text-align:center;padding: 18px;">
		<input type="button" value="保 存" class="searchbtn" onclick="modifymenu();"/>
		<input type="button"  id="toback" style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifymenu').dialog('close');"/>
	</div>
</form>
</body>
</html>
