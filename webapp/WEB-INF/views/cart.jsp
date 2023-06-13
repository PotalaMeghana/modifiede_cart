<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="eStoreProduct.model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
</head>
<body>
    <div class="container mt-5">
        <h2>Cart</h2>
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
                        <button class="btn btn-primary removeFromCart" data-product-id="<%= product.getId() %>">Remove from Cart</button>
                        <button class="btn btn-secondary addToWishListFromCart" data-product-id="<%= product.getId() %>">Add to Wishlist</button>
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
