package ee.sandra.veebipood.dto;


import ee.sandra.veebipood.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier2Product {
    private int id;
    private String title;
    private String slug;
    private double price;
    private Double retailPrice;
    private String description;
    private Supplier2Category category;
    private ArrayList<String> images;
    private Date creationAt;
    private Date updatedAt;
}
