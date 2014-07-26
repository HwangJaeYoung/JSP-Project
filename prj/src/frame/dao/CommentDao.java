package frame.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import frame.sql.CommentsSql;
import frame.sql.JdbcTemplate;
import frame.vo.CommentsVO;

public class CommentDao implements Dao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rset;

	public void insert(Object obj) throws Exception
	{		
		CommentsVO com = (CommentsVO)obj;
		
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(CommentsSql.insertSql);
		
		pstmt.setInt(1, com.getuNo());//p.getuNo()
		pstmt.setString(2, com.getId());//c.getId()
		pstmt.setString(3, com.getContent());
		
		pstmt.executeUpdate();
		
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

	}

	public void update(Object obj) throws Exception
	{
		CommentsVO com = (CommentsVO)obj;
		
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(CommentsSql.updateSql);
		
		pstmt.setString(1, com.getContent());
		pstmt.setInt(2, com.getcNo());
		pstmt.executeUpdate();
		
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

	}

	public void delete(Object obj) throws Exception
	{
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(CommentsSql.deleteSql);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

	}

	public Object select(Object obj) throws Exception
	{
		Object result = null;
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(CommentsSql.selectSql);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		rset = pstmt.executeQuery();		
		rset.next();
		
		int cno = rset.getInt("CNO");
		int pno = rset.getInt("UNO");
		String id = rset.getString("ID");
		String content = rset.getString("CONTENT");
		Date regdate = rset.getDate("REGDATE");
			
		result = new CommentsVO(cno, pno, id, content, regdate);
		
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}

	public ArrayList<Object> select() throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(CommentsSql.selectALLSql);
		
		rset = pstmt.executeQuery();
				
		while(rset.next()) {
			int cno = rset.getInt("CNO");
			int pno = rset.getInt("UNO");
			String id = rset.getString("ID");
			String content = rset.getString("CONTENT");
			Date regdate = rset.getDate("REGDATE");
			
			result.add(new CommentsVO(cno, pno, id, content, regdate));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}
	
	public ArrayList<CommentsVO> selectUno(int uno) throws Exception
	{
		ArrayList<CommentsVO> result = new ArrayList<CommentsVO>();
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(CommentsSql.selectUnoSql);
		pstmt.setInt(1, uno);
		rset = pstmt.executeQuery();		
		
		while(rset.next()) {
			int cno = rset.getInt("CNO");
			int pno = rset.getInt("UNO");
			String id = rset.getString("ID");
			String content = rset.getString("CONTENT");
			Date regdate = rset.getDate("REGDATE");
				
			result.add(new CommentsVO(cno, pno, id, content, regdate));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}
}
