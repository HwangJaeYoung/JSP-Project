package frame.sql;

public class PartySql
{
	public static String insertSql = "INSERT INTO PARTY VALUES(?, ?, 'F')";
	public static String selectAllSql = "SELECT * FROM PARTY WHERE ID = ? AND STATUS = 'T'";
	public static String selectSqlTrue = "SELECT * FROM "
										+ "(SELECT ROWNUM RNUM, A. * FROM (SELECT * FROM PARTY WHERE ID = ? AND STATUS = 'T' ORDER BY GNO DESC) A)"
										+ "WHERE RNUM BETWEEN (? - 1) * 9 + 1 AND ? * 9";
	public static String selectSqlFalse = "SELECT * FROM PARTY WHERE ID = ? AND STATUS = 'F'";
	public static String deleteSql = "DELETE FROM PARTY WHERE ID = ? AND GNO = ?";
	public static String deleteGroupSql = "DELETE FROM PARTY WHERE GNO = ?";
	public static String updateSql = "UPDATE PARTY SET STATUS = 'T' WHERE ID = ? AND GNO = ?";
	public static String selectAllSql2 = "SELECT * FROM PARTY";

}