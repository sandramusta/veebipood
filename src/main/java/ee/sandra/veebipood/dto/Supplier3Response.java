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
public class Supplier3Response {
    public ArrayList<Supplier3Product> products;
    public int total;
    public int skip;
    public int limit;
}
