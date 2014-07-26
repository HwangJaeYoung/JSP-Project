<%@page import="frame.vo.PartyVO"%>
<%@page import="frame.vo.SGroupVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%
   ArrayList<SGroupVO> sGroupList = (ArrayList<SGroupVO>) request
         .getAttribute("sGroupList");
   ArrayList<PartyVO> list = (ArrayList<PartyVO>) request
         .getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>

<style>
   
a:LINK {color: black; text-decoration: none;}
a:VISITED {color: black; text-decoration: none;}
a:ACTIVE {color: black; text-decoration: none;}
a:HOVER {color: black; text-decoration: none;}
   
td  {padding:15px; text-align:center; font-size:12px;}

<
style type ="text /css">font {
   font-family: 'Nanum Pen Script', cursive;
   font-size: x-large; 

}

#viewContainer {
position:absolute;
   margin: 30px auto;
   left:30px;
   
}

table {
   padding: 10px;
}

#groimg {
   -webkit-box-shadow: 5px 5px 5px 5px #C9C9C9;
   box-shadow: 5px 5px 5px 5px #C9C9C9;
}

#number{
position: absolute;
top: 720px;
left:500px;
}

body {
   align: center;
}
</style>
<title>Insert title here</title>
</head>
<body>
      <div id="viewContainer">
   <table>
      <% int count=0; %>
      <%  
       for (SGroupVO GroupTemp : sGroupList) { %>
      <%  if(GroupTemp.getGroimg() == null)
            GroupTemp.setGroimg("no_image.jpg");
      
         if(count == 0)
    {%>
      <tr>
         <% }
 
    if(count < 4)
  { %>
         <td><a href="control?cmd=postview&gno=<%=GroupTemp.getgNo()%>&secret=F">
               <img src="image/gimg/<%=GroupTemp.getGroimg()%>" id="groimg" width="150"
               height="200" class="p_img<%=GroupTemp.getgNo()%>"/> <br><font><%=GroupTemp.getgName()%></font>
         </a></td>
         <% count++;
          } else {%>
         <td><a href="control?cmd=postview&gno=<%=GroupTemp.getgNo()%>&secret=F">
               <img src="image/gimg/<%=GroupTemp.getGroimg()%>" id="groimg" width="150"
               height="200" class="p_img<%=GroupTemp.getgNo()%>"/> <br><font><%=GroupTemp.getgName()%></font>
         </a></td>
      
      <% count = 0;       
         } %>
<script type="text/javascript">

$(".p_img<%=GroupTemp.getgNo()%>").hover(

function () {

$(".p_img<%=GroupTemp.getgNo()%>").animate({

width: "175px", //커질크기
height: "233px"

}, 300 ); //속도(slow, fast, 숫자...)

},

function () {

$(".p_img<%=GroupTemp.getgNo()%>").animate({

   width:"150px",
      height:"200px"

}, 300 ); //속도(slow, fast, 숫자...)

}

)
</script>
      
      <%} %>
   <% if(count==0){ %>
   </tr><tr>   <td><a href="control?cmd=groupregister"><img src="image/GroupMake.jpg" id="groimg"
            width="150" height="200" class="c_img2" /><br><font> 그룹 생성하기</font></a><td>
   
   <%}else{ %>
   <td><a href="control?cmd=groupregister"><img src="image/GroupMake.jpg" id="groimg"
            width="150" height="200" class="c_img2" /><br><font> 그룹 생성하기</font></a></td></tr>
   
   <%} %>
   </table>
      </div>
   <script type="text/javascript">
      $(".c_img2").hover(
         function () {
            $(".c_img2").animate({
            
            width: "175px", //커질크기
            height: "233px"
            
            }, 200 ); //속도(slow, fast, 숫자...)   
         },
         
         function () {
            $(".c_img2").animate({
            
               width:"150px",
                height:"200px"
            
            }, 300 ); //속도(slow, fast, 숫자...)
         }
      )
   </script>
   
   <div id="number">
   <%for(int i = 0; (i * 9) < list.size(); i++) { %>
      <a href="control?pagenum=<%=i+1 %>"><%=i+1 %></a>
   <%} %>
   </div>
</body>
</html>