package eStoreProduct.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import eStoreProduct.model.Product;

import eStoreProduct.DAO.cartDAO;
import java.sql.SQLException;
import java.util.List;

@Controller
public class CartController {
	cartDAO cartimp;
	@Autowired
	public CartController(cartDAO cartdao)
	{
	    cartimp=cartdao;
	}

	
	@GetMapping("/addToCart")
	@ResponseBody
	public String addToCart(@RequestParam(value = "productId", required = true) int productId, Model model)
			throws NumberFormatException, SQLException {
		System.out.println("add to cart called1");
		//ProductDAO pdao = new ProductDAO();
		System.out.println("add to cart called2");
		return cartimp.addToCart(productId, 1) + " Added to cart";

	}

	@GetMapping("/removeFromCart")
	@ResponseBody
	public String removeFromCart(@RequestParam(value = "productId", required = true) int productId, Model model)
			throws NumberFormatException, SQLException {
		System.out.println("remove from cart called1");
		//ProductDAO pdao = new ProductDAO();
		System.out.println("remove from cart called2");
		return cartimp.removeFromCart(productId, 1) + " remove from cart";

	}
	

    @GetMapping("/cartItems")
    public String userCartItems(@RequestParam(value = "userId", required = true) int cust_id, Model model)
            throws NumberFormatException, SQLException {
        System.out.println("carts called1");
        //ProductDAO pdao = new ProductDAO();
        List<Product> products = cartimp.getCartProds(cust_id);
        
        // Set the products attribute in the model
        model.addAttribute("products", products);
        
        // Forward to the cart.jsp view
        return "cart";
    }
	 
}
 