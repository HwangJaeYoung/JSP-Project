package frame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import frame.sql.JdbcTemplate;
import frame.sql.PostSql;
import frame.vo.PostVO;

public class PostDao implements Dao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rset;

	
	public void like(Object obj) throws SQLException
	{
		PostVO pv = (PostVO) obj;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PostSql.likeSql);
		
		pstmt.setInt(1, pv.getLikeCount());
		pstmt.setInt(2, pv.getuNo());

		System.out.println(pv.getLikeCount()+" likes");
		
		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}
	
	public void insert(Object obj) throws Exception {
		PostVO pv = (PostVO) obj;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PostSql.insertSql);

		pstmt.setString(1, pv.getId());
		pstmt.setInt(2, pv.getgNo());
		pstmt.setString(3, pv.getContent());
		pstmt.setString(4, pv.getFile());
		pstmt.setInt(5, pv.getLikeCount());
		pstmt.setString(6, pv.getSecret());
		pstmt.setString(7, pv.getNotice());
		
		System.out.println(pv);
		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void update(Object obj) throws Exception {
		PostVO pv = (PostVO) obj;

		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PostSql.updateSql);

		pstmt.setString(1, pv.getContent());
		pstmt.setString(2, pv.getFile());
		pstmt.setInt(3, pv.getuNo());

		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void delete(Object obj) throws Exception {
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PostSql.deleteSql);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public Object select(Object obj) throws Exception {
		Object result = null;

		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PostSql.selectSql);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		rset = pstmt.executeQuery();

		rset.next();

		result = new PostVO(rset.getInt("UNO"), rset.getString("ID"),
				rset.getInt("GNO"), rset.getString("CONTENT"),
				rset.getDate("UPLOADDATE"), rset.getString("C_FILE"),
				rset.getInt("LIKECOUNT"), rset.getString("SECRET"),
				rset.getString("NOTICE"));

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

		return result;

	}

	public ArrayList<Object> select() throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
		
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PostSql.selectAllSql2);
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{

		result.add(new PostVO(rset.getInt("UNO"), rset.getString("ID"),
				rset.getInt("GNO"), rset.getString("CONTENT"),
				rset.getDate("UPLOADDATE"), rset.getString("C_FILE"),
				rset.getInt("LIKECOUNT"), rset.getString("SECRET"),
				rset.getString("NOTICE")));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

		return result;	
	}

	public ArrayList<PostVO> selectGroupPost(int gno) throws Exception {
		ArrayList<PostVO> result = new ArrayList<PostVO>();

		con = JdbcTemplate.getConnection();

		pstmt = con.prepareStatement(PostSql.selectAllSql);

		pstmt.setInt(1, gno);

		rset = pstmt.executeQuery();

		while (rset.next()) {
			result.add(new PostVO(rset.getInt("UNO"), rset.getString("ID"),
					rset.getInt("GNO"), rset.getString("CONTENT"), 
					rset.getDate("UPLOADDATE"), rset.getString("C_FILE"),
					rset.getInt("LIKECOUNT"), rset.getString("SECRET"), 
					rset.getString("NOTICE")));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

		return result;
	}
	
	public ArrayList<PostVO> selectGal(int gno, int pageNum) throws Exception {
		ArrayList<PostVO> result = new ArrayList<PostVO>();

		con = JdbcTemplate.getConnection();

		pstmt = con.prepareStatement(PostSql.selectGal);

		pstmt.setInt(1, gno);
		pstmt.setInt(2, pageNum);
		pstmt.setInt(3, pageNum);

		rset = pstmt.executeQuery();

		while (rset.next()) {
			result.add(new PostVO(rset.getInt("UNO"), rset.getString("ID"),
					rset.getInt("GNO"), rset.getString("CONTENT"), 
					rset.getDate("UPLOADDATE"), rset.getString("C_FILE"),
					rset.getInt("LIKECOUNT"), rset.getString("SECRET"), 
					rset.getString("NOTICE")));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

		return result;
	}
	
	public ArrayList<PostVO> selectGal(int gno) throws Exception {
		ArrayList<PostVO> result = new ArrayList<PostVO>();

		con = JdbcTemplate.getConnection();

		pstmt = con.prepareStatement(PostSql.selectAllGal);

		pstmt.setInt(1, gno);

		rset = pstmt.executeQuery();

		while (rset.next()) {
			result.add(new PostVO(rset.getInt("UNO"), rset.getString("ID"),
					rset.getInt("GNO"), rset.getString("CONTENT"), 
					rset.getDate("UPLOADDATE"), rset.getString("C_FILE"),
					rset.getInt("LIKECOUNT"), rset.getString("SECRET"), 
					rset.getString("NOTICE")));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

		return result;
	}
}
