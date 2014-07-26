package frame.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

	/**
	 * 디폴트 생성자
	 */
	public JdbcTemplate() {
	}

	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@210.107.234.105:1521:XE";
		String id = "talk";
		String password = "talk";
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("[JdbcTemplate.getConnection] : "
					+ e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * DB와 conect되었는지 여부를 Return 한다.
	 * 
	 * @return DB와 conect 되었는지 여부.
	 */
	public static boolean isconected(Connection con) {

		boolean validConnection = true;

		try {
			if (con == null || con.isClosed())
				validConnection = false;
		} catch (SQLException e) {
			validConnection = false;
			e.printStackTrace();
		}
		return validConnection;
	}

	/**
	 * Connection 객체를 시스템에 반환한다.
	 */
	public static void close(Connection con) {

		if (isconected(con)) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Statement를 Close 한다.
	 * 
	 * @param stmt
	 *            Statement 객체.
	 */
	public static void close(Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ResultSet을 Close 한다.
	 * 
	 * @param result
	 *            ResultSet 객체.
	 */
	public static void close(ResultSet rset) {

		try {
			if (rset != null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 지금까지의 트랜잭션을 Commit 처리한다.
	 */
	public static void commit(Connection con) {

		try {
			if (isconected(con)) {
				con.commit();
				System.out
						.println("[JdbcTemplate.commit] : DB Successfully Committed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 지금까지의 트랜잭션을 Rollback 처한다.
	 */
	public static void rollback(Connection con) {

		try {
			if (isconected(con)) {
				con.rollback();
				System.out
						.println("[JdbcTemplate.rollback] : DB Successfully Rollbacked!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
