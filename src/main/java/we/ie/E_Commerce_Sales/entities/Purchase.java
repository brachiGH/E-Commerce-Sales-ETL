package we.ie.E_Commerce_Sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Purchase {
    @ManyToOne
    private Product product;

    @ManyToOne
    private Bill Bill;

}

