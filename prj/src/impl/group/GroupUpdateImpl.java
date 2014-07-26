package impl.group;

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
import frame.biz.SGroupBiz;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class GroupUpdateImpl
 */
@WebServlet({ "/GroupUpdateImpl", "/groupupdateimpl" })
public class GroupUpdateImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 그룹의 정보를 써야하기 때문에 
		SGroupBiz gBiz = new SGroupBiz();
		SGroupVO group = (SGroupVO) gBiz.select(request.getParameter("gno"));
		request.setAttribute("group", group);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String upload = "c:\\jsp\\prj\\web\\image\\gimg";
		int size = 10 * 1024 * 1024;
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, upload, size, "euc-kr", new DefaultFileRenamePolicy( ));
	
		String title = multi.getParameter("title");
		String gno = multi.getParameter("gno");
		
		Enumeration e = multi.getFileNames( );
		String str = (String)e.nextElement();
		String fileName=multi.getFilesystemName(str);
		
		SGroupBiz biz = new SGroupBiz( );
		SGroupVO temp = new SGroupVO(title, fileName, Integer.parseInt(gno));
		
		biz.modify(temp);	
		
		// 그룹의 정보를 써야하기 때문에 request에 담는다.
		request.setAttribute("group", temp);
		
		response.sendRedirect("control?cmd=postview&gno=" + gno);	
	}
}
