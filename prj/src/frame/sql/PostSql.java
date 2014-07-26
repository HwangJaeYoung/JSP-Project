package frame.sql;


public class PostSql {
	public static String selectAllSql = "SELECT * FROM POST WHERE GNO = ? ORDER BY UNO DESC";
	public static String deleteSql = "DELETE FROM POST WHERE UNO = ?";
	public static String insertSql = "INSERT INTO POST VALUES(POST_SQ.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";
	public static String selectSql = "SELECT * FROM POST WHERE UNO = ?";
	public static String updateSql = "UPDATE POST SET CONTENT = ?, C_FILE = ? WHERE UNO = ?";
	public static String likeSql = "UPDATE POST SET LIKECOUNT = ? WHERE UNO = ?";
	public static String selectAllSql2 = "SELECT * FROM POST";
	
	public static String selectGal = "SELECT * FROM "
			+ "(SELECT ROWNUM RNUM, A. * FROM (SELECT * FROM POST WHERE GNO = ? AND C_FILE != 'NULL' AND C_FILE NOT LIKE '%.mp4' ORDER BY UNO DESC) A)"
			+ "WHERE RNUM BETWEEN (? - 1) * 12 + 1 AND ? * 12";
	
	public static String selectAllGal = "SELECT * FROM POST WHERE GNO = ? AND C_FILE != 'NULL' AND C_FILE NOT LIKE '%.mp4' ORDER BY UNO DESC";
}
