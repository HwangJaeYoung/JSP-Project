package impl.group;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import frame.biz.PartyBiz;
import frame.biz.SGroupBiz;
import frame.vo.CustomerVO;
import frame.vo.PartyVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class GroupRegisterImpl
 */
@WebServlet({ "/GroupRegisterImpl", "/groupregisterimpl" })
public class GroupRegisterImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 그룹 정보를 받아와서 그룹을 db에 등록.
		String upload = "c:\\jsp\\prj\\web\\image\\gimg";
		int size = 10 * 1024 * 1024;
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, upload, size, "euc-kr", new DefaultFileRenamePolicy( ));
	
		String title = multi.getParameter("title");

		Enumeration e = multi.getFileNames( );
		String str = (String)e.nextElement();
		String fileName=multi.getFilesystemName(str);
		
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		
		SGroupBiz biz = new SGroupBiz( );
		SGroupVO temp = new SGroupVO(title, user.getId(), fileName);
		biz.register(temp);	
		
		// 그룹에 참여한다는 것을 알려줘야하므로 party 디비에도 등록.
		PartyBiz pBiz = new PartyBiz();
		
		temp = biz.select(title, user.getId());
		
		pBiz.register(new PartyVO(user.getId(), temp.getgNo()));
		pBiz.modify(new PartyVO(user.getId(), temp.getgNo()));		
		
		response.sendRedirect("control");
	}
}