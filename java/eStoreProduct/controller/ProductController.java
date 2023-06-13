
  package eStoreProduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eStoreProduct.model.Product;
import eStoreProduct.DAO.ProductDAO;
@Controller
//@ComponentScan(basePackages = "Products")
public class ProductController {

	private final ProductDAO pdaoimp;
	
	@Autowired
	public ProductController(ProductDAO productdao)
	{
	    pdaoimp=productdao;
	}

	@GetMapping("/CategoriesServlet")
	@ResponseBody
	public String displayCategories(Model model) {
		List<String> categories = pdaoimp.getAllCategories();
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<option disabled selected>select Product category</option>");
		for (String category : categories) {
			htmlContent.append("<option value='").append(category).append("'>").append(category).append("</option>");
		}

		return htmlContent.toString();
	}

	@GetMapping("/products")
	@ResponseBody
	public String showCategoryProducts(@RequestParam(value = "category", required = false) String category, Model model) {
		List<Product> products;
		if (category != null && !category.isEmpty()) {
			products = pdaoimp.getProductsByCategory(category);
		} else {
			products = pdaoimp.getAllProducts();
		}
		model.addAttribute("products", products);
		//String htmlContent = generateProductCatalogHTML(products);

		return "productCatalog";
	}
	
	
	@GetMapping("/productsDisplay")
	public String showAllProducts(Model model) {
	    System.out.println("all prod display method mapping");
	    List<Product> products = pdaoimp.getAllProducts();

	    model.addAttribute("products", products);

	    return "productCatalog";
	}	
	
}