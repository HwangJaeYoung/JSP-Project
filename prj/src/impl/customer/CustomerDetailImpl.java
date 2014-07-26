package impl.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.Biz;
import frame.biz.CustomerBiz;
import frame.vo.CustomerVO;

/**
 * Servlet implementation class CustomerDetailImpl
 */
@WebServlet({ "/CustomerDetailImpl", "/customerdetailimpl" })
public class CustomerDetailImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");
		
		String id=user.getId();
		
		Biz biz = new CustomerBiz();
		
		CustomerVO c = (CustomerVO) biz.select(id);
		
		request.setAttribute("user", c);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}

}
