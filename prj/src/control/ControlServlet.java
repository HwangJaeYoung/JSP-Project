package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frame.vo.CustomerVO;

@WebServlet({ "/ControlServlet", "/control" })
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String secret = request.getParameter("secret");
		HttpSession session = request.getSession();
		CustomerVO user = (CustomerVO) session.getAttribute("user");

		RequestDispatcher rd = null;

		// cmd가 입력되지 않았을 경우.
		if (cmd == null || cmd.equals("")) {
			if (user != null) {
				// 로그인이 되어있다면 초기 페이지인 그룹 셀렉트 서블릿으로부터 그룹을 셀렉트해와서 메인화면으로 이동.
				request.setAttribute("center", "center.jsp");
				rd = request.getRequestDispatcher("groupselect");

				if (user.getId().equals("admin")) {
					request.setAttribute("center", "admin/adminpage.jsp");
					request.setAttribute("left", "admin/adminmenu.jsp");
					request.setAttribute("top", "admin/admintop.jsp");
					rd = request.getRequestDispatcher("main.jsp");
				}

			}

			else {
				// 로그인이 이루어져야만 모든 서비스를 이용할 수 있으므로 기본적으로 로그인 페이지로 이동.
				rd = request.getRequestDispatcher("loginimpl");
			}
		}

		// 로그인 처리 서블렛(loginimpl)으로 이동. 로그인과 로그아웃 실행.
		else if (cmd.equals("login")) {
			rd = request.getRequestDispatcher("loginimpl");
		}

		// 로그인시 그룹 초대를 화면 위쪽에 띄우기 위해 partyInvite 서블릿으로 이동
		else if (cmd.equals("partyinvite")) {
			rd = request.getRequestDispatcher("partyinviteimpl");
		}

		// 회원가입 정보를 받아오기 위해 실행. 회원가입 페이지로 이동.
		else if (cmd.equals("cregister")) {
			rd = request.getRequestDispatcher("customer/register.jsp");
		}

		// 실제로 회원가입 실행, 테이블 생성
		else if (cmd.equals("cregisterimpl")) {
			rd = request.getRequestDispatcher("cregisterimpl");
		}

		// 초대 메세지가 왔을 경우. 그 초대 메세지 버튼을 클릭했을 때 초대 메세지의 상세정보를 출력.
		else if (cmd.equals("invitelist")) {
			rd = request.getRequestDispatcher("inviteselectimpl");
		}

		// 초대 메세지에서 참여를 눌렸을 경우.
		else if (cmd.equals("partyjoin")) {
			rd = request.getRequestDispatcher("partyjoinimpl");
		}

		// 초대 메세지에서 거절을 눌렸을 경우.
		else if (cmd.equals("partydeny")) {
			rd = request.getRequestDispatcher("partydenyimpl");
		}

		// 그룹을 눌렀을 때 그룹의 타임라인을 보여주는 서블릿.
		else if (cmd.equals("postview")) {
			request.setAttribute("secret", secret);
			request.setAttribute("center", "post/postview.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("postviewimpl");
		}

		// 글 삭제를 눌렀을 때 글을 삭제하는 서블릿으로 이동.
		else if (cmd.equals("postdelete")) {

			rd = request.getRequestDispatcher("postdeleteimpl");
		}

		// 글 수정을 눌렀을 때 글을 삭제하는 서블릿으로 이동.
		else if (cmd.equals("postupdate")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "post/postupdate.jsp");
			rd = request.getRequestDispatcher("postupdateimpl");
		}

		// 회원 정보 보기
		else if (cmd.equals("myprofile")) {
			rd = request.getRequestDispatcher("customerdetailimpl");
			request.setAttribute("center", "customer/detail.jsp");
		}

		// 회원 정보 수정
		else if (cmd.equals("cupdate")) {
			request.setAttribute("center", "customer/update.jsp");
			rd = request.getRequestDispatcher("customerupdateimpl");
		}

		// 회원 탈퇴
		else if (cmd.equals("cdelete")) {
			rd = request.getRequestDispatcher("customerdeleteimpl");
		}

		// 그룹 초대
		else if (cmd.equals("groupinvite")) {
			request.setAttribute("center", "group/customerselect.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("groupinviteimpl");
		}

		// 그룹 만들기
		else if (cmd.equals("groupregister")) {
			request.setAttribute("center", "group/groupregister.jsp");
			rd = request.getRequestDispatcher("groupregisterimpl");
		}

		// 리더가 그룹의 정보를 변경하고 싶을 때 이동하는 서블릿
		else if (cmd.equals("groupleader")) {
			request.setAttribute("center", "group/groupleader.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("groupupdateimpl");
		}

		// 리더가 그룹의 삭제하고 싶을 때 이동하는 서블릿
		else if (cmd.equals("groupdeleteimpl")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("groupdeleteimpl");
		}

		// 코멘트로 이동
		else if (cmd.equals("commentsview")) {
			request.setAttribute("secret", secret);
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "comments/commentsview.jsp");
			rd = request.getRequestDispatcher("commentsviewimpl");
		}

		// 코멘트 작성
		else if (cmd.equals("comregister")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("comregisterimpl");
		}

		// 코멘트 수정 수행 부분 진짜 impl로 들어감
		else if (cmd.equals("comupdateimpl")) {
			request.setAttribute("secret", secret);
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "comments/update.jsp");
			rd = request.getRequestDispatcher("comupdateimpl");
		}

		// 코멘트 삭제
		else if (cmd.equals("comdelete")) {
			request.setAttribute("secret", secret);
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("comdeleteimpl");
		}

		// 갤러리 선택했을 때
		else if (cmd.equals("imageviewing")) {
			request.setAttribute("center", "post/imageviewing.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("postgalleryimpl");
		}

		// 그룹의 정보를 보여준다.
		else if (cmd.equals("groupinfo")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "group/groupinfo.jsp");
			rd = request.getRequestDispatcher("groupinfoimpl");
		}

		// 그룹을 탈퇴한다.
		else if (cmd.equals("groupexit")) {
			rd = request.getRequestDispatcher("groupexitimpl");
		}

		// 갤러리 선택했을 때
		else if (cmd.equals("gellery")) {
			request.setAttribute("center", "post/gellery.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("postgalleryimpl");
		}

		// admin일 때
		else if (cmd.equals("adminpage")) {
			request.setAttribute("center", "admin/adminpage.jsp");
			request.setAttribute("left", "admin/adminmenu.jsp");
			request.setAttribute("top", "admin/admintop.jsp");
			rd = request.getRequestDispatcher("main.jsp");
		}

		// admin 회원관리
		else if (cmd.equals("admincustomer")) {
			request.setAttribute("center", "admin/admincustomer.jsp");
			request.setAttribute("left", "admin/adminmenu.jsp");
			request.setAttribute("top", "admin/admintop.jsp");
			rd = request.getRequestDispatcher("admincustomerimpl");
		}

		// admin 그룹관리
		else if (cmd.equals("admingroup")) {
			request.setAttribute("center", "admin/admingroup.jsp");
			request.setAttribute("left", "admin/adminmenu.jsp");
			request.setAttribute("top", "admin/admintop.jsp");
			rd = request.getRequestDispatcher("admingroupimpl");
		}

		rd.forward(request, response);
	}

}
