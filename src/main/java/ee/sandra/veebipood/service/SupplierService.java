package ee.sandra.veebipood.service;

import ee.sandra.veebipood.dto.Supplier1Product;
import ee.sandra.veebipood.dto.Supplier2Product;
import ee.sandra.veebipood.dto.Supplier3Product;
import ee.sandra.veebipood.dto.Supplier3Response;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
public class SupplierService {

    public List<Supplier1Product> getSupplier1Products() {
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        Supplier1Product[] products = restTemplate.exchange(url, HttpMethod.GET, null, Supplier1Product[].class).getBody();
        return Arrays.stream(products)
                .filter(e -> e.getRating().getRate() > 3)
                .peek(e -> e.setPrice(e.getPrice()*1.24))
                .toList();
    }

    public List<Supplier2Product> getSupplier2Products() {
        String url = "https://api.escuelajs.co/api/v1/products";
        RestTemplate restTemplate = new RestTemplate();
        Supplier2Product[] products = restTemplate.exchange(url, HttpMethod.GET, null, Supplier2Product[].class).getBody();
        return Arrays.stream(products)
                .peek(e -> e.setRetailPrice(e.getPrice()*1.4))
                .toList();
    }

    public List<Supplier3Product> getSupplier3Products() {
        String url = "https://dummyjson.com/products";
        RestTemplate restTemplate = new RestTemplate();
        Supplier3Response response = restTemplate.exchange(url, HttpMethod.GET, null, Supplier3Response.class).getBody();
        return response.getProducts();
    }
}

