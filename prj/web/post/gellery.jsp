<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
	import = "java.util.*, frame.vo.*, frame.biz.*" %>
	
<% 
	SGroupVO group = (SGroupVO) request.getAttribute("group");
	ArrayList<PostVO> postImage = (ArrayList<PostVO>) request.getAttribute("postImage");
	ArrayList<PostVO> postList = (ArrayList<PostVO>) request.getAttribute("postList");
	ArrayList<PostVO> list = new ArrayList<PostVO>();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style>
#viewContainer {
position:absolute;
   top:400px;
   left:100px;
   
}
</style>

<script>
function chn(root) {
             mainimage.src = root;
            }
</script>
<style>
#viewContainer {
position:absolute;
   top:150px;
   left:10px;
   
}

#pagenumber {
position:absolute;
   top:500px;
   left:100px;
   
}

#viewimage {
position:absolute;
   top:150px;
   left:300px;
   
}

#line {
position:absolute;
   top:100px;
   left:20px;
   
}

#write{

position:absolute;
   top:80px;
   left:20px;
   
}


#line2 {
position:absolute;
   top:600px;
   left:20px;
   
}
</style>

<title>Insert title here</title>
</head>

   <% 
   if(postImage != null) {
   String result = null; 
   
   for(Object obj : postImage)
   {
      PostVO pv = (PostVO)obj;
         
      String tempStr = pv.getFile(); // 파일명을 가지고 온다.
      
      if(tempStr != null)
      {
         int position; // "."이 있는 위치를 찾는다.
         char[] tempChar; 
         position = tempStr.lastIndexOf("."); // "."이 있는 위치를 찾는다.
         tempChar = tempStr.toCharArray(); 
         result = "";
      
         for(int i = position; i < position + 4; i++)
            result += tempChar[i];
      
         if(!(result.equals("mp4")))
            list.add(pv);
      }
   }
   %>
   
   
<body>
<div id="write">
<font>●갤러리</font>
</div>
<div id="viewimage">
<img src="image/gimg/no_image.jpg" width="400" height="400" border="1" name="mainimage">
</div>

<div id = "line">
<hr width="700" color="gray">
</div>

<div id = "line2">
<hr width="700" color="gray">
</div>
      <div id="viewContainer">
   <table>
      <% int count=0; %>
               <%
               for(PostVO pv : list)
               { 
                     if(count == 0)
    {%>
      <tr>
         <% }
 
    if(count < 2)
  { %>
               
                  <td onClick="parent.프레임이름.location='image/post/<%=pv.getFile( ) %>'">
                     
                     <img src="image/post/<%=pv.getFile( ) %>" width="70" height="70" border="1" onmouseover="chn('image/post/<%=pv.getFile( ) %>')" style="cursor:hand" >
                     
                     
                  </td>
                           <% count++;
          } else {%>
                            <td>
                     <img src="image/post/<%=pv.getFile( ) %>" width="70" height="70" border="1" onmouseover="chn('image/post/<%=pv.getFile( ) %>')" style="cursor:hand" >
                  </td></tr>
          <% count = 0;    
                }%>
               
   <%}
   }%>
   </table>
   </div>
   <div id="pagenumber">
   <%for(int i = 0; (i * 12) < postList.size(); i++) { %>
      <a href="control?cmd=gellery&gno=<%=group.getgNo() %>&pageNum=<%=i+1 %>"><%=i+1 %></a>
   <%} %>
   
   </div>
</body>
</html>