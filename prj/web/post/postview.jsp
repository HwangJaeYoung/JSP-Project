<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR" import="frame.vo.*, frame.biz.*, java.util.*"%>

<%
   String secret = (String) request.getAttribute("secret");
   String left = (String) request.getAttribute("left");

   ArrayList<PostVO> post = (ArrayList<PostVO>) request.getAttribute("postlist");
   ArrayList<CustomerVO> customer = (ArrayList<CustomerVO>) request.getAttribute("customerlist");

   SGroupVO group = (SGroupVO) request.getAttribute("group");
   CustomerVO user = (CustomerVO) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<script type="text/javascript">
   function deleteData(f) {
      var cid = f.cid.value;
      var id = f.id.value;

      if (cid != id) {
         alert("다른 사용자는 삭제 할 수 없습니다.");
      }

      else {
         f.cmd.value = "postdelete";
         f.submit();
      }
   }

   function updateData(f) {
      var cid = f.cid.value;
      var id = f.id.value;

      if (cid != id) {
         alert("다른 사용자는 수정 할 수 없습니다.");
      }

      else {
         f.cmd.value = "postupdate";
         f.submit();
      }
   }

   function postregister(f) {
      var secret = f.secret.value;

      if (secret == "") {
         alert("게시판을 선택해 주세요.");
      } else {
         f.method = "POST";
         f.action = "postregisterimpl";
         f.submit();
      }

   }

   function comment(f) {
      f.cmd.value = "commentsview";
      f.method = "POST";
      f.submit();
   }
</script>

<link rel=stylesheet type="text/css" href="post/talkcss.css">

<style type="text/css">
body {

   overflow: hidden;
} /* 스크롤바 숨김 */
</style>
<title>Talk끼리</title>
</head>
<body>

   <form enctype="multipart/form-data">
      <table id="post_reg_table_head">
         <tr>
            <td width="700"><textarea cols="72" rows="7"
                  style="overflow-y: scroll; width: 700px;" name="longtext">내용을 입력하세요.</textarea></td>
         </tr>

         <tr>
            <td><input type="file" name="video"></td>
         </tr>

         <tr>
            <td><input type="button" value="게시"
               onclick="postregister(this.form)"> <input type="hidden"
               name="secret" value="<%=secret%>"> <input type="hidden"
               name="gno" value="<%=group.getgNo()%>"></td>
         </tr>
      </table>
   </form>



   <%
      for (int i = 0; i < post.size(); i++) {
         PostVO postTemp = post.get(i);
         CustomerVO customerTemp = customer.get(i);

         if (secret == null)// 최초에 그룹으로 진입했을 경우를 생각
         {
            secret = "F";

         }

         if (secret.equals("F") && postTemp.getSecret().equals("F")) // 가지고온 포스트가 일반글 일 때.
         {
            String tempStr;
            int position; // "."이 있는 위치를 찾는다.
            char[] tempChar;
            String result = null; //확장자를 뽑아서 저장하기 위한 변수

            if (postTemp.getFile() != null) {
               tempStr = postTemp.getFile();
               position = tempStr.lastIndexOf("."); // "."이 있는 위치를 찾는다.
               tempChar = tempStr.toCharArray();
               result = ""; //확장자를 뽑아서 저장하기 위한 변수

               for (int j = position; j < position + 4; j++) {
                  result += tempChar[j];
               }
            }
   %>

   <br>
   <form action="control" method="GET">

      <table>
         <tr>
            <td>
               <table id="post_table_head_border">
                  <tr>
                     <td>
                        <table >
                           <tr>
                              <td rowspan="2"><img border="1" 
                                 src="image/cimg/<%=customerTemp.getFile()%>" width="60"
                                 height="75"></td>
                              <td width="700"><div id="x"><table><tr><td colspan="6" align="right" width="700">
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
                     <td colspan="6"><center><img  width="400"
                        src="image/post/<%=postTemp.getFile()%>"></center></td>
                     <%
                        }
                     %>
                  </tr>

                  <tr>
                     
                     <td colspan=5 align=right>
                        <!-- 수정, 삭제 -->
                     
                        <table>
                           <tr>
                           <td align="left" width="440"><font>view <%=postTemp.getLikeCount()%></font></td>
                              <%
                                 if (user.getId().equals(postTemp.getId())) {
                              %>
                              
                              <td><button onclick="updateData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/modifywrite.jpg"></BUTTON> 
                              <input type="hidden" name="id" value="<%=user.getId()%>"></td>
                              <%
                                 }
                              %>
                              <td align="right"><button onclick="comment(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/coment.jpg"></BUTTON> 
                              <input type="hidden" name="secret" value="<%=secret%>"> 
                              <input type="hidden" name="gno" value="<%=postTemp.getgNo()%>">
                              <input type="hidden" name="uno"   value="<%=postTemp.getuNo()%>"> 
                              <input type="hidden" name="like" value="<%=postTemp.getLikeCount()%>">
                              <input type="hidden" name="cid" value="<%=postTemp.getId()%>">
                              <input type="hidden" name="cmd"></td>
                           </tr>
                        </table>

                     </td>
                  </tr>
               </table>
            </td>
         </tr>
      </table>
   </form>

   <%
      }

         else if (secret.equals("T") && postTemp.getSecret().equals("T")) // 비밀글 타임라인 일 때.
         {
            String tempStr;
            int position; // "."이 있는 위치를 찾는다.
            char[] tempChar;
            String result = null; //확장자를 뽑아서 저장하기 위한 변수

            if (postTemp.getFile() != null) {
               tempStr = postTemp.getFile();
               position = tempStr.lastIndexOf("."); // "."이 있는 위치를 찾는다.
               tempChar = tempStr.toCharArray();
               result = ""; //확장자를 뽑아서 저장하기 위한 변수

               for (int j = position; j < position + 4; j++) {
                  result += tempChar[j];
               }
            }
   %>

   <br>
   <form action="control" method="GET">
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
                     <td colspan="6"><img  width="400"
                        src="image/post/<%=postTemp.getFile()%>"></td>
                     <%
                        }
                     %>
                  </tr>

                  <tr>
                     <td colspan=6 align=right>
                        <!-- 수정, 삭제 -->

                        <table>
                        <tr><td align="left" width="600"><font>view <%=postTemp.getLikeCount()%></font></td>
                     <%
                        if (user.getId().equals(postTemp.getId())) {
                     %>
                     <td><button onclick="updateData(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/modifywrite.jpg"></BUTTON> 
                     <input type="hidden" name="id" value="<%=user.getId()%>"></td>
                     <%
                        }
                     %>
                     <td align="right"><button onclick="comment(this.form)" STYLE='background-color:transparent; color:transparent; border:0; cursor:hand'><img src="image/icon/coment.jpg"></BUTTON> 
                     <input type="hidden" name="secret" value="<%=secret%>"> 
                     <input type="hidden" name="gno" value="<%=postTemp.getgNo()%>">
                     <input type="hidden" name="uno" value="<%=postTemp.getuNo()%>">
                     <input type="hidden" name="like" value="<%=postTemp.getLikeCount()%>">
                     <input type="hidden" name="cid" value="<%=postTemp.getId()%>">
                     <input type="hidden" name="cmd"></td>
                  </tr>
                        </table>

                     </td>
                  </tr>
               </table>
            </td>
         </tr>
      </table>
</form>

   <%
      }
      }
   %>

</body>
</html>