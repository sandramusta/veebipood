package ee.sandra.veebipood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier3Product {
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    private double discountPercentage;
    private double rating;
    private int stock;
    private ArrayList<String> tags;
    private String brand;
    private String sku;
    private int weight;
    //private Dimensions dimensions;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    //private ArrayList<Review> reviews;
    private String returnPolicy;
    private int minimumOrderQuantity;
    //private Meta meta;
    private ArrayList<String> images;
    private String thumbnail;
}
