package frame.sql;


public class SGroupSql {
	public static String selectSql = "SELECT * FROM SGROUP WHERE GNO = ?";
	public static String selectGnoSql = "SELECT * FROM SGROUP WHERE GNAME = ? AND LEADERID = ?";
	public static String insertSql = "INSERT INTO SGROUP VALUES(SG_SQ.NEXTVAL, ?, ?, ?)";
	public static String deleteSql = "DELETE FROM SGROUP WHERE GNO = ?";
	public static String updateSql = "UPDATE SGROUP SET GNAME = ?, GROIMG = ? WHERE GNO = ?";
	public static String selectAllSql = "SELECT * FROM SGROUP";
	public static String selectPageSql = "SELECT * FROM "
			+ "(SELECT ROWNUM RNUM, A. * FROM (SELECT * FROM SGROUP ORDER BY GNO DESC) A)"
			+ "WHERE RNUM BETWEEN (? - 1) * 9 + 1 AND ? * 9";
}
