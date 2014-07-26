package impl.party;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.CustomerBiz;
import frame.biz.PartyBiz;
import frame.biz.SGroupBiz;
import frame.vo.CustomerVO;
import frame.vo.PartyVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class InviteSelectImpl
 */
@WebServlet({ "/InviteSelectImpl", "/inviteselectimpl" })
public class InviteSelectImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �׷� �ʴ� ����Ʈ�� ����Ʈ �ؿ��� ����. �� �� ���ǿ� �ִ´�.
		PartyBiz pBiz = new PartyBiz();
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		ArrayList<PartyVO> partyFalseList = (ArrayList<PartyVO>) pBiz.selectFalse(user.getId());
		session.setAttribute("partyFalseList", partyFalseList);
		
		// �׷� �ʴ� ����Ʈ�κ��� �׷��� ������ ������ ������ request�� �ִ� ����.
		SGroupBiz gBiz = new SGroupBiz();
		CustomerBiz cBiz = new CustomerBiz();
		ArrayList<SGroupVO> groupList = new ArrayList<SGroupVO>();
		ArrayList<CustomerVO> leaderList = new ArrayList<CustomerVO>();
		
		for(PartyVO partyTemp : partyFalseList) {
			SGroupVO groupTemp = (SGroupVO) gBiz.select(partyTemp.getgNo());
			groupList.add(groupTemp);
			leaderList.add((CustomerVO) cBiz.select(groupTemp.getLeaderId()));
		}
		
		request.setAttribute("groupList", groupList);
		request.setAttribute("leaderList", leaderList);
		
		RequestDispatcher rd = request.getRequestDispatcher("inviteList.jsp");
		
		rd.forward(request, response);
	}

}
