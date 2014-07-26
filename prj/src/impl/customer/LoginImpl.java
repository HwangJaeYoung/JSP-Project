package impl.customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.biz.Biz;
import frame.biz.CustomerBiz;
import frame.vo.CustomerVO;

@WebServlet({ "/LoginImpl", "/loginimpl" })
public class LoginImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 로그아웃
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session != null){
			session.invalidate();
		}
		
		response.sendRedirect("login.jsp");
	}

	// 로그인
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		Biz biz = new CustomerBiz();
		
		CustomerVO result = (CustomerVO) biz.select(id);
		
		if(result != null && pwd.equals(result.getPwd()) && result.getC_status().equals("T") && !(result.getId().equals("admin"))) {
			session.setAttribute("user", result);
			response.sendRedirect("control?cmd=partyinvite");
		}
		
		else if(result != null && pwd.equals(result.getPwd()) && result.getId().equals("admin"))
		{
			session.setAttribute("user", result);
			response.sendRedirect("control?cmd=adminpage");
		}
		
		else {
			response.sendRedirect("control");
		}
	}


}
