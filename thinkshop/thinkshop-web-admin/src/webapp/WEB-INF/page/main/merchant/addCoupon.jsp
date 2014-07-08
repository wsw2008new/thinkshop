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
			$("#addcoupon_form").validate({			
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
		        		required:"优惠券名称不能为空"
		        	},
		        	"code":{
		        		required:"优惠券编码不能为空"
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
	<form id="addcoupon_form" action="coupon/saveupdate.htm" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${entity.id}">
	<ul class="submitul2">
		<li><span>优惠券名称: </span><input id="name" type="text" name="name" value="${entity.name}" class="inp" /><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>商户名称: </span>
		<c:if test="${not empty entity.merchant_id}">
			<input  type="hidden" name="merchant_name" value="${entity.merchant_name}" class="inp"/>
			<input  type="hidden" name="merchant_id" value="${entity.merchant_id}" class="inp"/>
			<select name="merchant_id" id="merchant_id" disabled="disabled">
					<c:forEach var="type" items="${list.results}">
						<option value="${type.id}"<c:if test="${entity.merchant_id==type.id}">selected</c:if> >${type.name}</option>								
					</c:forEach>
			</select>
		</c:if>
		<c:if test="${empty entity.merchant_id}">
			<select name="merchant_id" id="merchant_id">
				<option value="">请选择</option>
					<c:forEach var="type" items="${list.results}">
						<option value="${type.id}#${type.name}"<c:if test="${entity.merchant_id==type.id}">selected</c:if> >${type.name}</option>								
					</c:forEach>
			</select>
		</c:if> 
		<em id="em3" style="color:red;"></em><em></em></li>
		<li><span>优惠券图片: </span><input  type="file" name="file" class="inp"/>
		<c:if test="${not empty entity.id }">
		<c:if test="${not empty entity.show_pic}">
		<input id="show_pic" type="hidden" name="show_pic" value="${entity.show_pic}" class="inp"  />
		<img alt="" src="<%=basePath%>${entity.show_pic}" width="15" height="15" id="img1" class="inp"></c:if>
		<c:if test="${empty entity.show_pic}"><img alt="" src="<%=basePath%>images/no-picx.png" width="15" height="15" id="img1" class="inp"></c:if>
		</c:if>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否推荐: </span>
		<select name="is_recommend">
			<option value="1" <c:if test="${entity.is_recommend == '1'}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.is_recommend == '0'}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>截止时间: </span><input id="end_time" type="text" name="end_time" value="${entity.end_time}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>使用地址: </span><input id="use_address" type="text" name="use_address" value="${entity.use_address}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li style="height: auto; line-height: normal;"><span>介绍信息: </span><textarea cols="15" style="width: 300px;" rows="5" name="introduce" class="inpl">${entity.introduce}</textarea><em>(备注不超过200字节)</em></li>
		<c:if test="${not empty entity.id }">
		<li><span>预览图: </span>
		<img alt="" src="<%=basePath%>images/bank_ico/" width="78" height="78" id="img1">
		</c:if>
		<div id="large"></div>
	</ul>
	
	<div class="submitsbtnbox" style="text-align:center;padding:18px;">
		<input type="submit" value="保 存" class="searchbtn" onclick=""/>
		<c:if test="${not empty entity.id }">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifyCoupon').dialog('close');"/>
		</c:if>
		<c:if test="${empty entity.id}">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addCoupon').dialog('close');"/>
		</c:if>
		
	</div>
</form>
</body>
</html>
