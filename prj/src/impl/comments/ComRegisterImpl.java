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

@WebServlet({ "/ComRegisterImpl", "/comregisterimpl" })
public class ComRegisterImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		
		String content = request.getParameter("content"); // 댓글
		content = MyUtil.convert(content);
		int pno = Integer.parseInt(request.getParameter("pno"));
		String id = request.getParameter("id");
		String cid = request.getParameter("cid");
		int like = Integer.parseInt(request.getParameter("like"));
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		Biz biz = new CommentBiz( );
		
		CommentsVO com_temp = new CommentsVO(pno, id, content);//3가지 정보만 입력
		
		biz.register(com_temp);
		
		request.setAttribute("like", like);
		
	    RequestDispatcher rd = request.getRequestDispatcher("control?cmd=commentsview&gno=" + gno + "&uno=" + pno + "&like=" + like + "&cid=" + cid);
	    rd.forward(request, response);
	}

}
