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
		
		// gno관련 포스트를 모두지우고
		// 또 그와 관련한 comments를 지워야한다.
		
		// 그룹에 달려있는 글들을 가져온다.
		PostBiz pBiz = new PostBiz();
		ArrayList<PostVO> postList = pBiz.selectGroupPost(temp.getgNo());
		
		// 글에 달린 코멘트들을 삭제하고 그 글을 삭제한다.
		CommentBiz cBiz = new CommentBiz();
		for(PostVO postTemp : postList) {
			cBiz.remove(postTemp.getuNo() + "");
		}
		
		for(PostVO postTemp : postList) {
			pBiz.remove(postTemp.getuNo() + "");
		}
		
		
		// 그룹과 유저의 연결고리인 party를 삭제한다.
		PartyBiz partyBiz = new PartyBiz();
		partyBiz.remove(Integer.parseInt(gno));
		
		// 그룹 삭제.
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