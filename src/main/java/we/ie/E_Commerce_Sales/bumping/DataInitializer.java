package we.ie.E_Commerce_Sales.bumping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import we.ie.E_Commerce_Sales.entities.Product;
import we.ie.E_Commerce_Sales.entities.Review;
import we.ie.E_Commerce_Sales.entities.Client;
import we.ie.E_Commerce_Sales.repositories.ClientRepository;
import we.ie.E_Commerce_Sales.repositories.ProductRepository;
import we.ie.E_Commerce_Sales.repositories.ReviewRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RandomDataGenerator dataGenerator;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        // Generate and save random products
        List<Product> products = dataGenerator.createRandomProducts(10000000);
        productRepository.saveAll(products);

        // Assuming you have a Client entity and repository
        Client sampleClient = clientRepository.findById(1L).orElseThrow(() -> new RuntimeException("Client not found"));

        // Generate and save random reviews for the products
        for (Product product : products) {
            List<Review> reviews = dataGenerator.createRandomReviews(5, product, sampleClient);
            reviewRepository.saveAll(reviews);
        }
    }
}
