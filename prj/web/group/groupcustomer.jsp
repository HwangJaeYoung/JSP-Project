<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">
	
	function exitGroup(form)
	{		
		var question = confirm("�׷��� �����ðڽ��ϱ�?");
		
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
<tr> <td colspan="2"> <input type ="button" value="�׷쳪����" onclick="exitGroup(this.form)"></td>
	 <td> <input type="hidden" name="gno" value="<%= 23%>"> </td>
	 
	 <!-- 21�� �׷� �ѹ��̰� �׷��� ������ ���� �������� �� �ش�׷��� �����ϱ� ������ �ű⼭ ��� �ҷ��ͼ� �񱳰����ϴ�. -->
</tr>
</table>
</form>

</body>
</html>