<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.*, frame.biz.*, frame.vo.*" %>
    
<% 
	ArrayList<Object> result = null;
	Biz biz = new CustomerBiz();
	result = biz.select( ); // �ʴ븦 �ϱ����� ȸ�������� Customer����� ������ �´�.
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<form action="partyregisterimpl" method="post">
<table align = "center" border="1" width = "400">
<tr> <th> ID </th> <th> NAME </th> <th> BIRTHDAY </th> <th> SEX </th> <th> �ʴ��ϱ� </th> </tr>

<% for(Object temp : result) {
	CustomerVO cv = (CustomerVO)temp; 	
%>
<tr>
	 <!-- ������ ���� ������ ǥ������ �ʴ´�. -->
     <td><%=cv.getId() %></td>
     <td><%=cv.getName() %></td>
     <td><%=cv.getBirthday() %></td>
     <td><%=cv.getSex() %></td>
     <td> <input type="checkbox" name="check" value="<%=cv.getId() %>"></td>
<%} %>
</tr>

<tr>
	<!-- ���⼭ �ѱ�� id�� �ش������� id�̴�. Hidden���� �ش� �׷��� ��ȣ�� ���� �Ѱ��־�� �Ѵ�. -->
	<td colspan="5" align="center"><input type="submit" value = "�ʴ�"></td>
	<input type="hidden" name = "gno" value ="<%= 21%>"> <!-- ���⿡�� 3�� request�� ���߿� ��ü�Ѵ�. value�� �׷��ȣ-->
</tr>
</table>
</form>

</body>
</html>