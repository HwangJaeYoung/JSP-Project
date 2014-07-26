<%@page import="java.util.ArrayList, frame.vo.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%
   ArrayList<PartyVO> partyFalseList = (ArrayList<PartyVO>) session
         .getAttribute("partyFalseList");
   ArrayList<SGroupVO> groupList = (ArrayList<SGroupVO>) request
         .getAttribute("groupList");
   ArrayList<CustomerVO> leaderList = (ArrayList<CustomerVO>) request
         .getAttribute("leaderList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style type="text/css">
body {
   width: 700px;
   height: 400px;
}

a:LINK {
   color: black;
   text-decoration: none;
}

a:VISITED {
   color: black;
   text-decoration: none;
}

a:ACTIVE {
   color: black;
   text-decoration: none;
}

a:HOVER {
   color: black;
   text-decoration: none;
}
</style>
<title>Talk끼리</title>
</head>
<body>
   <form action="">
      <table bordercolor="#7BC145" border="4">

         <tr>
            <td bgcolor="#7BC145" width="350px"><center>
                  <font color="white" face="돋움"><b>내용</b></font>
               </center></td>
            <td bgcolor="#7BC145" width="50px"><center><font color="white" face="돋움"><b>참여</b></font></center></td>
            <td bgcolor="#7BC145" width="50px"><center><font color="white" face="돋움"><b>거절</b></font></center></td>
         </tr>
               <% int count=0; %>
       
         <%
            for (int i = 0; i < partyFalseList.size(); i++) {
               PartyVO partyTemp = partyFalseList.get(i);
               SGroupVO groupTemp = groupList.get(i);
               CustomerVO customerTemp = leaderList.get(i);
         %>
         <tr>
            <td><center><font face="굴림" size="2"><b><%=customerTemp.getName()%>님께서 귀하를 <%=groupTemp.getgName()%>으로
               초대합니다.</b></font></center></td>
            <td><center><a
               href="control?cmd=partyjoin&gno=<%=groupTemp.getgNo()%>"><b><font size="2">○</font></b></a></center></td>
            <td><center><a
               href="control?cmd=partydeny&gno=<%=groupTemp.getgNo()%>"><b>x</b></a></center></td>
         </tr>
         <%
            count++;}
         %>
         <%if(count==0){ %>
                  <tr>
            <td colspan="3" height="19">
            </td>
         </tr>
         <%}%>
      </table>
   </form>
</body>
</html>