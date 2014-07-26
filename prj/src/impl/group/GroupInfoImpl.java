package impl.group;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.Biz;
import frame.biz.PartyBiz;
import frame.biz.SGroupBiz;
import frame.vo.PartyVO;
import frame.vo.SGroupVO;

@WebServlet({ "/GroupInfoImpl", "/groupinfoimpl" })

public class GroupInfoImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		// 그룹의 정보를 가지고 온다.
		Biz biz = new SGroupBiz( );
		SGroupVO sv = (SGroupVO)biz.select(gno);
		request.setAttribute("group", sv);
	
		// 그룹 번호에 해당하는 파티를 가지고 온다.
		biz = new PartyBiz( );
		ArrayList<Object> temp = new ArrayList<Object>( );
		ArrayList<PartyVO> party = new ArrayList<PartyVO>( );
			
		temp = biz.select();
		
		System.out.println(temp);
		
		for(Object obj : temp)
		{
			PartyVO pv = (PartyVO)obj;
			
			if(pv.getgNo() == gno && pv.getStatus().equals("T"))
				party.add(pv);			
		}
		
		for(Object obj : party)
		{
			System.out.println(obj);
		}
		
		request.setAttribute("party", party);
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
	}
}