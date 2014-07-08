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
			$("#addMerchant_form").validate({			
		    	errorElement: "em",	    	
		        rules: {
		        	"name":{
		        		required : true,
						byteRangeLength :[0,50],
						nobadchar:true
		        	},
		        	"address":{
		        		required : true,
		        		byteRangeLength:[0,100]
		        	},
		        	"logo":{
		        		required : true,
		        	},
		        	"merchant_type_id":{
		        		required : true,
		        	},
		            "introduce": {
		            	byteRangeLength:[0,200]                
		            }
		        },
		        messages: {
		        	"name":{
		        		required:"商户名称不能为空"
		        	},
		        	"code":{
		        		required:"地址不能为空"
		        	},
		        	"logo":{
		        		required:"logo不能为空"
		        	},
		        	"merchant_type_id":{
		        		required:"商户类型不能为空"
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
	<form id="addMerchant_form" action="<%=basePath%>merchant/saveupdate.htm" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${entity.id}">
	<ul class="submitul2">
		<li><span>商户名称: </span><input id="name" type="text" name="name" value="${entity.name}" class="inp"  /><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>商户类型: </span>
		<c:if test="${not empty entity.merchant_type_id}">
			<input  type="hidden" name="merchant_type_name" value="${entity.merchant_type_name}" class="inp"/>
			<input  type="hidden" name="merchant_type_id" value="${entity.merchant_type_id}" class="inp"/>
			<select name="merchant_type_id" id="merchant_type_id" disabled="disabled">
					<c:forEach var="type" items="${typeList.results}">
						<option value="${type.id}"<c:if test="${entity.merchant_type_id==type.id}">selected</c:if> >${type.name}</option>								
					</c:forEach>
			</select>
		</c:if> 
		<c:if test="${empty entity.merchant_type_id}">
			<select name="merchant_type_id" id="merchant_type_id">
				<option value="">请选择</option>
					<c:forEach var="type" items="${typeList.results}">
						<option value="${type.id}#${type.name}"<c:if test="${entity.merchant_type_id==type.id}">selected</c:if> >${type.name}</option>								
					</c:forEach>
			</select>
		</c:if> 
		<li><span>商户地址: </span><input id="address" type="text" name="address" value="${entity.address}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否锁定: </span>
		<select name="is_lock">
			<option value="1" <c:if test="${entity.is_lock == '1'}">selected</c:if> >已锁定</option>
			<option value="0" <c:if test="${entity.is_lock == '0'}">selected</c:if> >未锁定</option>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>是否入驻: </span>
		<select name="is_join">
			<c:forEach items="${list}" var="join">
				<option value="${join.id}" <c:if test="${entity.is_join == join.id}">selected</c:if>>${join.name}</option>
			</c:forEach>
		</select>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>商户特色: </span><input id="feature" type="text" name="feature" value="${entity.feature}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>人均消费: </span><input id="per_fee" type="text" name="per_fee" value="${entity.per_fee}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>营业时间: </span><input id="open_time" type="text" name="open_time" value="${entity.open_time}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>配套服务: </span><input id="service" type="text" name="service" value="${entity.service}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>预定热线: </span><input id="order_phone" type="text" name="order_phone" value="${entity.order_phone}" class="inp"/><em id="em2" style="color:red;"></em><em></em></li>
		<li><span>商户LOGO: </span><input id="logo" type="file" name="file" class="inp"/>
		<c:if test="${not empty entity.id }">
		<c:if test="${not empty entity.logo}">
		<input id="pop_pic1" type="hidden" name="logo" value="${entity.logo}" class="inp"  />
		<img alt="" src="<%=basePath%>${entity.logo}" width="15" height="15" id="img1" class="inp"></c:if>
		<c:if test="${empty entity.logo}"><img alt="" src="<%=basePath%>images/no-picx.png" width="15" height="15" id="img1" class="inp"></c:if>
		</c:if>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>展示图片1: </span><input  type="file" name="file" class="inp"/>
		<c:if test="${not empty entity.id }">
		<c:if test="${not empty entity.pop_pic1}">
		<input id="pop_pic1" type="hidden" name="pop_pic1" value="${entity.pop_pic1}" class="inp"  />
		<img alt="" src="<%=basePath%>${entity.pop_pic1}" width="15" height="15" id="img1" class="inp"></c:if>
		<c:if test="${empty entity.pop_pic1}"><img alt="" src="<%=basePath%>images/no-picx.png" width="15" height="15" id="img1" class="inp"></c:if>
		</c:if>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>展示图片2: </span><input  type="file" name="file"  class="inp"/>
		<c:if test="${not empty entity.id }">
		<c:if test="${not empty entity.pop_pic2}">
		<input id="pop_pic2" type="hidden" name="pop_pic2" value="${entity.pop_pic2}" class="inp"  />
		<img alt="" src="<%=basePath%>${entity.pop_pic2}" width="15" height="15" id="img1" class="inp"></c:if>
		<c:if test="${empty entity.pop_pic2}"><img alt="" src="<%=basePath%>images/no-picx.png" width="15" height="15" id="img1" class="inp"></c:if>
		</c:if>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>展示图片3: </span><input  type="file" name="file"  class="inp"/>
		<c:if test="${not empty entity.id }">
		<c:if test="${not empty entity.pop_pic3}">
		<input id="pop_pic3" type="hidden" name="pop_pic3" value="${entity.pop_pic3}" class="inp"  /><img alt="" src="<%=basePath%>${entity.pop_pic3}" width="15" height="15" id="img1" class="inp"></c:if>
		<c:if test="${empty entity.pop_pic3}"><img alt="" src="<%=basePath%>images/no-picx.png" width="15" height="15" id="img1" class="inp"></c:if>
		</c:if>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li><span>展示图片4: </span><input  type="file" name="file"  class="inp"/>
		<c:if test="${not empty entity.id }">
		<c:if test="${not empty entity.pop_pic4}">
		<input id="pop_pic4" type="hidden" name="pop_pic4" value="${entity.pop_pic4}" class="inp"  /><img alt="" src="<%=basePath%>${entity.pop_pic4}" width="15" height="15" id="img1" class="inp"></c:if>
		<c:if test="${empty entity.pop_pic4}"><img alt="" src="<%=basePath%>images/no-picx.png" width="15" height="15" id="img1" class="inp"></c:if>
		</c:if>
		<em id="em2" style="color:red;"></em><em></em></li>
		<li style="height: auto; line-height: normal;"><span>商家介绍: </span><textarea cols="15" style="width: 300px;" rows="5" name="introduce" class="inpl">${entity.introduce}</textarea><em>(备注不超过200字节)</em></li>
		<c:if test="${not empty entity.id }">
		<li><span>预览图: </span>
		<img alt="" src="<%=basePath%>images/bank_ico/" width="78" height="78" id="img1">
		</c:if>
		<div id="large"></div>
	</ul>
	
	<div class="submitsbtnbox" style="text-align:center;padding:18px;">
		<input type="submit" value="保 存" class="searchbtn" onclick=""/>
		<c:if test="${not empty entity.id }">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#modifyMerchant').dialog('close');"/>
		</c:if>
		<c:if test="${empty entity.id}">
		<input type="button"  style="margin-left:20px;" class="searchbtn" value="关闭" onclick="$('#addMerchant').dialog('close');"/>
		</c:if>
		
	</div>
</form>
</body>
</html>
