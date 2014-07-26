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
		// 유저가 누른 그룹의 gno를 가져온다.
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		PostBiz pBiz = new PostBiz( );
		ArrayList<PostVO> post = pBiz.selectGroupPost(gno); // 일단은  선택한 그룹의 타임라인에 포스팅 된 글들을 가지고 온다.
		
		// 각 글에 해당하는 사용자의 정보를 가져온다.
		CustomerBiz cBiz = new CustomerBiz( );
		ArrayList<CustomerVO> customer = new ArrayList<CustomerVO>();
		for(PostVO postTemp : post) {
			customer.add((CustomerVO) cBiz.select(postTemp.getId()));
		}
		
		// 그룹의 정보는 그룹의 여러 메뉴에서 써야하기 때문에 그룹의 정보를 셀렉트.
		SGroupBiz gBiz = new SGroupBiz();
		SGroupVO group = (SGroupVO) gBiz.select("" + gno);
		
		// 넘겨줘야 하는 정보를 request에 담는다.
		request.setAttribute("postlist", post);
		request.setAttribute("customerlist", customer);
		request.setAttribute("group", group);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

}
