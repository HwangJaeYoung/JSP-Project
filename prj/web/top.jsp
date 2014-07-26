<%@page import="java.util.ArrayList, frame.vo.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
   ArrayList<PartyVO> partyFalseList = (ArrayList<PartyVO>) session.getAttribute("partyFalseList");
   CustomerVO user = (CustomerVO) session.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style type="text/css">
   #top > #left {
      
      width: 310px;
      height: 100px;
         position: absolute;
        left: 0px;
         top: 0px;
   }
   
   #top > #right {
      background-color: rgb(123,193,69);
      width: 714px;
      height: 100px;
      position: absolute;
        left: 310px;
         top: 0px;
         float: right;
   }
   
   #top {
      width: 1024px;
      height: 100px;
      position: relative;
   }
   
   #top > #right > #setting {
      width: 80px;
      height: 30px;
      margin: 10px;
      top: 0px;
      right: 0px;
      position: absolute;
   }
   
   #top > #right > #info {
      width: 150px;
      height: 80px;
      margin: 10px;
      top: 0px;
      right: 50px;
      position: absolute;
      
   }
   
   #top > #right > #inviteList {
      width: 50px;
      height: 30px;
      margin: 10px;
      top: 50px;
      right: 0px;
      position: absolute;
   }
</style>
<script type="text/javascript">
   function menuSelect(menu, form) {      
      if(menu == "profile") {
         form.cmd.value = "myprofile";
         form.submit();
      }
      
      else if(menu == "logout") {
         form.cmd.value = "login";
         form.submit();
      }
   }
   
   function inviteList() {
      window.open("control?cmd=invitelist", "Talk����", "width=500, height=500");
   }
</script>
<title>Insert title here</title>
</head>
<body>
   <div id="top">
      <div id="left">
      <a href = "control"><img src = image/co_tag.jpg></a>
      </div>
      
      <div id="right">
         <div id="setting">
            <form method="get" action="control">
               <input type="hidden" name="cmd">
               <select onchange="menuSelect(this.options[this.selectedIndex].value, this.form)">
                  <option value="">����</option>
                  <option value="profile">�� ����</option>
                  <option value="logout">�α׾ƿ�</option>
               </select>
            </form>
         </div>
               
         <div id="inviteList">
            <%if(partyFalseList.size() > 0) { %>
               <button onclick="inviteList()" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/main/invite_yes.gif"></button>
            <%} 
              else { %>
                 <img src="image/main/invite_no.gif">
            <%} %>
         </div>
         
         <div id="info">
            <img src="image/cimg/<%=user.getFile() %>" width="65" height="80">
            <a><%=user.getName() %></a>
         </div>
      </div>
   </div>
</body>
</html>