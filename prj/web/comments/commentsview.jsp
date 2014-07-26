<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="frame.vo.*, frame.biz.*, java.util.*"%>

<%
	String secret = (String) request.getAttribute("secret");//secret�� ������ ���� ������ ��� ������
	String left = (String) request.getAttribute("left");

	PostVO post = (PostVO)request.getAttribute("post");// �ϳ��� �� ������ �޾� �´�.
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
		alert("�ٸ� ����ڴ� ���� �� �� �����ϴ�.");	
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
	  var comment = prompt("������ ����� �Է��ϼ���.", "");
	  
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
    var msgList = name.value;  // ���ڰ� �ԷµǴ� �̺�Ʈ�� �߻��Ǵ� value ��
    var msgListLength = msgList.length;  // �Է� �Ǵ� ������ ��ü ����
    
    var limitSize = msgMax;  // ���� �� ���� ����
    var charOne = "";  // �� ���ھ� �˻��ϱ� ���� ����
    var charByte = 0;  // �Է� �ǰ� �ִ� ������ ����(����Ʈ) �� ������ ����
    var msgLen = 0;  // �Է� ������ ��� subString �� ����
    var msgLast = "";  // ���ڼ��� �ʰ� �� ���, ���� �� ������ ���� ���� �����ش�
    
    for(var i = 0; i < msgListLength; i++) {  // �Է� �ǰ� �ִ� ������ ��ü���̸� ������ ����.
     charOne = msgList.charAt(i);  // �ѱ��ھ� ����
    
     // �ѱ��� 2byte �̹Ƿ�, �ѱ��� �Է� �ǰ� ���� ��� 2�� ���Ѵ�.
     if(escape(charOne).length > 4) {
      charByte += 2;
     } else {
      charByte++;  // �� �ܿ� ��쿡�� 1byte. 1�� ���Ѵ�.
     }
     
     if(charByte <= limitSize) {  // ��ü ũ�Ⱑ ���� ���� ���̸� ���� �ʴ´ٸ�..
      msgLen = i + 1;
     }
    }
    
    if(charByte > limitSize) {
     alert(limitSize + " ���ڸ� �ʰ� �Է� �� �� �����ϴ�.");
     msgLast = msgList.substr(0, msgLen);
     name.value = msgLast;
     charByte = limitSize;
    }
    
    document.getElementById('write').value = charByte;
   }
</script>

<link rel=stylesheet type="text/css" href="post/talkcss.css">

<title>Talk����</title>
</head>
<body>


<br>

   <%
   PostVO postTemp = post;
   CustomerVO customerTemp = customer;
      
   if(secret.equals("F")) // ������� ����Ʈ�� �Ϲݱ� �� ��.
   {   
      String tempStr;
      int position; // "."�� �ִ� ��ġ�� ã�´�.
      char[] tempChar; 
      String result = null; //Ȯ���ڸ� �̾Ƽ� �����ϱ� ���� ����
      
      if(postTemp.getFile() != null) {
         tempStr = postTemp.getFile();
         position = tempStr.lastIndexOf("."); // "."�� �ִ� ��ġ�� ã�´�.
         tempChar = tempStr.toCharArray(); 
         result = ""; //Ȯ���ڸ� �̾Ƽ� �����ϱ� ���� ����
         
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
                        <!-- ����, ���� -->

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

else if(secret.equals("T")) // ��б� Ÿ�Ӷ��� �� ��.
{

   String tempStr;
   int position; // "."�� �ִ� ��ġ�� ã�´�.
   char[] tempChar; 
   String result = null; //Ȯ���ڸ� �̾Ƽ� �����ϱ� ���� ����
   
   if(postTemp.getFile() != null) {
      tempStr = postTemp.getFile();
      position = tempStr.lastIndexOf("."); // "."�� �ִ� ��ġ�� ã�´�.
      tempChar = tempStr.toCharArray(); 
      result = ""; //Ȯ���ڸ� �̾Ƽ� �����ϱ� ���� ����
      
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
                        <!-- ����, ���� -->

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