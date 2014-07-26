<%@page import="frame.vo.CustomerVO"%>
<%@page import="frame.vo.SGroupVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	SGroupVO group = (SGroupVO) request.getAttribute("group");
	CustomerVO user = (CustomerVO) session.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);

#menu {
	width: 200px;
	height: 795px;
	position: relative;
	top: 0px;
	left: 0px;
}

#menu2 {
	position: absolute;
	top: 20px;
}

#groupchange1 {
	position: absolute;
	top: 130px;
	left: 20px;
}

#groupchange2 {
	position: absolute;
	top: 170px;
	left: 20px;
}

#free {
	position: absolute;
	top: 210px;
	left: 20px;
}

#secret {
	position: absolute;
	top: 250px;
	left: 20px;
}

#photo {
	position: absolute;
	top: 290px;
	left: 20px;
}

#home {
	position: absolute;
	top: 330px;
	left: 20px;
}

#invite {
	position: absolute;
	top: 450px;
	left: 15px;
}

#delete {
	position: absolute;
	top: 450px;
	left: 100px;
}

#delete2 {
	position: absolute;
	top: 450px;
	left: 50px;
}


#groupname {
	position: absolute;
	top: 48px;
	left: 20px;
	width: 140px;
}

a {
	text-decoration: none;
	font-family: 'Nanum Pen Script', cursive;
}

#groupname2 {
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;
	color: green;
	text-align: center;


}


a:LINK {
	color: black;
	text-decoration: none;
}

a:VISITED {
	color: black;
	text-decoration: none;
}

a:ACTIVE {
	color: black;
	text-decoration: none;
}

a:HOVER {
	color: black;
	text-decoration: none;
}

#f {
	font-family: 'Nanum Pen Script', cursive;
	font-size: large;
	color: black;
	font-weight: 100;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<div id="menu">
		<form enctype="multipart/form-data">
			<div id="menu2">
				<img src="image/menu1.jpg">
			</div>
			<%
				if (user.getId().equals(group.getLeaderId())) {
			%>
			<div id="groupchange1">
				<a href="control?cmd=groupleader&gno=<%=group.getgNo()%>"><img
					src="image/icon/change2.png" width="15" height="15">&nbsp;<font
					id="f">그룹 변경</font></a> <br>
				<img src="image/menubar.jpg" width="130" height="2">
			</div>

			<div id="groupchange2">
				<a href="control?cmd=groupinfo&gno=<%=group.getgNo()%>"><img
					src="image/icon/people.png" width="15" height="15">&nbsp;<font
					id="f">그룹 정보</font></a> <br>
				<img src="image/menubar.jpg" width="130" height="2">
			</div>

			<%
				} else {
			%>
			<div id="groupchange2">
				<a href="control?cmd=groupinfo&gno=<%=group.getgNo()%>"><img
					src="image/icon/people.png" width="15" height="15">&nbsp;<font
					id="f">그룹 정보</font></a> <br>
				<img src="image/menubar.jpg" width="130" height="2">
			</div>
			<%
				}
			%>

	<div id="groupname"><center><table><tr><td><font id="groupname2"><%=group.getgName()%></font></td></tr></table></center>
	</div>

			<div id="free">
				<a href="control?cmd=postview&gno=<%=group.getgNo()%>&secret=F">
					<img src="image/icon/board.png" width="15" height="15">&nbsp;<font
					id="f">공개 게시판</font>
				</a> <br>
				<img src="image/menubar.jpg" width="130" height="2">
			</div>

			<div id="secret">
				<a href="control?cmd=postview&gno=<%=group.getgNo()%>&secret=T">
					<img src="image/icon/locker2.png" width="15" height="15">&nbsp;<font
					id="f">비밀 게시판</font>
				</a> <br>
				<img src="image/menubar.jpg" width="130" height="2">
			</div>

			<div id="photo">
				<a href="control?cmd=gellery&gno=<%=group.getgNo() %>&pageNum=1"><img src="image/icon/photo.png" width="15" height="15">&nbsp;<font
					id="f">갤러리</font> </a><br>
				<img src="image/menubar.jpg" width="130" height="2">
			</div>

			<div id="home">
				<a href="control"> <img src="image/icon/home.png" width="15"
					height="15">&nbsp;<font id="f">내 그룹</font></a> <br> <img
					src="image/menubar.jpg" width="130" height="2">
			</div>

			<%
				if (user.getId().equals(group.getLeaderId())) {
			%>

			<div id="invite">
				<a href="control?cmd=groupinvite&gno=<%=group.getgNo()%>"><img
					src="image/icon/groupPlus.png" width="10" height="10">&nbsp;<font
					id="f">회원 초대</font></a>
			</div>

			<div id="delete">
				<a href="control?cmd=groupdeleteimpl&gno=<%=group.getgNo()%>"><img
					src="image/icon/delete.png" width="10" height="10"><font
					id="f"> 그룹 삭제</font></a>
			</div>
			<%
				} else {
			%>
			<div id="delete2">
				<a href="control?cmd=groupexit&gno=<%=group.getgNo()%>"><img
					src="image/icon/delete.png" width="10" height="10"><font
					id="f"> 그룹 탈퇴</font></a>
			</div>
			<%
				}
			%>
		</form>
	</div>
</body>
</html>