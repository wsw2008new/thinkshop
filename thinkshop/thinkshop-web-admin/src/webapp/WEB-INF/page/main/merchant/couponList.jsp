<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../base.jsp"%>
<%@include file="../../taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<%=basePath%>js/jui/css/ui-lightness/jquery-ui.css" rel="stylesheet" />	
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jui/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>js/DatePicker/WdatePicker.js" defer="defer"></script>
	<script type="text/javascript">
				$(document).ready(function(){
					//弹出添加页面
					$("#addCoupon").dialog({
						autoOpen:false,
						modal:true,
						title:"添加优惠券",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#addCoupon").html("");
						}
					});
					//弹出修改页面
					$("#modifyCoupon").dialog({
						autoOpen:false,
						modal:true,
						title:"修改优惠券",
						resizable:false,
						width:700,
						height:600,
						close:function(){
							$("#modifyCoupon").html("");
						}	
					});
				});
				
				
				
				//删除优惠券
				function deleteBank(id){
					if(confirm("确定删除吗?")){
							window.location.href = "<%=basePath%>coupon/delete.htm?id="+id;
						}else{
							return;
						}	
				}
				//添加优惠券
				function addCoupon(){
					var url="";
					$("#addCoupon").dialog('open');
					url="coupon/toadd.htm",
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#addCoupon").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#addCoupon").html(data);
						}
					}); 
				}
				//编辑优惠券
				function modifyCoupon(id,pic){
					var url="";
					$("#modifyCoupon").dialog('open');
					url="coupon/toadd.htm?id="+id+"&pic="+pic,
					$.ajax({
						type: "POST",
						async: true,
						url: url,
						beforeSend:function(XHR){
						$("#modifyCoupon").html("<p style='text-align:center;padding-top:100px;'>&nbsp;数据加载中...</p>");
						},
						success: function(data){
							$("#modifyCoupon").html(data);
						}
					}); 
				}
		</script>
</head>
<body style="background: #ffffff;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="leftpanel" style="height: 100%;">
					<div class="leftpane" style="height: 100%; padding-left: 0;">
						<div class="title1">优惠券信息</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td style="background: #ffffff;text-align: center;">
				<div class="searchbox">
					<form id="search_form" action="coupon/search.htm" method="post">
						<ul>
							<li><label>商家名称:</label><input id="code"
								value="${merchant_name}" name="merchant_name" type="text" class="inp"/>
							</li>
							<li><label>优惠券名称:</label><input id="name"
								name="name" value="${name}" type="text" class="inp" />
							</li>
							<li><label>所属支付方式:</label>
							<select name="is_lock">
								<option value="">请选择</option>
								<c:forEach var="type" items="${payType}">
									<option value="${type.id }"<c:if test="${payTypes==type.id}">selected</c:if> >${type.value }</option>								
								</c:forEach>
							</select>
							</li>
							<li><input type="submit" class="searchbtn" value="查 询" /> &nbsp;&nbsp;&nbsp;
								<input type="reset" class="searchresetbtn" value="重置查询数据">
							</li>
						</ul>
					</form>
				</div>
				<div  style="clear:both;padding-right:50px;text-align:right;">				
						<input type="button" class="searchresetbtn" onclick="addCoupon();" value="添加优惠券"/>
					</div>
				<table class="table" id="content">
					<thead>
						<tr>
							<td>ID</td>
							<td>优惠券图</td>
							<td>优惠券名称</td>
							<td>商户名称</td>
							<td>是否推荐</td>
							<td>截止时间</td>
							<td>使用地址</td>
							<td>详细信息</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="tablecontent">
					<c:if test="${empty bank.results}">
							<tr><td colspan="13">没有查到相关信息</td></tr>
						</c:if>
						<c:set var="id" value="1" />
						<c:forEach items="${bank.results}" var="bank">
							<tr>
								<td>${id}</td>
								<c:set var="id" value="${id+1}" />
								<td>
								<c:if test="${not empty bank.show_pic}"><img src="<%=basePath%>${bank.show_pic}" height="40" width="70"></c:if>
								<c:if test="${empty bank.show_pic}"><img src="<%=basePath%>images/no-picx.png" height="40" width="70"></c:if>
								</td>
								<td>${bank.name}</td>
								<td>${bank.merchant_name}</td>
								<td>
								<c:if test="${bank.is_recommend=='1'}"><font color="red">推荐</font></c:if>
								<c:if test="${bank.is_recommend=='0'}">未推荐</c:if>
								</td>
								<td>${bank.end_time}</td>
								<td>${bank.use_address}</td>
								<td>
								<span title="${bank.introduce}"><nobr>${fn:substring(bank.introduce,0,10)}...</nobr></span>
								</td>
								<td>
								<a href="javascript:void(0)" onclick="modifyCoupon('${bank.id}')"><img src='images/login/edit.gif'  alt="编辑" title="编辑"/></a>
								<a href="javascript:void(0)" onclick="deleteBank('${bank.id}')" ><img src='images/login/delete.gif'  alt="删除" title="删除"/></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="14" style="padding: 5px 0;"><jscn:pagination
									numEntries="10" currentPage="${bank.page.curPageNo}"
									pageSize="${bank.page.pageSize}" total="${bank.page.totalCount}"
									numEdgeEntries="1" pageUrl="${pageUrl}"></jscn:pagination>
							</td>
						</tr>
					</tfoot>
				</table>
			</td>
		</tr>
	</table>
	<!-- 弹出浮动层 -->
	<div id="modifyCoupon" style="width:auto;min-height:0px;height:0px;">

	</div>	
	<div id="addCoupon" style="width:auto;min-height:0px;height:0px;">

	</div> 
</body>
</html>