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

		// cmd�� �Էµ��� �ʾ��� ���.
		if (cmd == null || cmd.equals("")) {
			if (user != null) {
				// �α����� �Ǿ��ִٸ� �ʱ� �������� �׷� ����Ʈ �������κ��� �׷��� ����Ʈ�ؿͼ� ����ȭ������ �̵�.
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
				// �α����� �̷�����߸� ��� ���񽺸� �̿��� �� �����Ƿ� �⺻������ �α��� �������� �̵�.
				rd = request.getRequestDispatcher("loginimpl");
			}
		}

		// �α��� ó�� ����(loginimpl)���� �̵�. �α��ΰ� �α׾ƿ� ����.
		else if (cmd.equals("login")) {
			rd = request.getRequestDispatcher("loginimpl");
		}

		// �α��ν� �׷� �ʴ븦 ȭ�� ���ʿ� ���� ���� partyInvite �������� �̵�
		else if (cmd.equals("partyinvite")) {
			rd = request.getRequestDispatcher("partyinviteimpl");
		}

		// ȸ������ ������ �޾ƿ��� ���� ����. ȸ������ �������� �̵�.
		else if (cmd.equals("cregister")) {
			rd = request.getRequestDispatcher("customer/register.jsp");
		}

		// ������ ȸ������ ����, ���̺� ����
		else if (cmd.equals("cregisterimpl")) {
			rd = request.getRequestDispatcher("cregisterimpl");
		}

		// �ʴ� �޼����� ���� ���. �� �ʴ� �޼��� ��ư�� Ŭ������ �� �ʴ� �޼����� �������� ���.
		else if (cmd.equals("invitelist")) {
			rd = request.getRequestDispatcher("inviteselectimpl");
		}

		// �ʴ� �޼������� ������ ������ ���.
		else if (cmd.equals("partyjoin")) {
			rd = request.getRequestDispatcher("partyjoinimpl");
		}

		// �ʴ� �޼������� ������ ������ ���.
		else if (cmd.equals("partydeny")) {
			rd = request.getRequestDispatcher("partydenyimpl");
		}

		// �׷��� ������ �� �׷��� Ÿ�Ӷ����� �����ִ� ����.
		else if (cmd.equals("postview")) {
			request.setAttribute("secret", secret);
			request.setAttribute("center", "post/postview.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("postviewimpl");
		}

		// �� ������ ������ �� ���� �����ϴ� �������� �̵�.
		else if (cmd.equals("postdelete")) {

			rd = request.getRequestDispatcher("postdeleteimpl");
		}

		// �� ������ ������ �� ���� �����ϴ� �������� �̵�.
		else if (cmd.equals("postupdate")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "post/postupdate.jsp");
			rd = request.getRequestDispatcher("postupdateimpl");
		}

		// ȸ�� ���� ����
		else if (cmd.equals("myprofile")) {
			rd = request.getRequestDispatcher("customerdetailimpl");
			request.setAttribute("center", "customer/detail.jsp");
		}

		// ȸ�� ���� ����
		else if (cmd.equals("cupdate")) {
			request.setAttribute("center", "customer/update.jsp");
			rd = request.getRequestDispatcher("customerupdateimpl");
		}

		// ȸ�� Ż��
		else if (cmd.equals("cdelete")) {
			rd = request.getRequestDispatcher("customerdeleteimpl");
		}

		// �׷� �ʴ�
		else if (cmd.equals("groupinvite")) {
			request.setAttribute("center", "group/customerselect.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("groupinviteimpl");
		}

		// �׷� �����
		else if (cmd.equals("groupregister")) {
			request.setAttribute("center", "group/groupregister.jsp");
			rd = request.getRequestDispatcher("groupregisterimpl");
		}

		// ������ �׷��� ������ �����ϰ� ���� �� �̵��ϴ� ����
		else if (cmd.equals("groupleader")) {
			request.setAttribute("center", "group/groupleader.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("groupupdateimpl");
		}

		// ������ �׷��� �����ϰ� ���� �� �̵��ϴ� ����
		else if (cmd.equals("groupdeleteimpl")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("groupdeleteimpl");
		}

		// �ڸ�Ʈ�� �̵�
		else if (cmd.equals("commentsview")) {
			request.setAttribute("secret", secret);
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "comments/commentsview.jsp");
			rd = request.getRequestDispatcher("commentsviewimpl");
		}

		// �ڸ�Ʈ �ۼ�
		else if (cmd.equals("comregister")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("comregisterimpl");
		}

		// �ڸ�Ʈ ���� ���� �κ� ��¥ impl�� ��
		else if (cmd.equals("comupdateimpl")) {
			request.setAttribute("secret", secret);
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "comments/update.jsp");
			rd = request.getRequestDispatcher("comupdateimpl");
		}

		// �ڸ�Ʈ ����
		else if (cmd.equals("comdelete")) {
			request.setAttribute("secret", secret);
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("comdeleteimpl");
		}

		// ������ �������� ��
		else if (cmd.equals("imageviewing")) {
			request.setAttribute("center", "post/imageviewing.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("postgalleryimpl");
		}

		// �׷��� ������ �����ش�.
		else if (cmd.equals("groupinfo")) {
			request.setAttribute("left", "group/groupmenu.jsp");
			request.setAttribute("center", "group/groupinfo.jsp");
			rd = request.getRequestDispatcher("groupinfoimpl");
		}

		// �׷��� Ż���Ѵ�.
		else if (cmd.equals("groupexit")) {
			rd = request.getRequestDispatcher("groupexitimpl");
		}

		// ������ �������� ��
		else if (cmd.equals("gellery")) {
			request.setAttribute("center", "post/gellery.jsp");
			request.setAttribute("left", "group/groupmenu.jsp");
			rd = request.getRequestDispatcher("postgalleryimpl");
		}

		// admin�� ��
		else if (cmd.equals("adminpage")) {
			request.setAttribute("center", "admin/adminpage.jsp");
			request.setAttribute("left", "admin/adminmenu.jsp");
			request.setAttribute("top", "admin/admintop.jsp");
			rd = request.getRequestDispatcher("main.jsp");
		}

		// admin ȸ������
		else if (cmd.equals("admincustomer")) {
			request.setAttribute("center", "admin/admincustomer.jsp");
			request.setAttribute("left", "admin/adminmenu.jsp");
			request.setAttribute("top", "admin/admintop.jsp");
			rd = request.getRequestDispatcher("admincustomerimpl");
		}

		// admin �׷����
		else if (cmd.equals("admingroup")) {
			request.setAttribute("center", "admin/admingroup.jsp");
			request.setAttribute("left", "admin/adminmenu.jsp");
			request.setAttribute("top", "admin/admintop.jsp");
			rd = request.getRequestDispatcher("admingroupimpl");
		}

		rd.forward(request, response);
	}

}
