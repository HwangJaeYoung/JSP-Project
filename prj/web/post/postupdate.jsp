<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
   	import = "frame.biz.*, frame.vo.*" 
   
    %>
<%  
	PostVO pv = (PostVO) request.getAttribute("post"); //해당 포스트를 가지고 온다.
	CustomerVO cv = (CustomerVO) session.getAttribute("user");

	String tempStr = pv.getFile();
	
	int position; // "."이 있는 위치를 찾는다.
	char[] tempChar; 
	String result = null; //확장자를 뽑아서 저장하기 위한 변수
	
	if(tempStr != null) {
		position = tempStr.indexOf("."); // "."이 있는 위치를 찾는다.
		tempChar = tempStr.toCharArray(); 
		result = ""; //확장자를 뽑아서 저장하기 위한 변수
		
		for(int i = position; i < position + 4; i++)
			result += tempChar[i]; 
	}
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
	function register(form)
	{		
		form.method="POST";
		form.action="postupdateimpl";
		form.submit( );				
	}
</script>
<style>
#form{
position: absolute;
top:50px;
left:50px;
}
</style>

<title>Insert title here</title>
</head>
<body>

<form enctype="multipart/form-data" id="form">
<table align = "center" width ="400" height="400" border="1" bordercolor="#7BC145" style="border-style: solid;">


<tr> <td  bgcolor="#7BC145"><center><font>NAME</font></center></td> <td><%= cv.getName() %></td></tr>
<tr> <td bgcolor="#7BC145"><center><font>DATE</font></center></td> <td><%= pv.getUploadDate() %></td></tr>
<tr> <td bgcolor="#7BC145"><center><font>CONTENT</font></center></td> <td height="80"><textarea cols="72" rows="7"
						style="overflow-y: scroll; width: 450px;" name="content" ><%= pv.getContent() %></textarea></td></tr>
<tr> <td bgcolor="#7BC145"><center><font>IMG / VIDEO</font></center></td> <td><input type="file" name="play" ><input type="hidden" name="uno" value="<%=pv.getuNo()%>">
	 <input type="hidden" name="gno" value="<%=pv.getgNo()%>">
	 <input type ="hidden" name="secret" value="<%=pv.getSecret()%>"></td> </td></tr>

</table>
<br><br>
<center><button onClick="register(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/registerButton.jpg"></BUTTON></center>
</form>

</body>
</html>