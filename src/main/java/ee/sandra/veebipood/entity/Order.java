package ee.sandra.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private PaymentState paymentState;

    //Kui paremal pool on One
    //private muutuja ainsuses
    @ManyToOne
    private Person person;

    @ManyToMany
    private List<Product> products;
}
