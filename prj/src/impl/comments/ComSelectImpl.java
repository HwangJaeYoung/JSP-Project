package impl.comments;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.Biz;
import frame.biz.CustomerBiz;

@WebServlet({ "/ComSelectImpl", "/comselectimpl" })
public class ComSelectImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Biz biz = new CustomerBiz( );
		ArrayList<Object> list = biz.select();
		request.setAttribute("comselectAll", list);
		RequestDispatcher rd = request.getRequestDispatcher("postview.jsp");
		rd.forward(request, response);	
		
	}

}
