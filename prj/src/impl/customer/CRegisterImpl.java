package impl.customer;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import frame.biz.Biz;
import frame.biz.CustomerBiz;
import frame.vo.CustomerVO;


@WebServlet({ "/CRegisterImpl", "/cregisterimpl" })
public class CRegisterImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String upload = 
				"C://jsp//prj//web//image//cimg";
		int size = 10 * 1024 * 1024;		//이미지 저장, 업로드등 을 위해
		
		MultipartRequest multi = null;
		
		multi = new MultipartRequest
				(request,
				 upload,
				 size,
				 "euc-kr",
				 new DefaultFileRenamePolicy()
				); 
		
		String id = multi.getParameter("id");
		String pwd = multi.getParameter("pwd");
		String name = multi.getParameter("name");
		Date birthday = new Date((Integer.parseInt(multi.getParameter("year")) - 1900), (Integer.parseInt(multi.getParameter("month")) - 1), Integer.parseInt(multi.getParameter("day")));
		String sex = multi.getParameter("sex");
		String phone = multi.getParameter("phone");
		String address = multi.getParameter("address");
		String email = multi.getParameter("email");
				
		Enumeration e  = multi.getFileNames();
		String str = (String) e.nextElement();
		String fileName = multi.getFilesystemName(str);
		
		
		Biz biz = new CustomerBiz( );
		
		CustomerVO temp = new CustomerVO(id, pwd, name, birthday, sex, phone, address, email, fileName);
		biz.register(temp);	
		
		response.sendRedirect("login.jsp");
	}

}
