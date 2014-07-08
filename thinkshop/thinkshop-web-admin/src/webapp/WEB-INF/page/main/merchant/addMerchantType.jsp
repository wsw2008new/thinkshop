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
			$("#codes").focus(function(){
				$("#em3").html("");
			});
			$("#addMerchantType_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		        	"name":{
		        		required : true,
						byteRangeLength :[0,20],
						nobadchar:true
		        	}
		        },
		        messages: {
		        	"name":{
		        		required:"名称不能为空"
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
			 var ei = $("#large");
			    ei.hide();
			    $("#img1").mousemove(function(e){
			        ei.css({top:e.pageY,left:e.pageX}).html('<img style="border:1px solid gray;" src="' + this.src + '" />').show();
			    }).mouseout( function(){
			        ei.hide("slow");
			    });
			//$("#picture").change(function(){alert($("#picture").val());
		     //   $("#img1").attr("src",$("#picture").val());
		   // });
		});
		function checkBankCode(value){
			$.ajax({
				url:"<%=basePath%>/bank/checkBankCode.htm",
				data:"code="+encodeURI(value),
				success:function(data){
					var resultdata = eval("({" + data + "})");
					if(resultdata["result"] ==1){
						alert("该银行编码已存在！");
						$("#code").val('');
						$("#code").focus();
						return;
					}
				}
			});
		}
		var exts = "png", paths = "|";
		function CheckPreview(){
		var value = $("#picture").val(), check = true;
// 		if ( !value ) {
// 			alert("请先选择文件！");
// 			check = false; 
// 		} else 
			if ( !RegExp( "\.(?:" + exts + ")$$", "i" ).test(value) ) {
			alert("上传类型不正确！");
			check = false; 
		} 
		return check;
		}
	</script>
</head>
<body>
	<form id="addMerchantType_form" action="<%=basePath%>type/saveupdate.htm" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${entity.id}">
	<ul class="submitul2">
		<li><span>名称: </span><input id="name" type="text" name="name" value="${entity.name}" class="inp"  /><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否激活: </span>
		<select name="is_active">
			<option value="1" <c:if test="${entity.is_active == '1'}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.is_active == '0'}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>显示图片: </span><input id="file" type="file" name="file" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<c:if test="${not empty entity.id }">
		<li><span>预览图: </span>
		<c:if test="${not empty entity.show_pic }">
		<img alt="" src="<%=basePath%>${entity.show_pic}" width="78" height="78" id="img1">
		<input id="show_pic" type="hidden" name="show_pic" value="${entity.show_pic}" class="inp"  />
		</c:if>
		<c:if test="${empty entity.show_pic }">
		<img alt="" src="<%=basePath%>images/no-picx.png" width="78" height="78" id="img1">
		</c:if>
		</c:if>
		<div id="large"></div>
	</ul>
	
	<div class="submitsbtnbox" style="text-align:center;padding:18px;">
		<input type="submit" value="保 存" class="searchbtn" onclick=""/>
		<c:if test="${not empty entity.id }">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifyMerchantType').dialog('close');"/>
		</c:if>
		<c:if test="${empty entity.id}">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addMerchantType').dialog('close');"/>
		</c:if>
		
	</div>
</form>
</body>
</html>
