package frame.biz;

import java.sql.Date;
import java.util.ArrayList;

import frame.dao.CustomerDao;
import frame.vo.CustomerVO;

public class CustomerBiz implements Biz
{
	CustomerDao dao = null;
	
	public CustomerBiz( )
	{
		dao = new CustomerDao( );
	}
	
	public void register(Object obj) // 등록
	{
		try {
			dao.insert(obj);
		} catch (Exception e) {
			System.out.println("Insert Error");
			e.printStackTrace();
		}
	}

	public void modify(Object obj) // 수정
	{
		try {
			dao.update(obj);
		} catch (Exception e) {
			System.out.println("Update Error");
			e.printStackTrace();
		}
	}

	public void remove(Object obj) // 삭제
	{
		try {
			dao.delete(obj);
		} catch (Exception e) {
			System.out.println("Delete Error");
			e.printStackTrace();
		}
	}

	public Object select(Object obj) // 세부사항 선택
	{
		Object result = null;
		
		try {
			result = dao.select(obj);
		} catch (Exception e) {
			System.out.println("Select Error");
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Object> select() // 전체사항 선택
	{	ArrayList<Object> result = null;
	
		try {
			result = dao.select();
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<CustomerVO> select(String name, Date birthday) // 이름과 생년월일로 회원 선택
	{	ArrayList<CustomerVO> result = null;
	
		try {
			result = dao.select(name, birthday);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<Object> select(int pagenum, int useless) // gno를 가져오는 함수.
	{
		ArrayList<Object> result = null;
		
		try {
			result = dao.select(pagenum, useless);
		} catch (Exception e) {
			System.out.println("Select Error");
			e.printStackTrace();
		}
		return result;
	}
}