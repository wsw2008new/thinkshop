<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>	
	<script type="text/javascript" src="<%=basePath%>js/pay.validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		var json={"flag":true};
		$(function(){
			
			$("#addnews_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		        	"payType":{
		        		required : true
		        	},
		        	"name":{
		        		required : true,
						byteRangeLength :[0,20],
						nobadchar:true
		        	},
		        	"code":{
		        		required : true,
		        		byteRangeLength:[0,10]
		        	},
		        	"rate":{
		        		required : true,
		        		byteRangeLength:[0,6],
		        		amount:true
		        	},
		        	"webSite":{
		        		required : true,
		        		byteRangeLength:[0,200],
		        		comurl:true
		        	},
		        	"dispalyOrder":{
		        		byteRangeLength:[0,2],
		        		integer:true
		        	},
		            "remark": {
		            	byteRangeLength:[0,200]                
		            }
		        },
		        messages: {
		        	"payType":{
		        		required:"支付方式不能为空"
		        	},
		        	"name":{
		        		required:"支付银行名称不能为空"
		        	},
		        	"code":{
		        		required:"支付银行编码不能为空"
		        	},
		        	"rate":{
		        		required:"手续费率不能为空"
		        	},
		        	"webSite":{
		        		required:"网站地址不能为空"
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
	</script>
</head>
<body>
	<form id="addnews_form" action="news/saveupdate.htm" method="POST">
	<input type="hidden" name="id" value="${entity.id}">
	<ul class="submitul2">
		<li><span>新闻标题: </span>
			<input id="title" type="text" name="title" value="${entity.title}" class="inp"/>
		</li><li><span>新闻类型: </span>
			<select name="newsType" id="newsType"  >
				<option value="">请选择</option>
					<c:forEach var="type" items="${newsType}">
						<option value="${type.id}"<c:if test="${entity.newsType==type.id}">selected</c:if> >${type.value }</option>								
					</c:forEach>
			</select>
			<em id="em2" style="color:red;"></em><em></em>
		</li>
		<li><span>报道者: </span><input id="reporter" type="text" name="reporter" value="${entity.reporter}" class="inp"  /><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>新闻来源: </span><input id="fromWhere" type="text" name="fromWhere" value="${entity.fromWhere}" class="inp" /><em id="em3" style="color:red;"></em><em></em></li>
		<li><span>发生时间: </span><input id="happenTime" type="text" name="happenTime" value="${entity.happenTime}" onfocus="WdatePicker({isShowWeek:true})" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否激活: </span>
		<select name="isactive">
			<option value="1" <c:if test="${entity.isactive == 1}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.isactive == 0}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li style="height: auto; line-height: normal;"><span>新闻内容: </span><textarea id="content" cols="15" style="width: 300px;" rows="5" name="content" class="inpl">${entity.content}</textarea><em>(备注不超过200字节)</em></li>
	</ul>
	<script type="text/javascript">
	CKEDITOR.replace( 'content' );
	</script>
	<div class="submitsbtnbox" style="text-align:center;padding:18px;">
		<input type="submit" value="保 存" class="searchbtn" onclick=""/>
		<c:if test="${not empty entity.id }">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifyNews').dialog('close');"/>
		</c:if>
		<c:if test="${empty entity.id}">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addNews').dialog('close');"/>
		</c:if>
		
	</div>
</form>
</body>
</html>
