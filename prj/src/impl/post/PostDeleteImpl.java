package impl.post;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.Biz;
import frame.biz.CommentBiz;
import frame.biz.PostBiz;
import frame.vo.CommentsVO;

@WebServlet({ "/PostDeleteImpl", "/postdeleteimpl" })
public class PostDeleteImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uno = request.getParameter("uno");
		
		// Comments 삭제
		CommentBiz cBiz = new CommentBiz();
		
		ArrayList<CommentsVO> result = cBiz.selectUno(Integer.parseInt(uno));
		
		for(CommentsVO temp : result) {
			cBiz.remove(temp.getcNo());
		}
		
		// Post 삭제
		Biz pBiz = new PostBiz();
		
		pBiz.remove(uno); 

		RequestDispatcher rd = request.getRequestDispatcher("control?cmd=postview");
		
		rd.forward(request, response);
	}
}