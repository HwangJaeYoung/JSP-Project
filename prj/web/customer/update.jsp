<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="frame.vo.CustomerVO"%>

<%
	CustomerVO c = (CustomerVO) request.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
	function update(form) {
		var c = confirm("등록 하시겠습니까?");

		if (c == true) {
			form.method = "POST";
			form.action = "control?cmd=cupdate";
			form.submit();
		}
	}
</script>
<style>
#pan{
position: relative;
top:0px;
}

#pan2{
position: absolute;
top:40px;
left:150px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<div id="pan">
<div id="pan2">
	<form enctype="multipart/form-data">
		<input type="hidden" name="img" value="<%=c.getFile()%>">
		<center>
			<table>
				<tr>
					<td><font>●<b>회원정보 수정</b></font></td>
				</tr>
				<tr>
					<td><center>
							<hr size="2" color="gray" width="700" />
						</center></td>
				</tr>
			</table>
		</center>
		<table align="center" width="400" height="400">
			<tr>
				<th>MY PHOTO</th>
				<td><input type="file" name="img"></td>
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
				<th>PWD</th>
				<td><input type="password" name="pwd"></td>
			</tr>


			<tr>
				<th>PHONE</th>
				<td><input type="text" name="phone" value="<%=c.getPhone()%>"></td>
			</tr>
			<tr>
				<th>ADDRESS</th>
				<td><input type="text" name="address"
					value="<%=c.getAddress()%>"></td>
			</tr>

			<tr>
				<th>EMAIL</th>
				<td><input type="text" name="email" value="<%=c.getEmail()%>"></td>
			</tr>

			<tr>
			<td></td>
				<td>
							
					</td>
			</tr>
		</table>
		<center>
			<hr size="2" color="gray" width="700" />
			<button onClick="update(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/registerButton.jpg"></BUTTON>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</center>
	</form>
	</div>
	</div>
</body>
</html>