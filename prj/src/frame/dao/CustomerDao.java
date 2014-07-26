package frame.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import frame.sql.JdbcTemplate;
import frame.sql.CustomerSql;
import frame.vo.CustomerVO;

public class CustomerDao implements Dao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rset;

	public void insert(Object obj) throws Exception {
		CustomerVO c = (CustomerVO) obj;

		con = JdbcTemplate.getConnection();

		pstmt = con.prepareStatement(CustomerSql.insertSql);

		pstmt.setString(1, c.getId());
		pstmt.setString(2, c.getPwd());
		pstmt.setString(3, c.getName());
		pstmt.setDate(4, c.getBirthday());
		pstmt.setString(5, c.getSex());
		pstmt.setString(6, c.getPhone());
		pstmt.setString(7, c.getAddress());
		pstmt.setString(8, c.getEmail());
		pstmt.setString(9, c.getFile());
		pstmt.setString(10, "T");

		pstmt.executeUpdate();

		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);

	}

	public void update(Object obj) throws Exception {
		CustomerVO c = (CustomerVO) obj;

		con = JdbcTemplate.getConnection();

		pstmt = con.prepareStatement(CustomerSql.updateSql);

		pstmt.setString(1, c.getPwd());
		pstmt.setString(2, c.getPhone());
		pstmt.setString(3, c.getAddress());
		pstmt.setString(4, c.getEmail());
		pstmt.setString(5, c.getFile());
		pstmt.setString(6, c.getId());

		pstmt.executeUpdate();

		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public void delete(Object obj) throws Exception {
		CustomerVO c = (CustomerVO) obj;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(CustomerSql.deleteSql);

		pstmt.setString(1, c.getId());

		pstmt.executeUpdate();

		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
	}

	public Object select(Object obj) throws Exception {
		Object result = null;
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(CustomerSql.selectSql);
		pstmt.setString(1, obj.toString());
		rset = pstmt.executeQuery();

		rset.next();

		String id = rset.getString("ID");
		String pwd = rset.getString("PWD");
		String name = rset.getString("NAME");
		Date birthday = rset.getDate("BIRTHDAY");
		String sex = rset.getString("SEX");
		String phone = rset.getString("PHONE");
		String address = rset.getString("ADDRESS");
		String email = rset.getString("EMAIL");
		String file = rset.getString("C_FILE");
		String c_status = rset.getString("C_STATUS");

		result = new CustomerVO(id, pwd, name, birthday, sex, phone, address,
				email, file, c_status);

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		return result;
	}

	public ArrayList<Object> select() throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(CustomerSql.selectAllSql);
		rset = pstmt.executeQuery();
				
		while(rset.next()) {
			String id = rset.getString("ID");
			String pwd = rset.getString("PWD");
			String name = rset.getString("NAME");
			Date birthday = rset.getDate("BIRTHDAY");
			String sex = rset.getString("SEX");
			String phone = rset.getString("PHONE");
			String address = rset.getString("ADDRESS");
			String email = rset.getString("EMAIL");
			String file = rset.getString("C_FILE");
			String c_status = rset.getString("C_STATUS");
			
			result.add(new CustomerVO(id, pwd, name, birthday, sex, phone, address,
					email, file, c_status));
		}
		
		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}

	public ArrayList<CustomerVO> select(String name, Date birthday)
			throws Exception {
		ArrayList<CustomerVO> result = new ArrayList<CustomerVO>();
		
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(CustomerSql.selectNameBirthdaySql);
		pstmt.setString(1, name);
		pstmt.setDate(2, birthday);
		rset = pstmt.executeQuery();

		while(rset.next()) {
			String id = rset.getString("ID");
			String pwd = rset.getString("PWD");
			String sex = rset.getString("SEX");
			String phone = rset.getString("PHONE");
			String address = rset.getString("ADDRESS");
			String email = rset.getString("EMAIL");
			String file = rset.getString("C_FILE");
			String c_status = rset.getString("C_STATUS");

			result.add(new CustomerVO(id, pwd, name, birthday, sex, phone, address,
					email, file, c_status));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}
	
	public ArrayList<Object> select(int pagenum, int useless) throws Exception
	{
		ArrayList<Object> result = new ArrayList<Object>();
		
		con = JdbcTemplate.getConnection();
		pstmt = con.prepareStatement(CustomerSql.selectPageSql);
		pstmt.setInt(1, pagenum);
		pstmt.setInt(2, pagenum);
		rset = pstmt.executeQuery();

		while(rset.next()) {
			String id = rset.getString("ID");
			String pwd = rset.getString("PWD");
			String name = rset.getString("NAME");
			Date birthday = rset.getDate("BIRTHDAY");
			String sex = rset.getString("SEX");
			String phone = rset.getString("PHONE");
			String address = rset.getString("ADDRESS");
			String email = rset.getString("EMAIL");
			String file = rset.getString("C_FILE");
			String c_status = rset.getString("C_STATUS");
			
			result.add(new CustomerVO(id, pwd, name, birthday, sex, phone, address, email, file, c_status));
		}

		JdbcTemplate.close(rset);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(con);
		
		return result;
	}
	
}