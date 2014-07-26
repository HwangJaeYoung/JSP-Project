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
	
	public void register(Object obj) // ���
	{
		try {
			dao.insert(obj);
		} catch (Exception e) {
			System.out.println("Insert Error");
			e.printStackTrace();
		}
	}

	public void modify(Object obj) // ����
	{
		try {
			dao.update(obj);
		} catch (Exception e) {
			System.out.println("Update Error");
			e.printStackTrace();
		}
	}

	public void remove(Object obj) // ����
	{
		try {
			dao.delete(obj);
		} catch (Exception e) {
			System.out.println("Delete Error");
			e.printStackTrace();
		}
	}

	public Object select(Object obj) // ���λ��� ����
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

	public ArrayList<Object> select() // ��ü���� ����
	{	ArrayList<Object> result = null;
	
		try {
			result = dao.select();
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<CustomerVO> select(String name, Date birthday) // �̸��� ������Ϸ� ȸ�� ����
	{	ArrayList<CustomerVO> result = null;
	
		try {
			result = dao.select(name, birthday);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<Object> select(int pagenum, int useless) // gno�� �������� �Լ�.
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