import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class product {

    public void viewProducts() throws SQLException {
        Connection con = DBconnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTS");
        System.out.println("Product List:");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " - Rs." + rs.getDouble(3) + " - Ratings(out of 5)= " + rs.getDouble(4));
        }
    }
    public boolean checkProductExists(int productId) throws SQLException {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT product_id FROM PRODUCTS WHERE product_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public boolean addProduct(int pid, String name, double price, double rating)throws SQLException {
            Connection con = DBconnection.getConnection();
            String query = "INSERT INTO PRODUCTS (product_id, product_name, price, product_rating) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setDouble(4, rating);

            int rows = ps.executeUpdate();
			return false;

            /*if (rows > 0) {
                System.out.println("Product added successfully.");
                return true;
            } else {
                System.out.println("Failed to add product.");
                return false;
            }*/
    }
    }


