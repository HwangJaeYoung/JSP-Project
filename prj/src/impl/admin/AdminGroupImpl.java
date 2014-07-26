package impl.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.SGroupBiz;

@WebServlet({ "/AdminGroupImpl", "/admingroupimpl" })
public class AdminGroupImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		SGroupBiz biz = new SGroupBiz( );
		String temp = request.getParameter("pagenum");
		
		if(temp == null)
			temp = "1";
		int pagenum = Integer.parseInt(temp);  
		
		ArrayList<Object> gro = biz.select(pagenum, 0); // paging select
		request.setAttribute("group", gro);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);	
	}
}