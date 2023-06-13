<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="eStoreProduct.model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
       <div class="container mt-5">
        <h2>Wishlist</h2>
        <div class="row mt-4">
            <%-- Iterate over the products and render the HTML content --%>
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");

                for (Product product : products) {
            %>
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100">
                    <img class="card-img-top" src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getName() %></h5>
                        <p class="card-text"><%= product.getDescription() %></p>
                        <p class="card-text"><%= product.getPrice() %></p>
                        <button class="btn btn-primary removeFromWishlist" data-product-id="<%= product.getId() %>">Remove from wishlist</button>
                        <button class="btn btn-primary addToCartButton" data-product-id="<%= product.getId() %>">Add To Cart</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>