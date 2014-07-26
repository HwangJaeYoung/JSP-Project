package frame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import frame.sql.JdbcTemplate;
import frame.sql.SGroupSql;
import frame.vo.SGroupVO;

public class SGroupDao implements Dao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rset;

	public void insert(Object obj) throws Exception {
		SGroupVO sg = (SGroupVO) obj;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.insertSql);

		pstmt.setString(1, sg.getgName());
		pstmt.setString(2, sg.getLeaderId());
		pstmt.setString(3, sg.getGroimg());

		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void update(Object obj) throws Exception {
		SGroupVO sg = (SGroupVO) obj;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.updateSql);

		pstmt.setString(1, sg.getgName());
		pstmt.setString(2, sg.getGroimg());
		pstmt.setInt(3, sg.getgNo());

		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void delete(Object obj) throws Exception {
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.deleteSql);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		pstmt.executeUpdate();
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public Object select(Object obj) throws Exception {
		Object result = null;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.selectSql);
		pstmt.setInt(1, Integer.parseInt(obj.toString()));
		rset = pstmt.executeQuery();

		rset.next();

		result = new SGroupVO(rset.getInt("GNO"), rset.getString("GNAME"),
				rset.getString("LEADERID"), rset.getString("GROIMG"));

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}

	public ArrayList<Object> select() throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
		
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.selectAllSql);
		rset = pstmt.executeQuery();

		while(rset.next()) {
			result.add(new SGroupVO(rset.getInt("GNO"), rset.getString("GNAME"),
					rset.getString("LEADERID"), rset.getString("GROIMG")));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}
	
	

	// 그룹을 등록했을 때 gno는 시퀀스이므로 모른다. 따라서 그 gno를 가져오기 위한 셀렉트 함수.
	public SGroupVO select(String gName, String id) throws Exception {
		SGroupVO result = null;
		
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.selectGnoSql);
		pstmt.setString(1, gName);
		pstmt.setString(2, id);
		rset = pstmt.executeQuery();

		rset.next();

		result = new SGroupVO(rset.getInt("GNO"), rset.getString("GNAME"),
				rset.getString("LEADERID"));

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

		return result;
	}
	
	public ArrayList<Object> select(int pagenum, int useless) throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
		
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(SGroupSql.selectPageSql);
		pstmt.setInt(1, pagenum);
		pstmt.setInt(2, pagenum);
		rset = pstmt.executeQuery();

		while(rset.next()) {
			result.add(new SGroupVO(rset.getInt("GNO"), rset.getString("GNAME"),
					rset.getString("LEADERID"), rset.getString("GROIMG")));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}
	
}
