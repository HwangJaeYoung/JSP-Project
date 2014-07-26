package impl.group;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.CustomerBiz;
import frame.biz.PartyBiz;
import frame.biz.SGroupBiz;
import frame.util.MyUtil;
import frame.vo.CustomerVO;
import frame.vo.PartyVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class GroupInviteImpl
 */
@WebServlet({ "/GroupInviteImpl", "/groupinviteimpl" })
public class GroupInviteImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * get 방식으로 들어오면 초대할 회원을 찾는다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		String name = request.getParameter("name");
		
		// 이름을 입력했다면
		if(name!= null) {
			// 이름과 데이트를 가져와서 회원을 조회 후 selectlist에 넣어 request에 담는다.
			name = MyUtil.convert(name);
			
			Date birthday = new Date((Integer.parseInt(request.getParameter("year")) - 1900),(Integer.parseInt(request.getParameter("month")) - 1), Integer.parseInt(request.getParameter("day")));
			
			CustomerBiz cBiz = new CustomerBiz();

			ArrayList<CustomerVO> selectlist = cBiz.select(name, birthday);
			
			request.setAttribute("selectlist", selectlist);
		}
		
		SGroupBiz gBiz = new SGroupBiz();
		SGroupVO group = (SGroupVO) gBiz.select("" + gno);
		request.setAttribute("group", group);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
	
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * post 방식으로 들어오면 체크한 회원을 그룹에 초대한다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));
		String[] idList = request.getParameterValues("id");
		
		PartyBiz pBiz = new PartyBiz();
		
		for(String id : idList) {
			pBiz.register(new PartyVO(id, gno));
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("control?cmd=postview&gno=" + gno + "&secret=F");
		
		rd.forward(request, response);
	}

}
