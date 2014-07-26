<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="frame.vo.*, frame.biz.*, java.util.*"%>

<%
	String secret = (String) request.getAttribute("secret");//secret의 정보에 따라 나뉘어 줘야 함으로
	String left = (String) request.getAttribute("left");

	PostVO post = (PostVO)request.getAttribute("post");// 하나의 글 정보만 받아 온다.
	CustomerVO customer = (CustomerVO) request.getAttribute("customer");
	ArrayList<CommentsVO> comment = (ArrayList<CommentsVO>) request.getAttribute("commentlist");
	
	ArrayList<CustomerVO> commentCustomerList = (ArrayList<CustomerVO>) request.getAttribute("commentCustomerList");
	
	SGroupVO group = (SGroupVO) request.getAttribute("group");
	CustomerVO user = (CustomerVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<script type="text/javascript">
function deleteData(f)
{
	var cid = f.cid.value;
	var id = f.id.value;

	if(cid != id)
	{
		alert("다른 사용자는 수정 할 수 없습니다.");	
	}
	else
	{
		f.cmd.value = "postdelete";
		f.submit( );
	}
}

function register(f)
{
      f.method="POST";
      f.action="control?cmd=comregister";
      f.submit();
}

function update(f)
{
	  var comment = prompt("수정할 댓글을 입력하세요.", "");
	  
	  if(comment != null && comment.length != 0) {
		  f.content.value = comment;
	  }
	  
	  f.cmd.value = "comupdateimpl";
	  f.method="POST";
	  f.action="control";
	  f.submit();
}

function deleted(f)
{
      f.method="POST";
      f.action="control?cmd=comdelete";
      f.submit();
}

function comment(f)
{
     f.method="POST";
     f.action="control?cmd=postview";
     f.submit();
}

function chkByte(name, msgMax) {
    var msgList = name.value;  // 글자가 입력되는 이벤트가 발생되는 value 값
    var msgListLength = msgList.length;  // 입력 되는 글자의 전체 길이
    
    var limitSize = msgMax;  // 제한 할 글자 길이
    var charOne = "";  // 한 글자씩 검사하기 위한 변수
    var charByte = 0;  // 입력 되고 있는 문자의 길이(바이트) 를 저장할 변수
    var msgLen = 0;  // 입력 내용을 담아 subString 할 변수
    var msgLast = "";  // 글자수가 초과 할 경우, 제한 할 마지막 글자 까지 보여준다
    
    for(var i = 0; i < msgListLength; i++) {  // 입력 되고 있는 글자의 전체길이를 루프를 돈다.
     charOne = msgList.charAt(i);  // 한글자씩 읽음
    
     // 한글은 2byte 이므로, 한글이 입력 되고 있을 경우 2를 더한다.
     if(escape(charOne).length > 4) {
      charByte += 2;
     } else {
      charByte++;  // 그 외에 경우에는 1byte. 1을 더한다.
     }
     
     if(charByte <= limitSize) {  // 전체 크기가 제한 글자 길이를 넘지 않는다면..
      msgLen = i + 1;
     }
    }
    
    if(charByte > limitSize) {
     alert(limitSize + " 글자를 초과 입력 할 수 없습니다.");
     msgLast = msgList.substr(0, msgLen);
     name.value = msgLast;
     charByte = limitSize;
    }
    
    document.getElementById('write').value = charByte;
   }
</script>

<link rel=stylesheet type="text/css" href="post/talkcss.css">

<title>Talk끼리</title>
</head>
<body>


<br>

   <%
   PostVO postTemp = post;
   CustomerVO customerTemp = customer;
      
   if(secret.equals("F")) // 가지고온 포스트가 일반글 일 때.
   {   
      String tempStr;
      int position; // "."이 있는 위치를 찾는다.
      char[] tempChar; 
      String result = null; //확장자를 뽑아서 저장하기 위한 변수
      
      if(postTemp.getFile() != null) {
         tempStr = postTemp.getFile();
         position = tempStr.lastIndexOf("."); // "."이 있는 위치를 찾는다.
         tempChar = tempStr.toCharArray(); 
         result = ""; //확장자를 뽑아서 저장하기 위한 변수
         
         for(int j = position; j < position + 4; j++) {
            result += tempChar[0];
         }
      }
%>


   <form action="control" method="get">

      <table>
         <tr>
            <td>
               <table id="post_table_head_border">
                  <tr>
                     <td>
                        <table>
                           <tr>
                              <td rowspan="2"><img border="1" 
                                 src="image/cimg/<%=customerTemp.getFile()%>" width="60"
                                 height="75"></td>
                              <td><div id="x"><table><tr><td align="right" width="600">
                        <button onclick="deleteData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/x.png" width="22" height="22"></BUTTON>
                        </td></tr></table></div><font size="1"><b><%=customerTemp.getName()%></b></font>
                              
                              </td>

                           </tr>
                           <tr>
                              <td><font><%=postTemp.getUploadDate()%></font></td>
                           </tr>
                        </table>
                     </td>
                  </tr>
                  <tr><td><table id="post_table_content"><tr>
                     <td colspan="6" width="500" height="80"><%=postTemp.getContent()%></td>
                     </tr></table>
                  </tr>
                  <tr >
                     <%
                        if (result != null && result.equals(".mp4")) {
                     %>
                     <td colspan="6"><video controls width="500" height="300"
                           src="image/post/<%=postTemp.getFile()%>"></video></td>
                     <%
                        } else if (result != null) {
                     %>
                     <td colspan="6"><center><img width="400"
                        src="image/post/<%=postTemp.getFile()%>"></center></td>
                     <%
                        }
                     %>
                  </tr>

                  <tr>
                     <td colspan=6 align=right>
                        <!-- 수정, 삭제 -->

                        <table>
                           <tr><td align="left" width="700"><font>view <%=postTemp.getLikeCount()%></font></td>

                              <td align="right"><button onclick="comment(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/comentx.jpg"></BUTTON> <input type="hidden"
                                 name="secret" value="<%=secret%>"> <input
                                 type="hidden" name="gno" value="<%=postTemp.getgNo()%>">
                                 <input type="hidden" name="uno"
                                 value="<%=postTemp.getuNo()%>"> <input type="hidden"
                                 name="like" value="<%=postTemp.getLikeCount()%>">
                                  <input
                                 type="hidden" name="cid" value="<%=postTemp.getId()%>">
                                 <input type="hidden" name="cmd"></td>
                           </tr>
                        </table>

                     </td>
                  </tr>
         <tr><td>  
         
         
         <form>
      <table   width="650">
         <tr> 
         <form>
               <input type="hidden" name="uno" value="<%= postTemp.getuNo()%>">
                <input type="hidden" name="gno" value="<%= postTemp.getgNo()%>">
                <input type = "hidden" name = "pno" value = "<%=postTemp.getuNo()%>">
                <input type = "hidden" name = "id" value = "<%=user.getId()%>">
                <input type = "hidden" name = "cid" value = "<%=postTemp.getId()%>">
                <input type ="hidden" name="secret" value="<%=secret%>">
                <input type = "hidden" name = "like" value = "<%=postTemp.getLikeCount()%>">                
                <td><textarea onkeyup="chkByte(this, 50)" cols="80" rows="1" style="overflow-y:scroll" name="content" >  </textarea></td> 
               <td colspan="2" align="center"> 
               <button onclick="register(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/register4.png" width="25" height="22"></BUTTON>

               </td>
             </form>
             </tr>
          </table>
          <table >
              <%

              if(comment != null)
              {
                 for(int j = 0 ; j < comment.size(); j++) 
                {
                 CommentsVO commentTemp = comment.get(j);
                 CustomerVO commentCustomerTemp = commentCustomerList.get(j);
                
                 if(postTemp.getuNo()==commentTemp.getuNo()){
                   
                    
              %>
          
            <tr>
            <td align="center"><img src="image/cimg/<%=commentCustomerTemp.getFile() %>" width="30" height="30"></td>
              <td align="center"><font id="f"><b><%=commentCustomerTemp.getName() %></b></font></td>
              <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=commentTemp.getContent() %></td>
              
              <%
           if(user.getId().equals(commentTemp.getId())){
              %>
              <form>
              <input type="hidden" name="content">
              <input type="hidden" name="date" value="<%=commentTemp.getRegdate() %>" readonly="true">
              <input type ="hidden" name="secret" value="<%=secret%>">
              <input type="hidden" name="id" value="<%= postTemp.getId()%>">
              <input type="hidden" name="gno" value="<%= postTemp.getgNo()%>">
              <input type="hidden" name="uno" value="<%= postTemp.getuNo()%>">
              <input type = "hidden" name = "like" value = "<%=postTemp.getLikeCount()%>">
              <input type = "hidden" name = "cno" value ="<%=commentTemp.getcNo()%>">
              <input type = "hidden" name = "cid" value = "<%=postTemp.getId()%>">
              <input type = "hidden" name = "cmd">
              <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <button onclick="update(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/up.png" width="15" height="15"></BUTTON>
              </td>
              <td>
               <button onclick="deleted(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/comentdelete.png" width="15" height="15"></BUTTON>
              </td>  
              </form>          
          
           </tr> 
          <%
                 }
                }
              }
              }
              %>
          </table>
    </form>
    </td></tr>
               </table>
            </td>
         </tr>
         
      </table>

   </form>


   <br>
   
 
    <br>
    
   <%
       
      }      

else if(secret.equals("T")) // 비밀글 타임라인 일 때.
{

   String tempStr;
   int position; // "."이 있는 위치를 찾는다.
   char[] tempChar; 
   String result = null; //확장자를 뽑아서 저장하기 위한 변수
   
   if(postTemp.getFile() != null) {
      tempStr = postTemp.getFile();
      position = tempStr.lastIndexOf("."); // "."이 있는 위치를 찾는다.
      tempChar = tempStr.toCharArray(); 
      result = ""; //확장자를 뽑아서 저장하기 위한 변수
      
      for(int j = position; j < position + 4; j++) {
         result += tempChar[0];
      }
   }
%>

   <form action="control" method="get">

      <table>
         <tr>
            <td>
               <table id="post_table_head_border">
                  <tr>
                     <td>
                         <table>
                           <tr>
                              <td rowspan="2"><img 
                                 src="image/gimg/no_image.jpg" width="60"
                                 height="75"></td>
                              <td><div id="x"><table><tr><td align="right" width="600">
                        <button onclick="deleteData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/x.png" width="22" height="22"></BUTTON>
                        </td></tr></table></div><font size="1">no name</font>
                              
                              </td>

                           </tr>
                           <tr>
                              <td><font><%=postTemp.getUploadDate()%></font></td>
                           </tr>
                        </table>
                     </td>
                  </tr>
                  <tr><td><table id="post_table_content"><tr>
                     <td colspan="6" width="500" height="80"><%=postTemp.getContent()%></td>
                     </tr></table>
                     </td>
                  </tr>
                  <tr >
                     <%
                        if (result != null && result.equals(".mp4")) {
                     %>
                     <td colspan="6"><video controls width="500" height="300"
                           src="image/post/<%=postTemp.getFile()%>"></video></td>
                     <%
                        } else if (result != null) {
                     %>
                     <td colspan="6"><center><img width="400"
                        src="image/post/<%=postTemp.getFile()%>"></center></td>
                     <%
                        }
                     %>
                  </tr>

                  <tr>
                     <td colspan=6 align=right>
                        <!-- 수정, 삭제 -->

                        <table>
                           <tr>
<td align="left" width="700"><font>view <%=postTemp.getLikeCount()%></font></td>
                              <td align="right"><button onclick="comment(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/comentx.jpg"></BUTTON> <input type="hidden"
                                 name="secret" value="<%=secret%>"> <input
                                 type="hidden" name="gno" value="<%=postTemp.getgNo()%>">
                                 <input type="hidden" name="uno"
                                 value="<%=postTemp.getuNo()%>"> <input type="hidden"
                                 name="like" value="<%=postTemp.getLikeCount()%>">
                                  <input
                                 type="hidden" name="cid" value="<%=postTemp.getId()%>">
                                 <input type="hidden" name="cmd"></td>
                           </tr>
                        </table>

                     </td>
                  </tr>
         <tr><td>  
         
         
         <form>
      <table   width="650">
         <tr> 
         <form>
               <input type="hidden" name="uno" value="<%= postTemp.getuNo()%>">
                <input type="hidden" name="gno" value="<%= postTemp.getgNo()%>">
                <input type = "hidden" name = "pno" value = "<%=postTemp.getuNo()%>">
                <input type = "hidden" name = "id" value = "<%=user.getId()%>">
                <input type = "hidden" name = "cid" value = "<%=postTemp.getId()%>">
                <input type ="hidden" name="secret" value="<%=secret%>">
                <input type = "hidden" name = "like" value = "<%=postTemp.getLikeCount()%>">                
                <td><textarea onkeyup="chkByte(this, 50)" cols="80" rows="1" style="overflow-y:scroll" name="content" >  </textarea></td> 
               <td colspan="2" align="center"> 
               <button onclick="register(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/register4.png" width="25" height="22"></BUTTON>

               </td>
             </form>
             </tr>
          </table>
          <table >
              <%

              if(comment != null)
              {
                 for(int j = 0 ; j < comment.size(); j++) 
                {
                 CommentsVO commentTemp = comment.get(j);
                 CustomerVO commentCustomerTemp = commentCustomerList.get(j);
                
                 if(postTemp.getuNo()==commentTemp.getuNo()){
                   
                    
              %>
          
            <tr>
            <td align="center"><img 
                                 src="image/gimg/no_image.jpg" width="30"
                                 height="30"></td>
              
              <td colspan="4">&nbsp;&nbsp;&nbsp;<%=commentTemp.getContent() %></td>
              
              <%
           if(user.getId().equals(commentTemp.getId())){
              %>
              <form>
              <input type="hidden" name="content">
              <input type="hidden" name="date" value="<%=commentTemp.getRegdate() %>" readonly="true">
              <input type ="hidden" name="secret" value="<%=secret%>">
              <input type="hidden" name="id" value="<%= postTemp.getId()%>">
              <input type="hidden" name="gno" value="<%= postTemp.getgNo()%>">
              <input type="hidden" name="uno" value="<%= postTemp.getuNo()%>">
              <input type = "hidden" name = "like" value = "<%=postTemp.getLikeCount()%>">
              <input type = "hidden" name = "cno" value ="<%=commentTemp.getcNo()%>">
              <input type = "hidden" name = "cid" value = "<%=postTemp.getId()%>">
              <input type = "hidden" name = "cmd">
              <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <button onclick="update(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/up.png" width="15" height="15"></BUTTON>
              </td>
              <td>
               <button onclick="deleted(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/comentdelete.png" width="15" height="15"></BUTTON>
              </td>  
              </form>          
          
           </tr> 
          <%
                 }
                }
              }
              }
              %>
          </table>
    </form>
    </td></tr>
               </table>
            </td>
         </tr>
         
      </table>

   </form>

<br>

<%

}    
%>

</body>
</html>