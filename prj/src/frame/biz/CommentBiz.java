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
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<CommentsVO> selectUno(int uno) // 전체사항 선택
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
