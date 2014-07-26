<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import = "java.util.*, frame.vo.*, frame.biz.*"
    %>
    
<% Biz biz = new SGroupBiz( );
ArrayList<Object> temp = biz.select( ); 
ArrayList<Object> result = (ArrayList<Object>)request.getAttribute("group");

for(Object obj : result)
	System.out.println(obj);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
   function deleteData(form)
   {
        form.method = "POST";
        form.action = "control?cmd=groupdeleteimpl";
		form.submit();
   }
</script>
<style>
#pan{
position: relative;
top:-300px;
}

#form1 {
	position: absolute;
	top: 400px;
	left:50px;
}

#form2 {
	position: absolute;
	top: 470px;
	left: 50px;
}

#write {

	font-size: 25px;
	color: white;
}




</style>
<title>Insert title here</title>
</head>
<body>
<div id = "pan">
	<br>
	<br>
	<center>
		<table id="form1">
			<tr>
				<td><font>●<b>그룹 관리</b></font></td>
			</tr>
			<tr>
				<td><center>
						<hr size="2" color="gray" width="700" />
					</center></td>
			</tr>
		</table>
	</center>
<form id="form2">


<table border="1" bordercolor="#7BC145" align="center">

<tr> <th bgcolor="#7BC145"> <font id="write">번호</font> </th>
<th bgcolor="#7BC145"> <font id="write">그룹 이름</font> </th>
<th bgcolor="#7BC145"> <font id="write">리더 아이디</font> </th>
<th bgcolor="#7BC145"> <font id="write">삭제</font> </th>
 </tr>

	
	<% for(Object obj : result) {
		SGroupVO sg = (SGroupVO)obj;%>
		<tr>
		<form>
		<td align="center"> <%= sg.getgNo() %> </td>
		<td align="center"> <%= sg.getgName() %> </td>
		<td align="center"> <%= sg.getLeaderId() %> </td>
		<td>
			<button onClick="deleteData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><center><img src="image/x.png" width="25" height="25"></center></BUTTON>
			<input type="hidden" name="gno" value="<%= sg.getgNo() %>">
			<input type="hidden" name="check" value="true">
		</td>
		</form>
	</tr>
	<%} %>

</table>
<br>
	

   

	<center id="form3">
   <%for(int i = 0; (i * 9) < temp.size(); i++) { %>
      <a href="control?cmd=admingroup&pagenum=<%=i+1 %>"><%=i+1 %></a>
   <%} %>
		<hr size="2" color="gray" width="700" />
	</center>
	<br>
	<br>
</form>


   
</div>
</body>
</html>