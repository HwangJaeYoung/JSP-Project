package impl.post;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import frame.biz.Biz;
import frame.biz.PostBiz;
import frame.biz.SGroupBiz;
import frame.vo.PostVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class PostUpdateImpl
 */
@WebServlet({ "/PostUpdateImpl", "/postupdateimpl" })
public class PostUpdateImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uno = request.getParameter("uno"); // 해당 포스트 번호 
		String gno = request.getParameter("gno");
	
		PostBiz biz = new PostBiz( );
		PostVO pv = (PostVO)biz.select(uno); //해당 포스트를 가지고 온다.
		
		request.setAttribute("post", pv);
		
		SGroupBiz gBiz = new SGroupBiz();
		SGroupVO group = (SGroupVO) gBiz.select(gno);
		
		request.setAttribute("group", group);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "C://jsp//prj//web//image//post"; // 저장할 폴더

		String upload = path;
		int size = 500 * 1024 * 1024; // 500 megabytes, 업로드 할 파일의 크기이다.

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, upload, size, "euc-kr", new DefaultFileRenamePolicy());

		String contents = multi.getParameter("content");
		String secret = multi.getParameter("secret");
		int uno = Integer.parseInt(multi.getParameter("uno"));
		int gno = Integer.parseInt(multi.getParameter("gno"));
		
		Enumeration e = multi.getFileNames();
		String str = (String) e.nextElement();
		String fileName = multi.getFilesystemName(str);
		
		Biz biz = new PostBiz();
		PostVO temp = (PostVO) biz.select(uno);
		temp.setContent(contents);
		if(fileName != null) {
			temp.setFile(fileName);
		}
		biz.modify(temp);
		
		RequestDispatcher rd = request.getRequestDispatcher("control?cmd=postview&gno=" + gno + "&secret=" + secret);
		
		rd.forward(request, response);
	}

}
