package impl.group;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.PartyBiz;
import frame.biz.SGroupBiz;
import frame.vo.CustomerVO;
import frame.vo.PartyVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class GroupSelectServlet
 */
@WebServlet({ "/GroupSelectServlet", "/groupselect" })
public class GroupSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인유저의 정보를 가져오는 과졍.
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		
		PartyBiz pBiz = new PartyBiz();
		ArrayList<PartyVO> partyList;
		
		// 페이지 넘버 가져오기
		int pageNum;
		if(request.getParameter("pagenum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pagenum"));
			
			// PARTY 테이블로부터 로그인한 유저의 id와 그룹에 참여하는(status 속성이 "T")인 튜플들을 셀렉트.
			
			partyList = pBiz.selectTrue(user.getId(), pageNum);
		}
		
		else {
			// PARTY 테이블로부터 로그인한 유저의 id와 그룹에 참여하는(status 속성이 "T")인 튜플들을 셀렉트.
			partyList = pBiz.selectTrue(user.getId(), 1);
		}
		
		
		// 위에서 셀렉트한 GNO들로 각 그룹의 정보를 셀렉트 해와 sGroupResult에 add 하는 과정.
		ArrayList<SGroupVO> sGroupResult = new ArrayList<SGroupVO>();
		SGroupBiz gBiz = new SGroupBiz();
		for(PartyVO partyTemp : partyList) {
			sGroupResult.add((SGroupVO) gBiz.select(partyTemp.getgNo()));
		}
		
		// 그룹의 총 갯수를 알기 위해 참여하고 있는 그룹의 갯수를 가져와 request에 담는다.
		ArrayList<PartyVO> list = pBiz.select(user.getId());
		request.setAttribute("list", list);
		
		request.setAttribute("sGroupList", sGroupResult);

		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

}
