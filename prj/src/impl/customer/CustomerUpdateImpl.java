package impl.customer;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import frame.biz.Biz;
import frame.biz.CustomerBiz;
import frame.vo.CustomerVO;

/**
 * Servlet implementation class CustomerUpdateImpl
 */
@WebServlet({ "/CustomerUpdateImpl", "/customerupdateimpl" })
public class CustomerUpdateImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");

		String id = request.getParameter("id");

		Biz biz = new CustomerBiz();

		CustomerVO result = (CustomerVO) biz.select(id);

		request.setAttribute("user", result);

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String upload = "C://jsp//prj//web//image//cimg";
		int size = 10 * 1024 * 1024; // 이미지 저장, 업로드등 을 위해

		MultipartRequest multi = null;

		multi = new MultipartRequest(request, upload, size, "euc-kr",
				new DefaultFileRenamePolicy());

		HttpSession session = request.getSession();
		CustomerVO c = (CustomerVO) session.getAttribute("user");

		String id = c.getId();
		String pwd = multi.getParameter("pwd");
		String phone = multi.getParameter("phone");
		String address = multi.getParameter("address");
		String email = multi.getParameter("email");

		Enumeration e = multi.getFileNames();
		String str = (String) e.nextElement();
		String file = multi.getFilesystemName(str);

		Biz biz = new CustomerBiz();

		CustomerVO temp = new CustomerVO(id, pwd, phone, address, email, file);
		biz.modify(temp);

		response.sendRedirect("control?cmd=myprofile");
	}

}
