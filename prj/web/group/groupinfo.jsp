<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="java.util.*, frame.vo.*, frame.biz.*"%>

<%
	Biz biz = new CustomerBiz();
	SGroupVO group = (SGroupVO) request.getAttribute("group"); // 해당 그룹 정보를 가지고옴.
	ArrayList<PartyVO> party = (ArrayList<PartyVO>) request
			.getAttribute("party"); // 그룹 번호에 해당하는 Customer정보들을 가지고옴.
	ArrayList<CustomerVO> customer = (ArrayList<CustomerVO>) request
			.getAttribute("customer"); // 그룹 번호에 해당하는 Customer정보들을 가지고옴.
	String leader = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
#form1 {
	position: absolute;
	top: 120px;
	left: 50px;
}

#form2 {
	position: absolute;
	top: 200px;
	left: 200px;
}

#leadertable{
	position: absolute;
	top: 410px;
	left: 200px;
}
#groupwrite{
	position: absolute;
	top: 470px;
	left: 200px;
}

#grouptable{
	position: absolute;
	top: 495px;
	left: 200px;
}


#form3 {
	position: absolute;
	top: 610px;
	left: 50px;
}

font {
	color: black;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<br>
	<br>
	<center>
		<table id="form1">
			<tr>
				<td><font>●<b>그룹 정보</b></font></td>
			</tr>
			<tr>
				<td><center>
						<hr size="2" color="gray" width="700" />
					</center></td>
			</tr>
		</table>
	</center>
	<table align="center" id="form2">

		<tr>
			<td valign="bottom"><font>GROUP PHOTO:</font>
			<td><table bordercolor="gray" border="2">
					<tr>
						<td><img src="image/gimg/<%=group.getGroimg()%>" width="70"
							height="90"></td>
					</tr>
				</table></td>
		</tr>
		<tr><td><br></td></tr>
		<tr>
			<td><font>GROUP NAME: </font>
			<td><font><b><%=group.getgName()%></b></font></td>
		</tr>
		<tr><td><br></td></tr>
		<tr>
			<td><font>LEADER INFO</font></td></tr>
			

	</table>
	
	<div id="groupwrite"><font>MEMBER INFO</font></div>
	<table id="leadertable" border="1" bordercolor="#7BC145">
		<tr>
			<td bgcolor="#7BC145"><center><font>아이디</font></center></td>
			<td bgcolor="#7BC145"><center><font>이름</font></center></td>
			<td bgcolor="#7BC145"><center><font>생일</font></center></td>
			<td bgcolor="#7BC145"><center><font>전화번호</font></center></td>
			<%
				for (PartyVO pv : party) {
					if (pv.getId().equals(group.getLeaderId())) {
						leader = pv.getId();
						CustomerVO cv = (CustomerVO) biz.select(leader);
			%>
		
		<tr>
			<td><%=leader%></td>
			<td><%=cv.getName()%></td>
			<td><%=cv.getBirthday()%></td>
			<td><%=cv.getPhone()%></td>

			<%
				}
				}
			%>
		</tr>
	</table>


	<table id="grouptable" border="1" bordercolor="#7BC145">
		

					<tr>
			<td bgcolor="#7BC145"><center><font>아이디</font></center></td>
			<td  bgcolor="#7BC145"><center><font>이름</font></center></td>
			<td  bgcolor="#7BC145"><center><font>생일</font></center></td>
			<td bgcolor="#7BC145"><center><font>전화번호</font></center></td>
			</tr>
			<%
				for (PartyVO pv : party) {

					if ((pv.getId().equals(leader)) == false) {
						CustomerVO cv = (CustomerVO) biz.select(pv.getId());
			%>
		
		<tr>
			<td><%=pv.getId()%></td>
			<td><%=cv.getName()%></td>
			<td><%=cv.getBirthday()%></td>
			<td><%=cv.getPhone()%></td>

			<%
				}
				}
			%>
		</tr>
	</table>
	<center id="form3">
		<hr size="2" color="gray" width="700" />
	</center>
	<br>
	<br>

</body>
</html>