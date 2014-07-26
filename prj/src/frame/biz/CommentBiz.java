package frame.biz;

import java.util.ArrayList;

import frame.dao.CommentDao;
import frame.vo.CommentsVO;

public class CommentBiz implements Biz
{
	CommentDao dao = null;
	
	public CommentBiz( )
	{
		dao = new CommentDao( );
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
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<CommentsVO> selectUno(int uno) // ��ü���� ����
	{	ArrayList<CommentsVO> result = null;
	
		try {
			result = dao.selectUno(uno);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
			e.printStackTrace();
		}
		return result;
	}
	
}
