<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
	function register(form)
	{		
		var img = form.play.value;
		
		if(img == null || img == "")
		{
			alert("이미지를 입력하셔야 합니다.");
		}
		else
		{
			form.method="POST";
			form.action="groupregisterimpl";
			form.submit( );				
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

#white{
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;
	color: white;
	text-align: center;
	
}
</style>
<title>Insert title here</title>
</head>
<body>
<div id="pan">
<div id="pan2">
<br><br><br>
		<center>
			<table>
				<tr>
					<td><font>●그룹 생성</font></td>
				</tr>
				<tr>
					<td><center>
							<hr size="2" color="gray" width="700" />
						</center></td>
				</tr>
			</table>
		</center>
<form enctype="multipart/form-data">
<br><br><br><br>

<center>
<table border="1" bordercolor="#7BC145">

<tr> <td bgcolor="#7BC145" align="center"> <font id="white">Group name</font> </td> <td> <input type="text" name="title"> </td> </tr>
<tr> <td bgcolor="#7BC145" align="center"> <font id="white">Group img</font> </td> <td> <input type="file" name="play" ></td> </tr>


</table></center>
<br><br><br>
<center><button onClick="register(this.form)"
                     STYLE='background-color: transparent; color: transparent; border: 0; cursor: hand'>
                     <img src="image/registerButton.jpg">
                  </button></center>
</form>
		<center><br><br><br><br>
			<table>

				<tr>
					<td><center>
							<hr size="2" color="gray" width="700" />
						</center></td>
				</tr>
			</table>
		</center>
</div>
</div>
</body>
</html>