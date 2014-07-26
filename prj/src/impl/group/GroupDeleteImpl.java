package impl.group;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.CommentBiz;
import frame.biz.PartyBiz;
import frame.biz.PostBiz;
import frame.biz.SGroupBiz;
import frame.vo.CustomerVO;
import frame.vo.PostVO;
import frame.vo.SGroupVO;

@WebServlet({ "/GroupDeleteImpl", "/groupdeleteimpl" })
public class GroupDeleteImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String gno = request.getParameter("gno");
		SGroupBiz gBiz = new SGroupBiz( );
		
		SGroupVO temp = (SGroupVO) gBiz.select(gno);
		
		// gno���� ����Ʈ�� ��������
		// �� �׿� ������ comments�� �������Ѵ�.
		
		// �׷쿡 �޷��ִ� �۵��� �����´�.
		PostBiz pBiz = new PostBiz();
		ArrayList<PostVO> postList = pBiz.selectGroupPost(temp.getgNo());
		
		// �ۿ� �޸� �ڸ�Ʈ���� �����ϰ� �� ���� �����Ѵ�.
		CommentBiz cBiz = new CommentBiz();
		for(PostVO postTemp : postList) {
			cBiz.remove(postTemp.getuNo() + "");
		}
		
		for(PostVO postTemp : postList) {
			pBiz.remove(postTemp.getuNo() + "");
		}
		
		
		// �׷�� ������ ������� party�� �����Ѵ�.
		PartyBiz partyBiz = new PartyBiz();
		partyBiz.remove(Integer.parseInt(gno));
		
		// �׷� ����.
		gBiz.remove(gno);
		
		HttpSession session = request.getSession();
		
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		
		if(user.getId().equals("admin")) {
			response.sendRedirect("control?cmd=admingroup");	
		}
		
		else {
			response.sendRedirect("control");	
		}
	}
}