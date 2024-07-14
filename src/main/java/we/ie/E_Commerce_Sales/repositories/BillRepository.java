package we.ie.E_Commerce_Sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import we.ie.E_Commerce_Sales.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
