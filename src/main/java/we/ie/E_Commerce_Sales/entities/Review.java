package we.ie.E_Commerce_Sales.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Short rate;

    @NonNull
    private String _date;

    @ManyToOne
    @NonNull
    private Product product;

    @ManyToOne
    @NonNull
    private Client client;
}