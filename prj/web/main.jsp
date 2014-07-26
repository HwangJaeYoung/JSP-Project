<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.*"%>
<%
	String left = (String) request.getAttribute("left");

	String top = (String) request.getAttribute("top");
	if(top == null)
	{
		top = "top.jsp";
	}
	String center = (String) request.getAttribute("center");
	String bottom = "bottom.jsp";

	if(center == null) {
		center = "center.jsp";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript"> 
function displaywheel(e){ 
     var evt=window.event || e //equalize event object 
     var delta=evt.detail? evt.detail*(-120) : evt.wheelDelta //check for detail first so Opera uses that instead of wheelDelta 
     document.body.scrollTop += (delta*-1) 
} 
var mousewheelevt=(/Firefox/i.test(navigator.userAgent))? "DOMMouseScroll" : "mousewheel" //FF doesn't recognize mousewheel as of FF3.x 
if (document.attachEvent) //if IE (and Opera depending on user setting) 
  document.attachEvent("on"+mousewheelevt, displaywheel) 
else if (document.addEventListener) //WC3 browsers 
  document.addEventListener(mousewheelevt, displaywheel, false) 
</script>
<style type="text/css">	
@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
	a { text-decoration:none ;
	   font-family: 'Nanum Pen Script', cursive;
	   font-size: x-large;
	}
	font {
		font-family: 'Nanum Pen Script', cursive;
		font-size: x-large;
	}
	#table {
		width: 1024px;
		height: 900px;
		position: relative;
		margin: 0 auto;
	}
	
	#top {
		height: 100px;
		top: 0px;
		left: 0px;
		position: absolute;
	}
	
	#center {
		height: 795px;
		top: 100px;
		width: 1024px;
		left: 0px;
		position: absolute;
	}
	
	#bottom {
		height: 5px;
		bottom: 0px;
		left: 0px;
		position: absolute;
	}
	
	#left {
		height: 795px;
		left: 0px;
		width: 200px;
		position: absolute;
	}
	
	#content {
		position: absolute;
		height: 663px;
		left: 200px;
		width: 824px;
		overflow: scroll;
		overflow-x: hidden; 
	}
</style>
<title>Talk끼리</title>
</head>
<body>
	<div id="table">
		<%if(left == null) { %>
			<div id="top">
				<jsp:include page="<%=top %>"/>
			</div>
			
			<div id="center">
				<jsp:include page="<%=center %>"/>
			</div>
			
			<div id="bottom">
				<jsp:include page="<%=bottom %>"/>
			</div>
		<%} 
		  else { %>
			<div id="top">
				<jsp:include page="<%=top %>"/>
			</div>
			
			<div id="center">
				<div id="left">
					<jsp:include page="<%=left %>"/>
				</div>
				<div id="content">
					<jsp:include page="<%=center %>"/>
				</div>
			</div>
			
			<div id="bottom">
				<jsp:include page="<%=bottom %>"/>
			</div>
		<%} %>
	</div>
</body>
</html>