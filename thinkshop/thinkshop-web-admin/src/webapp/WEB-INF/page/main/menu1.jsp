<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="../base.jsp" %>
  <%@include file="../taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=basePath %>css/manage1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
		<style>
			.menu_body{
				background:#d6f4fe;
			}			
			.left_main_div{
         		height:auto ;
			}
		</style>
		<script>
			function ResumeError()
			{return true; }
			window.onerror = ResumeError;
		
			$(function(){
				$(".menu-box h1").click(function(){
					var ul=$(this).parent().next("ul");
					if(ul.css("display")=="none"){
						ul.show();
						$(this).removeClass("down");
						if($(this).attr("hasChild")>0 && $(this).attr("isLoad") === "false"){
							$.post("<%=basePath%>role/getUserMenus.htm","parentCode="+$(this).attr("pid"),function(result){
								ul.html(result);
			                },"html");
			                $(this).attr("isLoad","true");
						}
					}else{
						ul.hide();
						$(this).addClass("down");
					}
					
				});
			});
			


			function clicka(event){
				$("li a").removeClass("selected");
				$(event).addClass("selected");
			};
	

			
			function doMenuSwap(flag){
				var swapflag = document.getElementById("content_text"+flag);
				var title = document.getElementById("title"+flag);
				if(swapflag.style.display=="none"){
					swapflag.style.display="";
					title.className="";
				}else{
					swapflag.style.display="none";
					title.className="down";
				}
			}
		</script>
	</head>
	<body class="menu_body">
	 <div class="left_main_div" id="left_main_div">
	 		<div style="float:left;background:#D6F4FE;" id="left_div"></div>
	 		<!-- <div style="height:100%;"> -->
				<div id="menubox">
					<div class="utitle">
						<div class="utitle-left">
							<div class="utitle-right">管理选项</div>
						</div>
					</div>
					<c:forEach items="${menus}" var="menus">
					<div class="menu-box">
						<div class="menu">
							<div><h1 class="down" isLoad="false" hasChild="${menus.childrenCount}" pid="${menus.code}">${menus.title }</h1></div>
							<ul id="content_text1" style="display:none;">
							
							</ul>
						</div>
					</div>
					</c:forEach>
				</div>
				
			 <!-- </div> -->
	  </div>
	</body>
</html>
<script type="text/javascript">
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="images/manage/main_47.gif";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="images/manage/main_48.gif";
}
}

//获得body高度
//document.body.clientHeight
//alert(document.documentElement.clientHeight);

//自动设置高度

function doSetDivHeight(){

	var bodyheight=document.documentElement.clientHeight;

	document.getElementById("left_main_div").style.height=bodyheight+"px";
	document.getElementById("left_div").style.height=bodyheight+"px";
	
	setTimeout("doSetDivHeight()",100);
}

doSetDivHeight();

</script>