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
		// �α��������� ������ �������� ����.
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		
		PartyBiz pBiz = new PartyBiz();
		ArrayList<PartyVO> partyList;
		
		// ������ �ѹ� ��������
		int pageNum;
		if(request.getParameter("pagenum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pagenum"));
			
			// PARTY ���̺�κ��� �α����� ������ id�� �׷쿡 �����ϴ�(status �Ӽ��� "T")�� Ʃ�õ��� ����Ʈ.
			
			partyList = pBiz.selectTrue(user.getId(), pageNum);
		}
		
		else {
			// PARTY ���̺�κ��� �α����� ������ id�� �׷쿡 �����ϴ�(status �Ӽ��� "T")�� Ʃ�õ��� ����Ʈ.
			partyList = pBiz.selectTrue(user.getId(), 1);
		}
		
		
		// ������ ����Ʈ�� GNO��� �� �׷��� ������ ����Ʈ �ؿ� sGroupResult�� add �ϴ� ����.
		ArrayList<SGroupVO> sGroupResult = new ArrayList<SGroupVO>();
		SGroupBiz gBiz = new SGroupBiz();
		for(PartyVO partyTemp : partyList) {
			sGroupResult.add((SGroupVO) gBiz.select(partyTemp.getgNo()));
		}
		
		// �׷��� �� ������ �˱� ���� �����ϰ� �ִ� �׷��� ������ ������ request�� ��´�.
		ArrayList<PartyVO> list = pBiz.select(user.getId());
		request.setAttribute("list", list);
		
		request.setAttribute("sGroupList", sGroupResult);

		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

}
