package impl.party;

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

@WebServlet({ "/PartyDeleteImpl", "/partydenyimpl" })
public class PartyDeleteImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String id = ((CustomerVO) session.getAttribute("user")).getId();
		String gno = request.getParameter("gno");
		
		Biz biz = new PartyBiz( );
		PartyVO temp = new PartyVO(id, Integer.parseInt(gno));
		
		biz.remove(temp);
		
		response.sendRedirect("control?cmd=invitelist");
	}
}