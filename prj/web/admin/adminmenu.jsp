<%@page import="frame.vo.CustomerVO"%>
<%@page import="frame.vo.SGroupVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
#menu{
width:200px;
height:795px;
position: relative;
top:0px;
left:0px;}

#menu2{
position: absolute;
top:20px;
}

#groupchange1{
position: absolute;
top:120px;
left:20px;
}

#groupchange2{
position: absolute;
top:150px;
left:20px;
}

#free{
position: absolute;
top:140px;
left:20px;
}

#secret{
position: absolute;
top:170px;
left:20px;
}

a { text-decoration:none ;
font-family: 'Nanum Pen Script', cursive;

   
   }
   
a:LINK {color: black; text-decoration: none;}
a:VISITED {color: black; text-decoration: none;}
a:ACTIVE {color: black; text-decoration: none;}
a:HOVER {color: black; text-decoration: none;}
   
font {
font-family: 'Nanum Pen Script', cursive;
font-size:large;
color: gray;
font-weight: 100;

}
</style>
<title>Insert title here</title>
</head>
<body>
   <div id="menu">
   <form enctype="multipart/form-data">
   <div id = "menu2"><img src="image/menu1.jpg"></div>      
      <div id="free">
         <a href="control?cmd=admincustomer"><img src="image/icon/change2.png" width="15" height="15">&nbsp;<font> 회원관리 </font></a>
               <br><img src="image/menubar.jpg" width="130" height="2">
      </div>
      <div id="secret">
         <a href="control?cmd=admingroup"><img src="image/icon/change2.png" width="15" height="15">&nbsp;<font> 그룹관리 </font></a>
               <br><img src="image/menubar.jpg" width="130" height="2">
      </div>
     </form>
     </div>
</body>
</html>