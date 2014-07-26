<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="frame.vo.CustomerVO"%>
<%
	CustomerVO c = (CustomerVO) request.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<br><br>
	<center>
		<table>
			<tr>
				<td><font>●<b>회원정보</b></font></td>
			</tr>
			<tr>
				<td><center>
						<hr size="2" color="gray" width="700" />
					</center></td>
			</tr>
		</table>
	</center>
	<table align="center" width="400" height="400" >
		<tr>
			<th>MY PHOTO</th>
			<td><table bordercolor="gray" border="2"><tr><td><img src="image/cimg/<%=c.getFile()%>" width="100" height="125"></td></tr></table></td>
		</tr>
		<tr>
			<th>ID</th>
			<td><%=c.getId()%></td>
		</tr>


		<tr>
			<th>NAME</th>
			<td><%=c.getName()%></td>
		</tr>

		<tr>
			<th>BIRTHDAY</th>
			<td><%=c.getBirthday()%></td>
		</tr>


		<tr>
			<th>SEX</th>
			<td><%=c.getSex()%></td>
		</tr>

		<tr>
			<th>PHONE</th>
			<td><%=c.getPhone()%></td>
		</tr>

		<tr>
			<th>ADDRESS</th>
			<td><%=c.getAddress()%></td>
		</tr>

		<tr>
			<th>EMAIL</th>
			<td><%=c.getEmail()%></td>
		</tr>



	</table>
<br><br>
	<center>
		<hr size="2" color="gray" width="700" />
				<%
			if (c.getId().equals(c.getId()) || c.getId().equals("admin")) {
		%>
		<a href="control?cmd=cupdate&id=<%=c.getId()%>"><img src="image/modifyButton.jpg" width="60" height="35"></a>
		<a href="control?cmd=cdelete&id=<%=c.getId()%>"><img src="image/deleteButton.jpg" width="60" height="35" ></a>
			<%
			}
		%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</center>
</body>
</html>