package impl.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.Biz;
import frame.biz.PartyBiz;
import frame.vo.CustomerVO;
import frame.vo.PartyVO;

@WebServlet({ "/GroupExitImpl", "/groupexitimpl" })
public class GroupExitImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession( );
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		String gno = request.getParameter("gno");
		Biz biz = new PartyBiz( );

		// 해당 id의 gno를 비교해서 지운다. party에서 지우고
		biz.remove(new PartyVO(user.getId(), Integer.parseInt(gno)));
		
		// 그룹선택 페이지로 간다.
		response.sendRedirect("control");
	}
}