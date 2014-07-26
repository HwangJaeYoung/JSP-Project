package frame.biz;

import java.util.ArrayList;

import frame.dao.SGroupDao;
import frame.vo.SGroupVO;

public class SGroupBiz implements Biz
{
	SGroupDao dao = null;
	
	public SGroupBiz( )
	{
		dao = new SGroupDao( );
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
	
	public SGroupVO select(String gName, String id) // gno�� �������� �Լ�.
	{
		SGroupVO result = null;
		
		try {
			result = dao.select(gName, id);
		} catch (Exception e) {
			System.out.println("Select Error");
			e.printStackTrace();
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