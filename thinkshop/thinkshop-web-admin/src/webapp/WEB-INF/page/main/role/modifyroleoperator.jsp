<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../base.jsp" %>
<%@include file="../../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>js/multiselect2side/css/jquery.multiselect2side.css" type="text/css" media="screen" />
	<script type="text/javascript" src="<%=basePath%>js/multiselect2side/js/jquery.multiselect2side.js"></script>		
	<script type="text/javascript">
		$(function(){
			$('#searchable').multiselect2side({
				search: "查询: ",
				selectedPosition: 'right',
				moveOptions: false
				
			});
		});
	</script>
	<style type="text/css">
		
	</style>
</head>
<body>
	<input type="hidden" name="rolecode" value="${roleCode}">
	<select name="searchable[]" id='searchable' multiple='multiple' style="display:block;height:0px;font-size:0px;border: 1px solid #fff">
		<c:forEach items="${operators}" var="o">
		<option value="${o.operatorId}">${o.operatorId}:${o.name}</option>
		</c:forEach>
	</select>	
</body>
<script type="text/javascript">
		var basePath = "<%=basePath%>";
		$(function(){
			var code=$("input[name=rolecode]").val();
			$("#searchable").css("display","block");
			$.post(
					basePath+"rolegl/getroleoperators.htm?code="+code,
					function(data){
						var operators=data.operatorRoles;
						
						$.each(operators,function(index,operator){
							$("#searchablems2side__dx").append("<option value='" + operator.operatorId + "'>" + operator.operatorId +":"+operator.name+ "</option>");
						});					
					},"json"
			);
			
		});
	</script>
</html>
