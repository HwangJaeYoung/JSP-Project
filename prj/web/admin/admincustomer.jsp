<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import = "java.util.*, frame.vo.*, frame.biz.*"
    %>
    
<% 
Biz biz = new CustomerBiz( );
ArrayList<Object> temp = biz.select( ); 
ArrayList<Object> length = new ArrayList<Object>( );
ArrayList<Object> result = (ArrayList<Object>)request.getAttribute("customer");

for(Object obj : temp)
{
	CustomerVO cv = (CustomerVO)obj;
	
	if(cv.getC_status().equals("T"))
	{
		length.add(cv);	
	}
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
   function deleteData(form)
   {
        form.method = "GET";
        form.action = "control";
		form.submit();
   }
</script>
<style>

#pan{
position: relative;
top:0px;
}

#form1 {
	position: absolute;
	top: -60px;
	left:50px;
}

#form2 {
	position: absolute;
	top: 200px;
	left: 700px;
}

#write {

	font-size: 25px;
	color: white;

}

</style>
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
	<div id="pan">
		<table id="form1">
			<tr>
				<td><font>●<b>회원 관리</b></font></td>
			</tr>
			<tr>
				<td><center>
						<hr size="2" color="gray" width="700" />
					</center></td>
			</tr>
		</table>



<table align="center"   border="1"  bordercolor="#7BC145">

<tr> <th bgcolor="#7BC145" width="80"> <font id="write">아이디</font> </th>
<th bgcolor="#7BC145" width="80"> <font id="write">이름</font> </th>
<th bgcolor="#7BC145" width="85"> <font id="write">생년월일</font> </th>
<th bgcolor="#7BC145" width="130"> <font id="write">전화번호</font> </th>
<th bgcolor="#7BC145" width="180"> <font id="write">이메일</font> </th>
 <th bgcolor="#7BC145" width="20"><font id="write">삭제</font> </th></tr>

	<% for(Object obj : result) {
		CustomerVO cv = (CustomerVO)obj;%>
		
		<tr>
		<form>
		<%if(cv.getC_status().equals(("T"))) {
 
			String tempId = cv.getId( );%>
		<td align="center"> <%=cv.getId() %></td>
		<td align="center"> <%= cv.getName() %></td>
		<td align="center"> <%= cv.getBirthday() %></td>
		<td align="center"> <%= cv.getPhone() %></td>
		<td align="center"> <%= cv.getEmail() %></td>
		<td><button onClick="deleteData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><center><img src="image/x.png" width="22" height="22"></center></BUTTON>
			
			<input type="hidden" name="id" value="<%=tempId %>">
			<input type="hidden" name="check" value="true">
			<input type="hidden" name="cmd" value="cdelete">
		</td>
	</tr>
		<%} %>
		
		</form>
	<%} %>

</table>
<br>
	<div id="form3">
	<center>
		<%for(int i = 0; (i * 9) < length.size(); i++) { 
			System.out.println(length.size());
		%>
      <a href="control?cmd=admincustomer&pagenum=<%=i+1 %>"><%=i+1 %></a>
   <%} %>
   </center>
		<hr size="2" color="gray" width="700" />
	</div>
	<br>
	<br>
</div>
</body>
</html>