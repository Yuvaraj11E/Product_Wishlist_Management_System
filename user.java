import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {

	public int createUser(String name, int age, String address, String password) {
        int generatedId = -1;

        try {
            Connection con = DBconnection.getConnection();

            String sql = "INSERT INTO USERS (user_id, username, age, address, password) " +
                         "VALUES (user_id_seq.NEXTVAL, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, address);
            ps.setString(4, password);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            
        }

        return generatedId;
    }



	public int login(String username, String password) {
	    int userId = -1;
	    try {
	        Connection con = DBconnection.getConnection();
	        String sql = "SELECT user_id FROM USERS WHERE username = ? AND password = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            userId = rs.getInt("user_id");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error during login: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return userId;
	}

    public boolean deleteUser(String username, String password) {
        try {
            Connection con = DBconnection.getConnection();
            String sql = "DELETE FROM USERS WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "DELETE FROM USERS WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        int rows = ps.executeUpdate();
        return rows > 0;
    }


    public boolean checkUserExists(int userId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT username FROM USERS WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }


    public String getUsername(int userId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT username FROM USERS WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("username");
        }
        return null;
    }
}



