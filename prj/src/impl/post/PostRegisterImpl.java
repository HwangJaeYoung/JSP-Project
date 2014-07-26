package impl.post;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.vo.CustomerVO;
import frame.vo.PostVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import frame.biz.Biz;
import frame.biz.PostBiz;


@WebServlet({ "/PostRegisterImpl", "/postregisterimpl" })
public class PostRegisterImpl extends HttpServlet
{
   private static final long serialVersionUID = 1L;
   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
      String path = "C://jsp//prj//web//image//post"; // 저장할 폴더
      
      String upload = path;
      int size = 500 * 1024 * 1024; // 500 megabytes, 업로드 할 파일의 크기이다.

      MultipartRequest multi = null;
      multi = new MultipartRequest(request, upload, size, "euc-kr", new DefaultFileRenamePolicy());

      String contents = multi.getParameter("longtext"); // contents의 내용

      Enumeration e = multi.getFileNames();
      String str = (String) e.nextElement();
      String secret  = multi.getParameter("secret");
      String fileName = multi.getFilesystemName(str);
      
      // 유저의 아이디와 그룹의 번호를 가져온다
      HttpSession session = request.getSession();
      CustomerVO user = (CustomerVO) session.getAttribute("user");
      int gno = Integer.parseInt(multi.getParameter("gno"));
      
      Biz biz = new PostBiz();
      PostVO temp = new PostVO(user.getId(), gno, contents, fileName, 0, secret, "F");
      
      biz.register(temp);
     
      RequestDispatcher rd = request.getRequestDispatcher("control?cmd=postview&gno=" + gno +"&secret=" + secret);
      
      rd.forward(request, response);
   }
}