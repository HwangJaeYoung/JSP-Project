<%@page import="frame.vo.SGroupVO"%>
<%@page import="frame.vo.CustomerVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	ArrayList<CustomerVO> selectlist = (ArrayList<CustomerVO>) request
			.getAttribute("selectlist");
	SGroupVO group = (SGroupVO) request.getAttribute("group");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
#f2{
font-family: 'Nanum Pen Script', cursive;
font-size:x-large;
color: black;

}
#form1 {
	position: absolute;
	top: 120px;
	left: 20px;
}

#form2 {
	position: absolute;
	top: 200px;
	left: 160px;
}



#listview {
	position: absolute;
	top: 400px;
	left: 20px;
}

#form3 {
	position: absolute;
	top: 520px;
	left: 20px;
}

#bar {
	position: absolute;
	top: 300px;
	left: 20px;
}

#invite2{

position: absolute;
top:12px;
left:530px;
}

#searchbutton{

position: absolute;
top:18px;
left:280px;
}
</style>
</head>
<body>

	<center>
		<table id="form1">
			<tr>
				<td><font>●<b>회원 초대</b></font></td>
			</tr>
			<tr>
				<td><center>
						<hr size="2" color="gray" width="700" />
					</center></td>
			</tr>
		</table>
	</center>

	<%
		if (selectlist != null) {
	%>
	<div id="bar">
					
			<font><b>●검색 결과</b></font>
				<hr size="2" color="gray" width="700" />
			</div>	
			
	<div id="listview">
	
		<form action="control" method="POST">
			<table border="1" bordercolor="green">
				<tr bgcolor="#7BC145" style="font-family: 돋움">
					<th width="10"></th>
					<th width="60"><font id="f2" color="black">ID</font></th>
					<th width="70"><font id="f2">NAME</font></th>
					<th width="110"><font id="f2">BIRTHDAY</font></th>
					<th width="200"><font id="f2">E-MAIL</font></th>
				</tr>

				<%
					for (CustomerVO customerTemp : selectlist) {
				%>
				<tr>
					<td><input type="checkbox" name="id"
						value="<%=customerTemp.getId()%>"></td>
					<td><%=customerTemp.getId()%></td>
					<td><%=customerTemp.getName()%></td>
					<td><%=customerTemp.getBirthday()%></td>
					<td><%=customerTemp.getEmail()%></td>
				</tr>
				<%
					}
				%>
			</table>

			<div id="invite2"><input type="hidden" name="cmd" value="groupinvite"> <input
				type="hidden" name="gno" value="<%=group.getgNo()%>"> <button type="submit" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/inviteButton.jpg"></BUTTON></div>
		</form>
	</div>
	<%
		}
	%>

	<div id="search">
		<form action="control" method="GET">
			<table id="form2">

				<tr>
					<td><font>이름</font></td><td><input type="text" name="name"></td>
				<tr>
				<td><font>생년월일&nbsp;</font></td>
					<td><select name="year">
							<option value="">년도</option>
							<%
								for (int i = 2014; i >= 1900; i--) {
							%>
							<option value=<%=i%>><%=i%></option>
							<%
								}
							%>
					</select><select name="month">
							<option value="">월</option>
							<%
								for (int i = 1; i < 13; i++) {
							%>
							<option value=<%=i%>><%=i%></option>
							<%
								}
							%>
					</select><select name="day">
							<option value="">일</option>
							<%
								for (int i = 1; i < 32; i++) {
							%>
							<option value=<%=i%>><%=i%></option>
							<%
								}
							%>
					</select></td>


					<td colspan="2"><div id="searchbutton"><button type="submit" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/serchButton.jpg"></BUTTON></div> <input
						type="hidden" name="cmd" value="groupinvite"> <input
						type="hidden" name="gno" value="<%=group.getgNo()%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>
	<br>
	<center id="form3">
		<hr size="2" color="gray" width="700" />
	</center>
</body>
</html>