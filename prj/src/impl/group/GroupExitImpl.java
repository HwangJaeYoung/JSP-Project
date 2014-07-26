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

		// �ش� id�� gno�� ���ؼ� �����. party���� �����
		biz.remove(new PartyVO(user.getId(), Integer.parseInt(gno)));
		
		// �׷켱�� �������� ����.
		response.sendRedirect("control");
	}
}