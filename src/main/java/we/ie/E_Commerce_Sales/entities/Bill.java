package we.ie.E_Commerce_Sales.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Float total;

    @NonNull
    private String _date;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "product")
    private List<Purchase> products;

}
