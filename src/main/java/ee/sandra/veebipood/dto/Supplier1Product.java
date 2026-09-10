package ee.sandra.veebipood.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier1Product {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Supplier1Rating rating;
}
