<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
	
	function exitGroup(form)
	{		
		var question = confirm("그룹을 나가시겠습니까?");
		
		if(question == true)
		{
			form.method="POST";
			form.action="groupexitimpl";
			form.submit( );				
		}
	}

</script>

<title>Insert title here</title>
</head>
<body>

<form>
<table>
<tr> <td colspan="2"> <input type ="button" value="그룹나가기" onclick="exitGroup(this.form)"></td>
	 <td> <input type="hidden" name="gno" value="<%= 23%>"> </td>
	 
	 <!-- 21은 그룹 넘버이고 그룹을 누르는 순간 세션으로 그 해당그룹을 저장하기 때문에 거기서 계속 불러와서 비교가능하다. -->
</tr>
</table>
</form>

</body>
</html>