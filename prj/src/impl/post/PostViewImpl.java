package impl.post;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.CustomerBiz;
import frame.biz.PostBiz;
import frame.biz.SGroupBiz;
import frame.vo.CustomerVO;
import frame.vo.PostVO;
import frame.vo.SGroupVO;

/**
 * Servlet implementation class GroupViewImpl
 */
@WebServlet({ "/PostViewImpl", "/postviewimpl" })
public class PostViewImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ������ ���� �׷��� gno�� �����´�.
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		PostBiz pBiz = new PostBiz( );
		ArrayList<PostVO> post = pBiz.selectGroupPost(gno); // �ϴ���  ������ �׷��� Ÿ�Ӷ��ο� ������ �� �۵��� ������ �´�.
		
		// �� �ۿ� �ش��ϴ� ������� ������ �����´�.
		CustomerBiz cBiz = new CustomerBiz( );
		ArrayList<CustomerVO> customer = new ArrayList<CustomerVO>();
		for(PostVO postTemp : post) {
			customer.add((CustomerVO) cBiz.select(postTemp.getId()));
		}
		
		// �׷��� ������ �׷��� ���� �޴����� ����ϱ� ������ �׷��� ������ ����Ʈ.
		SGroupBiz gBiz = new SGroupBiz();
		SGroupVO group = (SGroupVO) gBiz.select("" + gno);
		
		// �Ѱ���� �ϴ� ������ request�� ��´�.
		request.setAttribute("postlist", post);
		request.setAttribute("customerlist", customer);
		request.setAttribute("group", group);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

}
