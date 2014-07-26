package frame.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

	/**
	 * ����Ʈ ������
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
	 * DB�� conect�Ǿ����� ���θ� Return �Ѵ�.
	 * 
	 * @return DB�� conect �Ǿ����� ����.
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
	 * Connection ��ü�� �ý��ۿ� ��ȯ�Ѵ�.
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
	 * Statement�� Close �Ѵ�.
	 * 
	 * @param stmt
	 *            Statement ��ü.
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
	 * ResultSet�� Close �Ѵ�.
	 * 
	 * @param result
	 *            ResultSet ��ü.
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
	 * ���ݱ����� Ʈ������� Commit ó���Ѵ�.
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
	 * ���ݱ����� Ʈ������� Rollback ó�Ѵ�.
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
