package eStoreProduct.DAO;

import java.util.List;

import eStoreProduct.model.Product;

public interface ProductDAO {

	public List<String> getAllProductCategories();
	
	public List<Product> getProductsByCategory(String category);
	
	public List<Product> getAllProducts();
	
	public List<String> getAllCategories();
	
	//public boolean getCartProducts(int pid);
	
}
