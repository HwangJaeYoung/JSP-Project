package impl.post;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.PostBiz;
import frame.biz.SGroupBiz;
import frame.vo.PostVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class PostGalleryImpl
 */
@WebServlet({ "/PostGalleryImpl", "/postgalleryimpl" })
public class PostGalleryImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		PostBiz biz = new PostBiz( );
		ArrayList<PostVO> postImage = biz.selectGal(gno, pageNum);	
		ArrayList<PostVO> postList = biz.selectGal(gno);	
		
		SGroupBiz gBiz = new SGroupBiz();
		
		SGroupVO group = (SGroupVO) gBiz.select(gno + "");
		
		request.setAttribute("group", group);
		request.setAttribute("postImage", postImage);
		request.setAttribute("postList", postList);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
		
	}

}
