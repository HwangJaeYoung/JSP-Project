package frame.biz;

import java.sql.SQLException;
import java.util.ArrayList;

import frame.dao.PostDao;
import frame.vo.PostVO;

public class PostBiz implements Biz
{
	PostDao dao = null;
	
	public PostBiz( )
	{
		dao = new PostDao( );
	}
	
	public void likeCount(Object obj)
	{
		try {
			dao.like(obj);
		} catch (SQLException e) {
			System.out.println("Like Error");
			e.printStackTrace();
		}	
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
	
	public ArrayList<PostVO> selectGroupPost(int gno) // ��ü���� ����
	{	ArrayList<PostVO> result = null;
	
		try {
			result = dao.selectGroupPost(gno);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<PostVO> selectGal(int gno, int pageNum) // ������ ����
	{	ArrayList<PostVO> result = null;
	
		try {
			result = dao.selectGal(gno, pageNum);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<PostVO> selectGal(int gno) // ������ ����
	{	ArrayList<PostVO> result = null;
	
		try {
			result = dao.selectGal(gno);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
}
