package frame.biz;

import java.util.ArrayList;

import frame.dao.PartyDao;
import frame.vo.PartyVO;

public class PartyBiz implements Biz
{
	PartyDao dao = null;
	
	public PartyBiz( )
	{
		dao = new PartyDao( );
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
	
	public void remove(int gno) // ����
	{
		try {
			dao.delete(gno);
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
	
	public ArrayList<PartyVO> selectTrue(String id, int pageNum) // ��ü���� ����
	{	ArrayList<PartyVO> result = null;
	
		try {
			result = dao.selectTrue(id, pageNum);
		} catch (Exception e) {
			System.out.println("Select ID, TRUE Error");
		}
		
		return result;
	}
	
	public ArrayList<PartyVO> selectFalse(String id) // ��ü���� ����
	{	ArrayList<PartyVO> result = null;
	
		try {
			result = dao.selectFalse(id);
		} catch (Exception e) {
			System.out.println("Select ID, FALSE Error");
		}
		
		return result;
	}
	
	public ArrayList<PartyVO> select(String id) // ��ü���� ����
	{	ArrayList<PartyVO> result = null;
	
		try {
			result = dao.select(id);
		} catch (Exception e) {
			System.out.println("Select ID, TRUE Error");
		}
		
		return result;
	}
}
