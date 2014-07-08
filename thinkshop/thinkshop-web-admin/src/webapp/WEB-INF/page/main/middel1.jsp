<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="../base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<style> 
.navPoint { 
 CURSOR: pointer; FONT-FAMILY: Webdings; FONT-SIZE: 9pt ;
} 
</style> 
<script>
function switchSysBar(){ 
var locate=location.href.replace('<%=basePath%>middel.htm','');
var ssrc=document.getElementById("frmTitle").style.display;
if (ssrc=="")
{ 
document.getElementById("img1").src="images/manage/t9.gif";
document.getElementById("frmTitle").style.display="none" ;
} 
else
{ 
document.getElementById("img1").src="images/manage/t8.gif";
document.getElementById("frmTitle").style.display="" ;
} 
} 
</script>

</head>

<body style="overflow:hidden">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td width="177" id="frmTitle" noWrap name="fmTitle" align="center" valign="top"><table width="177" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
      <tr>
        <td width="165"><iframe name="I1" height="100%" width="177" src="<%=basePath %>role/getUserMenus.htm?parentCode=0" border="0" frameborder="0" scrolling="auto" noresize> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe></td>
      </tr>
    </table></td>
    <!-- 开关条 -->
    <td width="7" bgcolor="#1791C2" style="width:7px;" valign="middle" onclick=switchSysBar()><SPAN class=navPoint 
id=switchPoint title=关闭/打开左栏><img src="<%=basePath %>images/manage/t8.gif" name="img1" id="img1"></SPAN></td>
    <td width="100%" align="center" valign="top" style="background:#1B8EC5;"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0"><tr><td><iframe name="mainFrm" height="100%" width="100%" border="0" frameborder="0" src="<%=basePath %>default.htm" scrolling="auto"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe></td></tr></table></td>
  </tr>
</table>
</body>
</html>