package we.ie.E_Commerce_Sales.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import we.ie.E_Commerce_Sales.dtos.ProductDTO;
import we.ie.E_Commerce_Sales.dtos.ReviewDTO;
import we.ie.E_Commerce_Sales.exceptions.ClientNotFoundException;
import we.ie.E_Commerce_Sales.exceptions.ProductNotFoundException;
import we.ie.E_Commerce_Sales.services.ProductService;




@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ProductRestController {
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> products() {
        return productService.listProducts();
    }
    
    @GetMapping("/productprice/{id}")
    public Double getPrice(@PathVariable(name = "id") Long productId) throws ProductNotFoundException {
        return productService.getPrice(productId);
    }

    @PostMapping("/product")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO);
        return productService.addProduct(productDTO);
    }


    @GetMapping("/reviews/{id}")
    public List<ReviewDTO> getReviews(@PathVariable(name = "id") Long productId) throws ProductNotFoundException {
        return  productService.getReviews(productId);
    }
    
    // @GetMapping("/addreview")
    @PostMapping("/review/{productId}/{clientId}")
    public void addReview(
            @PathVariable Long productId,
            @PathVariable Long clientId,
            @RequestParam(name="rate", defaultValue = "0") short rate) 
            throws ProductNotFoundException, ClientNotFoundException {

            productService.addReview(productId, clientId, rate);
        return ;
    }


    
    
}
