<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript">
	function login(form) {
		form.method = "POST";
		form.action = "control?cmd=login";
		form.submit();
	}
	
	function register(form) {
		form.method = "POST";
		form.action = "control?cmd=cregister";
		form.submit();
	}
</script>

<link href='http://fonts.googleapis.com/css?family=Concert+One'
   rel='stylesheet' type='text/css'>
<style>
font {
   font-family: 'Concert One', cursive;
}

<
style type ="text /css">font {
   font-size: 32px;
   font-family: 'Alegreya Sans SC', cursive;
}

#tableContainer {
   position:absolute;
   top:50%;
   left:50%;
   margin-left:-250px;
   margin-top:-200px;
   padding: 20px;
}

table {
	padding: 10px;
}
</style>
<title>Insert title here</title>
</head>
<body>
   <div id="tableContainer">
      <table>
         <tr>
            <td>
               <form action="control?cmd=login" method="POST">
                  <table>
                     <tr>
                        <td colspan="2"><img
                           src="image/login/talk.jpg" width="180" height="60" align="top"></td>
                     </tr>
                     <tr>
                        <td colspan="2">&nbsp;&nbsp;<input
                           type="text" name="id" placeholder="아이디"></td>
                     </tr>
                     <tr>
                        <td colspan="2">&nbsp;&nbsp;<input
                           type="password" name="pwd" placeholder="비밀번호"></td>
                     </tr>
                     <tr>
                        <td></td>
						<td><button onClick="login(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/login/loginButton.jpg" width="65" height="30"></BUTTON><button  onClick="register(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/login/joinButton.jpg" width="70" height="30"></button></td>
                     </tr>
                  </table>

               </form>
            </td>
            <td><img src="image/login/cott.jpg" width="200" height="200"
               align="right"></td>
         </tr>
      </table>

   </div>
</body>
</html>