package we.ie.E_Commerce_Sales.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import we.ie.E_Commerce_Sales.dtos.ProductDTO;
import we.ie.E_Commerce_Sales.entities.Product;

@Service
public class ProductMappingImp {
    public ProductDTO fromProduct(Product product){
        ProductDTO ProductDTO=new ProductDTO();
        BeanUtils.copyProperties(product,ProductDTO);
        return  ProductDTO;
    }
    
    public Product fromProductDTO(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        return  product;
    }
}
