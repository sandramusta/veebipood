package ee.sandra.veebipood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier2Category {
    private int id;
    private String name;
    private String slug;
    private String image;
    private Date creationAt;
    private Date updatedAt;
}
