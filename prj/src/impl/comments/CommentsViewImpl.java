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
import frame.biz.CommentBiz;
import frame.biz.CustomerBiz;
import frame.biz.PostBiz;
import frame.biz.SGroupBiz;
import frame.vo.CommentsVO;
import frame.vo.CustomerVO;
import frame.vo.PostVO;
import frame.vo.SGroupVO;

@WebServlet({ "/CommentsViewImpl", "/commentsviewimpl" })
public class CommentsViewImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ������ ���� �׷��� gno�� �����´�.
		int gno = Integer.parseInt(request.getParameter("gno"));
		int uno = Integer.parseInt(request.getParameter("uno"));
		int like = Integer.parseInt(request.getParameter("like"));
		String id = request.getParameter("cid");
		//String name = request.getParameter("name");
		
		PostBiz pBiz = new PostBiz( );
		Biz biz = new PostBiz( );
		CustomerBiz cBiz = new CustomerBiz( );
		
		PostVO post = (PostVO) pBiz.select(uno); // �ϴ���  ������ �׷��� Ÿ�Ӷ��ο� ������ �� ���� ��� ���� ���� ���� ������ �´�.
		CustomerVO customer = (CustomerVO) cBiz.select(id); // �ۿ� �ش��ϴ� ������� ������ �����´�.
		
		
		ArrayList<Object> com_temp = new ArrayList<Object>(); // �ӽ÷� value���� �ޱ����� �뵵.
		ArrayList<CommentsVO> comment = new ArrayList<CommentsVO>();
		biz = new CommentBiz();
		com_temp = biz.select();// com_temp�� ��� �۵��� �����Ѵ�.
		
		ArrayList<CustomerVO> commentCustomerList = new ArrayList<CustomerVO>(); // �̸� ����� ���� ����Ʈ
		
		if(uno == post.getuNo()){
			for(Object ob : com_temp)//comment�˻縦 ����
		      {
		   	   CommentsVO comv = (CommentsVO)ob;
		   	   if(comv.getuNo() == post.getuNo())//comment�� pNo�� post�� pNo�� ��ġ�Ѵٸ�
		   	   {
		   		   commentCustomerList.add((CustomerVO) cBiz.select(comv.getId()));
		       	   comment.add(comv);// ����(pNo) �ڸ�Ʈ ��� ����
		   	   }
		      }
			}
	
		
		
		PostVO postlike = new PostVO(uno, like);//likeCount
		pBiz.likeCount(postlike);//likeCount++
		
		SGroupBiz gBiz = new SGroupBiz();
		SGroupVO group = (SGroupVO) gBiz.select(gno + "");
		
		request.setAttribute("group", group);
		request.setAttribute("post", post);
		request.setAttribute("customer", customer);
		request.setAttribute("commentlist", comment);
		request.setAttribute("commentCustomerList", commentCustomerList);
		
		
				
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		
		rd.forward(request, response);
	}
}
