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
		// 그룹 초대 리스트를 셀렉트 해오는 과정. 그 후 세션에 넣는다.
		PartyBiz pBiz = new PartyBiz();
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		ArrayList<PartyVO> partyFalseList = (ArrayList<PartyVO>) pBiz.selectFalse(user.getId());
		session.setAttribute("partyFalseList", partyFalseList);
		
		// 그룹 초대 리스트로부터 그룹의 정보와 리더의 정보를 request에 넣는 과정.
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
