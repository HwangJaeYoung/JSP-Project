<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.ArrayList, frame.vo.CustomerVO"%>
<%
	ArrayList<Object> data = (ArrayList<Object>) request.getAttribute("cselect");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">Customer select</h2>
	
	<table align="center" border="1" width="850">
		<tr>
			<th>MY PHOTO</th>
			<th>ID</th>
			<th>NAME</th>
			<th>BIRTHDAY</th>
			<th>SEX</th>
			<th>PHONE</th>
			<th>ADDRESS</th>
			<th>EMAIL</th>
		</tr>
		
		<%for(Object obj : data) { 
			CustomerVO c = (CustomerVO) obj;
			if(!c.getId().equals("admin")) {%>
			<tr>
				<td width="50"><img src="cimg/<%=c.getFile() %>" width="50" height="60"></td>
				<td><a href="control?cmd=cdetail&id=<%=c.getId() %>"><%=c.getId() %></a></td>
				<td><%=c.getName() %></td>
				<td><%=c.getBirthday() %></td>
				<td><%=c.getSex() %></td>
				<td><%=c.getPhone() %></td>
				<td><%=c.getAddress() %></td>
				<td><%=c.getEmail() %></td>

			</tr>
		<%}
		} %>
	</table>
</body>
</html>