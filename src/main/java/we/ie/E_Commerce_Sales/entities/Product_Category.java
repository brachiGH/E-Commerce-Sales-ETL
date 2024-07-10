package we.ie.E_Commerce_Sales.entities;

import jakarta.persistence.ManyToOne;

public class Product_Category {
    @ManyToOne
    private Product product;

    @ManyToOne
    private Category category;
}
