<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>Uploadify上传</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="<%=basePath%>js/uploadify/uploadify.css" type="text/css"></link>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/uploadify/jquery.uploadify.min.js"></script>
		<script type="text/javascript">
			  
			$(function() {
			    $("#file_upload").uploadify({
			    	'height'        : 27, 
			    	'width'         : 80,  
			    	'buttonText'    : '浏 览',
			        'swf'           : '<%=basePath%>/js/uploadify/uploadify.swf',
			        'uploader'      : '<%=basePath%>/fashion/uploadify.htm',
			        'auto'          : false,
			        'fileTypeExts'  : '*.xls;*.png;*.jpg;*.gif;*.PNG;*.JPG;*.GIF',
			        'formData'      : {'title':'','desc':''},
			        'onUploadStart' : function(file) {
			        	 
			        	//校验   
		                var name=$('#title').val();    
			             if(name.replace(/\s/g,'') == ''){   
			                  alert("标题不能为空！");   
			                  return false;   
			             } 
			             
			             //校验   
		                var qq=$('#desc').val();    
			             if(qq.replace(/\s/g,'') == ''){   
			                  alert("介绍不能为空！");   
			                  return false;   
			             }
			             
			        	$("#file_upload").uploadify("settings", "formData", {'title':name,'desc':qq});
			        	//$("#file_upload").uploadify("settings", "qq", );
			        }
			    });
			});
		
	</script>
	</head>
	<body>
		标题: <input type="text" id="title" name="title" style="width: 404px;">
		<br>
		 介绍: 
		 <textarea rows="5" cols="45" id="desc" name="desc">介绍</textarea>
		<br>
		<input type="file" name="uploadify" id="file_upload" />
		<hr>
		<a href="javascript:$('#file_upload').uploadify('upload','*')">开始上传</a>&nbsp;   
        <a href="javascript:$('#file_upload').uploadify('cancel', '*')">取消所有上传</a> 
	</body>
</html>
