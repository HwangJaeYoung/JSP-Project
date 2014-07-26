package impl.party;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.PartyBiz;
import frame.vo.CustomerVO;
import frame.vo.PartyVO;

/**
 * Servlet implementation class PartySelectImpl
 */
@WebServlet({ "/PartySelectImpl", "/partyinviteimpl" })
public class PartySelectImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartyBiz pBiz = new PartyBiz();
		
		HttpSession session = request.getSession();
		
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		
		ArrayList<PartyVO> result = (ArrayList<PartyVO>) pBiz.selectFalse(user.getId());

		session.setAttribute("partyFalseList", result);
		
		response.sendRedirect("control");
	}

}
