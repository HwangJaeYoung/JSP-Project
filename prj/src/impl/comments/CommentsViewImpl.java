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
		// 유저가 누른 그룹의 gno를 가져온다.
		int gno = Integer.parseInt(request.getParameter("gno"));
		int uno = Integer.parseInt(request.getParameter("uno"));
		int like = Integer.parseInt(request.getParameter("like"));
		String id = request.getParameter("cid");
		//String name = request.getParameter("name");
		
		PostBiz pBiz = new PostBiz( );
		Biz biz = new PostBiz( );
		CustomerBiz cBiz = new CustomerBiz( );
		
		PostVO post = (PostVO) pBiz.select(uno); // 일단은  선택한 그룹의 타임라인에 포스팅 된 글중 댓글 보기 누른 것을 가지고 온다.
		CustomerVO customer = (CustomerVO) cBiz.select(id); // 글에 해당하는 사용자의 정보를 가져온다.
		
		
		ArrayList<Object> com_temp = new ArrayList<Object>(); // 임시로 value들을 받기위한 용도.
		ArrayList<CommentsVO> comment = new ArrayList<CommentsVO>();
		biz = new CommentBiz();
		com_temp = biz.select();// com_temp에 모든 글들을 저장한다.
		
		ArrayList<CustomerVO> commentCustomerList = new ArrayList<CustomerVO>(); // 이름 출력을 위한 리스트
		
		if(uno == post.getuNo()){
			for(Object ob : com_temp)//comment검사를 위해
		      {
		   	   CommentsVO comv = (CommentsVO)ob;
		   	   if(comv.getuNo() == post.getuNo())//comment의 pNo와 post의 pNo가 일치한다면
		   	   {
		   		   commentCustomerList.add((CustomerVO) cBiz.select(comv.getId()));
		       	   comment.add(comv);// 같은(pNo) 코멘트 모두 저장
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
