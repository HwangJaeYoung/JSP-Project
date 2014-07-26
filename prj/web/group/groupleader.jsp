<%@page import="frame.vo.SGroupVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%
   SGroupVO group = (SGroupVO) request.getAttribute("group");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script type="text/javascript">


   function updateData(form) {
      var question = confirm("그룹정보를 변경하시겠습니까?");

      if (question == true) {
         form.method = "POST";
         form.action = "control?cmd=groupleader";
         form.submit();
      }
   }
</script>
<style>


#form2{
position:absolute;
top:250px;
}
</style>



<title>Insert title here</title>
</head>
<body>

   <form enctype="multipart/form-data" id="form2">

      <center>
         <table cellpadding="2">
            <tr>
               <td><font face="돋움" color="black" id="font2">●<b>그룹 변경</b></font></td>
            </tr>
            <tr>
               <td><center>
                     <hr size="2" color="gray" width="700" />
                  </center></td>
            </tr>
         </table>
      </center>
      <table cellspacing="30" align="center"><tr><td>
      <table align="center">
         <tr>
            <td><font id="font2">그룹 이름</font></td>
            <td><input type="text" name="title"
               value="<%=group.getgName()%>"></td>
         </tr>
         <tr>
            <td><font  id="font2">그룹 이미지</font></td>
            <td><input type="file" name="play"></td>
         </tr>
         <tr>

            <td><input type="hidden" name="gno"
               value="<%=group.getgNo()%>"></td>
            
         </tr>
      </table>
      </td></tr></table>

      <center>
         <hr size="2" color="gray" width="700" />
         
         <button onClick="updateData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/registerButton.jpg"></BUTTON>
      </center>

   </form>
</body>
</html>