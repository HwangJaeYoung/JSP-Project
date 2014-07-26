package impl.party;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import frame.biz.Biz;
import frame.biz.PartyBiz;
import frame.vo.PartyVO;

@WebServlet({ "/InviteImpl", "/partyregisterimpl" })
public class PartyRegisterImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String[] check = request.getParameterValues("check"); // 리더가 초대한 회원의 id 목록
		String gno = request.getParameter("gno"); // 리더가 초대한 방의 방번호, SGroup에서 가져온다.
		
		Biz biz = new PartyBiz( );
	
		for(int i = 0; i < check.length; i++)
		{	
			PartyVO temp = new PartyVO(check[i], Integer.parseInt(gno), "F");
			biz.register(temp); // 파티목록의 생성
		}		
	}
}