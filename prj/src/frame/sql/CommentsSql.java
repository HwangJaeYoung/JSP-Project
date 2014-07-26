package frame.sql;

public class CommentsSql {
	public static String insertSql = "INSERT INTO COMMENTS VALUES(COM_SQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	public static String deleteSql = "DELETE FROM COMMENTS WHERE UNO = ?";
	public static String updateSql = "UPDATE COMMENTS SET CONTENT = ? WHERE CNO = ?";
	public static String selectSql = "SELECT * FROM COMMENTS WHERE CNO = ? ORDER BY CNO DESC";
	public static String selectALLSql = "SELECT * FROM COMMENTS ORDER BY CNO DESC";
	public static String selectUnoSql = "SELECT * FROM COMMENTS WHERE UNO = ? ORDER BY CNO DESC";
}

