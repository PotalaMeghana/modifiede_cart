package eStoreProduct.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.stereotype.Component;

import  eStoreProduct.model.*;
@Component
public class WishlistDAOImp implements WishlistDAO{

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://192.168.110.48:5432/plf_training";
	private static final String USERNAME = "plf_training_admin";
	private static final String PASSWORD = "pff123";

	public int addToWishlist(int productId, int customerId) {
		try {
			System.out.println("entered dao add to wishlist");
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "INSERT INTO slam_wishlist (c_id,p_id) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			int r = statement.executeUpdate();
			if (r > 0) {
				System.out.println("inserted into wishlist");
				return productId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
	public int removeFromWishlist(int productId, int customerId) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			String query = "DELETE FROM slam_wishlist WHERE c_id=? AND p_id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			int r = statement.executeUpdate();
			if (r > 0) {
				System.out.println("deleted from wishlist");
				return productId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	
	public List<Product> getWishlistProds(int cust_id) {
		ArrayList<Product> products = new ArrayList<Product>();
		System.out.println(cust_id + " from model");
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			//System.out.println(cust_id + " from model");
			String query = "select pd.* from productsdata pd ,slam_wishlist sc where sc.c_id=? and sc.p_id=pd.id";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, cust_id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				String description = resultSet.getString("description");
				String imageUrl = resultSet.getString("image_url");
				String category = resultSet.getString("category");
				Product product = new Product(id, name, price, description, imageUrl, category);
				products.add(product);
			}
			System.out.println(products.toString());
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return products;
	}
}
