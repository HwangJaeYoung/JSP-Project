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
	 * get ������� ������ �ʴ��� ȸ���� ã�´�.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		String name = request.getParameter("name");
		
		// �̸��� �Է��ߴٸ�
		if(name!= null) {
			// �̸��� ����Ʈ�� �����ͼ� ȸ���� ��ȸ �� selectlist�� �־� request�� ��´�.
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
	 * post ������� ������ üũ�� ȸ���� �׷쿡 �ʴ��Ѵ�.
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
