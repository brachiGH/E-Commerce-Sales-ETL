package we.ie.E_Commerce_Sales.services;

import java.util.List;

import we.ie.E_Commerce_Sales.dtos.ProductDTO;
import we.ie.E_Commerce_Sales.entities.Category;
import we.ie.E_Commerce_Sales.entities.Product;
import we.ie.E_Commerce_Sales.entities.Product_Category;
import we.ie.E_Commerce_Sales.entities.Purchase;
import we.ie.E_Commerce_Sales.entities.Review;
import we.ie.E_Commerce_Sales.exceptions.CategoryNotFoundException;
import we.ie.E_Commerce_Sales.exceptions.ClientNotFoundException;
import we.ie.E_Commerce_Sales.exceptions.ProductNotFoundException;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> listProducts();

    Double getPrice(Long productId) throws ProductNotFoundException;

    void changePrice(Long productId, double newprice) throws ProductNotFoundException;

    List<Review> getReviews(Long productId) throws ProductNotFoundException;

    List<Purchase> purchaseHisory(Long productId) throws ProductNotFoundException;

    List<Category> listCategories(Long productId) throws ProductNotFoundException;

    Product_Category addCategory(Long productId, String categoryName) throws ProductNotFoundException, CategoryNotFoundException;

    Review addReview(Long productId, Long clientId, Short review) throws ProductNotFoundException, ClientNotFoundException;
}
