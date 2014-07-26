package impl.comments;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.Biz;
import frame.biz.CommentBiz;
import frame.util.MyUtil;
import frame.vo.CommentsVO;

/**
 * Servlet implementation class ComUpdateImpl
 */
@WebServlet({ "/ComUpdateImpl", "/comupdateimpl" })
public class ComUpdateImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cno = Integer.parseInt(request.getParameter("cno"));
		String content = request.getParameter("content");
		String gno = request.getParameter("gno");
		String cid = request.getParameter("cid");
		int pno = Integer.parseInt(request.getParameter("uno"));
		int like = Integer.parseInt(request.getParameter("like"));
		
		content = MyUtil.convert(content);
		
		Biz biz = new CommentBiz( );
		
		CommentsVO com_temp = new CommentsVO(cno, content);
		biz.modify(com_temp);
		
		request.setAttribute("like", like);
		
		RequestDispatcher rd = request.getRequestDispatcher("control?cmd=commentsview&gno=" + gno + "&uno=" + pno + "&like=" + like + "&cid=" + cid); 
	    rd.forward(request, response);
	}
}
