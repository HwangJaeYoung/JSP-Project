<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.*, frame.biz.*, frame.vo.*" %>
    
<% 
	ArrayList<Object> result = null;
	Biz biz = new CustomerBiz();
	result = biz.select( ); // 초대를 하기위해 회원가입한 Customer목록을 가지고 온다.
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
<tr> <th> ID </th> <th> NAME </th> <th> BIRTHDAY </th> <th> SEX </th> <th> 초대하기 </th> </tr>

<% for(Object temp : result) {
	CustomerVO cv = (CustomerVO)temp; 	
%>
<tr>
	 <!-- 나머지 개인 정보는 표현하지 않는다. -->
     <td><%=cv.getId() %></td>
     <td><%=cv.getName() %></td>
     <td><%=cv.getBirthday() %></td>
     <td><%=cv.getSex() %></td>
     <td> <input type="checkbox" name="check" value="<%=cv.getId() %>"></td>
<%} %>
</tr>

<tr>
	<!-- 여기서 넘기는 id는 해당유저의 id이다. Hidden으로 해당 그룹의 번호도 같이 넘겨주어야 한다. -->
	<td colspan="5" align="center"><input type="submit" value = "초대"></td>
	<input type="hidden" name = "gno" value ="<%= 21%>"> <!-- 여기에서 3은 request로 나중에 대체한다. value는 그룹번호-->
</tr>
</table>
</form>

</body>
</html>