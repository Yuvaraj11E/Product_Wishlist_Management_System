import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String DB_URL = "jdbc:oracle:thin:@DESKTOP-C042DBS:1521/XE";
    private static final String USER = "yuvaraj";
    private static final String PASS = "******";
    private static Connection con = null;

    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return con;
    }

    public static void closeConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}

