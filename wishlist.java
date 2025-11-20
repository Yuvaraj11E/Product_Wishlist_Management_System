import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class wishlist {

    
	public boolean addToWishlist(int userId, int productId, int quantity) throws SQLException {
	    if (quantity <= 0 || quantity > 10) {
	        System.out.println("Quantity must be between 1 and 10.");
	        return false;
	    }

	    Connection con = DBconnection.getConnection();
	    String sql = "INSERT INTO WISHLIST(user_id, product_id, date_added, quantity) VALUES (?, ?, SYSDATE, ?)";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, userId);
	    ps.setInt(2, productId);
	    ps.setInt(3, quantity);
	    int rows = ps.executeUpdate();
	    return rows > 0;
	}



    public boolean deleteFromWishlist(int userId, int productId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "DELETE FROM WISHLIST WHERE user_id = ? AND product_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, productId);
        int rows = ps.executeUpdate();
        return rows > 0;
    }

 
    public boolean viewWishlist(int userId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT P.product_name, P.price, W.date_added, W.quantity " +
                     "FROM WISHLIST W JOIN PRODUCTS P ON W.product_id = P.product_id " +
                     "WHERE W.user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        boolean hasItems = false;
        System.out.println("\n--- Your Wishlist ---");
        while (rs.next()) {
            hasItems = true;
            System.out.println("Product: " + rs.getString("product_name") +
                               ", Price: Rs." + rs.getDouble("price") +
                               ", Quantity: " + rs.getInt("quantity") +
                               ", Added on: " + rs.getDate("date_added"));
        }
        if (!hasItems) {
            System.out.println();
        }
        return hasItems;
    }

   

    public int getWishlistCount(int userId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT COUNT(*) FROM WISHLIST WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,userId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }
}


