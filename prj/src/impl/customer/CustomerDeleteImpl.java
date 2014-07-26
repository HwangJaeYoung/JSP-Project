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
 * Servlet implementation class CustomerDeleteImpl
 */
@WebServlet({ "/CustomerDeleteImpl", "/customerdeleteimpl" })
public class CustomerDeleteImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	RequestDispatcher rd = request.getRequestDispatcher("control?cmd=login");

	String id = request.getParameter("id");

	Biz biz = new CustomerBiz();

	CustomerVO result = (CustomerVO) biz.select(id);

	biz.remove(result);	
	
	
	request.setAttribute("user", result);
	
	HttpSession session = request.getSession();
	
	CustomerVO user = (CustomerVO) session.getAttribute("user");
	
	if(user.getId().equals("admin")) {
		rd = request.getRequestDispatcher("control?cmd=admincustomer");
	}

	rd.forward(request, response);
	}

}
