package we.ie.E_Commerce_Sales.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import we.ie.E_Commerce_Sales.dtos.ProductDTO;
import we.ie.E_Commerce_Sales.exceptions.ProductNotFoundException;
import we.ie.E_Commerce_Sales.services.ProductService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
    
    
}
