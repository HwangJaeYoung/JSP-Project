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
	
	public ArrayList<PostVO> selectGroupPost(int gno) // 전체사항 선택
	{	ArrayList<PostVO> result = null;
	
		try {
			result = dao.selectGroupPost(gno);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<PostVO> selectGal(int gno, int pageNum) // 갤러리 선택
	{	ArrayList<PostVO> result = null;
	
		try {
			result = dao.selectGal(gno, pageNum);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
	
	public ArrayList<PostVO> selectGal(int gno) // 갤러리 선택
	{	ArrayList<PostVO> result = null;
	
		try {
			result = dao.selectGal(gno);
		} catch (Exception e) {
			System.out.println("SelectAll Error");
		}
		return result;
	}
}
