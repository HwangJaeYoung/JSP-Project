<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
td {
   font-size: small;
   text-align: left;
}
</style>
<title>Insert title here</title>
<script type="text/javascript">
   function register(form) {
      var con = confirm("회원 가입 하시겠습니까?");
      if (con == true) {
         form.method = "POST";
         form.action = "control?cmd=cregisterimpl";
         form.submit();
      }
   }

   function cancel(form) {
      var con = confirm("취소 하시겠습니까?");
      if (con == true) {
         form.method = "POST";
         form.action = "control?cmd=";
         form.submit();
      }
   }
</script>
<title>Talk끼리</title>
</head>
<body>
   <table align="center" width="1024">
      <tr height="100">
         <td><jsp:include page="register_top.jsp" /></td>
      </tr>

      <tr height="695">
         <td>

            <form enctype="multipart/form-data">
               <center>

                  <table>
                     <tr>
                        <td><font>●<b>회원가입</b></font></td>
                     </tr>
                     <tr>
                        <td><center>
                              <hr size="2" color="gray" width="700" />
                           <br>
                           </center></td>
                     </tr>
                  </table>
               </center>

               <table align="center" width="400" height="400">
                  <tr>
                     <td>ID</td>
                     <td><input type="text" name="id"></td>
                  </tr>
                  <tr>
                     <td>PWD</td>
                     <td><input type="text" name="pwd"></td>
                  </tr>
                  <tr>
                     <td>NAME</td>
                     <td><input type="text" name="name"></td>
                  </tr>
                  <tr>
                     <td>BIRTHDAY</td>
                     <td colspan="3"><select name="year">
                           <option value="">년도</option>
                           <%
                              for (int i = 2014; i >= 1900; i--) {
                           %>
                           <option value=<%=i%>><%=i%></option>
                           <%
                              }
                           %>
                     </select><select name="month">
                           <option value="">월</option>
                           <%
                              for (int i = 1; i < 13; i++) {
                           %>
                           <option value=<%=i%>><%=i%></option>
                           <%
                              }
                           %>
                     </select><select name="day">
                           <option value="">일</option>
                           <%
                              for (int i = 1; i < 32; i++) {
                           %>
                           <option value=<%=i%>><%=i%></option>
                           <%
                              }
                           %>
                     </select></td>
                  </tr>
                  <tr>
                     <td>SEX</td>
                     <td><input type="radio" name="sex" value="M">남자 <input
                        type="radio" name="sex" value="F">여자</td>
                  </tr>
                  <tr>
                     <td>PHONE</td>
                     <td><input type="text" name="phone"><font size="2">('-'없이
                           입력해주세요)</font></td>
                  </tr>
                  <tr>
                     <td>ADDRESS</td>
                     <td><input type="text" name="address"></td>
                  </tr>
                  <tr>
                     <td>EMAIL</td>
                     <td><input type="text" name="email"></td>
                  </tr>
                  <tr>
                     <td>MY PHOTO</td>
                     <td><input type="file" name="file"></td>
                  </tr>
                  <tr>
                     <td><p></p></td>
                  </tr>

               </table>

               <center>
               
                  <hr size="2" color="gray" width="700" />
                  
                  <br>
                  <button onClick="register(this.form)"
                     STYLE='background-color: transparent; color: transparent; border: 0; cursor: hand'>
                     <img src="image/registerButton.jpg">
                  </BUTTON>


                  <button onClick="cancel(this.form)"
                     STYLE='background-color: transparent; color: transparent; border: 0; cursor: hand'>
                     <img src="image/cancelButton.jpg">
                  </button>
                  
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               </center>
            </form>


         </td>
      </tr>

      <tr height="5">
         <td><jsp:include page="register_bottom.jsp" /></td>
      </tr>
   </table>

</body>
</html>