package frame.sql;

public class CustomerSql {
	public static String insertSql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String deleteSql = "UPDATE CUSTOMER SET C_STATUS = 'F' WHERE ID = ?";
	public static String updateSql = "UPDATE CUSTOMER SET PWD = ?, PHONE = ?, ADDRESS =?, EMAIL = ?, C_FILE = ? WHERE ID = ?";
	public static String selectSql = "SELECT * FROM CUSTOMER WHERE ID = ?";
	public static String selectAllSql = "SELECT * FROM CUSTOMER";
	public static String selectNameBirthdaySql = "SELECT * FROM CUSTOMER WHERE NAME = ? AND BIRTHDAY = ? AND C_STATUS = 'T'";
	public static String selectPageSql = "SELECT * FROM "
			+ "(SELECT ROWNUM RNUM, A. * FROM (SELECT * FROM CUSTOMER WHERE C_STATUS = 'T' ORDER BY ID DESC) A)"
			+ "WHERE RNUM BETWEEN (? - 1) * 9 + 1 AND ? * 9";
}
