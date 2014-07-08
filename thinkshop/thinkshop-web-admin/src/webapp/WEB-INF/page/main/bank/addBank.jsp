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
			$("#payType").blur(function(){
				var code = $("#codes").val();
				if(code != ""){
					$("#codes").val("");
					flag=false;
				}
			});
			$("#codes").blur(function(){
				var payType = $("#payType").val();
				if( payType == ""){
					$("#em3").html("请先选择所属支付方式");
					$("#codes").val("");
					flag=false;
					return;
				}
				flag=true;
				 var code=$(this).val();
				 $.post("<%=basePath%>bank/checkBankCode.htm","code="+code+"&payType="+payType,
					function(data){
						if(data.result==1){
							$("#em3").html("此银行编码已存在");
							flag=false;
						}
					},"json"
				 );
			});
			$("#codes").focus(function(){
				$("#em3").html("");
			});
			$("#addbank_form").validate({			
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
	<form id="addbank_form" action="bank/saveupdate.htm" method="POST">
	<input type="hidden" name="id" value="${entity.id}">
	<ul class="submitul2">
		<li><span>所属支付方式: </span>
		<c:if test="${not empty entity.payType}">
			<input id="payType" type="hidden" name="payType" value="${entity.payType}" class="inp"/>
			<select name="pte" id="pte" disabled="disabled">
					<c:forEach var="type" items="${payTypes}">
						<option value="${type.id}"<c:if test="${entity.payType==type.id}">selected</c:if> >${type.value }</option>								
					</c:forEach>
			</select>
		</c:if> 
		<c:if test="${empty entity.payType}">
			<select name="payType" id="payType"  >
				<option value="">请选择</option>
					<c:forEach var="type" items="${payTypes}">
						<option value="${type.id}"<c:if test="${entity.payType==type.id}">selected</c:if> >${type.value }</option>								
					</c:forEach>
			</select>
		</c:if> 
			<em id="em2" style="color:red;"></em><em></em>
		</li>
		<li><span>支付银行名称: </span><input id="name" type="text" name="name" value="${entity.name}" class="inp" <c:if test="${not empty entity.name}">disabled</c:if> /><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>支付银行编码: </span><input id="codes" type="text" name="code" value="${entity.code}" class="inp" <c:if test="${not empty entity.code}">disabled</c:if>  onChange="javascript:this.value=this.value.toUpperCase();"/><em id="em3" style="color:red;"></em><em></em></li>
		<li><span>手续费率: </span><input id="rate" type="text" name="rate" value="${entity.rate}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否支持借记卡: </span>
		<select name="isDebit">
			<option value="1" <c:if test="${entity.isDebit == true}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.isDebit == false}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否支持信用卡: </span>
		<select name="isCredit">
			<option value="1" <c:if test="${entity.isCredit == true}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.isCredit == false}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否支持企业网银: </span>
		<select name="isEnterpise">
			<option value="1" <c:if test="${entity.isEnterpise == true}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.isEnterpise == false}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否激活: </span>
		<select name="isActive">
			<option value="1" <c:if test="${entity.isActive == true}">selected</c:if> >是</option>
			<option value="0" <c:if test="${entity.isActive == false}">selected</c:if> >否</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>支付银行网站: </span><input id="webSite" type="text" name="webSite" value="${entity.webSite}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>指定排序: </span><input id="dispalyOrder" type="text" name="dispalyOrder" value="${entity.dispalyOrder}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
<!-- 		<li><span>支付银行Logo: </span> -->
		<input id="logoPath" type="hidden" name="logoPath" value="${entity.logoPath}" class="inp"/>
<!-- 		<input type="file" name="picture" id="picture"> -->
<!-- 		<em id="em2" style="color:red;"></em><em></em></li> -->
		<li style="height: auto; line-height: normal;"><span>备注信息: </span><textarea cols="15" style="width: 300px;" rows="5" name="remark" class="inpl">${entity.remark}</textarea><em>(备注不超过200字节)</em></li>
		<c:if test="${not empty entity.id }">
		<li><span>预览图: </span>
		<img alt="" src="<%=basePath%>images/bank_ico/${pic}" width="78" height="78" id="img1">
		</c:if>
		<div id="large"></div>
	</ul>
	
	<div class="submitsbtnbox" style="text-align:center;padding:18px;">
		<input type="submit" value="保 存" class="searchbtn" onclick=""/>
		<c:if test="${not empty entity.id }">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifyBank').dialog('close');"/>
		</c:if>
		<c:if test="${empty entity.id}">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addBank').dialog('close');"/>
		</c:if>
		
	</div>
</form>
</body>
</html>
