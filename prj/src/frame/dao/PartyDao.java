package frame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import frame.sql.JdbcTemplate;
import frame.sql.PartySql;
import frame.vo.PartyVO;

public class PartyDao implements Dao
{
	Connection con;
	PreparedStatement pstmt;
	ResultSet rset;
	
	public void insert(Object obj) throws Exception
	{
		PartyVO party = (PartyVO) obj;

		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PartySql.insertSql);
		
		pstmt.setString(1, party.getId());
		pstmt.setInt(2, party.getgNo());
		
		pstmt.executeUpdate();
		
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void update(Object obj) throws Exception
	{
		PartyVO party = (PartyVO)obj;

		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PartySql.updateSql);
		
		pstmt.setString(1, party.getId());
		pstmt.setInt(2, party.getgNo());
		
		pstmt.executeUpdate();
		
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void delete(Object obj) throws Exception
	{
		PartyVO party = (PartyVO) obj;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PartySql.deleteSql);
		
		pstmt.setString(1, party.getId());
		pstmt.setInt(2, party.getgNo());
		
		pstmt.executeUpdate();
		
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}
	
	public void delete(int gno) throws Exception
	{
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(PartySql.deleteGroupSql);
		
		pstmt.setInt(1, gno);
		
		pstmt.executeUpdate();
		
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public Object select(Object obj) throws Exception
	{
		return null;
	}

	public ArrayList<Object> select() throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
	      
	      con = JdbcTemplate.getConnection();
	      pstmt = con.prepareStatement(PartySql.selectAllSql2);
	      rset = pstmt.executeQuery();
	      
	      while(rset.next())
	      {
	         result.add(new PartyVO(rset.getString("ID"), rset.getInt("GNO"), rset.getString("STATUS")));
	      }
	      
	      JdbcTemplate.close(rset);
	      JdbcTemplate.close(pstmt);
	      JdbcTemplate.close(con);
	 
	      return result;   
	}
	
	public ArrayList<PartyVO> selectTrue(String id, int pageNum) throws Exception
	{
		ArrayList<PartyVO> result = new ArrayList<PartyVO>();
		
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(PartySql.selectSqlTrue);
		
		pstmt.setString(1, id);
		pstmt.setInt(2, pageNum);
		pstmt.setInt(3, pageNum);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			result.add(new PartyVO(id, rset.getInt("GNO"), "T"));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		   
		return result;
	}
	
	public ArrayList<PartyVO> selectFalse(String id) throws Exception
	{
		ArrayList<PartyVO> result = new ArrayList<PartyVO>();
		
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(PartySql.selectSqlFalse);
		
		pstmt.setString(1, id);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			result.add(new PartyVO(id, rset.getInt("GNO"), "F"));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		   
		return result;
	}
	
	public ArrayList<PartyVO> select(String id) throws Exception
	{
		ArrayList<PartyVO> result = new ArrayList<PartyVO>();
		
		con = JdbcTemplate.getConnection();
		
		pstmt = con.prepareStatement(PartySql.selectAllSql);
		
		pstmt.setString(1, id);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			result.add(new PartyVO(id, rset.getInt("GNO"), "T"));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		   
		return result;
	}
}
