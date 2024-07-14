package we.ie.E_Commerce_Sales.repositories;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import we.ie.E_Commerce_Sales.entities.Category;

public interface CatergoryRepository extends JpaRepository<Category, Long> {
}
