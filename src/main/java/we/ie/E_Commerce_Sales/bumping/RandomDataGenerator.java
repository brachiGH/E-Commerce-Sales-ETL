package we.ie.E_Commerce_Sales.bumping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import we.ie.E_Commerce_Sales.entities.Product;
import we.ie.E_Commerce_Sales.entities.Review;
import we.ie.E_Commerce_Sales.entities.Client;

@Component
public class RandomDataGenerator {

    private static final Random RANDOM = new Random();

    public Product createRandomProduct() {
        Product product = new Product();
        product.setName(RandomStringUtils.randomAlphabetic(10)); // Random 10-character name
        product.setPrice(RANDOM.nextDouble() * 100); // Random price between 0 and 100

        return product;
    }

    public Review createRandomReview(Product product, Client client) {
        Review review = new Review();
        review.setRate((short) (RANDOM.nextInt(6))); // Random rate between 0 and 5
        review.set_date("2024-07-" + (RANDOM.nextInt(31) + 1)); // Random date in July 2024
        review.setProduct(product);
        review.setClient(client);

        return review;
    }

    public List<Product> createRandomProducts(int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            products.add(createRandomProduct());
        }
        return products;
    }

    public List<Review> createRandomReviews(int count, Product product, Client client) {
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            reviews.add(createRandomReview(product, client));
        }
        return reviews;
    }
}
