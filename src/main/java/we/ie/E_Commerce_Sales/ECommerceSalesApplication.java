package we.ie.E_Commerce_Sales;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import we.ie.E_Commerce_Sales.entities.Bill;
import we.ie.E_Commerce_Sales.entities.Category;
import we.ie.E_Commerce_Sales.entities.Client;
import we.ie.E_Commerce_Sales.entities.Product;
import we.ie.E_Commerce_Sales.entities.Review;
import we.ie.E_Commerce_Sales.repositories.BillRepository;
import we.ie.E_Commerce_Sales.repositories.CategoryRepository;
import we.ie.E_Commerce_Sales.repositories.ClientRepository;
import we.ie.E_Commerce_Sales.repositories.ProductRepository;
import we.ie.E_Commerce_Sales.repositories.ReviewRepository;

@SpringBootApplication
public class ECommerceSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceSalesApplication.class, args);
		// System.out.println("d");
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository,
							CategoryRepository categoryRepository,
							ClientRepository clientRepository,
							ReviewRepository reviewRepository,
							ProductRepository productRepository) {

		return args -> {
			Stream.of("Morad", "Slim", "Aya").forEach(name -> {
				Client client = new Client();
				client.setName(name);
				client.setEmail(name+"@gmail.Com");
				clientRepository.save(client);
			});

			Stream.of("p1", "P2", "P3").forEach(name -> {
				Product product = new Product();
				product.setName(name);
				product.setPrice(Math.random()*29.99);
				productRepository.save(product);
			});

			Stream.of("C1", "C2", "C3").forEach(name -> {
				Category category = new Category();
				category.setName(name);
				categoryRepository.save(category);
			});

			Stream.of(15.9, 38.3, 4.76).forEach(total -> {
				Bill bill = new Bill();
				bill.setTotal(total);
				bill.set_date("01/01/2000");
				billRepository.save(bill);
			});

			Stream.of("12/07/2024", "29/11/2022", "02/02/2020").forEach(date -> {
				Review review = new Review();
				review.setRate((short)(Math.random()*5));
				review.set_date(date);
				reviewRepository.save(review);
			});

			clientRepository.findAll().forEach( clt -> {
				System.out.println(clt.getName());
			});
		};
	};
}