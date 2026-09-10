package ee.sandra.veebipood.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price; //komakohaga number
    private String image;
    private Integer stock; //täisarvuline number
    private Boolean active;

    @ManyToOne
    private Category category;
}
