package we.ie.E_Commerce_Sales.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;


    @OneToMany(mappedBy = "client")
    private List<Review> reviews;


    @OneToMany(mappedBy = "client")
    private List<Bill> Bills;
}
