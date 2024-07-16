package we.ie.E_Commerce_Sales.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import we.ie.E_Commerce_Sales.dtos.ProductDTO;
import we.ie.E_Commerce_Sales.entities.Category;
import we.ie.E_Commerce_Sales.entities.Client;
import we.ie.E_Commerce_Sales.entities.Product;
import we.ie.E_Commerce_Sales.entities.Product_Category;
import we.ie.E_Commerce_Sales.entities.Purchase;
import we.ie.E_Commerce_Sales.entities.Review;
import we.ie.E_Commerce_Sales.exceptions.CategoryNotFoundException;
import we.ie.E_Commerce_Sales.exceptions.ClientNotFoundException;
import we.ie.E_Commerce_Sales.exceptions.ProductNotFoundException;
import we.ie.E_Commerce_Sales.mappers.ProductMappingImp;
import we.ie.E_Commerce_Sales.repositories.CategoryRepository;
import we.ie.E_Commerce_Sales.repositories.ClientRepository;
import we.ie.E_Commerce_Sales.repositories.ProductRepository;
import we.ie.E_Commerce_Sales.repositories.Product_CategoryRepository;
import we.ie.E_Commerce_Sales.repositories.ReviewRepository;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ClientRepository clientRepository;
    private ReviewRepository reviewRepository;
    private Product_CategoryRepository product_CategoryRepository;
    private ProductMappingImp dtoMapper;


    // Logger log = (Logger) LoggerFactory.getLogger(this.getClass().getName());  === @slf4j

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        log.info("New Product");

        Product product = dtoMapper.fromProductDTO(productDTO);

        Product savedProduct = productRepository.save(product);
        return dtoMapper.fromProduct(savedProduct);
    }

    @Override
    public void changePrice(Long productId, double newprice) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.setPrice(newprice);
    }

    @Override
    public List<Review> getReviews(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        
        return product.getReviews();
    }

    @Override
    public List<Purchase> purchaseHisory(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        
        return product.getPurchases();
    }

    @Override
    public List<Category> listCategories(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        
        List<Category> categories = new ArrayList<>();
        List<Product_Category> product_Categories = product.getCategories();
        for (Product_Category product_Category : product_Categories) {
            categories.add(product_Category.getCategory());
        }

        return categories;
    }

    @Override
    public Product_Category addCategory(Long productId, String categoryName) throws ProductNotFoundException, CategoryNotFoundException {
        Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        Category category = categoryRepository.findById(categoryName)
                                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
            
        Product_Category pc = new Product_Category();

        pc.setCategory(category);
        pc.setProduct(product);
    
        Product_Category __pc__ = product_CategoryRepository.save(pc);
        return __pc__;
    }

    @Override
    public Review addReview(Long productId, Long clientId, Short rate) throws ProductNotFoundException, ClientNotFoundException {
        Product product = productRepository.findById(productId)
                                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        Client client = clientRepository.findById(clientId)
                                    .orElseThrow(() -> new ClientNotFoundException("Client not found"));
            
        Review review = new Review();

        review.setRate(rate);
        review.set_date(new StdDateFormat().format(new Date()));
        review.setProduct(product);
        review.setClient(client);

        Review review2 = reviewRepository.save(review);
        return review2;
    }

    @Override
    public Double getPrice(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return product.getPrice();
    }

    @Override
    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> ProductDTOS = products.stream()
                .map(product -> dtoMapper.fromProduct(product))
                .collect(Collectors.toList());

        return ProductDTOS;
    }

}
