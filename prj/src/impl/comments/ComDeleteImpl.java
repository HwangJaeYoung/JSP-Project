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

@WebServlet({ "/ComDeleteImpl", "/comdeleteimpl" })
public class ComDeleteImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		String gno = request.getParameter("gno");
		int like = Integer.parseInt(request.getParameter("like"));
		int pno = Integer.parseInt(request.getParameter("uno"));
		String cid = request.getParameter("cid");
		
		System.out.println(gno);
		
		Biz biz = new CommentBiz( );
		biz.remove(cno);

		request.setAttribute("like", like);
		
		RequestDispatcher rd = request.getRequestDispatcher("control?cmd=commentsview&gno=" + gno + "&uno=" + pno + "&like=" + like + "&cid=" + cid);	      
	    rd.forward(request, response);
	}

}
